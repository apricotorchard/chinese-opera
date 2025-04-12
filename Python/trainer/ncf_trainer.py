import os

import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, Dataset
from sklearn.metrics import roc_auc_score
from tqdm import tqdm

from dataset.ncf_dataset import NCFDataset
from utils.evaluator import Evaluator


class NCFTrainer:
    def __init__(self, model, train_data, test_data, device="cpu", lr=0.001, batch_size=256, epochs=1,
                 save_dir="checkpoints"):
        self.model = model.to(device)
        self.train_data = train_data
        self.test_data = test_data
        self.device = device
        self.epochs = epochs
        self.batch_size = batch_size
        self.criterion = nn.BCELoss()
        self.optimizer = optim.Adam(model.parameters(), lr=lr)
        self.save_dir = save_dir
        os.makedirs(self.save_dir, exist_ok=True)
        self.train_loader = DataLoader(NCFDataset(train_data), batch_size=batch_size, shuffle=True)
        self.test_loader = DataLoader(NCFDataset(test_data), batch_size=batch_size)
        # self.train_loader = DataLoader(train_data, batch_size=batch_size, shuffle=True)
        # self.test_loader = DataLoader(test_data, batch_size=batch_size)
        self.evaluator = Evaluator(model,self.test_loader)
        self.best_auc = 0

    def train(self):
        for epoch in range(self.epochs):
            self.model.train()
            total_loss = 0
            for users, items, labels in tqdm(self.train_loader, desc=f"Epoch {epoch + 1}/{self.epochs}"):
                users, items, labels = users.to(self.device), items.to(self.device), labels.float().to(self.device)

                self.optimizer.zero_grad()
                outputs = self.model(users, items).squeeze()
                loss = self.criterion(outputs, labels)
                loss.backward()
                self.optimizer.step()
                total_loss += loss.item()

            print(f"Epoch {epoch + 1}, Loss: {total_loss:.4f}")

            # Evaluation + checkpoint
            auc = self.evaluator.evaluate_binary_classification()
            self.best_model_path = os.path.join(self.save_dir, "best_model.pt")

            if auc > self.best_auc:
                self.best_auc = auc
                torch.save(self.model.state_dict(), self.best_model_path)
                print(f"Saved best model to {self.best_model_path} (AUC: {auc:.4f})")

    # def evaluate(self):
    #     self.model.eval()
    #     all_labels = []
    #     all_preds = []
    #     with torch.no_grad():
    #         for users, items, labels in self.test_loader:
    #             users, items = users.to(self.device), items.to(self.device)
    #             outputs = self.model(users, items).squeeze().cpu().numpy()
    #             all_preds.extend(outputs)
    #             all_labels.extend(labels.numpy())
    #     auc = roc_auc_score(all_labels, all_preds)
    #     print(f"Validation AUC: {auc:.4f}")
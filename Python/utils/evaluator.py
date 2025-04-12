import torch
from sklearn.metrics import roc_auc_score, accuracy_score
import numpy as np

class Evaluator:
    def __init__(self, model, test_loader, device="cpu"):
        self.model = model.to(device)
        self.test_loader = test_loader
        self.device = device

    def evaluate_binary_classification(self):
        self.model.eval()
        all_labels, all_preds = [], []

        with torch.no_grad():
            for users, items, labels in self.test_loader:
                users, items = users.to(self.device), items.to(self.device)
                outputs = self.model(users, items).squeeze().cpu().numpy()
                all_preds.extend(outputs)
                all_labels.extend(labels.numpy())

        auc = roc_auc_score(all_labels, all_preds)
        # acc = accuracy_score(all_labels, (np.array(all_preds) >= 0.5).astype(int))
        return auc

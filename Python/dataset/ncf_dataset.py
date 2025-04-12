import pandas as pd
import numpy as np
import torch
from sklearn.model_selection import train_test_split
from torch.utils.data import Dataset
class NCFDataPreprocessor:
    def __init__(self, path, sep='::', min_rating=4, num_neg=9,
                 test_size=0.2, random_state=42):
        self.path = path
        self.sep = sep
        self.min_rating = min_rating
        self.num_neg = num_neg
        self.test_size = test_size
        self.random_state = random_state
        self.user2id = {}
        self.item2id = {}
        self.num_users = 0
        self.num_items = 0
    def load_and_process(self):
        df = pd.read_csv(self.path, sep=self.sep, engine='python',
                         names=['user', 'item', 'rating', 'timestamp'])

        df = df[df['rating'] >= self.min_rating]
        self.user2id = {uid: idx for idx, uid in enumerate(df['user'].unique())}
        self.item2id = {iid: idx for idx, iid in enumerate(df['item'].unique())}
        df['user'] = df['user'].map(self.user2id)
        df['item'] = df['item'].map(self.item2id)
        df['label'] = 1
        user_item_dict = df.groupby('user')['item'].apply(set).to_dict()
        self.num_users = len(self.user2id)
        self.num_items = len(self.item2id)
        neg_df = self._negative_sampling(df)
        full_df = pd.concat([df[['user', 'item', 'label']], neg_df], ignore_index=True)
        full_df = full_df.sample(frac=1).reset_index(drop=True)
        train_data,test_data = train_test_split(full_df, test_size=self.test_size, random_state=self.random_state)
        return train_data,test_data,self.num_users,self.num_items,user_item_dict,self.user2id,self.item2id

    def _negative_sampling(self, df):
        neg_samples = []
        for user in df['user'].unique():
            watched = set(df[df['user'] == user]['item'])
            for _ in range(self.num_neg):
                item = np.random.randint(0, self.num_items)
                while item in watched:
                    item = np.random.randint(0, self.num_items)
                neg_samples.append([user, item, 0])
        return pd.DataFrame(neg_samples, columns=['user', 'item', 'label'])

class NCFDataset(Dataset):
    def __init__(self, dataframe):
        self.users = torch.tensor(dataframe['user'].values, dtype=torch.long)
        self.items = torch.tensor(dataframe['item'].values, dtype=torch.long)
        self.labels = torch.tensor(dataframe['label'].values, dtype=torch.float32)

    def __len__(self):
        return len(self.users)

    def __getitem__(self, idx):
        return self.users[idx], self.items[idx], self.labels[idx]
if __name__ == '__main__':
    processor = NCFDataPreprocessor(path='../data/ratings.dat', test_size=0.25, num_neg=99)
    train_data, test_data = processor.load_and_process()

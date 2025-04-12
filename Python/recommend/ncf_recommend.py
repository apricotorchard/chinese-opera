#基于交互的推荐（离线推荐）
import json
import os

import numpy as np
import torch

from model.ncf_arch import NCF


def load_model(model_path,num_users,num_items,device):
    model = NCF(num_users=num_users, num_items=num_items).to(device)
    model.load_state_dict(torch.load(model_path, map_location=device))
    model.eval()
    return model
def get_user_item_dict(train_df):
    """
    构建用户历史交互字典，用于过滤训练中已出现过的物品
    """
    user_item_dict = train_df.groupby('user')['item'].apply(set).to_dict()
    return user_item_dict

def recommend_all_users(model, num_users, num_items, user_item_dict=None, top_k=10, device='cpu'):
    all_recommendations = {}

    for user_id in range(num_users):
        user_tensor = torch.tensor([user_id] * num_items, dtype=torch.long).to(device)
        item_tensor = torch.arange(num_items, dtype=torch.long).to(device)

        with torch.no_grad():
            scores = model(user_tensor, item_tensor).squeeze().cpu().numpy()

        # 可选：过滤掉训练集中用户已经点击过的 item
        if user_item_dict and user_id in user_item_dict:
            seen_items = user_item_dict[user_id]
            scores[list(seen_items)] = -np.inf

        # 获取 top-k
        top_items = np.argsort(-scores)[:top_k]
        all_recommendations[user_id] = top_items.tolist()
    return all_recommendations

def translate_recommendations(internal_recommendations, user2id, item2id):
    """
        将基于内部 ID 的推荐结果转换为原始 ID。

        参数：
        - internal_recommendations: dict[int, list[int]]，用户内部 ID -> 推荐的物品内部 ID 列表
        - user2id: dict[原始用户 ID -> 内部 ID]
        - item2id: dict[原始物品 ID -> 内部 ID]

        返回：
        - translated_recommendations: dict[原始用户 ID -> 推荐的原始物品 ID 列表]
    """
    id2user = {v: k for k, v in user2id.items()}
    id2item = {v: k for k, v in item2id.items()}

    translated_recommendations = {}

    for user_internal_id, item_internal_ids in internal_recommendations.items():
        user_original_id = id2user.get(user_internal_id)
        item_original_ids = [id2item.get(item_id) for item_id in item_internal_ids if item_id in id2item]
        translated_recommendations[user_original_id] = item_original_ids

    return translated_recommendations

def save_recommendation_results(model, user2id, item2id, recommendations, save_dir='./results', device="cpu"):
    """
    保存模型的推荐结果、用户向量、物品向量及映射字典到指定目录。

    :param model: 训练好的模型
    :param user2id: 用户原始ID -> 内部ID 映射
    :param item2id: 物品原始ID -> 内部ID 映射
    :param recommendations: 推荐列表 dict[用户原始ID -> [推荐物品原始ID列表]]
    :param save_dir: 保存目录
    :param device: 模型所使用的设备
    """
    os.makedirs(save_dir, exist_ok=True)

    # 1. 保存推荐结果（处理 numpy.int64 问题）
    rec_path = os.path.join(save_dir, 'recommendations.json')
    clean_recommendations = {
        str(k): [int(i) for i in v] for k, v in recommendations.items()
    }
    with open(rec_path, 'w', encoding='utf-8') as f:
        json.dump(clean_recommendations, f, indent=2)

    # 2. 保存用户向量和物品向量
    # user_embeddings = model.user_embedding.weight.data.cpu().numpy()
    # item_embeddings = model.item_embedding.weight.data.cpu().numpy()
    # torch.save(user_embeddings, os.path.join(save_dir, 'user_embeddings.pt'))
    # torch.save(item_embeddings, os.path.join(save_dir, 'item_embeddings.pt'))
    user_embeddings = torch.cat([
        model.embedding_user_GMF.weight.data,
        model.embedding_user_MLP.weight.data
    ], dim=1).cpu().numpy()

    item_embeddings = torch.cat([
        model.embedding_item_GMF.weight.data,
        model.embedding_item_MLP.weight.data
    ], dim=1).cpu().numpy()

    torch.save(user_embeddings, os.path.join(save_dir, 'user_embeddings.pt'))
    torch.save(item_embeddings, os.path.join(save_dir, 'item_embeddings.pt'))
    # 3. 保存映射字典（也要转成 str-key）
    user2id_clean = {str(k): int(v) for k, v in user2id.items()}
    item2id_clean = {str(k): int(v) for k, v in item2id.items()}

    with open(os.path.join(save_dir, 'user2id.json'), 'w', encoding='utf-8') as f:
        json.dump(user2id_clean, f, indent=2)

    with open(os.path.join(save_dir, 'item2id.json'), 'w', encoding='utf-8') as f:
        json.dump(item2id_clean, f, indent=2)
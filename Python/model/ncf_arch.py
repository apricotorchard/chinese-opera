import torch
import torch.nn as nn

class NCF(nn.Module):
    def __init__(self,num_users,num_items,embedding_dim=32,hidden_dims=[64, 32, 16, 8], dropout=0.2):
        super(NCF, self).__init__()
        self.embedding_user_GMF = nn.Embedding(num_users, embedding_dim)
        self.embedding_item_GMF = nn.Embedding(num_items, embedding_dim)

        self.embedding_user_MLP = nn.Embedding(num_users, embedding_dim)
        self.embedding_item_MLP = nn.Embedding(num_items, embedding_dim)

        # MLP layers
        mlp_modules = []
        input_dim = embedding_dim * 2
        for dim in hidden_dims:
            mlp_modules.append(nn.Linear(input_dim, dim))
            mlp_modules.append(nn.ReLU())
            mlp_modules.append(nn.Dropout(p=dropout))
            input_dim = dim
        self.mlp = nn.Sequential(*mlp_modules)
        # Final prediction layer
        predict_size = embedding_dim + hidden_dims[-1]  # GMF + MLP concat
        self.predict_layer = nn.Linear(predict_size, 1)

        self._init_weights()

    def _init_weights(self):
        nn.init.normal_(self.embedding_user_GMF.weight, std=0.01)
        nn.init.normal_(self.embedding_item_GMF.weight, std=0.01)
        nn.init.normal_(self.embedding_user_MLP.weight, std=0.01)
        nn.init.normal_(self.embedding_item_MLP.weight, std=0.01)
        for m in self.mlp:
            if isinstance(m, nn.Linear):
                nn.init.xavier_uniform_(m.weight)
        nn.init.kaiming_uniform_(self.predict_layer.weight, a=1, nonlinearity='sigmoid')

    def forward(self, user_indices, item_indices):
        # GMF part
        gmf_user = self.embedding_user_GMF(user_indices)
        gmf_item = self.embedding_item_GMF(item_indices)
        gmf_output = gmf_user * gmf_item

        # MLP part
        mlp_user = self.embedding_user_MLP(user_indices)
        mlp_item = self.embedding_item_MLP(item_indices)
        mlp_input = torch.cat((mlp_user, mlp_item), dim=-1)
        mlp_output = self.mlp(mlp_input)

        # Concatenate GMF and MLP parts
        final_input = torch.cat((gmf_output, mlp_output), dim=-1)
        # prediction = self.predict_layer(final_input)
        prediction = self.predict_layer(final_input).squeeze(-1)
        return torch.sigmoid(prediction)
# if __name__ == '__main__':
#     model = NCF(num_users=6040, num_items=3706, embedding_dim=32)
#     user = torch.tensor([0, 1, 2])
#     item = torch.tensor([1, 2, 3])
#     output = model(user, item)
#     print(output)  # 输出预测分值

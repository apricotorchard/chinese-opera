# 使用 Node.js 20 作为基础镜像来构建应用  
FROM node:20 AS build-stage

# 设置工作目录
WORKDIR /app

# 将 package.json 和 package-lock.json 复制到容器中
COPY package*.json ./

# 安装依赖
RUN npm install

# 将 Vue 应用的源代码复制到工作目录
COPY . .

# 构建应用
RUN npm run build

# 使用 Nginx 作为基础镜像，用于运行静态文件
FROM nginx:alpine AS production-stage

# 将构建的文件复制到 Nginx 容器中
COPY --from=build-stage /app/dist /usr/share/nginx/opera

# 暴露端口 80
EXPOSE 80

# 启动 Nginx
CMD ["nginx", "-g", "daemon off;"]

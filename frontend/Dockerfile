# frontend/Dockerfile

# ベースイメージ
FROM node:18-alpine

# 作業ディレクトリの設定
WORKDIR /app

# package.jsonとpackage-lock.jsonをコピー
COPY package*.json ./

# 依存関係のインストール
RUN npm install

# Viteを明示的にグローバルインストール
RUN npm install -g vite

# ソースコードをコピー
COPY . .

# ポート5173を公開
EXPOSE 5173

# 開発サーバー起動
# --hostフラグでコンテナ外からのアクセスを許可
CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]
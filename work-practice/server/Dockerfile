FROM node:14 as server

WORKDIR /app

COPY package.json .

RUN npm install

COPY src /app

CMD ["node", "server.js"]

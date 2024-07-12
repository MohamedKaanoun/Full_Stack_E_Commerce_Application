FROM node:22-alpine as build-stage

WORKDIR /app

COPY package*.json .

RUN npm install

RUN npm install @mui/material @emotion/react @emotion/styled @mui/icons-material redux react-redux axios tailwindcss

RUN npx tailwindcss init

COPY . .

EXPOSE 3000

CMD ["npm", "start"]


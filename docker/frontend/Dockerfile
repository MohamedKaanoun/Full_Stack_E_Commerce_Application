FROM node:22-alpine as build-stage

WORKDIR /app

COPY front-end/package*.json ./

RUN npm install

COPY front-end ./

RUN npm run build

FROM nginx:alpine

# Copy the custom nginx configuration file
COPY front-end/nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build-stage /app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

[![ci](https://github.com/vartdalen/image-store-web/workflows/ci/badge.svg)](https://github.com/vartdalen/image-store-web/actions?workflow=ci)

# image-store-web
Java Spring Boot image store web server **application**.

Dockerized.

Exposes a RESTful API on the **device** that is running the **application**.

The purpose of the **application** is to offer a web interface for storing and accessing images on the **device**.

## Useful links

| Description | Link |
| ------:| -----------:|
| SQL database web application: | https://github.com/vartdalen/image-store-sql
| Dockerhub: | https://hub.docker.com/repository/docker/vartdalen/image-store-web

## Documentation

### Exposed paths

#### Data
```
"/actuator",                //  /health, /info
"/api",                     //  /images
"/v2",                      //  /api-docs
"/swagger-ui",              //  /index.html
```

#### Static
```
"/swagger-resources",
"/js",
"/css",
"/img",
"/favicon.ico"
```

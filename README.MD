# spring-react-template

## About this project

This is template for spring-react application with authorization by Json Web Token (JWT).
When I was learning authorization in SPA it was difficult to find any working example. So I created my own.
This is simple project with authorization, home page and logout.

## Contribute to this project

If you find something that can be improved, please, [open an issue](https://github.com/dmitryblackwell/spring-react-template/issues/new), so I can fix it.

Or you can fix it on your own and suggest a pull request to me.

Thank you, in advance.

## Getting started

For this project you need to install [npm](https://nodejs.org/en/) and [jdk](https://jdk.java.net/java-se-ri/11).
Dont forget to setup system variables.

```$xslt
git clone https://github.com/dmitryblackwell/spring-react-template.git
cd spring-react-template
mvnw spring-boot:run -P prod
```
`-P prod` means that app is going to run under prod maven profile with maven frontend.
This plugin automatically install node_modules and create bundle.js for website.

If you want to run it for development purposes than you need to import it your IDE and run

1. SpringReactTemplateApplication for spring web api.
1. Webpack dev server for automatic rendering your fronted part - `npm start`.


## Tech Stack

Backend:
1. Spring Boot
1. Spring Data
1. Spring Security
1. Hibernate ORM

Frontend:
1. React
1. React Hooks
1. Webpack
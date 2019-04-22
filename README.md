# lpweb-vendas-api
Backend com Spring Boot e Spring MVC de um Sistema de Vendas

A API RES do Sistema de Vendas terá as seguintes funcionalidades:  
* Projeto criado com Spring Boot e Java 8
* Banco de dados MySQL e H2 com JPA e Spring Data JPA
* Autenticação e autorização com Spring Security, OAuth2 e tokens JWT (JSON Web Token)
* Migração de banco de dados com Flyway
* Deploy na nuvem do Heroku
* Caching com EhCache
* Integração contínua com TravisCI
* Documentaçao da API com o Swagger

### Como executar a aplicação
Certifique-se de ter o Maven instalado e adicionado ao PATH de seu sistema operacional, assim como o Git.
```
git clone https://github.com/jcpinheiro/lpweb-vendas-api.git
cd lpweb-vendas-api
mvn spring-boot:run
Acesse os endpoints através da url http://localhost:8080
```
### Importando o projeto no Intellij IDE
No terminal, execute a seguinte operação:
```
mvn idea:idea
```
No Intellij, importe o projeto como projeto Maven ou
utilize o recurso da versão de 2019 abrindo a pasta do projeto como um Projeto do Intellij


```
### Importando o projeto no Eclipse ou STS
No terminal, execute a seguinte operação:
```
mvn eclipse:eclipse
```
No Eclipse/STS, importe o projeto como projeto Maven.

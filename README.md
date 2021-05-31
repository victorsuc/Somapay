# Somapay
Projeto de teste da somapay

Requisitos para funcionamento do projeto:
- Java 11
- Spring 5
- PostgreSQL 11

Para aplicação se conectar com o banco de dados, colocar variáveis de conexão com o banco no arquivo application.properties conforme as configurações do seu PostgreSQL.

Nesta aplicação foi utilizado Spring Security e JWT, portanto, é necessário criar um usuário e acessar o endpoint "/login" com as credenciais login e senha fornecidas no 
cadastro para geração de um token. O mesmo será válido por 1 hora.

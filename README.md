## Projeto Desenvolvedor Full Stack Java

### Este projeto fornece uma API para gerenciamento de profissionais cadastrados. 
### Tecnologias Utilizadas
- **Java**
- **Spring Boot**
- **JSF (JavaServer Faces) com PrimeFaces**
- **PostgreSQL**
- **JUnit**
- **Mockito**

### Configuração do Ambiente de Desenvolvimento e Instruções de como rodar o projeto localmente
Certifique-se de ter o JDK (Java Development Kit) Java e o Apache Maven instalados em sua máquina antes de prosseguir.  
Utilizei JUnit como framework de teste e o Mockito para criar mocks e simular o comportamento!
#### Clone o repositório para o seu ambiente local:
```bash
git clone https://github.com/s-v7/CadastroProfissionais
```
#### Navegue até o diretório do projeto::
```bash
cd CadastroProfissionais
```
#### Compile o projeto usando Maven::
```bash
mvn clean install
```
Execute o projeto usando o Maven. Na linha de comando, vá para o diretório do projeto e execute o seguinte comando
#### Execute o aplicativo Spring Boot:
```bash
mvn spring-boot:run
```
Acesse a aplicação em seu navegador usando o seguinte URL

- **O aplicativo será iniciado e estará acessível em http://localhost:8080.**
#### Scripts para inicialização do banco de dados

O projeto utiliza um banco de dados PostgreSQL. Antes de executar o aplicativo, você precisa criar um banco de dados e configurar as credenciais corretas no arquivo application.properties.

- **Aqui estão os scripts SQL para criar as tabelas necessárias:**
```bash
-- Script para criar DataBase cadastro_profissionais
CREATE DATABASE cadastro_profissionais
    WITH 
    OWNER = s-v7
    ENCODING = 'UTF8'
    LC_COLLATE = 'pt_BR.UTF-8'
    LC_CTYPE = 'pt_BR.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- Script para criar a tabela Professional
CREATE TABLE Professional (
    id SERIAL PRIMARY KEY,
    registration_type VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    phone_number VARCHAR(20)
);

-- Script para criar a tabel ProfessionalTitle
CREATE TABLE ProfessionalTitle (
    id SERIAL PRIMARY KEY,
    professional_id INTEGER REFERENCES Professional(id),
    title VARCHAR(100) NOT NULL
);

-- Script para criar a tabela de autenticação
CREATE TABLE autenticacao_usuario (
    id SERIAL PRIMARY KEY,
    nome_usuario VARCHAR(100),
    senha VARCHAR(255)
);
```
Autor[Silas Vasconcelos{s-v7}]

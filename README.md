# CRUD de Produtos com Spark, PostgreSQL e Java

Este projeto implementa um CRUD de produtos utilizando o framework **Spark**, banco de dados **PostgreSQL** e **Java** com Maven. O sistema permite cadastrar, listar, editar e excluir produtos via um formulário HTML.

## Tecnologias Utilizadas
- Java 11+
- Spark Framework
- PostgreSQL
- Maven
- HTML, CSS e JavaScript (Fetch API)
- Eclipse IDE

## Instalação e Configuração

### 1. Clonar o Repositório
```bash
git clone https://github.com/thuzada/Integracao-Spark-e-Eclipse.git
cd Integracao-Spark-e-Eclipse
```

### 2. Configurar o Banco de Dados PostgreSQL
Crie o banco de dados e a tabela:
```sql
CREATE DATABASE produtos_db;
\c produtos_db;

CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    descricao TEXT
);
```

### 3. Configurar o Projeto
No arquivo **`src/main/resources/application.properties`**, configure as credenciais do PostgreSQL:
```properties
db.url=jdbc:postgresql://localhost:5432/produtos_db
db.user=seu_usuario
db.password=sua_senha
```

### 4. Executar o Projeto
No terminal, execute:
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.seuprojeto.App"
```
O servidor iniciará em `http://localhost:4567`.

## Funcionalidades

### 1. Cadastro de Produtos
Preencha o formulário em `http://localhost:4567/form.html` e clique em "Salvar" para cadastrar um novo produto.

### 2. Listagem de Produtos
A lista de produtos é carregada automaticamente ao abrir a página.

### 3. Edição de Produtos
Clique em "Editar" ao lado de um produto para preencher o formulário com seus dados. Após a edição, clique em "Salvar" para atualizar.

### 4. Exclusão de Produtos
Adicione um botão "Excluir" ao lado dos produtos para permitir a remoção.

## Autor
[Arthur](https://github.com/thuzada)

## Licença
Este projeto está licenciado sob a [MIT License](LICENSE).


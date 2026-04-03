# BichoFull
Projeto da disciplina de Laboratório de Software.
Sistema full stack para simulação do Jogo do Bicho.

## Tecnologias
- Spring Boot
- MySQL
- React
- JWT


## Contrato inicial da API
Base da API:

http://localhost:8080/api

### Usuários

Criar usuários
POST /api/usuarios

Exemplo de JSON:

{
    "nome": "lica"
    "email": "lica@email.com"
    "senha": "123"
}

Listar usuários
GET /api/usuarios

Buscar usuário
GET /api/usuarios/{id}

Atualizar usuário
PUT /api/usuarios/{id}

Deletar usuário
DELETE /api/usuarios/{id}

### Animais
### Apostas
### Resultados


## PASSO A PASSO PARA EXECUTAR O PROJETO

## Pré-requisitos

Antes de começar, instale:

- Java JDK 17 ou superior
- Maven
- Git
- Mysql

### Verificar se está instalado

Abra o terminal e execute:

```bash
java -version
mvn -version
git --version

## Clonar o repositório

### Execute no terminal
git clone https://github.com/SEU-USUARIO/bichofull-backend.git

### Depois entre na pasta do projeto
cd bichofull-backend

### Banco de Dados
mysql -u root -p
senha: 123456
### para mais informações acessar application.porperties (backend/src/main/resources/application.properties)

## Compilar o projeto (Este comando compila o projeto ignorando os testes)
mvn clean install -DskipTests

## Executar o sistema (no backend)
mvn spring-boot:run
## Acessar aplicação
http://localhost:8080

## Executar no Frontend
npm run dev
### Acessar aplicação
http://localhost:5137/home

## Funcionalidades do sistema
- Cadastro do usuario
- Login de usuarios
- Criação de apostas
- Listagem de apostas
- Processamento de resultados (ganhou/perdeu)

## Estrutura do projeto
controller/     -> Endpoints da API
service/        -> Regras de négocio
repository/     -> Acesso ao banco de dados
entity/         -> Entidades do sistema
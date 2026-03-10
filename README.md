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
    "nome": "Liandra"
    "email": "liandra@email.com"
    "senha": "123456"
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

Listar animais

GET /api/animais

Buscar animal

GET /api/animais/{id}

### Apostas

Criar aposta

POST /api/apostas

Exemplo JSON:

{
  "usuarioId": 1,
  "animalId": 5,
  "valor": 10
}

Listar apostas

GET /api/apostas

Histórico de apostas de um usuário

GET /api/usuarios/{id}/apostas

### Resultados

Resultado do dia

GET /api/resultados

Criar resultado

POST /api/resultados
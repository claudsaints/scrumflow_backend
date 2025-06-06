# ScrumFlow

ScrumFlow é uma aplicação web de gerenciamento de projetos baseada no Scrum, inspirada no Trello.  
Desenvolvido com **Spring Boot**, a aplicação permite que usuários criem e organizem projetos, listas e tarefas, com autenticação baseada em **JWT** e uma estrutura limpa utilizando DTOs, serviços e boas práticas RESTful.

---

## 🔧 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- Hibernate
- PostgreSQL (ou H2 para testes)
- Maven
- Lombok


---

## 🧩 Funcionalidades

- Autenticação e autorização com JWT
- Cadastro e login de usuários
- Criação de projetos com listas e tarefas
- Gerenciamento de membros por projeto
- Separação clara entre DTOs de entrada e saída
- Acesso seguro aos dados baseados no usuário autenticado
- CRUD completo para:
    - Projetos
    - Listas de projeto (como colunas do Trello)
    - Tarefas
    - Membros do projeto

---

## 🔐 Autenticação com JWT

- O usuário faz login e recebe um token JWT.
- Todas as requisições autenticadas devem incluir o token no header:  
  `Authorization: Bearer <token>`
- O token contém o **email do usuário** e é utilizado para obter os dados via `getSubject()`.

---

## 📦 Exemplos de DTOs

- `ProjectCreateDTO`: usado no `POST /projects`, contém apenas `name` e `description`.
- `ProjectDataDTO`: usado no `GET /projects/{id}`, retorna também `id`, `createdAt`, `ownerEmail`.
- `UserCreateDTO`: usado para registro.
- `UserDataDTO`: usado para exibir dados do usuário.

---

## 🚀 Como rodar localmente

1. Clone o projeto:
   ```bash
   git clone https://github.com/seuusuario/scrumflow.git
   cd scrumflow

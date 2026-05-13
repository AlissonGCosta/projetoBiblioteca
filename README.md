# API de Biblioteca - Spring Web + JPA + Security (JWT)

## Descrição da atividade
Implementar uma API REST de biblioteca com autenticação JWT e autorização por perfil, utilizando Spring Boot.

## Objetivo
Construir uma API segura com operações de livros e autores, com controle de acesso por papéis (`ADMIN` e `USER`), aplicando boas práticas de arquitetura, validação, tratamento de erro e testes.

---

## Requisitos obrigatórios

### 1) Entidades JPA
- `User`
  - `id`
  - `username`
  - `password`
  - `role`
- `Author`
  - `id`
  - `name`
  - `nationality` (opcional)
- `Book`
  - `id`
  - `title`
  - `available`
  - `author` (`ManyToOne` com `Author`)

### 2) Segurança
- Login com `username/password`.
- Geração de token JWT.
- Perfis:
  - `ADMIN`: cadastra, atualiza e remove livros e autores.
  - `USER`: apenas lista e busca livros e autores.

### 3) Endpoints REST
- `POST /auth/login` (público): retorna token JWT.

#### Livros
- `GET /books` (`USER` ou `ADMIN`)
- `GET /books/{id}` (`USER` ou `ADMIN`)
- `POST /books` (`ADMIN`)
- `PUT /books/{id}` (`ADMIN`)
- `DELETE /books/{id}` (`ADMIN`)

#### Autores
- `GET /authors` (`USER` ou `ADMIN`)
- `GET /authors/{id}` (`USER` ou `ADMIN`)
- `POST /authors` (`ADMIN`)
- `PUT /authors/{id}` (`ADMIN`)
- `DELETE /authors/{id}` (`ADMIN`)

### 4) Regras de negócio
- O campo `title` é obrigatório no cadastro e na atualização de livros.
- Não é permitido cadastrar ou atualizar livro com `title` vazio, em branco ou nulo.
- O campo `name` é obrigatório no cadastro e na atualização de autores.
- Não é permitido cadastrar ou atualizar autor com `name` vazio, em branco ou nulo.
- O campo `available` representa disponibilidade para empréstimo:
  - `true`: livro disponível.
  - `false`: livro indisponível (emprestado ou reservado).
- Todo `Book` deve estar vinculado a um `Author` existente.
- Não permitir cadastrar/atualizar livro com `authorId` inexistente (`404 Not Found`).
- Operações por ID de livros e autores (`GET`, `PUT`, `DELETE`) devem retornar `404 Not Found` quando o recurso não existir.
- Ações de escrita (`POST`, `PUT`, `DELETE`) em `/books` e `/authors` só podem ser executadas por `ADMIN`.
- Usuário `USER` tentando ação de `ADMIN` deve receber `403 Forbidden`.
- Requisição sem token JWT em endpoint protegido deve receber `401 Unauthorized`.
- (Regra recomendada) Não permitir excluir `Author` que possua livros vinculados; retornar `409 Conflict`.

### 5) Persistência
- Usar Spring Data JPA com H2 **ou** PostgreSQL.
- Criar dados iniciais com:
  - 1 usuário `ADMIN`
  - 1 usuário `USER`
  - ao menos 1 `Author`
  - ao menos 1 `Book` vinculado a um `Author`

### 6) Qualidade
- Criar DTOs de request/response (não expor entidades diretamente).
- Tratar exceções com `@ControllerAdvice`.
- Criar ao menos:
  - 1 teste de repositório (JPA) para `Book` ou `Author`
  - 1 teste de controller com segurança (`MockMvc`) para `/books` ou `/authors`

---

## Complementos recomendados (para melhorar a entrega)

### Stack sugerida
- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (`jjwt` ou equivalente)
- Validation (`spring-boot-starter-validation`)
- H2 (desenvolvimento) ou PostgreSQL
- Testes: JUnit 5 + Spring Boot Test + MockMvc

### Estrutura de pacotes sugerida
```text
com.seuprojeto.biblioteca
├── config
├── security
├── auth
├── user
├── author
├── book
├── dto
├── exception
└── repository
```

### Critérios de validação esperados
- `POST /books` com `title` vazio -> `400 Bad Request`.
- `POST /authors` com `name` vazio -> `400 Bad Request`.
- `POST /books` com `authorId` inexistente -> `404 Not Found`.
- `GET /books/{id}` inexistente -> `404 Not Found`.
- `GET /authors/{id}` inexistente -> `404 Not Found`.
- `USER` tentando `POST/PUT/DELETE` em `/books` ou `/authors` -> `403 Forbidden`.
- Login válido retorna JWT.
- Requisição sem token em endpoint protegido -> `401 Unauthorized`.

---

## Novas tasks adicionadas (Author)
1. Criar entidade `Author` e `AuthorRepository`.
2. Ajustar entidade `Book` para relacionamento `ManyToOne` com `Author`.
3. Criar DTOs de `Author` (`create`, `update`, `response`).
4. Atualizar DTOs de `Book` para receber/retornar referência de autor (`authorId` e/ou `authorName`).
5. Implementar `AuthorService` com validações de negócio.
6. Criar `AuthorController` com endpoints protegidos por perfil.
7. Atualizar tratamento de exceções para cenários de autor não encontrado e conflito de exclusão.
8. Adicionar dados iniciais de autores e livros vinculados.
9. Adicionar testes de repositório e controller cobrindo `Author`.

---

## Sugestão de roteiro de implementação
1. Criar entidades `User`, `Author` e `Book` + repositórios.
2. Configurar relacionamento entre `Book` e `Author`.
3. Implementar DTOs e validações.
4. Implementar serviço de autenticação e geração de JWT.
5. Configurar Spring Security (filtro JWT + autorização por perfil).
6. Criar controllers de `Auth`, `Book` e `Author`.
7. Implementar `@ControllerAdvice` para erros padronizados.
8. Popular dados iniciais (`data.sql` ou `CommandLineRunner`).
9. Criar testes mínimos exigidos (JPA + MockMvc com segurança).

---

## Entregáveis
- Código-fonte funcional.
- `README.md` com instruções para rodar.
- Coleção de requisições (opcional: Postman/Insomnia).
- Testes passando (`mvn test`).

---

## Comandos úteis
```bash
# executar aplicação
./mvnw spring-boot:run

# rodar testes
./mvnw test
```

No Windows PowerShell:
```powershell
.\mvnw.cmd spring-boot:run
.\mvnw.cmd test
```

---

## Exemplo de fluxo de uso da API
1. Fazer login em `POST /auth/login` com `username/password`.
2. Copiar o token JWT retornado.
3. Enviar `Authorization: Bearer <token>` nos endpoints `/authors` e `/books`.
4. Como `ADMIN`, cadastrar um autor.
5. Como `ADMIN`, cadastrar um livro vinculando ao `authorId`.
6. Testar permissões com usuário `USER` e `ADMIN`.

---

## Observações finais
- Evite retornar entidade JPA diretamente no controller.
- Centralize respostas de erro para manter padrão de API.
- Mantenha senhas criptografadas (ex.: `BCryptPasswordEncoder`).
- Garanta separação clara entre camadas: controller, service, repository.
- Prefira validações declarativas (`@Valid`, `@NotBlank`) combinadas com regras no service.

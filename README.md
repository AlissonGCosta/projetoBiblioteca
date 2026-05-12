# API de Biblioteca - Spring Web + JPA + Security (JWT)

## Descrição da atividade
Implementar uma API REST de biblioteca com autenticação JWT e autorização por perfil, utilizando Spring Boot.

## Objetivo
Construir uma API segura com operações de livros e controle de acesso por papéis (`ADMIN` e `USER`), aplicando boas práticas de arquitetura, validação, tratamento de erro e testes.

---

## Requisitos obrigatórios

### 1) Entidades JPA
- `User`
  - `id`
  - `username`
  - `password`
  - `role`
- `Book`
  - `id`
  - `title`
  - `author`
  - `available`

### 2) Segurança
- Login com `username/password`.
- Geração de token JWT.
- Perfis:
  - `ADMIN`: cadastra, atualiza e remove livros.
  - `USER`: apenas lista e busca livros.

### 3) Endpoints REST
- `POST /auth/login` (público): retorna token JWT.
- `GET /books` (`USER` ou `ADMIN`)
- `GET /books/{id}` (`USER` ou `ADMIN`)
- `POST /books` (`ADMIN`)
- `PUT /books/{id}` (`ADMIN`)
- `DELETE /books/{id}` (`ADMIN`)

### 4) Regras de negócio
- Não permitir cadastro de livro com título vazio.
- Retornar `404` quando livro não existir.
- Retornar `403` quando usuário sem permissão tentar ação de `ADMIN`.

### 5) Persistência
- Usar Spring Data JPA com H2 **ou** PostgreSQL.
- Criar dados iniciais com:
  - 1 usuário `ADMIN`
  - 1 usuário `USER`

### 6) Qualidade
- Criar DTOs de request/response (não expor entidades diretamente).
- Tratar exceções com `@ControllerAdvice`.
- Criar ao menos:
  - 1 teste de repositório (JPA)
  - 1 teste de controller com segurança (`MockMvc`)

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
├── book
├── dto
├── exception
└── repository
```

### Critérios de validação esperados
- `POST /books` com `title` vazio -> `400 Bad Request`.
- `GET /books/{id}` inexistente -> `404 Not Found`.
- `USER` tentando `POST/PUT/DELETE /books` -> `403 Forbidden`.
- Login válido retorna JWT.
- Requisição sem token em endpoint protegido -> `401 Unauthorized`.

---

## Sugestão de roteiro de implementação
1. Criar entidades `User` e `Book` + repositórios.
2. Implementar DTOs e validações.
3. Implementar serviço de autenticação e geração de JWT.
4. Configurar Spring Security (filtro JWT + autorização por perfil).
5. Criar controllers de `Auth` e `Book`.
6. Implementar `@ControllerAdvice` para erros padronizados.
7. Popular dados iniciais (`data.sql` ou `CommandLineRunner`).
8. Criar testes mínimos exigidos (JPA + MockMvc com segurança).

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
3. Enviar `Authorization: Bearer <token>` nos endpoints `/books`.
4. Testar permissões com usuário `USER` e `ADMIN`.

---

## Observações finais
- Evite retornar entidade JPA diretamente no controller.
- Centralize respostas de erro para manter padrão de API.
- Mantenha senhas criptografadas (ex.: `BCryptPasswordEncoder`).
- Garanta separação clara entre camadas: controller, service, repository.


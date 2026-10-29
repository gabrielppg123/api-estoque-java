# API de Estoque — Java & Spring Boot

API REST para gerenciamento de produtos e evolução de um sistema de controle de estoque, desenvolvida com **Java, Spring Boot e PostgreSQL**.

O projeto tem como objetivo aplicar conceitos de desenvolvimento back-end em um domínio de negócio real: persistência relacional, regras de estoque, tratamento de erros, testes automatizados e práticas de engenharia de software.

> **Status:** em desenvolvimento. A versão inicial possui operações de cadastro, consulta e atualização de produtos. As demais funcionalidades estão descritas no roteiro de evolução, sem serem apresentadas como concluídas.

## Funcionalidades implementadas

- Cadastro de produtos com persistência em PostgreSQL.
- Listagem de produtos cadastrados.
- Atualização de produtos existentes por identificador.
- Resposta HTTP **201 Created** no cadastro.
- Resposta HTTP **404 Not Found** ao tentar atualizar um produto inexistente.
- Identificador gerado automaticamente para cada produto.
- Cálculo inicial do valor total de estoque, ainda em evolução.

## Tecnologias utilizadas

| Tecnologia | Utilização |
| --- | --- |
| Java | Linguagem de programação |
| Spring Boot / Spring Web | Inicialização da aplicação e endpoints HTTP |
| Spring Data JPA | Acesso aos dados por meio de repositórios |
| Hibernate | Implementação de persistência JPA |
| PostgreSQL | Banco de dados relacional |
| Maven / Maven Wrapper | Gerenciamento de dependências e execução |
| Git / GitHub | Versionamento e hospedagem do código |

## API — endpoints disponíveis

**URL-base local:** `http://localhost:8080`

| Método | Rota | Descrição | Retorno esperado |
| --- | --- | --- | --- |
| `GET` | `/produtos` | Lista os produtos persistidos | `200 OK` |
| `POST` | `/produtos` | Cadastra um produto | `201 Created` |
| `PUT` | `/produtos/{id}` | Atualiza um produto existente | `200 OK` ou `404 Not Found` |
| `GET` | `/valorTotalEstoque` | Retorna o cálculo de estoque da implementação atual | `200 OK` |

> **Nota:** o cálculo de valor total será revisado para garantir que utilize exclusivamente os produtos persistidos no banco de dados.

### Exemplo de cadastro

**Requisição**

```http
POST /produtos
Content-Type: application/json
```

```json
{
  "nome": "Teclado mecânico",
  "preco": 189.90,
  "tipo": "Periféricos",
  "estoque": 15
}
```

**Resposta:** `201 Created`, com os dados do produto cadastrado, incluindo o identificador gerado.

### Exemplo de atualização

**Requisição**

```http
PUT /produtos/1
Content-Type: application/json
```

```json
{
  "nome": "Teclado mecânico",
  "preco": 179.90,
  "tipo": "Periféricos",
  "estoque": 12
}
```

Caso o identificador não exista, a API retorna `404 Not Found`.

## Estrutura atual do código

```text
src/main/java/br/com/estudos/estoque/
├── EstoqueApplication.java    # Inicialização do Spring Boot
├── Produto.java               # Entidade de produto
├── RepositorioProdutos.java   # Repositório Spring Data JPA
├── ControladorProdutos.java   # Endpoints REST
├── Servicos.java              # Cálculos e serviços iniciais
└── teste.http                 # Requisições manuais para testar a API
```

A estrutura será organizada em camadas conforme a evolução das funcionalidades, preservando a separação entre regras de negócio, acesso a dados e comunicação HTTP.

## Como executar localmente

### Pré-requisitos

- JDK compatível com a versão Java configurada no `pom.xml`.
- PostgreSQL instalado e em execução.
- Git para clonar o projeto.
- Maven **não precisa** ser instalado separadamente: o repositório inclui Maven Wrapper.

### 1. Clonar o projeto

```bash
git clone https://github.com/gabrielppg123/api-estoque-java.git
cd api-estoque-java
```

### 2. Preparar o banco de dados

Crie um banco de dados PostgreSQL chamado `estoque`, utilizando uma conta com as permissões necessárias.

```sql
CREATE DATABASE estoque;
```

O projeto utiliza uma configuração local semelhante a:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/estoque
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
```

Defina a variável de ambiente **`DB_PASSWORD`** com a senha do seu banco **no ambiente local**, por exemplo em **Run → Edit Configurations → Environment variables** no IntelliJ IDEA. Não inclua senhas reais em arquivos versionados ou em commits.

> A atualização automática do esquema pelo Hibernate, quando habilitada, é adequada apenas para desenvolvimento. O projeto adotará migrações versionadas de banco de dados em uma etapa futura.

### 3. Executar a aplicação

**Windows (PowerShell):**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux/macOS:**

```bash
./mvnw spring-boot:run
```

Se estiver usando o IntelliJ IDEA, também é possível iniciar a classe `EstoqueApplication`, desde que a variável `DB_PASSWORD` esteja configurada na execução.

### 4. Verificar o funcionamento

Com a aplicação iniciada, acesse:

```http
GET http://localhost:8080/produtos
```

É possível testar as demais rotas pelo cliente HTTP integrado ao IntelliJ ou por ferramentas como Postman e Insomnia.

## Decisões técnicas desta versão

- **Persistência real:** os produtos são armazenados no PostgreSQL, não apenas em uma lista temporária em memória.
- **Repositório JPA:** o acesso aos produtos utiliza `JpaRepository`.
- **Tratamento explícito de ausência:** a atualização consulta o produto antes de modificá-lo e retorna `404` quando o identificador não existe.
- **Proteção de credenciais:** a senha do banco é obtida por variável de ambiente, sem ficar diretamente no código-fonte.

## Roteiro de evolução

As melhorias abaixo estão **planejadas** e serão adicionadas progressivamente, acompanhadas de testes e documentação.

| Etapa | Funcionalidades e práticas | Situação |
| --- | --- | --- |
| 1 — Fundamentos | Consulta por ID, exclusão, organização em camadas, DTOs, validações, erros padronizados, paginação e filtros | Planejada |
| 2 — Domínio de estoque | Movimentações de entrada e saída, prevenção de saldo negativo, pedidos, transações e controle de concorrência | Planejada |
| 3 — Qualidade | Testes unitários e de integração, Testcontainers, migrações com Flyway, OpenAPI/Swagger | Planejada |
| 4 — Operação e segurança | Docker Compose, Spring Security, autenticação, logs, métricas e CI/CD com GitHub Actions | Planejada |
| 5 — Integrações | Deploy na AWS, mensageria e integrações entre serviços orientadas a casos de uso | Planejada |

O foco é evoluir um sistema coerente e verificável, em vez de adicionar dependências sem uma necessidade de negócio.

## Autor

Desenvolvido por [@gabrielppg123](https://github.com/gabrielppg123).

---

*Este README descreve a versão inicial e será atualizado juntamente com os próximos commits do projeto.*

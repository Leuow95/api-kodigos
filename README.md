# Kodigos Challenge API

Este repositório contém uma API Spring Boot que permite registrar usuários e criar ordens com descrições, checklists e fotos (em formato base64). A aplicação usa PostgreSQL como banco de dados.

## Pré-requisitos
Antes de executar a API, certifique-se de ter os seguintes requisitos instalados:

- **Docker**: [Instalação do Docker](https://www.docker.com/get-started)
- **Docker Compose**: Já incluído no Docker Desktop para Windows e macOS. Para Linux, siga as instruções [aqui](https://docs.docker.com/compose/install/).
- **Maven** (opcional): Necessário apenas se você quiser compilar o projeto localmente sem usar o Docker Compose. [Instalação do Maven](https://maven.apache.org/install.html)

## Estrutura do Projeto
- **src/**: Código-fonte da API.
- **Dockerfile**: Define a imagem Docker para a API.
- **docker-compose.yml**: Configura os serviços Docker (API e PostgreSQL).
- **target/**: Diretório gerado pelo Maven após a compilação.

## Como Executar a API

### Passo 1: Clone o Repositório
Clone este repositório em sua máquina:

```bash
git clone https://github.com/seu-usuario/kodigos-challenge-api.git
cd kodigos-challenge-api
```

### Passo 2: Inicie os Serviços com Docker Compose
Execute o seguinte comando para iniciar o banco de dados PostgreSQL e a API Spring Boot:

```bash
docker-compose up --build
```

O comando acima:
- Constrói a imagem da API (se necessário).
- Inicia o PostgreSQL e a API em containers separados.
- Expõe a API na porta 8080 e o PostgreSQL na porta 5432.

### Passo 3: Verifique se a API Está Funcionando
Após a inicialização, verifique se a API está rodando acessando:

```text
http://localhost:8080
```

Se tudo estiver configurado corretamente, você deve ver uma mensagem de sucesso ou a página inicial da API.

### Passo 4: Teste a API
Você pode testar a API usando os endpoints abaixo. Use ferramentas como `curl`, Postman ou Insomnia.

#### 1. Registrar um Usuário
Envia uma requisição POST para registrar um novo usuário:

```bash
curl -X POST http://localhost:8080/auth/register \
-H "Content-Type: application/json" \
-d '{"username": "leo@gmail.com", "password": "1234"}'
```

Resposta esperada:

```json
"User registered successfully"
```

#### 2. Criar uma Ordem
Envia uma requisição POST para criar uma nova ordem. Certifique-se de incluir um token JWT no cabeçalho Authorization. Para fins de teste, use o token fictício `Bearer <token>`:

```bash
curl -X POST http://localhost:8080/order/create \
-H "Authorization: Bearer <token>" \
-H "Content-Type: application/json" \
-d '{
"description": "Uma descrição",
"checklist": ["item1", "item2"],
"photoData": "uma_string_base64_muito_longa..."
}'
```

Resposta esperada:

```json
"Ordem criada com sucesso"
```

### Passo 5: Parar os Serviços
Para parar os serviços, pressione `Ctrl+C` no terminal onde o Docker Compose está rodando. Para remover os containers e volumes, execute:

```bash
docker-compose down
```

Se desejar limpar completamente os dados do banco de dados, remova o volume persistente:

```bash
docker volume rm <nome_do_volume>
```

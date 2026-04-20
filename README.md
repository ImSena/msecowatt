# EcoWatt - Monitoramento ESG para Cidades Inteligentes 🌿⚡

O **EcoWatt** é uma plataforma de monitoramento de eficiência energética desenvolvida para apoiar iniciativas de **ESG (Environmental, Social, and Governance)** em Cidades Inteligentes. A API permite gerenciar empresas, registrar leituras de consumo de energia e gerar alertas inteligentes baseados em métricas de sustentabilidade.

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.x
* **Banco de Dados:** MongoDB (NoSQL)
* **Segurança:** Spring Security + JWT com Criptografia Assimétrica (RSA - Par de chaves Pública/Privada)
* **Containerização:** Docker & Docker Compose
* **CI/CD:** GitHub Actions
* **Documentação API:** Insomnia / Swagger (OpenAPI)

---

## 🏗️ Arquitetura e Design

O projeto foi construído seguindo princípios de **Clean Architecture** e **Domain-Driven Design (DDD)** para garantir manutenibilidade e escalabilidade:

* **Value Objects:** Utilização de Java Records para garantir a imutabilidade de dados sensíveis como CNPJ, Endereços e Credenciais.
* **Segurança Assimétrica:** Implementação de autenticação JWT utilizando um par de chaves RSA (`app.pub` e `app.key`). As chaves garantem que apenas o detentor da chave privada possa assinar os tokens.
* **Desacoplamento:** Camadas de domínio isoladas de frameworks externos.

---

## 🔑 Geração das Chaves de Segurança (Pré-requisito)

Por boas práticas de segurança, as chaves criptográficas reais não são enviadas para o repositório (estão no `.gitignore`). Se você acabou de clonar este projeto do GitHub, **é obrigatório gerar um novo par de chaves RSA** antes de executar a aplicação.

Abra o seu terminal, navegue até a pasta `src/main/resources` do projeto e execute os comandos abaixo para gerar os arquivos `app.key` e `app.pub`:

```bash
# Navegue até o diretório de resources (crie se não existir)
mkdir -p src/main/resources
cd src/main/resources

# 1. Gerar a chave privada RSA de 2048 bits
openssl genrsa -out app.key 2048

# 2. Extrair a chave pública a partir da chave privada gerada
openssl rsa -in app.key -pubout -out app.pub
```
Após a execução, certifique-se de que os arquivos app.key e app.pub estão dentro da pasta src/main/resources.

---
## 🛠️ Como Executar Localmente com Docker
Com as chaves geradas, você pode subir todo o ambiente (API + Banco de Dados) com um único comando, sem precisar instalar o MongoDB na sua máquina.

```bash
# 1. Clone o repositório e gere as chaves: (Siga o passo anterior)

# 2. Volte para a raiz do projeto e suba os containers:
docker-compose up -d --build

# 3. Acesse a API:

  A API estará disponível em: http://localhost:8080

  O MongoDB (Docker) estará disponível para inspeção na porta: 27018 (evitando conflito com instalações locais na porta 27017).
```
---
## 🔄 Pipeline CI/CD
O projeto utiliza GitHub Actions para automatizar todo o ciclo de vida da aplicação:

* **Etapa de Build & Test:** Ocorre em cada Push. O pipeline configura o JDK 21, injeta as chaves RSA seguras via GitHub Secrets, sobe um container temporário de MongoDB e executa os testes automatizados.

* **Deploy em Staging:** Disparado automaticamente ao realizar commits na branch staging.

* **Deploy em Produção:** Disparado ao realizar merge na branch main, realizando o build da imagem Docker final.

---
## 📡 Endpoints Principais (Resumo)
🔐 Autenticação e Usuários
```
POST /auth/login: Autentica o usuário e retorna o token JWT.

POST /api/v1/users: Cadastro de novos usuários com perfis de acesso (RBAC).
```

🏢 Gestão de Empresas (ESG)
```
POST /api/v1/companies: Cadastra uma empresa monitorada (Nome, CNPJ, Localização).

GET /api/v1/companies/cnpj/{cnpj}: Busca detalhes de uma empresa específica.
```

📊 Consumo de Energia
```
POST /api/v1/energy-readings: Registra o consumo em kWh de uma empresa em uma data específica.

GET /api/v1/energy-readings/period: Filtra o consumo por período (início/fim) para relatórios de sustentabilidade.
```

⚠️ Alertas
```
POST /api/v1/alerts: Gera notificações automáticas em caso de consumo excessivo ou anomalias detectadas.
```

---

## 👨‍💻 Autor

**ImSena**

* 🌐 **Site:** [corecode.com.br](https://corecode.com.br)
* 💼 **LinkedIn:** [linkedin.com/in/bruno-sena-a6120417b](www.linkedin.com/in/bruno-sena-a6120417b)

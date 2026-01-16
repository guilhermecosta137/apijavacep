#  CEP Consultation API

> Uma API REST moderna para consulta de CEPs brasileiros, desenvolvida com Spring Boot 3 e Java 17.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

##  Sobre o Projeto

Essa API foi criada para facilitar a consulta de endereços através do CEP (Código de Endereçamento Postal) brasileiro. Ela consome a API do [ViaCEP](https://viacep.com.br/) e retorna informações completas sobre o endereço consultado, além de gerar arquivos JSON com os dados.

**Por que esse projeto é legal?**
- 🎯 Resolve um problema real: todo mundo precisa consultar CEP
- 🛠️ Usa tecnologias modernas e relevantes no mercado
- 📚 Código limpo e bem estruturado
- 🐳 Pronto para deploy com Docker
- 📖 Documentação automática com Swagger


## 🛠️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Spring Boot 3.1.0** - Framework para APIs REST
- **Maven** - Gerenciador de dependências
- **Gson** - Serialização/desserialização JSON
- **SpringDoc OpenAPI** - Documentação automática da API
- **Docker** - Containerização da aplicação
- **ViaCEP API** - Fonte dos dados de CEP

##  Pré-requisitos

Antes de começar, você precisa ter instalado:

- [Java 17](https://www.oracle.com/java/technologies/downloads/#java17) ou superior
- [Maven 3.6+](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started) (opcional, mas recomendado)

##  Como Rodar o Projeto

### Opção 1: Rodando localmente com Maven

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/cep-consultation-api.git
cd cep-consultation-api

# Compile o projeto
mvn clean install

# Execute a aplicação
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

### Opção 2: Rodando com Docker

```bash
# Compile o projeto
mvn clean package

# Construa a imagem Docker
docker build -t cep-api .

# Execute o container
docker run -p 8080:8080 cep-api
```

### Opção 3: Aplicação Console (sem Spring Boot)

Se você quer apenas testar a consulta de CEP sem subir o servidor:

```bash
# Compile as classes
javac -cp ".:gson-2.10.1.jar" *.java

# Execute
java -cp ".:gson-2.10.1.jar" Principal
```

## 📡 Endpoints da API

### Consultar CEP

```http
GET /api/cep/{cep}
```

**Parâmetros:**
- `cep` (string) - CEP a ser consultado (8 dígitos, com ou sem hífen)

**Exemplos:**
```bash
# Com hífen
curl http://localhost:8080/api/cep/01001-000

# Sem hífen
curl http://localhost:8080/api/cep/01001000
```

**Respostas:**

✅ **200 OK** - CEP encontrado
```json
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "complemento": "lado ímpar",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP"
}
```

❌ **400 Bad Request** - CEP inválido
```json
{
  "erro": "CEP inválido. Digite um CEP com 8 dígitos."
}
```

❌ **404 Not Found** - CEP não encontrado
```json
{
  "erro": "CEP não encontrado."
}
```

## 📖 Documentação Interativa

Após iniciar a aplicação, acesse o Swagger UI para testar os endpoints diretamente no navegador:

```
http://localhost:8080/swagger-ui.html
```

## 🤝 Como Contribuir

Contribuições são sempre bem-vindas! Se você tem alguma sugestão para melhorar o projeto:

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

##  Encontrou um Bug?

Abra uma [issue](https://github.com/seu-usuario/cep-consultation-api/issues) descrevendo:
- O que você esperava que acontecesse
- O que realmente aconteceu
- Passos para reproduzir o problema

##  Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Autor

Feito por guilherme costa
- LinkedIn: [seu-linkedin](https://linkedin.com/in/guilherme-costa-55850036a/)
- Email: guilhermethynk299@gmail.com

---

⭐ Se esse projeto te ajudou de alguma forma, considera dar uma estrela!

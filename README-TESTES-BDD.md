# SmartCollector — testes automatizados BDD

## Tecnologias
- Java 21 e Maven
- Cucumber/Gherkin para BDD
- REST Assured para chamadas HTTP e asserções
- JSON Schema Validator para contratos JSON
- JUnit Platform para executar o Cucumber

## Ajustes feitos
- Dependências de teste adicionadas ao `pom.xml`.
- Runner em `src/test/java/com/example/smartcollector/testes/RunCucumberTest.java`.
- Steps iniciais em `.../steps/ApiSteps.java`.
- Features em `src/test/resources/features`.
- Schemas em `src/test/resources/schemas`, aplicados à resposta do login e ao cadastro de item.

## Pré-requisitos
1. Java 21.
2. Maven instalado ou Maven Wrapper adicionado ao projeto.
3. SmartCollector em execução e conectado a um banco de testes Oracle.
4. Nunca execute os testes contra dados de produção.

## Configuração
A URL padrão é `http://localhost:8080`. Para alterar:

Windows PowerShell:
```powershell
$env:API_BASE_URL = "http://localhost:8080"
$env:BDD_TEST_PASSWORD = "SenhaTeste123"
```

Linux/macOS:
```bash
export API_BASE_URL="http://localhost:8080"
export BDD_TEST_PASSWORD="SenhaTeste123"
```

## Executar
Na raiz do projeto:
```bash
mvn clean test
```

O relatório HTML do Cucumber deve ser gerado em `target/cucumber-report.html`.

## Observações importantes
- O enum `UsuarioRole` do projeto contém `ADMIN` e `USER`; por isso os cenários usam `USER`, não `OPERADOR`.
- O teste de cadastro de item depende de um usuário autenticável e de uma base Oracle de testes.
- O cenário de e-mail duplicado usa um endereço fixo de teste; mantenha esse endereço reservado para testes e não o utilize em produção.
- Os JSON Schemas de login e item já são validados nos passos iniciais. Ainda é necessário completar a cobertura de todos os endpoints (Auth, Usuários, Catadores, Centros de Coleta, Descartadores, Itens, Coletas e Coleta-Itens) para atender integralmente ao enunciado.
- Não foram incluídas evidências de execução fictícias. Após executar os testes, capture prints reais e registre os resultados no PDF/PPT.

# language: pt

Funcionalidade: Autenticação de usuários
  Como usuário do SmartCollector
  Quero criar minha conta
  Para utilizar as funcionalidades da plataforma

  Cenário: Registrar usuário com dados válidos
    Dado que não existe um usuário com o e-mail "teste.bdd@smartcollector.com"
    Quando envio uma requisição de registro com os seguintes dados:
      | nome  | Usuario Teste                  |
      | email | teste.bdd@smartcollector.com   |
      | senha | SenhaTeste123                  |
      | funcao| USER                       |
    Então a resposta deve possuir status HTTP 200

  Cenário: Impedir registro com e-mail já cadastrado
    Dado que existe um usuário com o e-mail "duplicado.bdd@smartcollector.com"
    Quando envio uma requisição de registro com os seguintes dados:
      | nome   | Outro Usuario                  |
      | email  | duplicado.bdd@smartcollector.com|
      | senha  | SenhaTeste123                  |
      | funcao | USER                       |
    Então a resposta deve possuir status HTTP 400
    E a resposta deve informar "Email já cadastrado"
# language: pt

Funcionalidade: Gerenciamento de itens recicláveis
  Como operador do SmartCollector
  Quero cadastrar itens recicláveis
  Para manter os materiais disponíveis para as operações de coleta

  Cenário: Cadastrar item com dados válidos
    Dado que possuo um token de autenticação válido
    Quando cadastro um item com os seguintes dados:
      | nome   | Papelão para reciclagem |
      | volume | 25.0                    |
    Então a resposta deve possuir status HTTP 200
    E o corpo da resposta deve conter o nome "Papelão para reciclagem"
    E o volume retornado deve ser 25.0
    E o JSON da resposta deve respeitar o schema de item
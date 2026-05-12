# language: pt
Funcionalidade: Gestão e Registro de Leituras de Energia

  Cenário: Registrar uma leitura de energia com sucesso
    Dado que a API de leituras está pronta
    E eu monto um payload para a empresa "company-999" com consumo de 150.5 e data "2026-05-11"
    Quando eu envio um POST para "/energy-readings"
    Então o status code deve ser 201
    E a resposta deve conter um "id" gerado

  Cenário: Falhar ao tentar registrar leitura sem informar a empresa
    Dado que a API de leituras está pronta
    E eu monto um payload vazio sem a identificacao da empresa
    Quando eu envio um POST para "/energy-readings"
    Então o status code deve ser 404

  Cenário: Consultar uma leitura específica pelo ID
    Dado que a API de leituras está pronta
    Quando eu envio um GET para "/energy-readings"
    Então o status code deve ser 200
    E a resposta deve conter um "id" gerado
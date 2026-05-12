# language: pt
Funcionalidade: Gestão de Empresas
  Como um administrador do sistema
  Quero registrar e consultar empresas parceiras

  Cenário: Registrar uma empresa com sucesso (Caminho Feliz)
    Dado que a API de empresas está pronta
    E eu monto um payload para a empresa "Tech Solutions" com CNPJ "12.345.678/0001-99"
    Quando a requisicao POST for enviada para "/companies"
    Então o status code da empresa deve ser 201
    E a resposta da empresa deve conter um "id" gerado
    E a resposta da empresa deve conter o nome "Tech Solutions"

  Cenário: Falhar ao tentar registrar empresa com CNPJ vazio (Cenário Negativo)
    Dado que a API de empresas está pronta
    E eu monto um payload de empresa faltando o CNPJ
    Quando a requisicao POST for enviada para "/companies"
    Então o status code da empresa deve ser 400

  Cenário: Listar todas as empresas cadastradas (Validação de Contrato)
    Dado que a API de empresas está pronta
    Quando a requisicao GET for enviada para "/companies"
    Então o status code da empresa deve ser 200
    E a lista de empresas deve respeitar o contrato JSON Schema
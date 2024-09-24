## Projeto de Extensão - Programação Orientada a Objetos em Java

### CHD Ponto Digital

![Java](https://img.shields.io/badge/Java-22-orange)
![Maven](https://img.shields.io/badge/Maven-3.9.9-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-green)
![License](https://img.shields.io/badge/license-MIT-brightgreen)

CHD Ponto Digital é um sistema destinado ao gerenciamento e registro de pontos de funcionários, permitindo o controle eficiente dos horários de entrada e saída durante o expediente.

## Requisitos

- **Java**: Versão 22
- **Maven**: Versão 3.9.9

## Instruções para rodar o projeto

1. Clone o repositório:
    ```bash
    git clone https://github.com/LeonardaSaad/PE-POOJava.git
    ```
2. Acesse o diretório do projeto:
    ```bash
    cd PE-POOJava/
    ```
3. Execute o comando abaixo para iniciar o servidor:
    ```bash
    mvn spring-boot:run
    ```

## Funcionalidades e Rotas

### Criar Funcionário

- **Método**: POST
- **Endpoint**: `/funcionarios`
- **Exemplo de Requisição**:
    ```json
    {
      "name": "Nome do funcionário"
    }
    ```
- **Exemplo de Resposta**:
    ```json
    {
      "status": true,
      "message": "Funcionário criado com sucesso",
      "employeeId": "9aee",
      "dateTime": null
    }
    ```

### Registrar Entrada de Ponto

- **Método**: POST
- **Endpoint**: `/registro_ponto`
- **Exemplo de Requisição**:
    ```json
    {
      "funcionario_id": "9aee"
    }
    ```
- **Exemplo de Resposta**:
    ```json
    {
      "status": true,
      "message": "Entrada registrada com sucesso",
      "employeeId": "9aee",
      "dateTime": "24/09/2024 15:26"
    }
    ```

### Listar Registros de Ponto

- **Método**: GET
- **Endpoint**: `/registro_ponto`
- **Exemplo de Resposta**:
    ```json
    [
      {
        "pontoId": 1,
        "funcionario": {
          "funcionario_id": "9aee",
          "name": "Nome do funcionário"
        },
        "entradaPonto": "2024-09-22T11:51:58.963",
        "saidaPonto": "2024-09-22T11:52:02.947",
        "dataEntradaConverted": "22/09/2024 11:51",
        "dataSaidaConverted": "22/09/2024 11:52"
      },
    ]
    ```

## Licença

Este projeto está sob a licença MIT.

# 🏫 API de Reserva de Salas de Aula

Este projeto é uma API RESTful desenvolvida em **Java** para gerenciar a reserva de salas de aula dentro de uma instituição de ensino. O sistema permite o cadastro de salas e o agendamento de reservas por professores, garantindo que não haja conflitos de horários.

Projeto desenvolvido para a disciplina de **Integração de Sistemas de Software** (Avaliação A2-1).

## 🛠️ Tecnologias Utilizadas
* **Java** (JDK 11 ou superior)
* **API HTTP Nativa do Java** (`com.sun.net.httpserver.HttpServer`)
* **MySQL & JDBC** (Persistência de dados)
* **Jackson** (Serialização e Desserialização de JSON)
* **Maven** (Gerenciamento de dependências)

## ⚙️ Funcionamento e Como Executar

### 1. Banco de Dados
Antes de rodar a aplicação, crie o banco de dados no MySQL executando o script abaixo:

```sql
CREATE DATABASE iss_reserva;
USE iss_reserva;

CREATE TABLE sala (
    id VARCHAR(50) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    capacidade INT NOT NULL
);

CREATE TABLE reserva (
    id VARCHAR(50) PRIMARY KEY,
    sala_id VARCHAR(50) NOT NULL,
    professor VARCHAR(100) NOT NULL,
    data_reserva VARCHAR(10) NOT NULL,
    horario VARCHAR(5) NOT NULL,
    FOREIGN KEY (sala_id) REFERENCES sala(id)
);
```

*(Nota: Lembre-se de configurar a sua senha do MySQL na classe `ConnectionFactory.java`).*

### 2. Rodando o Servidor
Execute a classe principal `ApiServer.java`. O servidor iniciará localmente na porta `8000` (`http://localhost:8000`).

## 🧠 Regras de Negócio e Exceções Personalizadas
Para garantir a consistência dos dados, a API implementa regras de negócio robustas na camada de *Service*:
* **`ReservaConflitoException` (Erro 400):** Impede que duas reservas sejam feitas para a mesma sala, na mesma data e no mesmo horário.
* **`SalaNaoEncontradaException` (Erro 404):** Lançada ao tentar buscar, deletar ou criar uma reserva em uma sala que não existe no banco de dados.
* **`ReservaNaoEncontradaException` (Erro 404):** Lançada ao tentar buscar, atualizar ou deletar uma reserva inexistente.

---

## 🚀 Endpoints e Exemplos (Postman)

### 🏢 Salas (`/salas`)

| Método | Rota | Descrição |
|---|---|---|
| **GET** | `/salas` | Lista todas as salas cadastradas. |
| **GET** | `/salas/{id}` | Busca uma sala específica pelo ID. |
| **POST** | `/salas` | Cadastra uma nova sala. |
| **PUT** | `/salas` | Atualiza os dados de uma sala existente. |
| **DELETE**| `/salas/{id}` | Remove uma sala do sistema. |

#### Exemplo de Body para POST `/salas`
```json
{
  "nome": "Laboratório de Informática 1",
  "capacidade": 30
}
```

#### Exemplo de Body para PUT `/salas`
*(O ID gerado na criação deve ser enviado no corpo da requisição)*
```json
{
  "id": "efdf310e-3b8f-4edf-a810-ca0f055b76d9",
  "nome": "Laboratório de Redes",
  "capacidade": 25
}
```

---

### 📅 Reservas (`/reservas`)

| Método | Rota | Descrição |
|---|---|---|
| **GET** | `/reservas` | Lista todas as reservas cadastradas. |
| **GET** | `/reservas/{id}` | Busca uma reserva específica pelo ID. |
| **POST** | `/reservas` | Cria uma nova reserva (valida conflitos). |
| **PUT** | `/reservas` | Atualiza uma reserva existente. |
| **DELETE**| `/reservas/{id}`| Cancela/Remove uma reserva do sistema. |

#### Exemplo de Body para POST `/reservas`
*(O `salaId` deve ser um ID válido de uma sala previamente cadastrada)*
```json
{
  "salaId": "efdf310e-3b8f-4edf-a810-ca0f055b76d9",
  "professor": "Prof. Odair",
  "dataReserva": "2026-05-10",
  "horario": "19:00"
}
```

#### Exemplo de Body para PUT `/reservas`
```json
{
  "id": "coloque-o-id-da-reserva-aqui",
  "salaId": "efdf310e-3b8f-4edf-a810-ca0f055b76d9",
  "professor": "Prof. Odair Silva",
  "dataReserva": "2026-05-10",
  "horario": "21:00"
}
```

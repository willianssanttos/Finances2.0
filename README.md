## Sistema Controle Financeiro Pessoal

## Descrição

O Sistema de Controle Financeiro Pessoal é uma API desenvolvida em Java utilizando o Spring Framework, projetada para ajudar os usuários a gerenciar suas finanças pessoais de forma eficiente. A aplicação oferece funcionalidades de cadastro e consulta de receitas, despesas, e transações, além de relatórios de extratos financeiros organizados por período. O sistema utiliza o banco de dados PostgreSQL, com operações de persistência gerenciadas pelo JdbcTemplate.

## Funcionalidades

## Gestão de Usuários

- Cadastro de Usuários: Permite o registro de novos usuários no sistema com validação de dados.
- Autenticação: Integração com Spring Security e JWT para login seguro e gerenciamento de sessões.
- Validação de E-mail: Verifica a autenticidade do e-mail do usuário antes de concluir o cadastro.



## Gestão de Contas Financeiras

- Cadastro de Contas Financeiras: Permite o usuário cadastrar múltiplas contas, como "Banco", "Carteira", etc., com nome, tipo e saldo inicial.
- Edição e Exclusão de Contas: O usuário pode editar ou excluir contas. Ao excluir uma conta, todas as transações associadas a essa conta são também excluídas.

## Gestão de Transações

- Cadastro de Receitas e Despesas: Registra entradas e saídas financeiras, associadas a categorias específicas.
- Controle de Categorias: Gerenciamento das categorias para organizar receitas e despesas.
- Relatórios: Geração de extratos financeiros por período, possibilitando uma visão clara dos gastos e ganhos.

## Extratos e Relatórios
- Visualização de Extrato Financeiro: O sistema permite a geração de um extrato financeiro completo para o mês e ano corrente, ou para um período específico informado pelo usuário.
- Relatórios de Ganhos e Gastos: O sistema exibe o total de ganhos e gastos do mês e permite visualizar a distribuição percentual das categorias que mais contribuíram para cada tipo de transação (receitas ou despesas).

## Tecnologias Utilizadas

- Spring Security: Implementa autenticação e autorização de usuários.
- JWT: Utilizado para controle de acesso a endpoints e autenticação de sessões.
- Java 17
- JDBC para a comunicação com o banco de dados.
- PostgreSQL para persistência de dados.
- Maven para gerenciamento de dependências.


## Configurações de Banco de Dados
Configure o banco de dados PostgreSQL e atualize o arquivo `application.properties`, com suas credenciais. Os scripts SQL necessários para a criação das tabelas e functions, e o arquivo dos endpoints para teste estão disponíveis na pasta `Scripts`.

## Funções no Banco de Dados
O sistema utiliza triggers e functions no banco de dados PostgreSQL para:

- Atualização automática dos saldos: Uma trigger é acionada ao criar ou atualizar uma transação, recalculando automaticamente o saldo da conta afetada.

## Como Executar

1. Clone o repositório do projeto.
2. Configure um banco de dados PostgreSQL ou de sua preferencia, e execute os scripts SQL na pasta Scripts na raiz do projeto.
  - Observação: Execute primeiro o script de criação do banco de dados, seguido pelos scripts das functions.
3. Atualize as credenciais do banco de dados na classe `application.properties` conforme sua configuração local.
4. Compile o projeto usando o Maven:
  - mvn clean install
5. Importa a collection, dos endpoints, em uma plataforma de sua preferencia insomnia ou postman

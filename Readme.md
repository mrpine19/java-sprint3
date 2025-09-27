# 5. Procedimentos para Rodar a Aplicação

Este guia fornece as instruções necessárias para configurar, importar e executar o projeto Java (`TesteAplicacao.java`), demonstrando as funcionalidades do CRUD e a lógica de negócio no console.

## 5.1. Pré-requisitos e Versões Necessárias

Para garantir que o código compile corretamente, os seguintes pré-requisitos são obrigatórios:

| Ferramenta               | Versão Mínima       | Uso no Projeto                                                   |
|--------------------------|---------------------|------------------------------------------------------------------|
| Java Development Kit (JDK) | 17 ou superior      | Linguagem de programação.                                        | |
| IDE                       | IntelliJ IDEA, Eclipse ou VS Code | Edição e execução do código.                                    |

## 5.2. Configuração do Banco de Dados (Oracle)

O projeto precisa que as tabelas estejam criadas e que a conexão seja válida.

### Criação das Tabelas

Execute o script DDL final que contém as instruções `CREATE TABLE` e `CREATE SEQUENCE/TRIGGER` no seu schema Oracle.

### Configuração da Conexão

Verifique e ajuste a classe `ConnectionFactory.java` no pacote `br.com.healthtech.imrea.conexao` para garantir que as credenciais e o URL da sua instância Oracle estejam corretos (conforme instrução da sprint de colocar o usuário e senha no código).

## 5.3. Execução Passo a Passo

O projeto deve ser executado a partir do método `main` da classe de teste para demonstrar as operações de CRUD e a lógica de negócio.

### Opção A: Execução via IDE (Recomendado)

1. **Abrir o Projeto**: Use o seu IDE (IntelliJ, Eclipse, etc.) para importar o projeto como um projeto Maven existente.
2. **Localizar o Arquivo**: Navegue até o arquivo `TesteAplicacao.java` (`br.com.healthtech.imrea.main`).
3. **Executar**: Clique com o botão direito sobre o arquivo `TesteAplicacao.java` e selecione "Run 'TesteAplicacao.main()'" (ou o equivalente no seu IDE).

## 5.4. Resultado da Execução

Após rodar o código, o console exibirá as seguintes mensagens, confirmando que a lógica de negócio foi executada e que os dados foram persistidos/manipulados no banco de dados:

- Mensagens de sucesso e IDs gerados (CRUD - Create).
- Resultados das buscas (CRUD - Read).
- Confirmações de atualização e remoção (CRUD - Update e Delete).

# Documentação CareLink - Implementação Java (Sprint 3)

Este documento detalha a implementação Java desenvolvida para a Sprint 3 do projeto CareLink, focando na demonstração de funcionalidades CRUD e lógicas de negócio essenciais para o gerenciamento de teleconsultas. O objetivo é validar a integração da aplicação com o Oracle Database, estabelecendo a base para o desenvolvimento das próximas etapas, incluindo inteligência artificial e automação.

## 5. Procedimentos para Rodar a Aplicação

Este guia fornece as instruções necessárias para configurar, importar e executar o projeto Java (`TesteAplicacao.java`), demonstrando as funcionalidades do CRUD e a lógica de negócio simulada em um ambiente de console interativo.

### 5.1. Pré-requisitos e Versões Necessárias

Para garantir que o projeto seja executado corretamente, os seguintes pré-requisitos e versões são obrigatórios:

| Ferramenta / Componente     | Versão Mínima                     | Uso no Projeto                                                                 |
| :-------------------------- |:----------------------------------| :----------------------------------------------------------------------------- |
| Java Development Kit (JDK)  | 17 ou superior                    | Ambiente de desenvolvimento e execução da aplicação Java.                      |
| Driver JDBC para Oracle     | `ojdbc17.jar`                     | **OBRIGATÓRIO** para a conexão com o Oracle Database.             |
| IDE                         | IntelliJ IDEA, Eclipse ou VS Code | Ambiente para edição, compilação e execução do código.                         |
| Banco de Dados              | Oracle Database 19c ou superior   | Plataforma para armazenamento e persistência dos dados do sistema CareLink. |

### 5.2. Configuração do Banco de Dados (Oracle)

O projeto depende de um ambiente Oracle Database pré-configurado com as tabelas do CareLink e credenciais de acesso válidas.

#### 5.2.1. Criação das Tabelas e Schema

* **Schema:** Certifique-se de que o schema Oracle designado para o projeto (ex: `RM566520`) esteja criado.

### 5.3. Execução Passo a Passo

O projeto deve ser executado a partir do método `main` da classe `TesteAplicacao` para interagir com o sistema via console.

#### **5.3.1. Configuração do Classpath (Adição do Driver JDBC)**

Para estabelecer a conexão com o banco de dados Oracle, o driver JDBC (`ojdbc17.jar`) precisa ser explicitamente adicionado ao *classpath* do seu projeto na IDE. Sem este passo, você provavelmente enfrentará erros de conexão (`No suitable driver found`).

1.  **Localize o Driver:** O arquivo **`ojdbc17.jar`** está localizado na pasta `libs` na raiz do projeto.
2.  **Adicione na IDE:**
    * **IntelliJ IDEA:** Vá em `File > Project Structure... > Modules`. Selecione o módulo do seu projeto, clique na aba `Dependencies`, depois no botão `+` (mais) e escolha `JARs or directories...`. Navegue até a pasta `libs` e selecione `ojdbc17.jar`.
    * **Eclipse:** Clique com o botão direito no seu projeto no `Package Explorer`, selecione `Build Path > Configure Build Path...`. Na aba `Libraries`, clique em `Add External JARs...`, navegue até a pasta `libs` e selecione `ojdbc17.jar`.
    * **VS Code (com extensões Java):** Normalmente, o VS Code detecta JARs em pastas como `libs`. Se não, você pode precisar adicioná-lo manualmente via `settings.json` ou ajustando o `JAVA_HOME` para incluir o diretório.

#### **5.3.2. Opção A: Execução via IDE (Recomendado)**

1.  **Abrir o Projeto:** Inicie sua IDE (IntelliJ IDEA, Eclipse, VS Code).
2.  **Localizar o Arquivo:** Navegue até a classe `TesteAplicacao.java` (localizada no pacote `br.com.healthtech.imrea.main` ou similar).
3.  **Executar:** Clique com o botão direito sobre o arquivo `TesteAplicacao.java` e selecione "Run 'TesteAplicacao.main()'" (ou o comando equivalente na sua IDE).

### 5.4. Interação e Resultado da Execução

Após rodar o código, o console exibirá o **Menu Principal do Sistema CareLink - Gerenciamento**. Você poderá interagir com as opções apresentadas, demonstrando as funcionalidades de CRUD para Pacientes, Profissionais, Cuidadores e Consultas, além das lógicas de negócio simuladas (Realizar Novo Agendamento e Buscar Pacientes de Alto Risco).

O console mostrará:

* Mensagens de confirmação para operações de criação, atualização e exclusão.
* Listas de registros ao consultar as entidades.
* Resultados das buscas de pacientes de alto risco.

A interação via console permitirá a verificação visual da correta manipulação dos dados e da aplicação das lógicas de negócio configuradas.
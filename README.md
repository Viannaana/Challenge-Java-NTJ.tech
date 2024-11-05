# Claud.IA

## Sumário

- [Descrição do Projeto](#descrição-do-projeto)
- [Modelo Relacional das Entidades](#modelo-relacional-das-entidades)
- [Modelo Lógico das Entidades](#modelo-lógico-das-entidades)
- [Objetivos do Projeto](#objetivos-do-projeto)
- [Endpoints Disponíveis](#endpoints-disponíveis)
- [Estrutura dos Pacotes](#estrutura-dos-pacotes)
- [Configuração e Execução no Azure DevOps](#configuração-e-execução-no-azure-devops)
- [Swagger](#swagger)
- [Integrantes do Grupo](#integrantes-do-grupo)

## Descrição do Projeto

Este projeto visa desenvolver uma solução inovadora para simplificar a busca em bases de dados, utilizando inteligência artificial (IA) generativa e deep learning. Denominada **Claud.IA**, a solução permite que os usuários expressem consultas em linguagem natural e obtenham respostas precisas, tornando o processo de busca mais intuitivo e eficaz.

## Modelo Relacional das Entidades

![Modelo Relacional](https://github.com/Viannaana/Challenge-Java-NTJ.tech/assets/145307161/35339937-4c79-47ee-9532-7b069ba8dc87)

## Modelo Lógico das Entidades

![Modelo Lógico](https://github.com/Viannaana/Challenge-Java-NTJ.tech/assets/145307161/ed6e91c9-952b-4677-8540-78c909895aab)

## Objetivos do Projeto

1. **Simplificação da Análise de Dados:** Desenvolver uma solução que lide com a diversidade de fontes, formatos e estruturas dos dados, tornando a análise mais eficiente.
2. **Processamento em Tempo Real:** Possibilitar o processamento de dados em tempo real para tomadas de decisão rápidas e ágeis.
3. **Geração de Insights Significativos:** Utilizar IA generativa e deep analytics para extrair insights acionáveis.
4. **Capacidades de Previsão e Prescrição:** Incorporar modelos preditivos e algoritmos de machine learning para fornecer insights precisos e guiar decisões estratégicas.
5. **Usabilidade e Acessibilidade:** Garantir uma interface intuitiva, fácil de usar, com recursos de suporte adequados.

## Endpoints Disponíveis

| Endpoint                                                  | Método | Descrição                                                |
|-----------------------------------------------------------|--------|----------------------------------------------------------|
| `https://sprintcloudia.azurewebsites.net/fornecedores`    | GET    | Retorna a lista de fornecedores                          |
| `https://sprintcloudia.azurewebsites.net/fornecedores`    | POST   | Adiciona um novo fornecedor                              |
| `https://sprintcloudia.azurewebsites.net/fornecedores/{id}`| GET    | Retorna os detalhes de um fornecedor específico          |
| `https://sprintcloudia.azurewebsites.net/fornecedores/{id}`| PUT    | Atualiza os detalhes de um fornecedor específico         |
| `https://sprintcloudia.azurewebsites.net/fornecedores/{id}`| DELETE | Deleta um fornecedor específico                          |
| `https://sprintcloudia.azurewebsites.net/produtos`        | GET    | Retorna a lista de produtos                              |
| `https://sprintcloudia.azurewebsites.net/produtos`        | POST   | Adiciona um novo produto                                 |
| `https://sprintcloudia.azurewebsites.net/produtos/{id}`   | GET    | Retorna os detalhes de um produto específico             |
| `https://sprintcloudia.azurewebsites.net/produtos/{id}`   | PUT    | Atualiza os detalhes de um produto específico            |
| `https://sprintcloudia.azurewebsites.net/produtos/{id}`   | DELETE | Deleta um produto específico                             |
| `https://sprintcloudia.azurewebsites.net/clientes`        | GET    | Retorna a lista de clientes                              |
| `https://sprintcloudia.azurewebsites.net/clientes`        | POST   | Adiciona um novo cliente                                 |
| `https://sprintcloudia.azurewebsites.net/clientes/{id}`   | GET    | Retorna os detalhes de um cliente específico             |
| `https://sprintcloudia.azurewebsites.net/clientes/{id}`   | PUT    | Atualiza os detalhes de um cliente específico            |
| `https://sprintcloudia.azurewebsites.net/clientes/{id}`   | DELETE | Deleta um cliente específico                             |
| `https://sprintcloudia.azurewebsites.net/pedidos`         | GET    | Retorna a lista de pedidos                               |
| `https://sprintcloudia.azurewebsites.net/pedidos`         | POST   | Adiciona um novo pedido                                  |
| `https://sprintcloudia.azurewebsites.net/pedidos/{id}`    | GET    | Retorna os detalhes de um pedido específico              |
| `https://sprintcloudia.azurewebsites.net/pedidos/{id}`    | PUT    | Atualiza os detalhes de um pedido específico             |
| `https://sprintcloudia.azurewebsites.net/pedidos/{id}`    | DELETE | Deleta um pedido específico                              |

## Estrutura dos Pacotes

### 📂 Model
Contém as classes que representam as entidades do sistema, refletindo as regras de negócio e mapeando dados para o banco de dados.

### 📂 Dto
Contém as classes responsáveis por transportar dados entre as camadas da aplicação, facilitando a transferência de dados.

### 📂 Repository
Interfaces que fazem a comunicação com o banco de dados via JPA, gerenciando operações CRUD e consultas diretamente nas entidades.

### 📂 Controller
Contém as classes que lidam com as requisições HTTP, processam a lógica de negócio necessária e retornam respostas ao cliente.

### 📂 Handler
Gerencia exceções e erros durante a execução da aplicação, garantindo respostas apropriadas para problemas como recursos não encontrados.

## Configuração e Execução no Azure DevOps

Para a aplicação Claud.IA, configuramos uma pipeline no **Azure DevOps** utilizando o modo clássico. Realizamos os seguintes passos:

1. **Configuração da Pipeline:** No repositório do projeto, configuramos a pipeline no modo clássico e definimos todas as variáveis e dependências necessárias para o build da aplicação.

2. **Execução do Build:** Após configurar a pipeline, realizamos o processo de build, que compilou a aplicação com sucesso.

3. **Configuração da Release e Deployment:** Com o build concluído, configuramos a release da aplicação e definimos o ambiente de destino para o deployment.
   - A aplicação foi implantada em um Web App no Azure, utilizando o mesmo recurso configurado para a release.

4. **Execução do Web App:** Após o deployment, iniciamos o Web App para tornar a aplicação disponível na nuvem.

Os testes podem ser realizados na aplicação em execução utilizando a seguinte URL:

- **Base URL:** [https://sprintcloudia.azurewebsites.net](https://sprintcloudia.azurewebsites.net)

## :clipboard: Swagger

A aplicação conta com uma interface Swagger para facilitar a interação e documentação dos endpoints. Acesse o Swagger no seguinte link:

- **Swagger URL:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Integrantes do Grupo

- **Ana Júlia Almeida Silva Neves** – RM: 98974
- **Nicoly Oliveira Santos** – RM: 552410
- **Vitor da Silva Pereira** – RM: 551831
- **Rafael Minoro Itokazo** – RM: 99988


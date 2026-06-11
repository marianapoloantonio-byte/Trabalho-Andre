# Sistema de E-Commerce de Jogos

**Status do Projeto:** Concluído & Pronto para Entrega  
**Componentes da Equipe:** Alana, Mariana e Yasmin  
**Disciplina:** Desenvolvimento de Software | Professor: André  

---

## 1. Informações Gerais e Fluxo do Sistema

Este projeto consiste em um sistema em terminal desenvolvido em **Java** que simula uma plataforma de e-commerce e gestão de jogos digitais. O objetivo principal foi aplicar de forma prática os conceitos avançados de Programação Orientada a Objetos e garantir a persistência segura dos dados.

O sistema divide-se em dois fluxos principais de execução baseados em perfis de acesso:

* **Menu do Administrador:** Área restrita para a gestão de inventário. Permite realizar o **CRUD completo de Jogos** (Cadastrar novos títulos, Listar o catálogo em tempo real, Atualizar informações como preço ou gênero, e Remover jogos obsoletos).
* **Menu do Usuário (Cliente):** Fluxo intuitivo para o consumidor final. Permite criar uma conta do zero (armazenada fisicamente), efetuar autenticação por e-mail/senha, navegar pelo catálogo de jogos disponíveis, realizar compras com simulação de checkout financeiro, gerenciar uma biblioteca pessoal e deixar avaliações com notas por estrelas (⭐). Permite também o **CRUD de Perfil**, dando a liberdade para o usuário atualizar seus dados ou remover a sua conta permanentemente.

---

## 2. Arquitetura de Software & Pilares de POO

O projeto foi estruturado utilizando o padrão arquitetural **MVC (Model-View-Controller)** para garantir uma separação limpa de responsabilidades entre a interface de terminal, as regras de negócio e os modelos de dados.

### Pilares Práticos de POO Aplicados:
* **Abstração e Herança:** Criação da classe abstrata `Pessoa` que serve de molde para as classes filhas `Usuario` e `Administrador`, herdando os atributos base (`id`, `nome`, `email`) e assinando o método abstrato `exibirPerfil()`.
* **Encapsulamento:** Todos os atributos críticos das entidades foram protegidos com modificadores de acesso `private` ou `protected`, sendo o acesso rigidamente controlado através de métodos seletores (`getters`) e modificadores (`setters`).
* **Polimorfismo:** Demonstrado tanto na sobreposição de métodos (`@Override` em `exibirPerfil` e no mecanismo de `login`), quanto no tratamento genérico das formas de pagamento.
* **Interfaces:** * `Autenticavel`: Padroniza o comportamento de login seguro.
  * `Pagamento`: Desacopla as regras financeiras, permitindo que as classes de pagamento processem transações de forma independente.

### Relações entre as Classes (Associação, Agregação e Composição)

Para mapear o domínio de forma realista e profissional, estruturamos a comunicação do software utilizando diferentes níveis de acoplamento entre as entidades do pacote `Model`:

#### 1. Associação Simples (Conexão e Dependência de Uso)
Acontece quando duas classes são independentes em seus ciclos de vida, mas interagem para realizar uma ação.
* **`Compra` ➡️ `Pagamento`:** A classe `Compra` conhece e invoca o método da interface `Pagamento`. Graças ao polimorfismo, a compra delega o processamento financeiro sem precisar se acoplar a uma regra específica, seja ela `PagamentoPix`, `PagamentoCartao` ou `PagamentoBoleto`.
* **`Avaliacao` ➡️ `Usuario` e `Jogos`:** A entidade `Avaliacao` funciona como uma linha de ligação no banco de dados, guardando a referência de qual cliente escreveu o comentário e para qual jogo aquela nota se destina.

#### 2. Agregação (Relação "Tem um" - Parte/Todo Independente)
Ocorre quando uma classe contém uma coleção de objetos de outra classe, mas a destruição da classe principal não afeta a existência dos objetos contidos.
* **`Usuario` ➡️ `Jogos` (Através do `List<Jogos> biblioteca`):** O usuário possui uma biblioteca com jogos adquiridos. Caso o cliente utilize a função de excluir sua própria conta do sistema, os objetos do tipo `Jogos` continuam existindo perfeitamente no catálogo global da loja. Eles não são apagados do sistema apenas porque um usuário deixou de existir.

#### 3. Composição (Relação "Dono de" - Vínculo de Sobrevivência)
É a forma mais forte de relacionamento, onde os objetos filhos pertencem exclusivamente ao objeto pai e têm seu ciclo de vida rigidamente atrelado a ele.
* **`Jogos` ➡️ `Avaliacao` (Através do `List<Avaliacao> avaliacoes`):** As avaliações pertencem de forma exclusiva e intrínseca a um jogo. Se o Administrador apagar um jogo do catálogo, todas as avaliações, notas e comentários atrelados a ele deixam de fazer sentido e são excluídas da memória e do disco junto com o jogo. Não existem avaliações órfãs no sistema.

### Persistência de Dados e Auditoria:
* **Persistência física:** Implementada na classe `ArquivoService` (`Service`), utilizando a **Serialização de Objetos em Java** para gravar e ler as coleções em arquivos binários (`jogos.dat` e `usuarios.dat`). Qualquer alteração (CRUD) é refletida instantaneamente no disco, sobrevivendo ao encerramento da aplicação.
* **Sistema de Logs:** Através da classe `LogService`, ações críticas do sistema geram um histórico detalhado em tempo real salvo no arquivo de texto puro `log.txt`.

---

## 3. Uso de Inteligência Artificial (Processo de Co-Criação)

Durante o desenvolvimento do projeto, utilizamos ferramentas de Inteligência Artificial como apoio em diferentes etapas do trabalho. Inicialmente, a IA foi utilizada para auxiliar na definição do tema do projeto e no planejamento das funcionalidades que seriam implementadas, ajudando a organizar as ideias da equipe e estruturar melhor o escopo do sistema.

Ao longo do desenvolvimento, a ferramenta também foi utilizada para esclarecer dúvidas relacionadas à linguagem Java, conceitos de Programação Orientada a Objetos, persistência de dados e organização da arquitetura do projeto. Além disso, serviu como fonte de inspiração para soluções de implementação, sugestões de melhorias e boas práticas de programação.

A IA foi utilizada como uma ferramenta de apoio ao aprendizado e à produtividade, contribuindo para a pesquisa e para a tomada de decisões técnicas. Entretanto, todas as escolhas de desenvolvimento, implementação da lógica de negócio, testes e validações foram realizadas pela equipe.

---

## 4. Como Executar o Projeto

1. Clone o repositório:
```bash
git clone [https://github.com/marianapoloantonio-byte/Trabalho-Andre.git](https://github.com/marianapoloantonio-byte/Trabalho-Andre.git)

2. Entre na pasta do projeto:

```bash
cd Trabalho-Andre
```

3. Compile os arquivos Java:

```bash
javac src/**/*.java
```

4. Execute a classe principal:

```bash
java Main
```

5. O sistema criará automaticamente os arquivos:
- jogos.dat
- usuarios.dat
- log.txt

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

### Persistência de Dados (O Critério da "Queda de Energia"):
Para cumprir a exigência de que os dados não fossem perdidos ao encerrar o programa, implementamos a classe `ArquivoService`. Esta classe utiliza **Serialização de Objetos em Java** para gravar e ler coleções em arquivos binários físicos (`jogos.dat` e `usuarios.dat`). 
* *Como funciona:* Sempre que o sistema inicia, ele lê os arquivos binários. Sempre que um registro é criado, alterado ou removido, o arquivo correspondente é atualizado instantaneamente em disco.

### Sistema de Auditoria (Logs):
Através da classe `LogService`, o sistema monitora operações críticas (como cadastros, alterações e exclusões) e escreve um histórico detalhado em tempo real em um arquivo físico de texto puro (`log.txt`).

---

## 3. Uso de Inteligência Artificial (Processo de Co-Criação)

Conforme solicitado nas orientações do projeto, documentamos aqui a nossa experiência utilizando ferramentas de Inteligência Artificial (Generative AI) durante o ciclo de desenvolvimento:

1. **Refatoração de Código e Padrões:** Utilizamos a IA como um revisor de código parceiro (*Pair Programmer*) para nos ajudar a converter os repositórios que antes operavam puramente em memória RAM estática para um modelo robusto baseado em persistência binária física via arquivos `.dat`.
2. **Fechamento de CRUDs:** A IA auxiliou na identificação de lacunas nas operações de dados (como os métodos de Update e Delete que faltavam nos fluxos de tela das Views), gerando estruturas limpas de manipulação e limpeza de buffers do `Scanner`.
3. **Análise de Critérios:** Usamos a ferramenta para validar se a nossa arquitetura estava cobrindo todos os tópicos da folha de avaliação do Professor André antes da submissão final, garantindo que nenhum pilar técnico ficasse de fora.

*Nota de Transparência:* A IA atuou como uma aceleradora de produtividade e mentora sintática, mas toda a lógica de negócio, arquitetura estrutural de pacotes e regras do domínio de e-commerce foram desenhadas, validadas e testadas pela equipe.

## 4. Como Executar o Projeto

1. Certifique-se de que tem o **JDK 17** (ou superior) instalado e configurado na sua máquina.
2. Clone este repositório:
   ```bash
   git clone [https://github.com/marianapoloantonio-byte/Trabalho-Andre.git](https://github.com/marianapoloantonio-byte/Trabalho-Andre.git)

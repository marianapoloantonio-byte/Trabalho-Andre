# Trabalho em java - Sistema de E-Commerce de Jogos.

**Status do Projeto:** Em desenvolvimento 

Sistema em terminal desenvolvido em **Java** para simular uma plataforma completa de gerenciamento, compra e catálogo de jogos virtuais. Este projeto serve como avaliação prática e documentação de portfólio.

## 1. Informações Gerais e Funcionalidades

O sistema orquestra a interação entre clientes e administradores em um ambiente de loja de jogos digitais.
* **Menu do Administrador (CRUD):** Permite o cadastro, listagem detalhada e remoção de jogos do catálogo do sistema.
* **Menu do Usuário (Cliente):** Fluxo completo de criação de conta, login por e-mail, navegação pelo catálogo de jogos disponíveis, simulação de compras e gerenciamento de uma biblioteca pessoal de jogos adquiridos.
* **Sistema Financeiro:** Simulação de fluxos de checkout e transações com opções de pagamento via Cartão de Crédito, Pix e Boleto Bancário.
* **Registro de Logs:** Geração automatizada de um arquivo físico (`log.txt`) para auditoria de ações críticas no sistema.

## 2. Estrutura das Classes e Relações

O projeto foi construído aplicando fortemente os pilares da Programação Orientada a Objetos (POO):

### Herança e Abstração
* `Pessoa` é uma **Classe Abstrata** que serve como superclasse para `Usuario` e `Administrador`, compartilhando atributos comuns como nome, e-mail e ID/idade, além de definir o método abstrato `exibirPerfil()`.

### Associações, Agregações e Composições
* **Agregação (`Usuario` ➡️ `Jogos`):** A classe `Usuario` possui uma lista (`List<Jogos> biblioteca`). Trata-se de uma agregação, pois os jogos existem independentemente do usuário, mas podem fazer parte da sua coleção particular de jogos.
* **Associação (`Compra` ➡️ `Usuario` e `Jogos`):** A classe de amarração `Compra` (assim como o `CompraController`) associa uma entidade de usuário a um jogo específico no momento em que a transação financeira é validada.
* **Injeção de Dependência (`Controllers` ➡️ `Repositories`/`Services`):** As classes de controle (como `JogoController`, `UsuarioController`) utilizam os repositórios em memória e serviços de validação de forma associada para isolar as regras de negócio das interações de tela da `MenuView`.

##  Equipe e Organização

O desenvolvimento foi distribuído em três frentes principais:

* **Mariana:** Modelagem e Armazenamento (Criação de entidades, heranças e repositórios em memória como `UsuarioRepository` e `JogoRepository`).
* **Alana:** Regras de Negócio e Financeiro (Interfaces de pagamento, `BibliotecaService` e persistência de arquivos com `LogService`).
* **Yasmin:** Fluxo, Controle e Telas (Desenvolvimento da interface por terminal `MenuView`, manipulação de fluxos com `Scanner`/`switch-case`, tratamento inicial de dados nos `Controllers` e ponto de entrada na `Main.java`).

## 3. Uso de Inteligência Artificial (ChatGPT / Gemini)



## 4. Como Executar o Projeto

1. Certifique-se de ter o **JDK 17** (ou superior) instalado.
2. Clone este repositório para sua máquina local:
```bash
   git clone [https://github.com/marianapoloantonio-byte/Trabalho-Andre.git](https://github.com/marianapoloantonio-byte/Trabalho-Andre.git)

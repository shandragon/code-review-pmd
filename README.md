# Revisão de Código e Análise Estática (PMD)

Este repositório apresenta um pequeno sistema de cadastro/pedidos com **defeitos inseridos de forma proposital** para prática de:

1. **Code review manual** (leitura crítica do código);
2. **Análise estática automatizada** com **PMD**.

**ATENÇÃO:** Este projeto é um laboratório didático de revisão de código e análise estática. Ela contém defeitos inseridos de forma proposital para fins de treinamento.

## Instruções

### Como executar o projeto

Execute a classe Main.java

### Como executar o PMD

```bash
mvn pmd:pmd
```

Será gerado um relatório em target/site/pmd.html

## Estrutura

```
code-review-pmd/
├── pom.xml                 # build Maven + plugin do PMD configurado
├── pmd-ruleset.xml         # regras do PMD usadas na laboratório
├── README.md
└── src/main/java/br/edu/cerqueira/adailton/codereview/
    ├── CadastroUsuarioService.java
    ├── RelatorioVendasService.java
    ├── ValidadorPedido.java
    ├── ValidadorCPF.java
    └── Main.java
```


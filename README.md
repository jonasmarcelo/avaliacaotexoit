# Avaliação Texo IT

Contrução de uma API RESTful para possibilitar a leitura da lista de indicados e vencedores
da categoria Pior Filme do Golden Raspberry Awards

## Detalhes do projeto
O projeto foi construído com Java 11, SpringBoot 2.6.3 e banco de dados H2

## Instruções para rodar a API e Testes

1 - Baixe o projeto no GitHub no endereço: https://github.com/jonasmarcelo/avaliacaotexoit

2 - Importe o projeto na sua IDE de preferência

3 - Execute a classe principal do projeto para subir a API: AvaliacaoTexoItApplication

4 - Para obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que
obteve dois prêmios mais rápido, acesse o endpoint: localhost:8081/avaliacaotexoit/api/filme/produtoresMaiorMenorIntervaloPremio

Obs: No projeto existem dois arquivos com informações diferentes para testar o retorno da API, fique a vontade para criar outros arquivos...

### Caminho dos arquivos:

- Arquivo1: /filmes/movielist

- Arquivo2: /filmes/movielist2

5 - Para rodar os testes de integração da API, basta abrir a classe FilmeResourceTest e executar com o JUnit

# Autor
Jonas Marcelo de Carvalho







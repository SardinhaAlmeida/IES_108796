# lab2

## lab2_1
### alínea (a)
### alínea (b)

## lab2_2
**alínea (c)**
Como estou a usar o VSCode:
* download do zip disponibilizado que continha o projeto JakartaWebStarted (e pequena alteração no pom nas versões do junit)

**alínea (d)** 
* Implementei uma função igual ao exercício anterior para printar uma mensagem que depende dos parâmetros passados no URL
* corri o comando *mvn package*: deste resultou um **ficheiro .war** na pasta target do projeto.
* criei o ficheiro **docker-compose.yml** e escrevi-o com base nas indicações dadas no passo *Run the application using Docker Compose* do artigo disponibilizado.
* corri o comando *docker-compose up*

### alínea (e)
* Usando os dois URLs fornecidos no enunciado, verifiquei que o container estava a correr e que a aplicação(.war) foi corretamente "implantada" no tomcat mostrando o resultado esperado.
* De acordo com a função que implementei na alínea anterior, ao utilizar, por exemplo, o URL http://127.0.0.1:8888/JakartaWebStarter-1.0-SNAPSHOT/hello-servlet?msg=ola, é printada a mensagem ola, como esperado.
* Fazer *docker-compose down* e docker stop <container_id> para parar o container (docker ps para ver os containers que estão a correr).
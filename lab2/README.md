# lab2

## lab2_1
### alínea (a)
### alínea (b)

## lab2_2
### alínea (c)
Como estou a usar o VSCode:
* download do zip disponibilizado que continha o projeto JakartaWebStarted (e pequena alteração no pom nas versões do junit)

### alínea (d)
* Implementei uma função igual ao exercício anterior para printar uma mensagem que depende dos parâmetros passados no URL
* corri o comando *mvn package*: deste resultou um **ficheiro .war** na pasta target do projeto.
* criei o ficheiro **docker-compose.yml** e escrevi-o com base nas indicações dadas no passo *Run the application using Docker Compose* do artigo disponibilizado.
* corri o comando *docker-compose up*

### alínea (e)
* Usando os dois URLs fornecidos no enunciado, verifiquei que o container estava a correr e que a aplicação(.war) foi corretamente "implantada" no tomcat mostrando o resultado esperado.
* De acordo com a função que implementei na alínea anterior, ao utilizar, por exemplo, o URL http://127.0.0.1:8888/JakartaWebStarter-1.0-SNAPSHOT/hello-servlet?msg=ola, é printada a mensagem ola, como esperado.
* Fazer *docker-compose down* e docker stop <container_id> para parar o container (docker ps para ver os containers que estão a correr).

## lab2_3
A **Spring Boot** é uma plataforma que ajuda e permite facilitar o desenvolvimento de aplicações consoante as configurações que necessitamos. Por exemplo, este caso, através do *Spring Initializr*, é-nos **criado um projeto maven** com as características que escolhemos e com as dependencias que necessitamos.
### alínea (a)
* Criei o projeto maven utilizando o Spring Initializr, com a dependencia *Spring Web*.
* Fiz download e foi possível fazer *mvn package*
* Corri o comando *./mvnw spring-boot:run* e recebi o resultado esperado (whitelabel error page) ao aceder ao URL http://localhost:8080/

### alínea (b)
O tutorial seguido para este exercício resultou numa wep app com uma página inicial estática que muda o seu conteúdo consoante o URL.
* Em semelhança ao exercício anterior, foi criado uma projeto maven como Spring Initializr, com as dependencias *Spring Web*, *Thymeleaf* e *Spring Boot DevTools*.
* Criei a classe java **GreetingControler.java**:
    - *@Controller*: trata os HTTP requests
    - *@GetMapping("/greeting")* : mapeia os HTTP *GET requests* para /greeting para o método greeting() 
    - *@RequestParam(name="name", required=false, defaultValue="World")*: o parâmetro name é opcional e tem o valor por defeito "World"

* Criei o ficheiro **greetings.html** : 
- **ThymeLeaf** é um mecanismo de *templates* que permite a criação de *páginas HTML dinâmicas*.
    - Neste caso, o Thymeleaf analisa neste templalte o *th:text* para nesse local colocar o valor do parâmetro *name* que foi definido Controler.

* Adicionei código no ficheiro *application.properties* (na pasta resources) para definir a porta do servidor web (9000).
* Corri o comando *./mvnw spring-boot:run* e recebi o resultado esperado (whitelabel error page) ao aceder ao URL http://localhost:9000/greeting (em alternativa, correr os comandos *./mvnw clean package* e *java -jar -Dserver.port=9000 target/serving-web-content-0.0.1-SNAPSHOT.jar*).
    - Ao aceder ao URL http://localhost:9000/greeting?name=Joao, o resultado foi a página com a mensagem "Hello, Joao!".


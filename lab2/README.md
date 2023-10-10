# lab2

## lab2_1
Docker Containers são utilizados para implementar ambientes virtualizados para qualquer tipo de serviço, ao contrário dos **Servlet Containers** que fornecem um *ambiente de execução* para executar código Java relacionado com o **servidor web do lado do servidor** (sem relação com virtualização).

### alínea (a)
Ao seguir os passos do tutorial (exceto o passo 3.4), resultou um **servlet** a correr dentro de um **embedded jetty server**.

### alínea (b)
* Utilizando a função *getParameter(parameter)* com o objeto request, implementei a funcionalidade onde se o utilizador passar algum parâmetro no URL, é printada essa mesma mensagem. Caso contrário, é printada a mensagem "Hello World!".

Assim, pude observar o **Servlet** a funcionar: Este consiste numa classe Java que corre no servidor e recebe pedidos do cliente, processa-os e responde.

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
* Criei a classe java *GreetingControler.java*:
    - **@Controller**: trata os HTTP requests
    - **@GetMapping("/greeting")** : mapeia os HTTP *GET requests* para /greeting para o método greeting() 
    - **@RequestParam(name="name", required=false, defaultValue="World")**: o parâmetro name é opcional e tem o valor por defeito "World"

* Criei o ficheiro *greetings.html* : 
    - **ThymeLeaf** é um mecanismo de *templates* que permite a criação de *páginas HTML dinâmicas*.
        - Neste caso, o Thymeleaf analisa neste templalte o *th:text* para nesse local colocar o valor do parâmetro *name* que foi definido Controler.

* Adicionei código no ficheiro *application.properties* (na pasta resources) para definir a porta do servidor web (9000).
* Corri o comando *./mvnw spring-boot:run* e recebi o resultado esperado ao aceder ao URL http://localhost:9000/greeting (em alternativa, correr os comandos *./mvnw clean package* e *java -jar target/serving-web-content-0.0.1-SNAPSHOT.jar*) : uma página com a mensagem "Hello, World!".
    - Ao aceder ao URL http://localhost:9000/greeting?name=Joao, o resultado foi a página com a mensagem "Hello, Joao!".

* Por fim criei o ficheiro *index.html* na pasta static com o código html que funciona como uma home page e permite a navegação entre esta e a página principal criada anteriormente.

### alínea (c)
Para este exercício, seguindo o tutorial resultou uma **RESTful web service**, onde criei um **REST endpoint** que recebe um pedido HTTP GET e retorna um **objeto JSON**.
* Criei uma classe *Greeting.java* que serve para modelar a apresentação dos dados.
* Criei uma nova classe *GreetingRestController.java*:
    - **@RestController**: (combina *@Controller* e *@ResponseBody*) com esta anotação a classe funciona como um controler mas todos os seus métodos retornam um objeto do domínio(classe descrita acima) ao invés de uma view.
    - **@GetMapping("/restgreeting")** (diferente do URL da alínea anterior)

Assim, nesta alínea o resultado vai ser diferente da anterior, pois o que é retornado é um objeto JSON e não uma página HTML:
* Corri o comando *./mvnw spring-boot:run* e recebi o resultado esperado ao aceder ao URL http://localhost:9000/restgreeting : um objeto JSON com a mensagem "Hello, World!".
    - Ao aceder ao URL http://localhost:9000/restgreeting?name=Sara, o resultado foi um objeto JSON com a mensagem "Hello, Sara!".

* É possível aceder ao endpoint pelo terminal usando o comando *curl -v http://localhost:9000/restgreeting*

## lab2_4
Neste exercício, o objetivo é criar uma **REST API** que apresenta ao utilizador frases random de filmes ou séries.
* Para começar, segui os mesmos passos do exercício anterior para começar a criar a web service.
* Tratamento dos dados:
    * Criei uma classe *Shows_Quotes.java* onde para cada filme ou série vai existir uma lista de quotes associadas.
    * Na classe *MoviesController.java* criei um HashMap onde a key é o id do filme ou série e o value é um objeto da classe *Shows_Quotes.java*.
* Criei as classes *Quote.java*, *Shows.java* e *Quote_Show_.java* para modelar a apresentação dos dados consoante cada endpoint, respetivamente.
* A classe principal, **MoviesController.java** começa com a anotação *@RestControler* e tem um método GET, para cada endpoint, que retorna um objeto JSON:
    * **@GetMapping("/api/quote")** : Utiliza a função random para escolher um id random e depois retorna uma frase random da lista de quotes do filme ou série com esse id.
    * **@GetMapping("/api/shows")** : retorna a lista de filmes ou séries utilizando o HashMap criado anteriormente.
    * **@GetMapping("/api/quotes")** : necessita de um parâmetro show ,ou seja, pode ser acedido através do URL http://localhost:9000/api/quotes?show=1, por exemplo, onde o parâmetro show é o id do filme ou série e retorna a lista de quotes desse filme ou série, recorrente ao HashMap criado anteriormente.

* Em adição, defini a porta do servidor web (9001) no ficheiro *application.properties* e criei uma home page estática através do ficheiro *index.html* que pode ser acedida pelo URL http://localhost:9001/.
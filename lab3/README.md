# lab3

## lab3_1

### alínea (b)
Segui os exercícios do tutorial, bem como analisei o código do GitHub de forma a completar o objetivo de criar uma **aplicação CRUD** com Spring Boot e Thymeleaf. É possível ver o resultado final acedendo a localhost:8080 no browser.

* *foi necessário mudar o nome da clase User para MyUser*.

### alínea (c)

* A classe “UserController” recebe uma instância de “userRepository” através do construtor e este novo user repository é instanciado devido à anotação **@Autowired**.

* Os métodos invocados no objeto "userRepository" pela classe "UserController" são *save()*, *findAll()*, *findById()* e *delete()*.
Estes métodos são definidos pela classe **CrudRepository** à qual a classe "UserRepository" faz *extends*.

* Os dados estão a ser guardados numa base de dados **h2**, definida no ficheiro *pom.xml*. Esta base de dados é uma *base de dados em memória*, ou seja, quando o servidor é desligado, os dados são perdidos.

* A regra do "not empty" endereço de email está definida na classe *MyUser* através da anotação **@NotBlank**.

### alínea (d)

* Foi necessários alterar a classe MyUser adicionando a variável Phone no construtor, adicionando numa anotação @NotBlank e criar os respetivos métodos get e set.
* Adicionar também um campo para o phone no ficheiro add-user.html.

* A anotação **@NotBlank** é usada para marcar um campo como uma *sequência de caracteres não nulos*, ou seja, que deve conter pelo menos um caracter que não seja um espaço em branco.

## lab3_2
### alínea (a)
Comecei por iniciar a base de dados dentro de um container docker como indicado no guião, correndo o seguinte comando:

```bash
$ sudo docker run --name mysql5 -e MYSQL_ROOT_PASSWORD=secret1 -e MYSQL_DATABASE=demo -e MYSQL_USER=demo -e MYSQL_PASSWORD=secret2 -p 33060:3306 -d mysql/mysql-server:5.7
```
* O container terá o nome mysql5, a password do root é secret1, o nome da base de dados é demo, o nome do utilizador é demo e a password é secret2. O container está a correr na porta 33060.

### alínea (b) -> (f)
* Segui as indicações do guião tomando ao mesmo tempo por base o tutorial indicado.

* O ficheiro *application.properties* utilizado foi o apresentado no guião.

### alínea (g)
* Para testar a aplicação, é necessário iniciar o container com *docker start mysql* e correr a aplicação.

* Para testar os métodos da REST API, utilizei a extensão do VS Code *Thunder Client*:

    -Exemplo de Insert:
        -![POST](prints/Screenshot from 2023-11-02 18-23-54.png)

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
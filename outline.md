
## Week 1
### 04/10/2021 - Course introduction
* Syllabus, teaching methodology and bibliography.
  * Evaluation
  * Resources

### 06/10/2021 - Mutability and imutability
* Mutability and imutability, revisited
  * Tradeoffs between both approaches
* Classes, methods and properties, revisited
* Introduction to automated tests using JUnit
* Live demo: Implementation of a _persistent stack_ (`PersistentStack`) and a _mutable stack_ (`MutableStack`)
* Documentation:
  * [Classes](https://kotlinlang.org/docs/classes.html)
  * [Properties](https://kotlinlang.org/docs/properties.html)
* For reference: 
  * [Video lecture (in Portuguese)](https://www.youtube.com/watch?v=xQeBuUmZB5Q&list=PL8XxoCaL3dBh9aZK9m1WxCC-EWHx4SXuH&index=1)

## Week 2
### 11/10/2021 - Dealing with mutable state
* The problem with mutable state
* Dealing with mutable state: 
  * Encapsulation and information hiding
* Design by contract as a means to increase software's robustness
  * Pre-conditions, post-conditions and invariants
  * Automatic tests as an alternative to post-conditions
* Documentation:
  * [Encapsulation](https://en.wikipedia.org/wiki/Encapsulation_(computer_programming))
  * [Seriouslsy Good Software, by Marco Faella - Chapter 5](https://www.manning.com/books/seriously-good-software?gclid=Cj0KCQjw5JSLBhCxARIsAHgO2Sfco_i6rtgxyOsDnsCbtP4RbxLIkcF0w_QEdZYvHpoc5trlc0rk_sYaApJ-EALw_wcB)
* For reference: 
  * [Video lecture (in Portuguese)](https://www.youtube.com/watch?v=MVNYKxsWh-4&list=PL8XxoCaL3dBh9aZK9m1WxCC-EWHx4SXuH&index=2)

### 13/10/2021 - The Billboard Application
* Live coding session: design and implementation of the Billboard console application
  * Suported commands: post message, get all messages, get messages from a given author, exit
  * Design principles:
    * Simplicity: [KISS](https://en.wikipedia.org/wiki/KISS_principle)
    * Valorization of imutability
    * Testability
    * Effecive use Kotlin's type system
      * [Design Smell: Primitive obsession](https://blog.ploeh.dk/2011/05/25/DesignSmellPrimitiveObsession/)
* ~~Introduction to GCP's Firestore~~ (we will be using MongoDB instead. See lecture of 20/10/2021)
  * ~~[Overview of the data model](https://firebase.google.com/docs/firestore/data-model)~~
  * ~~[Supported data types](https://firebase.google.com/docs/firestore/manage-data/data-types)~~
  * ~~Considerarions on the inadequacy of the chosen approach to be used in production scenarios~~
    * ~~[Server client libraries](https://firebase.google.com/docs/firestore/client/libraries#server_client_libraries)~~
  * ~~Setup: [Quickstart using a server client library](https://cloud.google.com/firestore/docs/quickstart-servers)~~
* Complementary documentation:
  * [Designing with types: Introduction](https://fsharpforfunandprofit.com/posts/designing-with-types-intro/)
  * [Designing with types: Single case union types](https://fsharpforfunandprofit.com/posts/designing-with-types-single-case-dus/)
  * [Designing with types: Making illegal states unrepresentable](https://fsharpforfunandprofit.com/posts/designing-with-types-making-illegal-states-unrepresentable/)
* For reference: 
  * [Video lecture (in Portuguese)](https://www.youtube.com/watch?v=eCp0hZ9jxJ0&list=PL8XxoCaL3dBh9aZK9m1WxCC-EWHx4SXuH&index=3)

## Week 3
### 18/10/2021 - Dynamic binding
* Dynamic binding through interface implementation
  * [The interface construct as a means to define an abstraction](https://kotlinlang.org/docs/interfaces.html)
  * [Interface implementation (i.e. method override)](https://kotlinlang.org/docs/interfaces.html#implementing-interfaces)
* Automatic tests
  * Motivation
  * Consequences on the software's design: making dependencies explicit
  * [Test doubles: introduction](https://martinfowler.com/bliki/TestDouble.html)
* For reference: 
  * [Video lecture (in Portuguese)](https://www.youtube.com/watch?v=zI3h7YgYEMU&list=PL8XxoCaL3dBh9aZK9m1WxCC-EWHx4SXuH&index=4)

### 20/10/2021 - The Billboard Application, continued
* Live coding session: design and implementation of the Billboard console application
* Revisited design principles
  * [DRY - Don't Repeat Yourself](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself)
  * Design smell: [primitive obsession](https://blog.ploeh.dk/2011/05/25/DesignSmellPrimitiveObsession/)
* Mongo DB as an alternative do Firestore
  * Motivation
* For reference: 
  * [Video lecture (in Portuguese)](https://www.youtube.com/watch?v=G_emzTPBVVQ&list=PL8XxoCaL3dBh9aZK9m1WxCC-EWHx4SXuH&index=5) 
  * [Local Mongo DB instalation](https://docs.mongodb.com/manual/administration/install-community/)
  * [Getting started with Mongo DB Atlas](https://www.mongodb.com/cloud/atlas/register)
  * [Mongo DB Compass](https://www.mongodb.com/products/compass)
  * [Using KMongo][https://litote.org/kmongo/quick-start/#with-the-sync-driver]

## Week 4
### 25/10/2021 - The Billboard Application, continued
* Creating an infrastructure for dispatching the execution of the application's commands
  * Using an associative container that associates user entered strings to the corresponding command implementation
* Implementation of the supported commands:
  * [Using an Object-Oriented style: interface and implementing classes](https://kotlinlang.org/docs/interfaces.html)
    * [Object expressions](https://kotlinlang.org/docs/object-declarations.html)
    * [Overloading invoke operator](https://kotlinlang.org/docs/operator-overloading.html#invoke-operator)
  * Using functions and partial function application
* For reference: 
  * [Video lecture (in Portuguese)](https://www.youtube.com/watch?v=75DGDRN68lM&list=PL8XxoCaL3dBh9aZK9m1WxCC-EWHx4SXuH&index=6)

### 27/10/2021 - Laboratory 
* Practical class dedicated to the course's project

## Week 5
### 01/11/2021 - National holliday

### 03/11/2021 - Laboratory
* Practical class dedicated to the course's project

## Week 6


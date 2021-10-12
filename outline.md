
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

### 13/10/2021 - Billboard Application
* Live coding session: design and implementation of the Billboard console application
  * Suported commands: post message, get all messages, get messages from a given author, exit
  * Design principles:
    * Simplicity: [KISS](https://en.wikipedia.org/wiki/KISS_principle)
    * Valorization of imutability
    * Testability
    * Effecive use Kotlin's type system
      * [Design Smell: Primitive obsession](https://blog.ploeh.dk/2011/05/25/DesignSmellPrimitiveObsession/)
* Introduction to GCP's Firestore
  * [Overview of the data model](https://firebase.google.com/docs/firestore/data-model)
  * [Supported data types](https://firebase.google.com/docs/firestore/manage-data/data-types)
  * Considerarions on the inadequacy of the chosen approach to be used in production scenarios
    * [Server client libraries](https://firebase.google.com/docs/firestore/client/libraries#server_client_libraries)
  * Setup: [Quickstart using a server client library](https://cloud.google.com/firestore/docs/quickstart-servers)
* Complementary documentation:
  * [Designing with types: Introduction](https://fsharpforfunandprofit.com/posts/designing-with-types-intro/)
  * [Designing with types: Single case union types](https://fsharpforfunandprofit.com/posts/designing-with-types-single-case-dus/)
  * [Designing with types: Making illegal states unrepresentable](https://fsharpforfunandprofit.com/posts/designing-with-types-making-illegal-states-unrepresentable/)
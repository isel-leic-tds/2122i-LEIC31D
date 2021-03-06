
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
  * [Video lecture (in Portuguese)](https://youtu.be/xQeBuUmZB5Q)

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
  * [Video lecture (in Portuguese)](https://youtu.be/MVNYKxsWh-4)

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
  * [Video lecture (in Portuguese)](https://youtu.be/eCp0hZ9jxJ0)

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
  * [Video lecture (in Portuguese)](https://youtu.be/zI3h7YgYEMU)

### 20/10/2021 - The Billboard Application, continued
* Live coding session: design and implementation of the Billboard console application
* Revisited design principles
  * [DRY - Don't Repeat Yourself](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself)
  * Design smell: [primitive obsession](https://blog.ploeh.dk/2011/05/25/DesignSmellPrimitiveObsession/)
* Mongo DB as an alternative do Firestore
  * Motivation
* For reference: 
  * [Video lecture (in Portuguese)](https://youtu.be/G_emzTPBVVQ) 
  * [Local Mongo DB instalation](https://docs.mongodb.com/manual/administration/install-community/)
  * [Getting started with Mongo DB Atlas](https://www.mongodb.com/cloud/atlas/register)
  * [Mongo DB Compass](https://www.mongodb.com/products/compass)
  * [Using KMongo](https://litote.org/kmongo/quick-start/#with-the-sync-driver)

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
  * [Video lecture (in Portuguese)](https://youtu.be/75DGDRN68lM)

### 27/10/2021 - Laboratory 
* Practical class dedicated to the course's project

## Week 5
### 01/11/2021 - National holliday

### 03/11/2021 - Laboratory
* Practical class dedicated to the course's project

## Week 6
### 08/11/2021 - Single Responsibility Principle
* Considerations on software design
  * Design principles identified thusfar:
    * [Don't Repeat Yourself (DRY)](http://wiki.c2.com/?DontRepeatYourself)
    * Single Responsibility Principle (SRP)
      * [@wiki.c2](https://wiki.c2.com/?SingleResponsibilityPrinciple)
      * [@Clean.Coder.Blog](https://blog.cleancoder.com/uncle-bob/2014/05/08/SingleReponsibilityPrinciple.html)
  * Critical analysis of the Billboard Application in light of these principles
* Kotlin language constructs:
  * [Interface](https://kotlinlang.org/docs/interfaces.html)
  * [Object expressions](https://kotlinlang.org/docs/object-declarations.html)
  * [SAM interfaces and SAM conversions](https://kotlinlang.org/docs/fun-interfaces.html)
* For reference: 
  * [Video lecture (in Portuguese)](https://youtu.be/lsxpggQ_QpE)

### 10/11/2021
* Part 1 - Sum types in Kotlin
  * Refactoring commands design to no longer violate the Single Responsibility Principle
    * Using enum classes and asserting its inadequacy to the problem at hand
    * Using a closed hierarchy (Kotlin's approach to sum types)
  * Kotlin language constructs:
    * [Object declarations](https://kotlinlang.org/docs/object-declarations.html#object-declarations-overview)
    * [Enum classes](https://kotlinlang.org/docs/enum-classes.html)
    * [Sealed classes](https://kotlinlang.org/docs/sealed-classes.html)
* Part 2 - Laboratory: Practical class dedicated to the course's project 
* For reference:
  * [Video lecture (in Portuguese)](https://youtu.be/VgEXezI-Zwo)

## Week 7
### 15/11/2021 - The Billboard Application: error handling
* Considerations on software design 
  * Error handling: errors as a first class concept in domain modeling
    * [Exceptions in Kotlin](https://kotlinlang.org/docs/exceptions.html)
  * Revisited design principle:
    * [DRY - Don't Repeat Yourself](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself)
* For reference:
  * [Video lecture (in Portuguese)](https://youtu.be/fZoTteiM5Sg)

### 17/11/2021 - Laboratory 
* Practical class dedicated to the course's project

## Week 8
### 22/11/2021
* There was no lecture

### 24/11/2021 - The Billboard Application: conclusion and critical analisys
* Considerations on software design 
  * Using an OOP design style (i.e. through inheritance)
  * Using a style based in function composition (i.e. through higher-order functions and partial application)
* Testing the Billboard Application design in the light of the [Open-Closed Principle](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle)
  * By adding a new command (i.e. the user command, which displays the id of the current user)
  * By adding support for logging command execution time
* Design patterns: concept and purpose
  * [The Decorator design pattern](https://en.wikipedia.org/wiki/Decorator_pattern), applied to the Billboard Application
  * [The Command design pattern](https://en.wikipedia.org/wiki/Command_pattern), applied to the Billboard Application
  * Comparison with the function based approach: function composition =)
* For reference:
  * [Video lecture (in Portuguese)](https://youtu.be/8mUWK3p52w4)

## Week 9
### 29/11/2021 - Desktop Compose: Introduction
* Desktop Compose: [getting started](https://github.com/JetBrains/compose-jb/tree/master/tutorials/Getting_Started)
* Programming model
  * @Composable functions: State -> @Composable -> UI
  * First rule of @Composable functions: A @Composable function can only be called from other @Composable functions (they are "colored")
* Execution model
  * For @Composable functions
  * For UI event handling
  * Threading model for event-driven execution environments
* For reference:
  * [IntelliJ plugin for @Composable preview](https://plugins.jetbrains.com/plugin/16541-compose-multiplatform-ide-support)
  * [What color is your function](https://journal.stuffwithstuff.com/2015/02/01/what-color-is-your-function/)
  * [Video lecture (in Portuguese)](https://youtu.be/Xfocm9cqpjo)

### 01/12/2021 - National Holliday

## Week 10
### 06/12/2021 - Desktop Compose: Layouts
* Desktop Compose: Composing a UI
  * Displaying [text](https://foso.github.io/Jetpack-Compose-Playground/foundation/text/), [images](https://foso.github.io/Jetpack-Compose-Playground/foundation/image/) resources and background colors
  * [Layouts](https://developer.android.com/jetpack/compose/layouts/basics)
* For reference:
  * [Compose Playground](https://foso.github.io/Jetpack-Compose-Playground/)
  * [Video lecture (in Portuguese)](https://youtu.be/ffJuOL6ZH90)

### 08/12/2021 - National Holliday

## Week 11
### 13/12/2021 - Desktop Compose: mental model
* Desktop Compose: mental model
  * Rules for composable functions: purpose and motivation
  * [Thinking in Compose](https://developer.android.com/jetpack/compose/mental-model)
* Desktop Compose: [state managment](https://developer.android.com/jetpack/compose/state#state-and-composition)
  * Building blocks: `remember` and `mutableStateOf`
* For reference:
  * [Video lecture (in Portuguese)](https://youtu.be/RKis8f2-R74)

### 15/12/2021 - The Model View Controller pattern
* Designing an interactive application with Desktop Compose: Tic-Tac-Toe
  * The big idea: the [Model View Controller](http://wiki.c2.com/?ModelViewController) design pattern
    * Purpose and motivation
    * Dependencies' constraints
    * Data flow
  * Another big ideia: valuing of immutability 
  * The not that big ideas =) (but essential, nevertheless)
    * Minimalistic public surface of software artifacts
    * Hiding implementation details and thereby promoting modularity
    * Increasing expressiveness through type extensions
* Kotlin language constructs:
  * [Operator overloading](https://kotlinlang.org/docs/operator-overloading.html)
  * [Extensions](https://kotlinlang.org/docs/extensions.html)
  * [Equality](https://kotlinlang.org/docs/equality.html)
* For reference:
  * [Video lecture (in Portuguese)](https://youtu.be/fEIYisNhr0E)
  * [Latency Numbers Every Programmer Should Know](https://gist.github.com/jboner/2841832)

## Week 12
### 03/01/2022 - Software organization
* Designing an interactive application with Desktop Compose: Tic-Tac-Toe (continued)
* The [Model View Controller - (MVC)] (http://wiki.c2.com/?ModelViewController) design pattern, revisited
* Software organization:
  * [Layered Architecture](https://en.wikipedia.org/wiki/Multitier_architecture) (sometimes also called multitier architecture)
  * MVC, a pattern for the Presentation Layer
* Accessing data: the Repository design pattern
* Patterns for representing data
  * [Domain Model](https://martinfowler.com/eaaCatalog/domainModel.html)
  * [Data Transfer Object](https://martinfowler.com/eaaCatalog/dataTransferObject.html)
* For reference:    
  * [Video lecture (in Portuguese)](https://youtu.be/-pgmrXhLEvE)
  * [Other Description of the DTO pattern](https://www.baeldung.com/java-dto-pattern)

### 05/01/2022 - The Tic-Tac-Toe application with Desktop Compose
* Designing an interactive application with Desktop Compose: Tic-Tac-Toe (continued)
* A mental model for Desktop Compose, continued
  * Stateless @Composables
  * Statefull @Composables
    * Characterizing state: application state vs presentation state
* Considerations on the organization of the presentation layer implemented using Desktop Compose   
* For reference:    
  * [Video lecture (in Portuguese)](https://youtu.be/q6jtfl3ij7Q)


## Week 13
### 10/01/2022 - Execution model in Desktop Compose applications
* Execution in applications that use Desktop Compose, revisited
  * Threading model for UI event handling: restrictions and consequences
* Introduction to Kotlin's Coroutines
  * Purpose and motivation
  * [Coroutines basics](https://kotlinlang.org/docs/coroutines-basics.html)
  * Suspending functions
  * Coroutine builders, corountine scopes and dispatchers (introduction)
* For reference:    
  * [Video lecture (in Portuguese)](https://youtu.be/JoeC8PIrvGc)
  * [Conversation with Pedro F??lix about coroutines (in Portuguese)](https://youtu.be/K_fqNQz3UoU)


### 12/01/2022 - Fixing the Tic-Tac-Toe application
* Fixing the Tic-Tac-Toe application
  1. Dispaching I/O to other threads using the coroutines' programming model
  2. Adding support for periodic refresh
* Desktop Compose programming model, continued
  * Building blocks
    * [rememberCoroutineScope](https://developer.android.com/jetpack/compose/side-effects#remembercoroutinescope)
    * @Composable [LaunchedEffect](https://developer.android.com/jetpack/compose/side-effects#launchedeffect)
* Kotlin language constructs:
  * [Qualified `this@` expressions](https://kotlinlang.org/docs/this-expressions.html)
* For reference:    
  * [Video lecture (in Portuguese)](https://youtu.be/j6YlvcF_WdQ)

## Week 14
### 17/01/2021 - Laboratory 
* Practical class dedicated to the course's project

### 19/01/2021 - Laboratory 
* Practical class dedicated to the course's project

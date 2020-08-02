<h3>Design Patterns</h3>

What are Design patterns?<br>
According to Wikipedia, a software design pattern is a general, reusable solution to a commonly occurring problem
 within a given context in software design. Each pattern is like a blueprint that you can customize to solve a particular design problem in your code.
<br><br>
Why Design Patterns ?<br>
In short, they are used so that developers do not need to reinvent the wheel. <br>
Detailed answer - While programming, you might come across some code design problems which occur frequently. Some of
 these problems already have a well-defined solution which is generally more flexible, modular & abstract.

Advantages of Design Patterns:<br>
<ul>
<li>Design patterns make your lives easy as a programmer as these make possible you to get benefit from the experience
 and knowledge of your predecessors those have worked on the same type of project .Thus make it easy for further development of the applications.</li>
<li>Appropriate design patterns used in development of applications make development fast and easily documented.</li>
<li>The benefit of design patterns lies in reusability and extensibility of the already developed applications.</li>
<li>Design patterns make your lives easy as a programmer as these make possible you to get benefit from the experience.</li>
<li>Design patterns are more sophisticated and advance approaches than basic data structures such as arrays, linked
 lists, and binary trees.</li>
<li>All design patterns use self-descriptive naming conventions and a design pattern based name of program objects
 captures a basic idea about the working and use of that particular object.</li>
</ul>

Disadvantages of Design Patterns:<br>
<ul>
<li> There is a learning curve required.</li>
<li> The overall software development process has to be modified to take design patterns into account. So, 
integrating patterns into a software development process is a human-intensive activity.</li>
<li>Not guaranteed to solve all the problems.</li>
</ul>

Design Patterns can be broadly divided into three categories:<br>
<ol><li>Creational -> These patterns deal with how Objects are created. You do not have to write large, complex code to
 instantiate an object. It does this by having the subclass of the class create the objects. The new operator is often 
 considered harmful as it scatters objects all over the application. Over time it can become challenging to change an 
 implementation because classes become tightly coupled. Creational Design Patterns address this issue by decoupling
  the client entirely from the implementation process. Types of Creational Patterns:
 <ul>
 <li>Builder Pattern</li>
 <li>Prototype Pattern</li>
 <li>Singleton Pattern</li>
 <li>Object Pool Pattern</li>
 <li>Factory Pattern</li>
 <li>Abstract Factory Pattern</li>
 </ul>
</li>
<li>Structural -> Structural Design Patterns are Design Patterns that ease the design by identifying a simple way to
 realize relationships between entities. They are concerned with how classes and objects can be composed, to form larger
  structures. These patterns focus on how the classes inherit from each other and how they are composed from other
   classes. Types of Structural Patterns:
 <ul>
 <li>Adapter Pattern</li>
 <li>Bridge Pattern</li>
 <li>Composite Pattern</li>
 <li>Decorator Pattern</li>
 <li>Facade Pattern</li>
 <li>Flyweight Pattern</li>
 <li>Private Class Pattern</li>
 <li>Proxy Pattern</li>
 </ul>
</li>
<li>Behavioral -> Behavioral patterns describe interactions between objects and focus on how objects communicate with
 each other. Behavioral patterns are concerned with algorithms and the assignment of responsibilities between objects
 . Behavioral patterns describe not just patterns of objects or classes but also the patterns of communication between them. 
 That means the implementation and the client should be loosely coupled in order to avoid hard coding and dependencies.                                                                                                                            
Types of Behavioral Patterns:
 <ul>
 <li>Interpreter Pattern</li>
 <li>Template Pattern</li>
 <li>Chain of Responsibility Pattern</li>
 <li>Command Pattern</li>
 <li>Iterator Pattern</li>
 <li>Mediator Pattern</li>
 <li>Memento Pattern</li>
 <li>Observer Pattern</li>
 <li>State Pattern</li>
 <li>Strategy Pattern</li>
 <li>Visitor Pattern</li>
 <li>Null Object Pattern</li>
 </ul>
</li>
</ol>


Things to remember<br>
<ul>
<li><b>Note that patterns do not magically improve the quality of your code.</b></li>
<li><b>Excessive use leads to over-engineering.</b></li>
</ul>

I will quote an answer on a StackOverflow question on "When to use and when not to use"
Always follow KISS first, patterns later, maybe much later. A
 pattern is a state of mind, mostly. 
Don't ever try to force your code into a specific pattern, rather notice which patterns start to crystalize out of
 your code and help them along a bit.
Deciding "ok, I'm going to write a program that does X using pattern Y" is a recipe for disaster. It might work for
 hello-world class programs fit for demonstrating the code constructs for patterns, but not much more.
# Spring 5.0 Cookbook
This is the code repository for [Spring 5.0 Cookbook](https://www.packtpub.com/application-development/spring-50-cookbook?utm_source=github&utm_medium=repository&utm_campaign=9781787128316), published by [Packt](https://www.packtpub.com/?utm_source=github). It contains all the supporting project files necessary to work through the book from start to finish.
## About the Book
The Spring framework has been the go-to framework for Java developers for quite some time. It enhances modularity, provides more readable code, and enables the developer to focus on developing the application while the underlying framework takes care of transaction APIs, remote APIs, JMX APIs, and JMS APIs.

The upcoming version of the Spring Framework has a lot to offer, above and beyond the platform upgrade to Java 9, and this book will show you all you need to know to overcome common to advanced problems you might face while developing in Spring 5.0.

It aims to provide a compendium of all code recipes related to building applications using the new Spring 5.0 Framework. Each recipe will showcase some old and new issues and solutions, right from configuring Spring 5.0 container to testing its components. Most importantly, the book will highlight concurrent processes, asynchronous MVC and reactive programming using Reactor Core APIs. Aside from the core components, this book will also include integration of third-party technologies that are mostly needed in building enterprise applications.

By the end of the book, the reader will not only be well versed with the essential concepts of Spring, but will also have mastered its latest features in a solution-oriented manner.
## Instructions and Navigation
All of the code is organized into folders. Each folder starts with a number followed by the application name. For example, Chapter02.



The code will look like the following:
```
public Set<String> getDistinctNames(){
    Function<Employee,String> allNames = (e) -> e.getFirstName();
    Set<String> setNames = employeeDaoImpl.getEmployees()
    .stream()
    .filter((a) -> a.getAge() > 25)
    .map(allNames)
    .collect(Collectors.toCollection(HashSet::new));
    return setNames;
}
```

Firstly, this book is intended for readers who have a background at least in Java SDK programming. This book does not cover anything about how to start dealing with Java as a language. Secondly, each chapter contains recipes that can be developed using STS Eclipse 3.8 and can be executed using Apache Tomcat 9.x and the Reactor Netty server. The following are the required tools and libraries needed to perform the recipes in this book:

Any machine with at least 4 GB of RAM
Java 1.8
STS Eclipse 3.8
Apache Tomcat 9.x
OpenSSL for Windows
MySQL 5.7
MongoDB 3.2
RabbitMQ 3.6
Erlang 9.0
Apache Couchdb 2.1.0
Docker Toolbox for Windows
Google Chrome or Mozilla Firefox browser

Other versions of these requirements will not be covered in this book.

## Related Products
* [Learning Spring 5.0](https://www.packtpub.com/application-development/learning-spring-50?utm_source=github&utm_medium=repository&utm_campaign=9781787120341)

* [Getting started with Spring 5.0 [Video]](https://www.packtpub.com/web-development/getting-started-spring-50-video?utm_source=github&utm_medium=repository&utm_campaign=9781787288607)

* [Mastering Spring 5.0](https://www.packtpub.com/application-development/mastering-spring-50?utm_source=github&utm_medium=repository&utm_campaign=9781787123175)

### Suggestions and Feedback
[Click here](https://docs.google.com/forms/d/e/1FAIpQLSe5qwunkGf6PUvzPirPDtuy1Du5Rlzew23UBp2S-P3wB-GcwQ/viewform) if you have any feedback or suggestions.

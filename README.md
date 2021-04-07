# Electronics Shop Web

Simple web application written in Java using JavaEE for Basics of Web Applications classes at TUL. 

Main focus of this app was to learn concepts like: 
* MVC model
* Component-Container architecture
* Inversion of control (IOC)
* Web Application Scopes (Request, Session, Application)
* CDI
* Data validaion
* Declarative security
* Programmatic security
* REST API

Data model is a simplified version of  [OOP-final-project](https://github.com/pantczak/OOP-final-project) data model.

## Important info about branches
* ```main``` branch contains basic MVC app with no security ( it was used to learn first 6 concepts)
* ```security_and_auth``` branch focuses on declarative security (i.a. file relam on payara server, security constraints, security mapping)
* ```rest``` branch focuses on programmatic security (Soteria JSR-375) and REST API

## Used technologies
* Java 11
* JavaEE/JakartaEE 8
* Payara server

## Other info
There are two other repositories that are based on this one:
* [ElectronicsShopEE](https://github.com/pantczak/ElectronicsShopEE) - ElectronicsShopWeb rewritten to try other technologies (i.a. BootsFaces), with more focus on GUI as it was not important for this app (***PROJECT UNDER DEVELOPMENT***)
* [ElectronicsShopHexed](https://github.com/pantczak/ElectronicsShopHexed) - ElectronicsShopWeb rewritten to use hexagonal architecture (microservices) with focus on testing (***PROJECT UNDER DEVELOPMENT***)

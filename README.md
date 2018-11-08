# Dreambooks - book catalog 
Simple book catalog app with an admin panel created with Spring MySQL Bootstrap

## Table of contents
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Live](#live)
* [Status](#status)
* [Inspiration](#inspiration)

## Technologies 
Project is created with:
* Java 8
* Spring (Boot, MVC, Security, Data)
* Lombok
* Thymeleaf
* Bootstrap 4
* JavaScript
* MySQL
* Mockito


## Setup 
1. After you clone the project open it with you favorite IDE (I work with Intellij)
2. Create a database schema in your MySQL Workbench
3. Go to application.properties
    1. Change spring.datasource.url=jdbc:mysql://localhost:3306/**YOURSCHEMANAME**?useSSL=false
    2. Set up spring.datasource.username=**LOGIN**
    3. Set up spring.datasource.password=**PASSWORD**
    4. Change spring.jpa.hibernate.ddl-auto to **create**
    5. **Remove #** form spring.datasource.initialization-mode=always
 4. Run application


## Features
* Main Page
    * Every user have his own bookmark
    * You can search book by title
    * You can search book by category
    * After login (USER) you can 
        * Add book to bookmark
        * Delete book from bookmark 
* Admin Panel
    * Dashboard
        * Display number of current books, user, categories
        * Display only five new users in table
        * You can add new book, category, user
    * Books
        * Search book by title 
        * Update/Delete book
    * Categories
        * Search category by description
        * Update/Delete book
        * Display books assigned to categories
    * Users
        * Search user by last name
        * Update/Delete user
            * Unactive user 
            * Change role
            

## Live 
You can try the application live here: 
<https://dreambookss.herokuapp.com/>

**Please note that this is a free version of the database and the disk space is only 5 MB**

Admin 
```
login: root@localhost
password: admin123
```

## Status
Project is: in progress 
* I still have to work on the responsiveness of the site, it is terribly bad :)

## Inspiration
Security Application inspired by [Gustavo Ponce](https://github.com/gustavoponce7)

Admin Panel inspired by [Brad Traversy](https://github.com/bradtraversy)

## Contact
Created by [JakubWylezek](https://github.com/JakubWylezek) - feel free to contact me!

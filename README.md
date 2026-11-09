# Lab-Live-2-MATHWHIZ-game-project-

# SAIBA KHAN – MATH WHIZ
## Java Web Application Development Lab Assignment

**Project Title:** Math Whiz – Mathematics Quiz Game  
**Student Name:** Saiba Khan  
**Course Name:** Java Web Application Development  
**Lab Topic:** Java Servlet and JDBC  
**Total Marks:** 10

---

## 1. Introduction

Math Whiz is a simple web-based mathematics quiz game developed using Java Servlet, JSP, JDBC, and MySQL. The main purpose of this project is to practice Java web application development and understand how a web application communicates with a database.

In this game, a player answers four different mathematical challenges. The system checks the submitted answers, displays whether they are correct or incorrect, and calculates the player's total score out of 10.

## 2. Objectives

The main objectives of this project are:

- To develop a web application using Java Servlet and JSP.
- To use Java methods for solving mathematical problems.
- To connect a Java web application with a MySQL database using JDBC.
- To validate user input and display appropriate messages.
- To calculate and store players' scores.
- To display a leaderboard based on players' results.

## 3. Technologies Used

| Technology | Purpose |
|---|---|
| Java 17 or later | Application logic |
| Java Servlet | Server-side processing |
| JSP | User interface |
| JDBC | Database connectivity |
| MySQL 8 | Database management |
| Apache Tomcat 10.1 | Web server |
| Maven | Project and dependency management |
| JUnit 5 | Unit testing |

## 4. Game Description

The Math Whiz game contains four mathematical challenges. Players enter their answers, and the application checks them using Java methods.

| Challenge | Description | Marks |
|---|---|---:|
| Challenge 1 | Prime Number Check | 2 |
| Challenge 2 | Modulus Calculation | 2 |
| Challenge 3 | Trigonometric Question 1 | 3 |
| Challenge 4 | Trigonometric Question 2 | 3 |
| **Total** | | **10** |

The player receives marks according to the correctness of the submitted answers.

## 5. Implementation Details

### 5.1 Prime Number Check

The `isPrime(int n)` method checks whether a given integer is a prime number.

- Returns `1` if the number is prime.
- Returns `0` if the number is not prime.
- Numbers smaller than 2 are considered non-prime.

### 5.2 Modulus Calculation

The `calculateMod(int a, int b)` method calculates the remainder after dividing one integer by another.

If the divisor is zero, the application displays the validation message:

`The divisor must not be zero`

### 5.3 Trigonometric Calculations

The application uses the following methods:

- `calculateSin(double degree)`
- `calculateCos(double degree)`
- `calculateTan(double degree)`

The methods convert degrees into radians before performing the calculations.

A tolerance of ±0.01 is used when checking trigonometric answers. The application also handles undefined tangent values, such as 90° and 270°.

## 6. Database Description

The project uses MySQL to store players' game results.

**Database Name:** `math_whiz_db`  
**Table Name:** `game_scores`

The table contains the following fields:

| Field | Description |
|---|---|
| id | Unique record ID |
| player_name | Player's name |
| score | Total score |
| difficulty | Game difficulty |
| played_at | Date and time of the game |

The default difficulty level is `NORMAL`.

### Database Setup

Create the database and table using the following SQL commands:

```sql
CREATE DATABASE IF NOT EXISTS math_whiz_db;

USE math_whiz_db;

CREATE TABLE IF NOT EXISTS game_scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    score INT NOT NULL,
    difficulty VARCHAR(20) DEFAULT 'NORMAL',
    played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE USER IF NOT EXISTS
'mathwhiz_app'@'localhost'
IDENTIFIED BY 'MathWhizLocal@123';

ALTER USER 'mathwhiz_app'@'localhost'
IDENTIFIED BY 'MathWhizLocal@123';

GRANT SELECT, INSERT
ON math_whiz_db.*
TO 'mathwhiz_app'@'localhost';

FLUSH PRIVILEGES;
```

## 7. JDBC Database Connection

The application uses JDBC to connect Java with MySQL. The database connection is managed through the `DBConnection` class.

The connection details are:

- Database: `math_whiz_db`
- Username: `mathwhiz_app`
- Password: `MathWhizLocal@123`

Environment variables can also be used to configure the database connection.

## 8. Leaderboard

The leaderboard displays the top 10 players according to their scores.

The records are arranged by:

1. Highest score first.
2. Earlier playing time first when scores are equal.

SQL query:

```sql
SELECT *
FROM game_scores
ORDER BY score DESC, played_at ASC
LIMIT 10;
```

## 9. Screenshots of the Project

The following screenshots show the different pages and features of the Math Whiz application.

### Welcome Page
<img width="967" height="581" alt="image" src="https://github.com/user-attachments/assets/dd26e50a-4688-4053-9454-9f1632f390e2" />


### Game Page
<img width="917" height="895" alt="image" src="https://github.com/user-attachments/assets/1435de7b-9af5-48d5-b66e-e161d572f82b" />


### Result Page
<img width="898" height="768" alt="Screenshot 2026-11-09 224728" src="https://github.com/user-attachments/assets/8efe715c-76cf-46ba-ad9d-f7cbe8d8fbbc" />

### Leaderboard
<img width="907" height="512" alt="image" src="https://github.com/user-attachments/assets/62aa6ef1-082a-4f48-b51e-02e1d6b87730" />


## 10. How to Run the Project

1. Install JDK 17 or later.
2. Install Apache Tomcat 10.1.
3. Install MySQL 8.
4. Create the database and table using the SQL commands provided above.
5. Make sure the database credentials match the `DBConnection.java` file.
6. Open the project in an IDE such as IntelliJ IDEA or Eclipse.
7. Build the project using Maven:

```bash
mvn clean package
```

8. Run the unit tests:

```bash
mvn test
```

9. Deploy the generated WAR file from the `target` folder to Tomcat 10.1.
10. Start the Tomcat server and open the application in a browser.

**Project URL:**

[`(http://localhost:8080/MathWhiz_war_exploded/)`]

## 11. Expected Output

After starting the application:

- The welcome page appears.
- The player enters their name and starts the game.
- Four mathematical challenges are displayed.
- The player submits answers.
- The application checks the answers and calculates marks.
- The result page displays the score out of 10.
- The player's score is stored in the MySQL database.
- The leaderboard displays the top 10 players.

## 12. Learning Outcomes

By completing this project, I learned:

- How to develop a Java web application using Servlet and JSP.
- How to process user input on the server side.
- How to create and use Java methods for mathematical calculations.
- How to connect Java applications with MySQL using JDBC.
- How to perform database operations.
- How to validate user input and handle errors.
- How to test Java methods using JUnit.

## 13. Conclusion

Through this lab assignment, I developed a web-based mathematics quiz game named **Math Whiz** using Java Servlet, JSP, JDBC, and MySQL.

This project helped me understand the basic concepts of Java web application development, server-side processing, database connectivity, and result management. It also gave me practical experience in combining Java programming with web technologies.

**GitHub Repository:**  
https://github.com/saibakhan1104-png/Lab-Live-2-MATHWHIZ-game-project-/tree/main


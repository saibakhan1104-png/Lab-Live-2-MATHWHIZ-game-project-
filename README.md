# Lab-Live-2-MATHWHIZ-game-project-

# SAIBA KHAN – MATH WHIZ

A web-based mathematical game built with **Java Servlets, JSP and JDBC (MySQL)**.
Players enter their name, then enter their own values and answers for four challenges (prime, modulus, two trigonometry), see their score out of 10, and their result is stored in a database and ranked on a Top 10 leaderboard.

## Project Objectives
- Build reusable Java methods for mathematical calculations (`isPrime`, `calculateMod`, `calculateSin/Cos/Tan`).
- Process requests on the server side with a Servlet (`GameServlet`).
- Connect to MySQL with JDBC and `PreparedStatement`.
- Store, sort and display scores (Top 10 leaderboard).
- Document and submit the project through GitHub.

> All answers are calculated by Java methods in `MathService` at run time – no stored answers.

## Technologies Used
Java 17+ (tested with JDK 25), Java Servlet 4 / JSP / JSTL 1.2, JDBC, MySQL 8, Maven, Apache Tomcat 9.0, JUnit 5, HTML/CSS.

## Software Requirements
- JDK 17 or newer
- Apache Tomcat **9.0.x** (`javax.servlet` namespace)
- MySQL Server 8.x
- Maven 3.9+ (or IntelliJ's bundled Maven)
- IDE: IntelliJ IDEA (Community users: install the *Smart Tomcat* plugin)

## Project Structure
```
MathWhiz/
├── src/main/java/
│   ├── controller/GameServlet.java
│   ├── model/GameScore.java, Question.java
│   ├── service/MathService.java
│   └── dao/ScoreDAO.java, DBConnection.java
├── src/main/webapp/ index.jsp, game.jsp, result.jsp, leaderboard.jsp, css/style.css, WEB-INF/web.xml
├── src/test/java/service/MathServiceTest.java
├── database/math_whiz_db.sql
└── README.md
```

## Database Configuration
Defaults (in `dao/DBConnection.java`): host `localhost:3306`, database `math_whiz_db`, user `root`, empty password.
Override with environment variables `DB_URL`, `DB_USER`, `DB_PASSWORD` or edit the file.

### Database Creation SQL (`database/math_whiz_db.sql`)
```sql
CREATE DATABASE IF NOT EXISTS math_whiz_db;
USE math_whiz_db;

CREATE TABLE IF NOT EXISTS game_scores (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    score       INT          NOT NULL,
    difficulty  VARCHAR(20)  NOT NULL DEFAULT 'NORMAL',
    played_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

## Steps to Run
1. Clone the repository and open the `MathWhiz` folder in your IDE as a Maven project.
2. Run `database/math_whiz_db.sql` in MySQL (Workbench or `mysql -u root -p < database/math_whiz_db.sql`).
3. Set the DB user/password (see *Database Configuration*).
4. Build: `mvn clean package` (also runs the unit tests) → creates `target/MathWhiz.war`.
5. Deploy: copy the WAR into Tomcat's `webapps/` folder and start Tomcat, **or** run it from the IDE with a Tomcat 9 run configuration (application context `/MathWhiz`).
6. Open <http://localhost:8080/MathWhiz/>.

## Game Rules & Scoring
| Question | Area | Marks |
|---|---|---|
| Q1 | Prime number (1 = prime, 0 = not prime) | 2 |
| Q2 | Modulus (integers only, divisor never zero) | 2 |
| Q3 | Trigonometry (sin / cos / tan, degrees) | 3 |
| Q4 | Trigonometry (sin / cos / tan, degrees) | 3 |
| **Total** | | **10** |

- Numbers below 2 are not prime.
- Angles are entered in degrees and converted to radians with `Math.toRadians` before `Math.sin/cos/tan`.
- Decimal answers are accepted within a tolerance of **±0.01** (so round to 2 decimals).
- The player types the values: one integer for the prime question, two integers for the modulus question, and an angle in degrees plus a function (sin / cos / tan) for each trigonometry question. The player then types an answer and the server marks it Correct or Wrong.
- A zero divisor, or tan of an undefined angle (90°, 270°…), is rejected with a validation error.
- Difficulty: `NORMAL` (stored in the database with every score).
- Leaderboard: `ORDER BY score DESC, played_at ASC LIMIT 10`.

## Testing
`mvn test` runs `MathServiceTest`:

| Test case | Expected |
|---|---|
| Prime 7 / 8 / 1 | 1 / 0 / 0 |
| 17 % 5 | 2 |
| sin 30° / cos 60° / tan 45° | 0.5 / 0.5 / 1.0 |
| Divisor 0 | Validation error (`ArithmeticException`) |
| All correct | 10 marks |
| Wrong answers | Marks deducted per question |

## Application Screenshots
### Welcome Page
<img width="967" height="581" alt="image" src="https://github.com/user-attachments/assets/dd26e50a-4688-4053-9454-9f1632f390e2" />


### Game Page
<img width="917" height="895" alt="image" src="https://github.com/user-attachments/assets/1435de7b-9af5-48d5-b66e-e161d572f82b" />


### Result Page
<img width="898" height="768" alt="Screenshot 2026-11-09 224728" src="https://github.com/user-attachments/assets/8efe715c-76cf-46ba-ad9d-f7cbe8d8fbbc" />

### Leaderboard
<img width="907" height="512" alt="image" src="https://github.com/user-attachments/assets/62aa6ef1-082a-4f48-b51e-02e1d6b87730" />


## GitHub Project Directory Link
https://github.com/saibakhan1104-png/Lab-Live-2-MATHWHIZ-game-project-/tree/main


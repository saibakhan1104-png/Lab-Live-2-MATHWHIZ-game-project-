-- Run this script while connected to MySQL as an administrator (for example root).
CREATE DATABASE IF NOT EXISTS math_whiz_db
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS math_whiz_db.game_scores (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    score       INT          NOT NULL,
    difficulty  VARCHAR(20)  NOT NULL DEFAULT 'NORMAL',
    played_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Local-development credentials used by DBConnection.java.
-- Change this password in BOTH this script and DB_PASSWORD before deployment.
CREATE USER IF NOT EXISTS 'mathwhiz_app'@'localhost'
  IDENTIFIED BY 'MathWhizLocal@123';
GRANT SELECT, INSERT ON math_whiz_db.* TO 'mathwhiz_app'@'localhost';
FLUSH PRIVILEGES;

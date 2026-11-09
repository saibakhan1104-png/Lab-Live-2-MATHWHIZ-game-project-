<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><c:out value="${initParam.appTitle}"/></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
  <h1><c:out value="${initParam.appTitle}"/></h1>
  <p class="sub">Four maths challenges: prime numbers, modulus and trigonometry. Difficulty: NORMAL.</p>

  <c:if test="${not empty error}"><div class="error"><c:out value="${error}"/></div></c:if>

  <form action="${pageContext.request.contextPath}/game" method="post">
    <input type="hidden" name="action" value="start">
    <label for="playerName">Player Name</label>
    <input type="text" id="playerName" name="playerName" maxlength="100" placeholder="Enter your name" required>
    <button class="btn" type="submit">Start Game</button>
    <a class="btn secondary" href="${pageContext.request.contextPath}/game?action=leaderboard">Leaderboard</a>
  </form>
</div>
</body>
</html>

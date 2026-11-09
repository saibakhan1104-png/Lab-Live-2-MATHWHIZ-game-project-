<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><c:out value="${initParam.appTitle}"/> - Leaderboard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
  <h1>Top 10 Leaderboard</h1>
  <p class="sub"><c:out value="${initParam.appTitle}"/></p>

  <c:if test="${not empty error}"><div class="error"><c:out value="${error}"/></div></c:if>

  <table>
    <tr><th>#</th><th>Player</th><th>Score</th><th>Difficulty</th><th>Date / Time</th></tr>
    <c:forEach var="s" items="${scores}" varStatus="st">
      <tr class="${st.count == 1 ? 'rank1' : ''}">
        <td>${st.count}</td>
        <td><c:out value="${s.playerName}"/></td>
        <td>${s.score}</td>
        <td><c:out value="${s.difficulty}"/></td>
        <td><c:out value="${s.playedAtText}"/></td>
      </tr>
    </c:forEach>
    <c:if test="${empty scores}"><tr><td colspan="5">No scores yet. Be the first to play!</td></tr></c:if>
  </table>

  <a class="btn" href="${pageContext.request.contextPath}/index.jsp">Play Game</a>
</div>
</body>
</html>

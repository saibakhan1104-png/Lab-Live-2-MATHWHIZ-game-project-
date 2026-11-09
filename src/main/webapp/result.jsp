<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><c:out value="${initParam.appTitle}"/> - Result</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
  <h1><c:out value="${initParam.appTitle}"/></h1>
  <p class="sub">Result for <b><c:out value="${sessionScope.playerName}"/></b></p>

  <c:if test="${not empty saveError}"><div class="error"><c:out value="${saveError}"/></div></c:if>

  <div class="score">${score} / 10</div>

  <table>
    <tr><th>Challenge</th><th>Your answer</th><th>Correct</th><th>Result</th><th>Marks</th></tr>
    <c:forEach var="d" items="${details}">
      <tr>
        <td><c:out value="${d[0]}"/></td>
        <td><c:out value="${d[1]}"/></td>
        <td><c:out value="${d[2]}"/></td>
        <td class="${d[3] == 'Correct' ? 'ok' : 'bad'}"><c:out value="${d[3]}"/></td>
        <td>${d[4]}</td>
      </tr>
    </c:forEach>
  </table>

  <a class="btn" href="${pageContext.request.contextPath}/index.jsp">Play Again</a>
  <a class="btn secondary" href="${pageContext.request.contextPath}/game?action=leaderboard">Leaderboard</a>
</div>
</body>
</html>

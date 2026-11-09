<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><c:out value="${initParam.appTitle}"/> - Game</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
  <h1><c:out value="${initParam.appTitle}"/></h1>
  <p class="sub">Welcome, <b><c:out value="${sessionScope.playerName}"/></b>! Enter your values, work out the answers, then submit.</p>

  <c:if test="${not empty error}"><div class="error"><c:out value="${error}"/></div></c:if>

  <form action="${pageContext.request.contextPath}/game" method="post">
    <input type="hidden" name="action" value="submit">

    <div class="question">
      <p><b>Q1. Prime Number</b> <span class="marks">2 marks</span></p>
      <label>Enter an integer</label>
      <input type="number" name="primeNumber" step="1" placeholder="e.g. 7" value="<c:out value='${param.primeNumber}'/>" required>
      <label style="margin-top:10px">Your answer</label>
      <div class="radio">
        <label><input type="radio" name="primeAnswer" value="1" ${param.primeAnswer == '1' ? 'checked' : ''} required> 1 (Prime)</label>
        <label><input type="radio" name="primeAnswer" value="0" ${param.primeAnswer == '0' ? 'checked' : ''}> 0 (Not Prime)</label>
      </div>
    </div>

    <div class="question">
      <p><b>Q2. Modulus</b> <span class="marks">2 marks</span></p>
      <label>Enter two integers (a % b)</label>
      <div class="row">
        <input type="number" name="modA" step="1" placeholder="a, e.g. 17" value="<c:out value='${param.modA}'/>" required>
        <span class="op">%</span>
        <input type="number" name="modB" step="1" placeholder="b, e.g. 5 (not 0)" value="<c:out value='${param.modB}'/>" required>
      </div>
      <label style="margin-top:10px">Your answer (remainder)</label>
      <input type="number" name="modAnswer" step="1" placeholder="Whole number" value="<c:out value='${param.modAnswer}'/>" required>
    </div>

    <c:forEach var="i" begin="1" end="2">
      <c:set var="dName" value="trig${i}Degree"/>
      <c:set var="fName" value="trig${i}Func"/>
      <c:set var="aName" value="trig${i}Answer"/>
      <div class="question">
        <p><b>Q${i + 2}. Trigonometry</b> <span class="marks">3 marks</span></p>
        <label>Enter an angle in degrees and choose the function</label>
        <div class="row">
          <input type="number" name="${dName}" step="any" placeholder="degrees, e.g. 30" value="<c:out value='${param[dName]}'/>" required>
          <select name="${fName}" required>
            <option value="SIN" ${param[fName] == 'SIN' ? 'selected' : ''}>sin</option>
            <option value="COS" ${param[fName] == 'COS' ? 'selected' : ''}>cos</option>
            <option value="TAN" ${param[fName] == 'TAN' ? 'selected' : ''}>tan</option>
          </select>
        </div>
        <label style="margin-top:10px">Your answer (round to 2 decimals)</label>
        <input type="number" name="${aName}" step="any" placeholder="e.g. 0.5" value="<c:out value='${param[aName]}'/>" required>
      </div>
    </c:forEach>

    <button class="btn" type="submit">Submit Answers</button>
  </form>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>JungmanGram follow.jsp </title>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    rel="stylesheet">
  <link rel="shortcut icon" href="/images/favicon.ico">
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>

  <%@ include file="../include/nav.jsp" %>
  
  <main id="explore">
  
    <ul class="explore__users u-default-box">
    
    <c:forEach var="follow" items="${follows}">
      <li class="explore__user">
        <div class="explore__content">
          <img src="/images/avatar.jpg" />
          <div class="explore__info">
            <span class="explore__username">${follow.toUser.username}</span>
          </div>
        </div>
        <c:if test="${principal.user.id ne follow.toUser.id}">
	        <c:choose>
	        	<c:when test="${follow.followState eq true}">
	        		<button onClick="follow(false, ${follow.toUser.id})" class = "following_btn">팔로잉</button>
	        	</c:when>
	        	<c:otherwise>
	        		<button onClick="follow(true, ${follow.toUser.id})" class = "follow_btn">팔로우</button>
	        	</c:otherwise>
	        </c:choose>
        </c:if>
      </li>
     </c:forEach>
  
      
    </ul>
  </main>
    <%@ include file="../include/footer.jsp" %>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Feed | JungmanGram</title>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    rel="stylesheet">
    <link rel="shortcut icon" href="/images/favicon.ico">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
  <%@ include file="../include/nav.jsp" %>
  
  <main id="feed">
    <c:forEach var = "image" items="${images}">
 
	    <div class="photo u-default-box">
	      <header class="photo__header">
	        <img src="/images/avatar.jpg" />
	        <span class="photo__username">${image.user.username}</span><br>
	        <span class="photo__username">${image.location}</span><br>
	      </header>
	      <img src="/upload/${image.postImage}" />
	      <div class="photo__info">
	          <div class="photo__actions">
	            <span class="photo__action">
	              <i class="fa fa-heart-o heart"></i>
	            </span>
	            <span class="photo__action">
	              <i class="fa fa-comment-o"></i>
	            </span>
	          </div>
	          <span class="photo__likes">5,103 likes</span>
	          
	          <span class = "photo__caption_auth"><a href="/user/${image.user.id}">${image.user.username}</a></span> ${image.caption}
	          <div class = "hash__tag">
	          	<c:forEach var ="tag" items="${image.tags}">
          			<a href="#">#${tag.name}</a>
          		</c:forEach>
	          </div>
	          <ul class="photo__comments">
	            <li class="photo__comment">
	              <span class="photo__comment-author">serranoarevalo</span> i love this!
	            </li>
	            <li class="photo__comment">
	              <span class="photo__comment-author">serranoarevalo</span> i don't love this!
	            </li>
	          </ul>
	          <span class="photo__date">${image.createDate}</span>
	          <div class="photo__add-comment-container">
	            <textarea placeholder="Add a comment..."></textarea>
	            <i class="fa fa-ellipsis-h"></i>
	          </div>
	      </div>
	    </div>
    
    </c:forEach>
  </main>
  <%@ include file="../include/footer.jsp" %>


  
</body>
</html>

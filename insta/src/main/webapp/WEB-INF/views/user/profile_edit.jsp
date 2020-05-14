<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Proflie Modify | JungmanGram</title>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    rel="stylesheet">
  <link rel="shortcut icon" href="/images/favicon.ico">
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
  <%@ include file="../include/nav.jsp" %>

  <main id="edit-profile">
    <div class="edit-profile__container u-default-box">
      
      <header class="edit-profile__header">
        <div class="fucker-container">
          <img src="/upload/${user.profileImage }" />
        </div>
        <!-- master comments -->
        <h1 class="edit-profile__username">${user.username}</h1>
      </header>

      <form:form action="/user/editProc" method="POST" class="edit-profile__form">
        <div class="edit-profile__row">
          <label class="edit-profile__label" for="name">이름</label>
          <input id="name" name="name" type="text" value="${user.name}">
        </div>
        <div class="edit-profile__row">
          <label class="edit-profile__label" for="username">닉네임</label>
          <input id="username" name="username" type="text" value="${user.username}">
        </div>
        <div class="edit-profile__row">
          <label class="edit-profile__label" for="website">홈페이지</label>
          <input id="website" name="website" type="url" value="${user.website}">
        </div>
        <div class="edit-profile__row">
          <label class="edit-profile__label" for="bio">소개글</label>
          <textarea id="bio" name="bio">${user.bio}</textarea>
        </div>
        <div class="edit-profile__row">
          <label class="edit-profile__label" for="email">이메일</label>
          <input id="email" name="email" type="email" value="${user.email }">
        </div>
        <div class="edit-profile__row">
          <label class="edit-profile__label" for="phone-number">휴대전화</label>
          <input id="phone-number" name="phone" type="text" value="${user.phone}">
        </div>
        <div class="edit-profile__row">
          <label class="edit-profile__label" for="gender">성별</label>
          <input id="gender" name="gender" type="text" value="${user.gender}">
        </div>
        <div class="edit-profile__row">
          <span></span>
          <input style="background-color:#3897F0;" type="submit">
        </div>
      </form:form>

    </div>
  </main>
  <%@ include file="../include/footer.jsp" %>
</body>
</html>


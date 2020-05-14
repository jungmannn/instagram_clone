<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>JungmanGram</title>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    rel="stylesheet">
    <link rel="shortcut icon" href="/images/favicon.ico">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
  <main id="login">
    
    <div class="password__column">
      <div class="login__box">
        <a href="/"><img src="/images/loginLogo.png"></a>
        <form action="/auth/passwordProc" method="post" class="login__form">
          <input type="password" name="password" placeholder="기존 비밀번호" required="">
          <input type="password" name="password" placeholder="새 비밀번호" required="">
          <input type="password" name="passwordCheck" placeholder="새 비밀번호 확인" required="">
          <input type="submit" value="변경">
        </form>
        <span class="login__divider">
          or
        </span>
        <a href="#" class="login__small-link">Forgot password?</a>
      </div>
      
      <div class="login__t-box">
        <span class="login__text">
          Get the app.
        </span>
        <div class="login__appstores">
          <img src="/images/ios.png" class="login__appstore">
          <img src="/images/android.png" class="login__appstore">
        </div>
      </div>
    </div>
  </main>
  <%@ include file="../include/footer.jsp" %>
</body>
</html>
    
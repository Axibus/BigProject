<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="useredit" method="post">
                                <div class="al_form-fields">
                                    <p>
                                    userId:  <input type = "text" name="id" value="${user.getUserId() }"></br>
                                        <label>
                                        userName
                                        <span class="required">*</span>
                                        </label>
                                        <input type="text" name="name">
                                                                                原值：${user.getUserName() }
                                    </p>
                                    <p>
                                        <label>
                                        password
                                        <span class="required">*</span>
                                        </label>
                                        <input type="password" name="password">
                                        	原值：${user.getPassword() }
                                    </p>
                                    <p>
                                        <label>
                                        eMail
                                        <span class="required">*</span>
                                        </label>
                                        <input type="text" name="email">
                                        	原值：${user.geteMail() }
                                    </p>
                                </div>
                                <div class="form-action">
                                    <div class="new_act new_act_3">
                                        <!--  >a class="button_act button_act_3">Login</a-->
                                        <input type="submit"/>
                                        <label>
                                        <input type="checkbox">
                                        Remember me
                                        </label>
                                    </div>
                                    <p class="lost_password">
                                        <a href="#">Lost your password?</a>
                                    </p>
                                </div>
                            </form>
</body>
</html>
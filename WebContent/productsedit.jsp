<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
							<form action="addproduct" method="post" enctype="multipart/form-data">
                                <div class="al_form-fields">
                                    <p>
                                        <label>
                                        productname
                                        <span class="required">*</span>
                                        </label>
                                        <input type="text" name="name">
                                    </p>
                                    <p>
                                        <label>
                                        proType
                                        <span class="required">*</span>
                                        </label>
                                        <select name = "pid">
                                        <c:forEach items="${list }" var="p">
                                        	<option value="${p.getId() }">${p.getType() }</option>
                                        </c:forEach>
                                        </select>
                                    </p>
                                    <p>
                                        <label>
                                        price
                                        <span class="required">*</span>
                                        </label>
                                        <input type="text" name="price">
                                    </p>
                                    <p>
                                        <label>
                                        Img1
                                        <span class="required">*</span>
                                        </label>
                                        <input type="file" name="img1">
                                    </p>
                                    <p>
                                        <label>
                                        Img2
                                        <span class="required">*</span>
                                        </label>
                                        <input type="file" name="img2">
                                    </p>
                                    <p>
                                        <label>
                                        Img3
                                        <span class="required">*</span>
                                        </label>
                                        <input type="file" name="img3">
                                    </p>
                                    <p>
                                        <label>
                                        Intro
                                        <span class="required">*</span>
                                        </label>
                                        <textarea name="intro" rows="5" cols="40"></textarea>
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
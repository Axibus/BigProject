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
							<form action="orderadd" method="post" enctype="multipart/form-data">
                                <div class="al_form-fields">
                                	<p>
                                        <label>
                                        Product:${p.getName() }
                                        <input type="hidden" name="pid" value="${p.getId() }">
                                        
                                        </label>
                                    </p>
                                    <p>
                                        <label>
                                        count:${count }
                                        </label>
                                    </p>
                                    <p>
                                        <label>
                                        totalprice:${count*p.getPrice() }
                                        </label>
                                    </p>
                                
                                    <p>
                                        <label>
                                        Addresses
                                        <span class="required">*</span>
                                        </label>
                                        <select name = "aid">
                                        <c:forEach items="${addresses }" var="a">
                                        	<option value="${a.getId() }">${a.getAddress() }</option>
                                        </c:forEach>
                                        </select>
                                    </p>
                                    
                                </div>
                                <div class="form-action">
                                    <input type="submit"/>
                                    <p class="lost_password">
                                        <a href="#">Lost your password?</a>
                                    </p>
                                </div>
                            </form>
</body>
</html>
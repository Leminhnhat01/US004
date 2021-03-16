<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript">
		function goToMain(){
			window.location.href="/MotorSpringMVC/main";
		}
		function goToFind(){
			window.location.href="/MotorSpringMVC/find";
		}
		
</script>
<button class="btn" onclick="goToMain()"><i class="fa fa-plus"></i> <spring:message code="label.addNewReceipt"></spring:message></button>
<button class="btn" onclick="goToFind()"><i class="fa fa-search"></i> <spring:message code="label.findReceipt"></spring:message></button>
 
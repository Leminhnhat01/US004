<%@ page language="java" contentType="text/html;text/css charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="<c:url value="/css/main.css" />" rel="stylesheet">

<script type="text/javascript">
		function validate(){
			var error=[];
			var policyNo;
			var valid = true;
			policyNo = document.getElementById("policyNo").value;

			if(!isNaN(policyNo)){
				errorMessageAmount='<spring:message code="label.errorPolicyEmpty" />';
				error['errPolicyNo']=errorMessageAmount;
				valid =false;
			}
			if(Object.keys(error).length>0){
				document.getElementById("errorMessagePolicy").innerHTML = replaceUndefinied(error.errPolicyNo);
			}
			return valid;
		}
		function replaceUndefinied(item) {
			   var str =  JSON.stringify(item, function (key, value) {return (value === undefined) ? "" : value});
			   return JSON.parse(str);
		}
</script>
</head>

<body>
	<div style="text-align: right;padding:5px;margin:5px 0px;background:#ccc;">
	       <a href="/MotorSpringMVC/find?lang=en">(English)</a>
	       &nbsp;&nbsp;
	       <a href="/MotorSpringMVC/find?lang=vi">(Vietnamese)</a>
	</div>
    <jsp:include page="_header.jsp"></jsp:include>
    
	<div class="app">
        <div class="appbody">
        
        	 <div class="sidebarTest">
        	 	<jsp:include page="_sidebar.jsp"></jsp:include>            	
            </div>
            
            <div class="text">
            	<div class="header" >
                    <i class="fa fa-search"></i>
                    <p style="margin-top: -2px;"><spring:message code="label.find" /></p>
                </div>
                <form action="/MotorSpringMVC/findItem" method="get" name="motorFind" onsubmit="return validate();">
                	<div class="form-element-wrapper">
						<spring:message code="label.receiptNo"/>
						<input class="form-element" disabled="disabled" type="text" name="receipt_no" value="${receiptModel.receipt_no}"  >
					</div>
                
					<div class="form-element-wrapper">
						<spring:message code="label.policyNo"/>
						<input class="form-element" id="policyNo" type="text" name="policy_no" value="${policyNO}">
						<p class="error" id="errorMessagePolicy"></p>		
						
					</div>
					
					<c:if test="${not empty error}">
						<div style="color:red;height: 15px;margin-left:41px"><h5>${error}</h5><br></div>
					</c:if>
					
					<div class="form-element-wrapper">
						<spring:message code="label.currency"/>
						<input class="form-element" disabled="disabled" id="amount" type="text" name="amount" value="${receiptModel.currency}" >
						<p class="error" id="errorMessageAmount"></p>			
					</div>
					
					<div class="form-element-wrapper">
						<spring:message code="label.amount"/>
						<input class="form-element" disabled="disabled" id="amount" type="text" name="amount" value="${receiptModel.amount}" >
						<p class="error" id="errorMessageAmount"></p>	
						
					</div>
					
					<spring:message code="label.find" var="labelFind"></spring:message>
					<div class="btnCreate"><input class="btn-submit-form form-element form-button" type="submit" value="${labelFind}"></div>
					<c:if test="${not empty success}">
						<div style="color:green"><h5>${success}</h5><br></div>
					</c:if>
				</form>
				<c:if test="${not empty receiptModel}">
				
				<table>
					<tr>
						<td><spring:message code="label.receiptNo"/>:</td>
						<td class="p1">${receiptModel.receipt_no}</td>
					</tr>
						<tr>
						<td><spring:message code="label.policyNo"/>:</td>
						<td class="p1">${receiptModel.policy_no}</td>
					</tr>
						<tr>
						<td><spring:message code="label.currency"/>:</td>
						<td class="p1">${receiptModel.currency}</td>
					</tr>
						<tr>
						<td><spring:message code="label.amount"/>:</td>
						<td class="p1">${receiptModel.amount}</td>
					</tr>
				</table>
				</c:if>
				
				
            </div>
        </div>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>	
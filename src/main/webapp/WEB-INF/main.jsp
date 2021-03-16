<%@ page language="java" contentType="text/html;text/css charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><spring:message code="label.addNewReceipt" /></title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="<c:url value="/css/main.css" />" rel="stylesheet">


<script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript">
</script>

<script type="text/javascript">
		function validate(){
			var error=[];
			var policyNo,amount;
			var valid = true;
			policyNo = document.getElementById("policyNo").value;
			amount = document.getElementById("amount").value;
			if(!isNaN(policyNo))
			{
				errorMessagePolicy='<spring:message code="label.errorPolicyno" />';
				error['errPolicyNo']=errorMessagePolicy;
				valid =false;
			}
			if(!amount>0)
			{
				errorMessageAmount='<spring:message code="label.errorAmount" />';
				error['errAmount']=errorMessageAmount;
				valid =false;
			}
			if(isNaN(amount)){
				errorMessageAmount='<spring:message code="label.errorAmount2" />';
				error['errAmount']=errorMessageAmount;
				valid =false;
			}
			if(Object.keys(error).length>0){
				document.getElementById("errorMessagePolicy").innerHTML = replaceUndefinied(error.errPolicyNo);
				document.getElementById("errorMessageAmount").innerHTML = replaceUndefinied(error.errAmount);
			}
			console.log(Object.keys(error).length);
			console.log(error);
			return valid;
		}
		function replaceUndefinied(item) {
			   var str =  JSON.stringify(item, function (key, value) {return (value === undefined) ? "" : value});
			   return JSON.parse(str);
		}
		
		function searchAjax() {
			$.ajax({
		        data : {'policyNo': $('#policyNo').val()},
			    url : '/MotorSpringMVC/search',
			    headers : {
			        'Content-Type' : 'application/json'
			    },
			    type: 'GET',
			    success : function(result) {
					$('#currency').val(result);
			    },      
			    error : function(){
			        console.log("Your PolicyNo is empty");
			    }
			});
		}
		
</script>

</head>
<body>
	<div style="text-align: right;padding:5px;margin:5px 0px;background:#ccc;">
	       <a href="/MotorSpringMVC/main?lang=en">(English)</a>
	       &nbsp;&nbsp;
	       <a href="/MotorSpringMVC/main?lang=vi">(Vietnamese)</a>
	</div>
    <jsp:include page="_header.jsp"></jsp:include>
	<div class="app">
        <div class="appbody">
        	 <div class="sidebarTest">
        	 	<jsp:include page="_sidebar.jsp"></jsp:include>
            </div>
            <div class="text">
            	<div class="header">
                    <i class="fa fa-id-card"></i>
                    <p style="margin-top: -2px;"><spring:message code="label.receiptMotor" /></p>
                </div>
                <form action="/MotorSpringMVC/create" method="post" name="motorReceipt" onsubmit="return validate();">
					
					<div class="form-element-wrapper">
						<spring:message code="label.policyNo"/>
						<input class="form-element" id="policyNo" onchange="searchAjax()" type="text" maxLength ="8" name="policy_no" value="${receipt.policy_no}">
						<p class="error" id="errorMessagePolicy"></p>		
					</div>
					
					<c:if test="${not empty error}">
						<div style="color:red;height: 15px;margin-left:41px"><h5>${error}</h5><br></div>
					</c:if>
					
					<div class="form-element-wrapper">
						<spring:message code="label.currency"/>
						<input style="background-color:#ededed" class="form-element" id="currency" readonly type="text" name="currency" value="${receipt.currency}">
						<%-- <select name="currency" class="form-element" style="width:96.5%;">
							<c:forEach items="${listCurrency}" var="currency">
								<option value="${currency.name}" <c:if test="${currency.name eq receipt.currency}">
										selected="selected"
									</c:if>
									>
									${currency.name}
								</option>
							</c:forEach>
						</select> --%>
					</div>
															
					<div class="form-element-wrapper">
						<spring:message code="label.amount"/>
						<input class="form-element" id="amount" type="text" name="amount" value="${receipt.amount}" >
						<p class="error" id="errorMessageAmount"></p>	
					</div>
					<c:if test="${not empty errorAmount}">
						<div style="color:red;height: 15px;margin-left:41px"><h5>${errorAmount}</h5><br></div>
					</c:if>
					
					<spring:message code="label.submit" var="labelSubmit"></spring:message>
					<div class="btnCreate">
						<input class="btn-submit-form form-element form-button" type="submit" value="${labelSubmit}">
					</div>
					<c:if test="${not empty success}">
						<div style="color:green;text-align: center;font-size: xx-large;"><h5>${success}</h5><br></div>
					</c:if>
				</form>
				
            </div>
        </div>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>

</html>	


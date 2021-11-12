<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function goHomePage() {
		alert("The form is submitting");
		var form = document.getElementById("editForm");
		form.action = "/ServletJspCRUD/home";
		form.method = "get";
		submitForm(form);
	}
	function submitForm(form) {
		//get the form element's document to create the input control with
		//(this way will work across windows in IE8)
		var button = form.ownerDocument.createElement('input');
		//make sure it can't be seen/disrupts layout (even momentarily)
		button.style.display = 'none';
		//make it such that it will invoke submit if clicked
		button.type = 'submit';
		//append it and click it
		form.appendChild(button).click();
		//if it was prevented, make sure we don't get a build up of buttons
		form.removeChild(button);
	}
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="get" id="editForm">
		<input type="button" value="GO" onclick="goHomePage()" />
	</form>
</body>
</html>
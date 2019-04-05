<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
<head>
<body>

	<form action="update.do" method="post">
		<h1>UPDATE ACCOUNT DETAILS</h1>
		ACCOUNT ID:<br> <input type="text" name="accountid" value="${account.accountId}" readonly="readonly"<br> <br> <br>
		
		NEW NAME:<br> <input type="text" name="accountholdername" value="${account.accountHolderName}"
			placeholder="enter your name here"><br> <br> <br>
		NEW ACCOUNT TYPE:<br>
		<div class="form-group" style="width: 250px">
			<label for="exampleFormControlSelect1">Account Type</label> <select
			 name ="accounttype" id="exampleFormControlSelect1">
				<option>${account.accountType}</option>
				<option value ="saving">Saving</option>
				<option value = "current">Current</option>
			</select>
		</div>
		<br> <br> <br>
		ACCOUNT BALANCE:<br> <input type="text" name="accountbalance" value="${account.accountBalance}" readonly = "readonly"<br> <br> <br>
		<button name="subject" type="submit" value="HTML">SUBMIT</button>
	</form>


</body>

</head>
</html>
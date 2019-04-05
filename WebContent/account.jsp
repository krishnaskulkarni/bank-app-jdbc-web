<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>HERE ARE YOUR ACCOUNT DETAILS</h1>
	<br>
	<br>
	<div class="container">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>account Id</th>
					<th>customer name</th>
					<th>account type</th>
					<th>balance</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${account.accountId}</td>
					<td>${account.accountHolderName}</td>
					<td>${account.accountType}</td>
					<td>${account.accountBalance}</td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>
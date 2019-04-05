<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
</head>
<body>
	<h1>HERE ARE YOUR ACCOUNT DETAILS</h1>
	<br><br>
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
				<c:forEach var="account" items="${accounts}">
					<tr>
						<td>${account.accountId}</td>
						<td>${account.accountHolderName}</td>
						<td>${account.accountType}</td>
						<td>${account.accountBalance}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirmation</title>
<link rel="stylesheet" href="Confirmation.css">
</head>
<body>
	<header>
		<div class="login">
			<p>
				社員ID<br>Ｓ０００１<br>名前<br>山田太郎
			</p>
		</div>
		<h1 class="title">確認画面</h1>
	</header>
	<p class="naiyo">申請内容</p>
	<table class="naiyotable">
		<tr class="table">
			<th>取得日</th>
			<th>種類</th>
			<th>休暇方法</th>
			<th>申請状況</th>
		</tr>
		<tr>
			<th>20xx/xx/xx</th>
			<th>有休</th>
			<th>午後</th>
			<th><span class="zumi">承認済</span></th>
		</tr>
		<tr>
			<th>20xx/xx/xx</th>
			<th>特休</th>
			<th>全日</th>
			<th><span class="mati">承認待ち</span></th>
		</tr>
	</table>
	<div class="naiyo">
		<p class="text">
			有休残日数 : xx日<br>有休取得率 : xx.x%<br>特休取得日数 : xx日<br> <br>

			<button type="button">戻る</button>
		</p>
	</div>

</body>
</html>
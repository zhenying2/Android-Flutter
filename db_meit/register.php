<?php

	if(isset($_POST["Token"])){

		$token = $_POST["Token"];
		//데이터베이스에 접속해서 토큰을 저장
		include_once 'config.php';
		$conn = mysqli_connect("localhost","zhenying2","maria1816@","zhenying2");
		$query = "INSERT INTO MESSAGE_USER(Token) Values ('$token') ON DUPLICATE KEY UPDATE Token = '$token'; ";
		mysqli_query($conn, $query);

		mysqli_close($conn);
	}
?>

<?php
    $con=mysqli_connect("localhost","zhenying2","maria1816@","zhenying2");

    $userID=$_POST["userID"];
    $userPassword=$_POST["userPassword"];
    $userName=$_POST["userName"];
    $userGender=$_POST["userGender"];
    $userNumber=$_POST["userNumber"];

    $statement=mysqli_prepare($con, "INSERT INTO USER VALUES (?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sssss",$userID, $userPassword, $userName, $userGender, $userNumber);
    mysqli_stmt_execute($statement);

    $response=array();
    $response["success"]=true;

    echo json_encode($response);
?>
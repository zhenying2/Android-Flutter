<?php
$con=mysqli_connect("localhost","zhenying2","maria1816@","zhenying2");
 
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$userName = $_GET['userName'];
$userNumber = $_GET['userNumber'];

$result = mysqli_query($con,"SELECT userID FROM USER where userName='$userName' and userNumber='$userNumber'");
 
$row = mysqli_fetch_array($result);
$data = $row[0];
 
if($data){
    echo $data;
}


mysqli_close($con);
?>
<?php

$connect = mysqli_connect("localhost","zhenying2","maria1816@","zhenying2");

if(!$connect){
    echo "Error : ".mysqli_connect_error();
    exit();

}

echo "연결성공하하하";

$userUID = $_POST["userUID"]; // ESP32를 통해 read한 각 tagger의 uid 정보 추출
$userNumber = $_POST["userNumber"];
$Position = $_POST["Position"];

$query = "INSERT INTO RFID(userUID,userNumber, Position) VALUES ('$userUID', '$userNumber','$Position')"; 
$result=mysqli_query($connect, $query);
mysqli_close($connect);

echo "Insertion Success!<br>" ; 

?>


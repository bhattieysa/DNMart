<?php


$server="localhost";
$username="trukuafl_Dn-Mart";
$password="Shonumonu1234";
$db_name="trukuafl_DN-Mart";


$con=mysqli_connect($server,$username,$password,$db_name);
if(!$con){
    echo mysqli_connect_error()."<h3 style='color:red'>There is an error in your connection</h3>";
}



?>
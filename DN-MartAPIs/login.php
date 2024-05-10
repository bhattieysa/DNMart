<?php

include("connection.php");





$response = array("error" => FALSE);


$email=$_POST["email"];
$password=$_POST["password"];

//image$=$_GET["image"];


$sql="SELECT `id`,`name`,`email` FROM `users` WHERE `email`='$email' AND `password`='$password'";


    $result=mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0){
    
    while($row=mysqli_fetch_array($result)){
    
    
    
        $response["error"] = FALSE;
        $response["id"]=$row['id'];
    
    
       
        echo json_encode($response);
    }
    
}
else{
    
        $response["error"] = TRUE;
      
        echo json_encode($response);
    
}
    
    
    





?>

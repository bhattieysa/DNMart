<?php

include("connection.php");
$response = array("error" => FALSE);

$name=$_POST["name"];
$email=$_POST["email"];
$password=$_POST["password"];
$phonenumber=$_POST["phonenumber"];
$image=$_POST["image"];
//image$=$_GET["image"];

if($image!='flag'){
    
    
    $imageName=$phonenumber.".jpg";
    
    
    
    
}

$UPLOADS_PATH="images/$phonenumber.jpg";





        
        
        





$sql="INSERT INTO `users`( `name`, `email`, `number`, `password`,`image`) VALUES('$name','$email','$phonenumber','$password','$imageName')";


    $result=mysqli_query($con,$sql);


if($result){
    
        $response["error"] = FALSE;
    
    
       file_put_contents($UPLOADS_PATH,base64_decode($image));
    
    
    
        echo json_encode($response);
    
    
    
}else{
    
        $response["error"] = TRUE;
      
        echo json_encode($response);
    
}
    
    
    





?>

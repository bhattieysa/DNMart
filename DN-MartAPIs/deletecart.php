<?php

include("connection.php");






$response = array();


$id=$_GET["id"];
$user_id=$_GET["user_id"];



$query="DELETE FROM `cart` WHERE `user_id`='$user_id'AND`product_id`='$id'";
$result=mysqli_query($con,$query);


 
if($result){
    
        $error=FALSE;

         array_push($response,array("error"=>$error));
    
    
    
}else{
    
                 
    $error= TRUE;
array_push($response,array("error"=>$error));
   
    
   
      
       
    
}
   echo json_encode(array("products"=>$response)); 



  


?>

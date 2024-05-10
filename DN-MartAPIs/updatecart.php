<?php

include("connection.php");






$response = array();


$quantity=$_GET["quantity"];
$id=$_GET["id"];
$user_id=$_GET["user_id"];



$query="UPDATE `cart` SET `quantity`='$quantity' WHERE `user_id`='$user_id'AND`product_id`='$id'";
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

<?php

include("connection.php");






$response = array();


$product_id=$_POST["product_id"];
$user_id=$_POST["user_id"];



$query="SELECT * FROM `cart` WHERE `user_id`='$user_id' AND `product_id`='$product_id'";
$rslt=mysqli_query($con,$query);

if(mysqli_num_rows($rslt) > 0){
    
    
     $response["error"] = "TRUE";
    $response["error1"] = "TRUE1";
    echo json_encode($response);
    
    
    
    
}else{






$sql="INSERT INTO `cart`( `user_id`, `product_id`) VALUES ('$user_id','$product_id')";


    $result=mysqli_query($con,$sql);


    
  
if($result){
    
        $response["error"] = FALSE;
    $response["error1"] = "TR";
    echo json_encode($response);
    
    
    
}else{
    
        $response["error"] = TRUE;
    $response["error1"] = "TR";
      
        echo json_encode($response);
    
}
    

}

  


?>

<?php

include("connection.php");

$response = array("error" => FALSE);


$u_id=$_POST["u_id"];
$barcode=$_POST["barcode_id"];

//image$=$_GET["image"];


$sql="SELECT `product_id` FROM `barcode_products` WHERE `barcode_id`='$barcode'";


    $result=mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0){
    
   $row=mysqli_fetch_array($result);
    
    $query="INSERT INTO `cart`(`user_id`, `product_id`, `quantity`) VALUES ('$u_id','$row[0]','1')";
    
    $result1=mysqli_query($con, $query);
    
    if($result1){
    
        $response["error"] = FALSE;
        
    
    
       
        echo json_encode($response);
    }else{
        
        $response["error"] = TRUE;
      
        echo json_encode($response);
        
    }
    
}
else{
    
        $response["error"] = TRUE;
      
        echo json_encode($response);
    
}
    
    
    





?>

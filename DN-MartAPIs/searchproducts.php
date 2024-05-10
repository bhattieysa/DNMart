<?php

include("connection.php");






$response = array();


$product=$_GET["product"];







//image$=$_GET["image"];


$sql="SELECT * FROM `products` WHERE `name` LIKE '%$product%'";


    $result=mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0){
    
   while($row=mysqli_fetch_array($result)){
    

    $error=FALSE;

         array_push($response,array("error"=>$error,"name"=>$row[1],"des"=>$row[3],"price"=>$row[4],"quantity"=>$row[6],"image"=>$row[2],"discount"=>$row[5],"id"=>$row[0],"category"=>$row[7]));
   
    }
    
}
else{
    
         $error= TRUE;
array_push($response,array("error"=>$error));
   
    
}
    
    
    

echo json_encode(array("products"=>$response));
mysqli_close($con);



?>

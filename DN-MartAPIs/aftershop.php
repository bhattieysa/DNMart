<?php

include("connection.php");






$response = array();


$id=$_GET["id"];







//image$=$_GET["image"];


$sql="SELECT * FROM products WHERE category='$id' ";


    $result=mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0){
    
   while($row=mysqli_fetch_array($result)){
    

    $error=FALSE;

         array_push($response,array("error"=>$error,"name"=>$row['name'],"des"=>$row['des'],"price"=>$row['price'],"quantity"=>$row['quantity'],"image"=>$row['image'],"discount"=>$row['discount'],"id"=>$row['id']));
   
    }
    
}
else{
    
         $error= TRUE;
array_push($response,array("error"=>$error,"name"=>"","des"=>"","price"=>"","quantity"=>"","image"=>"","discount"=>"","id"=>""));
   
    
}
    
    
    

echo json_encode(array("products"=>$response));
mysqli_close($con);



?>

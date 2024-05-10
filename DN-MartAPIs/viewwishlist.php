<?php

include("connection.php");






$response = array();


$id=$_GET["u_id"];







//image$=$_GET["image"];


$sql="SELECT `p_id` FROM `wishproducts` WHERE `u_id`='$id' ";


    $result=mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0){
    
   while($row=mysqli_fetch_array($result)){
    

   
    
    
    
    
    $sql1="SELECT `id`,`name`,`image`,`des`,`price`,`discount`,`quantity`,`category` FROM `products` WHERE `id`='$row[0]'";
    
        $result1=mysqli_query($con,$sql1);
    if(mysqli_num_rows($result1)>0){
    
   while($row1=mysqli_fetch_array($result1)){
    
    
     $error=FALSE;
    

         array_push($response,array("error"=>$error,"name"=>$row1['name'],"des"=>$row1['des'],"price"=>$row1['price'],"quantity"=>$row1['quantity'],"image"=>$row1['image'],"discount"=>$row1['discount'],"id"=>$row1['id']));
   
    }
    
}
else{
    
         $error= TRUE;
array_push($response,array("error"=>$error,"name"=>"","des"=>"","price"=>"","quantity"=>"","image"=>"","discount"=>"","id"=>""));
   
    
}
    
    
   }} else{
    
         $error= TRUE;
array_push($response,array("error"=>$error,"name"=>"","des"=>"","price"=>"","quantity"=>"","image"=>"","discount"=>"","id"=>""));
   
    
}  

echo json_encode(array("products"=>$response));
mysqli_close($con);



?>

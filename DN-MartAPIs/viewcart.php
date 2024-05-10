<?php

include("connection.php");






$response = array();


$id=$_GET["id"];







//image$=$_GET["image"];


$sql="SELECT `product_id` , `quantity` FROM `cart` WHERE `user_id`='$id'";


    $result=mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0){
    
   while($row=mysqli_fetch_array($result)){
       
       $query="SELECT `id`, `name`, `image`, `des`, `price`, `discount`, `quantity`, `category` FROM `products` WHERE `id`='$row[0]'";
       $result1=mysqli_query($con,$query);
       
       if(mysqli_num_rows($result1)>0){
           $row1=mysqli_fetch_array($result1);
           
            $error=FALSE;

         array_push($response,array("error"=>$error,"name"=>$row1['name'],"des"=>$row1['des'],"price"=>$row1['price'],"quantity"=>$row1['quantity'],"image"=>$row1['image'],"discount"=>$row1['discount'],"id"=>$row1['id'],"cartquantity"=>$row['quantity']));
           
           
       }else{
           
           
             
    $error= TRUE;
array_push($response,array("error"=>$error,"name"=>"","des"=>"","price"=>"","quantity"=>"","image"=>"","discount"=>"","id"=>"","cartquantity"=>""));
   
    
    
       }
       
       
       
       
       
       
   }
    
  
    

}



echo json_encode(array("products"=>$response));
mysqli_close($con);



?>

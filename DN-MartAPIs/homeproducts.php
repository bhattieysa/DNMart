<?php

include("connection.php");





$response = array();








$id=$_GET['idd'];

$query="SELECT COUNT(id) FROM `cart` WHERE `user_id`='$id'";
$rslt=mysqli_query($con,$query);
$row1=mysqli_fetch_array($rslt);
 


$sql="SELECT `id`,`name`,`image`,`des`,`price`,`discount`,`quantity` FROM `products` WHERE `discount` != 0 ";


    $result=mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0){
    
    while($row=mysqli_fetch_array($result)){
    
    
    
        $error=FALSE;
        
        array_push($response,array("error"=>$error,"name"=>$row['name'],"des"=>$row['des'],"price"=>$row['price'],"quantity"=>$row['quantity'],"image"=>$row['image'],"discount"=>$row['discount'],"id"=>$row['id'],"count"=>$row1[0]));
   
    
    
       
        
    }
    
}
else{
    
        $error= TRUE;
      
          array_push($response,array("error"=>$error,"name"=>$row['name'],"des"=>$row['des'],"price"=>$row['price'],"quantity"=>$row['quantity'],"image"=>$row['image'],"discount"=>$row['discount'],"id"=>$row['id'],"count"=>$row1[0]));
   
    
}
    
    
    
echo json_encode(array("products"=>$response));
mysqli_close($con);




?>

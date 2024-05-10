<?php

include("connection.php");






$response = array();


$id=$_GET["id"];
$total=0;







//image$=$_GET["image"];


$sql="SELECT `product_id`,`quantity` FROM `cart` WHERE `user_id`='$id'";


    $result=mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0){
    
   while($row=mysqli_fetch_array($result)){
       
       $query="SELECT `price` FROM `products` WHERE `id`='$row[0]'";
       $result1=mysqli_query($con,$query);
       
       if(mysqli_num_rows($result1)>0){
           $row1=mysqli_fetch_array($result1);
           
           
           
           
           $price=$row1[0];
           $quantity=$row[1];
           
           $Total=$price*$quantity;
           
           $total=$total+$Total;
           
           
           
           
           
           
           
            
           
           
       }else{
           
           
            $error= TRUE;
array_push($response,array("error"=>$error,"total"=>$total));
    
           
       }
       
       
       
       
       
       
   }
    
  $error=FALSE;

         array_push($response,array("error"=>$error,"total"=>$total));
    

}else{
    
    
     $error= TRUE;
array_push($response,array("error"=>$error,"total"=>$total));
    
    
}



echo json_encode(array("products"=>$response));
mysqli_close($con);



?>

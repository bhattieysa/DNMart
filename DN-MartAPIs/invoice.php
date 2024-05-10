<?php

include("connection.php");




$subtotal=0;
$response = array();


$sql="SELECT * FROM `shipping` ";
$rslt1=mysqli_query($con,$sql);
if($rslt1){


$row1=mysqli_fetch_array($rslt1);
    
    
$shipping=$row1[0];

}



$id=$_GET['id'];
$o_id=$_GET['o_id'];

$query="SELECT * FROM `OrderList` WHERE `user_id`='$id' AND `order_id`='$o_id'";
$rslt=mysqli_query($con,$query);
if($rslt){


$row=mysqli_fetch_array($rslt);



$sql="SELECT * FROM `cart` WHERE `user_id`='$id'";


    $result=mysqli_query($con,$sql);

if($result){
    
    while($row1=mysqli_fetch_array($result)){
        
       $p_id=$row1[2];
        $product="SELECT * FROM `products` WHERE `id`='$p_id'";
         $result1=mysqli_query($con,$product);
        
        if($result1){
         
            
            $row2=mysqli_fetch_array($result1);
        
        
        
         $error=FALSE;
        
        
       
        
        
        $amount=$row2[4]*$row1[3];
        $subtotal=$subtotal+$amount;
          
        $date=date("d-M-Y");

            array_push($response,array("error"=>$error,"product_name"=>$row2[1],"quantity"=>$row1[3],"product_id"=>$row2[0],"price"=>$row2[4],"amount"=>$amount,"shipping"=>$shipping,"date"=>$date,"customer_name"=>$row[1],"subtotal"=>$subtotal));
        
        
        }
        else{
    
        $error= TRUE;
      
          array_push($response,array("error"=>$error));
   
    
}



    }



    
    
    
       
   
    
    
       
        
    
    
}
else{
    
        $error= TRUE;
      
          array_push($response,array("error"=>$error));
   
    
}
    
    
}else{
    
        $error= TRUE;
      
          array_push($response,array("error"=>$error));
   
    
}
echo json_encode(array("products"=>$response));
mysqli_close($con);




?>

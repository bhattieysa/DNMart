<?php

include("connection.php");




$response = array();




$id=$_GET['id'];
$o_id=$_GET['o_id'];

$query="SELECT * FROM `order_products` WHERE `user_id`='$id' AND `order_id`='$o_id'";
$rslt=mysqli_query($con,$query);

if($rslt){
    
    if(mysqli_num_rows($rslt)==0){


    
           
        $product="DELETE FROM `orderlist` WHERE `user_id`='$id' AND `order_id`='$o_id'";
         $result1=mysqli_query($con,$product);
        
        if($result1){
         
            
        
        
         $error=FALSE;
        
         array_push($response,array("error"=>$error));
   
       
        
        }
        else{
    
        $error= FALSE;
      
          array_push($response,array("error"=>$error));
   
    
}
   
    }else{
   
        $error=TRUE;
         array_push($response,array("error"=>$error));
   
        
        
        
    }

 
    

    
    
}else{
    
        $error= TRUE;
      
          array_push($response,array("error"=>$error));
   
    
}
echo json_encode(array("products"=>$response));
mysqli_close($con);




?>

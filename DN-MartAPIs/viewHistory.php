<?php

include("connection.php");






$response = array();


$u_id=$_GET["id"];









$sql="SELECT * FROM `orderlist` WHERE `user_id`='$u_id'";


    $result=mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0){
    
   while($row=mysqli_fetch_array($result)){
       
       $o_id=$row[8];
       
       $query="SELECT `quantity`,`order_id`,`product_id`,`shipping`,`total` FROM `order_products` WHERE `order_id`='$o_id' AND `user_id`='$u_id'";
       $result1=mysqli_query($con,$query);
       
       if(mysqli_num_rows($result1)>0){
           $row1=mysqli_fetch_array($result1);
           
           
           
           
           
            $error=FALSE;
            array_push($response,array("error"=>$error,"name"=>$row['name'],"o_id"=>$o_id,"price"=>$row1['total'],"status"=>$row['status'],"date"=>$row['date']));
           
           
       }else{
           
           
             
    $error= TRUE;
            array_push($response,array("error"=>$error,"name"=>$row['name'],"o_id"=>$o_id,"price"=>$row1['total'],"status"=>$row['status']));
    
    
       }
       
       
       
       
       
       
   }
    
  
    

}



echo json_encode(array("products"=>$response));
mysqli_close($con);



?>

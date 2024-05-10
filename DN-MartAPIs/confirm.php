<?php

include("connection.php");
$response = array("error" => FALSE);

$name=$_POST["name"];
$number=$_POST["number"];
$bill=$_POST["bill"];
$address=$_POST["address"];
$u_id=$_POST['u_id'];
$o_id=$_POST['o_id'];
$total=$_POST['total'];


$shipping="SELECT `shipping`FROM `shipping`";
$shipping=mysqli_query($con,$shipping);
    if(mysqli_num_rows($shipping)>0){
        $rw=mysqli_fetch_array($shipping);
        
        $shipping=$rw[0];
        
        
        }else{
        
        $response["error"] = TRUE;
                   echo json_encode($response);
        
    }



           $select="SELECT `product_id`,`quantity` FROM `cart` WHERE `user_id`='$u_id'";
           $reslt1=mysqli_query($con,$select);
           
        if(mysqli_num_rows($reslt1)>0){
         while($row11=mysqli_fetch_array($reslt1)){
             
             
             
             $p_id=$row11[0];
              $quantity=$row11[1];
             
             $insert="INSERT INTO `order_products`( `user_id`, `order_id`, `product_id`, `quantity`,`shipping`,`total`) VALUES('$u_id','$o_id','$p_id','$quantity','$shipping','$total') ";
             
              $rslt1=mysqli_query($con,$insert);
            
             
             
             
             
         }
             if($rslt1){
                 
                 
                 
                 
                 $del="DELETE FROM `cart` WHERE `user_id`='$u_id'";
                 $rslt=mysqli_query($con,$del);
                 
                 if($rslt){
                       $response["error"] = FALSE;
   
                        echo json_encode($response);
                     
                 }else{
                     
                     $response["error"] = TRUE;
                     echo mysqli_error($con);
                   echo json_encode($response); 
                     
                 }
                 
                 
                 
                 
                 
                 
                 
               
                 
             }else{
                 
                 $response["error"] = TRUE;
                   echo json_encode($response);
                 
             }
               
               
            }else{
            
            $response["error"] = TRUE;
              echo json_encode($response);
            
        }


    
    





?>

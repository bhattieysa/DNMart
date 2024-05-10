<?php

include("connection.php");
$response = array("error" => FALSE);
$response["error1"] = "noid";






$name=$_POST['name'];
$address=$_POST['address'];
$city=$_POST['city'];
$cnic=$_POST['cnic'];
$u_id=$_POST['u_id'];
$number=$_POST['number'];


//
//$name=$_GET['name'];
//$address=$_GET['address'];
//$city=$_GET['city'];
//$cnic=$_GET['cnic'];
//$u_id=$_GET['u_id'];
//$number=$_GET['number'];
//


$order_id=date("ymd").date("his");

$date=date("Y-m-d");
//$date=date("d-M-Y");

$a=FALSE;

$sql1="SELECT * FROM `orderlist` WHERE `user_id`='$u_id'";
 $result1=mysqli_query($con,$sql1);

       
       if(mysqli_num_rows($result1)>0){
          
           while($row1=mysqli_fetch_array($result1)){
               
             $sql2="SELECT * FROM `order_products` WHERE  `order_id`='$row1[8]'";
              $result2=mysqli_query($con,$sql2);

       
             if(mysqli_num_rows($result2)>0){
                 
     
                 
                 
    
              }else{
                 $a=TRUE;
                

                     
              $sql3="UPDATE `orderlist` SET `name`= '$name',`address`='$address',`number`='$number',`city`='$city',`cnic`='$cnic',`status`='pending',`user_id`='$u_id',`order_id`='$row1[8]',`date`='$date' WHERE `order_id`= '$row1[8]'
                 ";
              $result3=mysqli_query($con,$sql3);

                     if($result3){
                    
                        $response["error"] = FALSE;
                         $response["error1"] = $row1[8];

                        echo json_encode($response);



                }else{

                        $response["error"] = TRUE;


                        echo json_encode($response);

                }
             }
               
               
               
               
               
               
           }
           if($a==FALSE){
               
               
               
               



                $sqla="INSERT INTO `OrderList`( `name`, `address`, `number`, `city`, `cnic`,  `user_id`, `order_id`, `date`) VALUES ('$name','$address','$number','$city','$cnic','$u_id','$order_id','$date')";


                    $resulta=mysqli_query($con,$sqla);
                echo mysqli_error($con);


                if($resulta){

                    
                        $response["error"] = FALSE;
                      $response["error1"] = $order_id;

                        echo json_encode($response);



                }else{

                        $response["error"] = TRUE;


                        echo json_encode($response);

                }














               
               
               
           }
           
           
           
       }else{

        
        
        




            $sql="INSERT INTO `OrderList`( `name`, `address`, `number`, `city`, `cnic`,  `user_id`, `order_id`, `date`) VALUES ('$name','$address','$number','$city','$cnic','$u_id','$order_id','$date')";


                $result=mysqli_query($con,$sql);
            echo mysqli_error($con);


            if($result){

                    $response["error"] = FALSE;

                    $response["error1"] = $order_id;

                    echo json_encode($response);



            }else{

                    $response["error"] = TRUE;


                    echo json_encode($response);

            }
    
    
       }





?>

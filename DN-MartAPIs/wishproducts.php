<?php

include("connection.php");


$response = array();




$u_id=$_POST["u_id"];
$p_id=$_POST["p_id"];







//image$=$_GET["image"];


$sql="SELECT * FROM products WHERE id ='$p_id' LIMIT 1 ";


    $result=mysqli_query($con,$sql);

if($result){
    
    
    $sql1="SELECT * FROM `wishproducts` WHERE `p_id`=$p_id AND`u_id`=$u_id";
    $result1=mysqli_query($con,$sql1);

       
       if(mysqli_num_rows($result1)>0){
//           $response["error1"] = "yes";
                $response["error"] = "FALSE";


                echo json_encode($response);

           
       }else{

    
    
        $insert="INSERT INTO `wishproducts`( `u_id`, `p_id`) VALUES ($u_id,$p_id) ";
        $result2=mysqli_query($con,$insert);
            if($result2){
   
//                array_push($response,array("error"=>$error));
   

                $response["error"] = "FALSE";

                echo json_encode($response);
            }else{

                $response["error"] = TRUE;

                echo json_encode($response);

            }
           }
    
    
}
else{
    
        $response["error"] = TRUE;
    
        echo json_encode($response);
    
    
}
    
    
    



?>

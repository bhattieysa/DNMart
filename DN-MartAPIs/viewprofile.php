<?php

include("connection.php");





$response = array("error" => FALSE);


$id=$_POST["id"];





//image$=$_GET["image"];


$sql="SELECT `name`,`email`,`number`,`image` FROM `users`  WHERE `id`='$id' ";


    $result=mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0){
    
    while($row=mysqli_fetch_array($result)){
    
    
    
        $response["error"] = FALSE;
        
        $response["name"]=$row['name'];
        $response["email"]=$row['email'];
         $response["number"]=$row['number'];
         $response["image"]=$row['image'];
        
    
    
       
        echo json_encode($response);
    }
    
}
else{
    
        $response["error"] = TRUE;
      
        echo json_encode($response);
    
}
    
    
    





?>

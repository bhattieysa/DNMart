<?php

include("connection.php");


if($_POST['submi']){

$shipping=$_POST['shipping'];


$query="UPDATE `shipping` SET `shipping`=$shipping";
$rslt=mysqli_query($con,$query);

  if($rslt){
      
      echo"added successfully";
  }else{
      echo"failed to add";
      
  }

}
?>
<html>
<body>
    
    
    <form method="post" >
        
        
     <input name="shipping" placeholder="Enter new shipping for all new orders"  type="number" required >
          
        
        

        <input type="submit" name="submi" value="Submit">
        
    

    
    
    </form>
    
    
    
    
    
    
    
    </body>







</html>

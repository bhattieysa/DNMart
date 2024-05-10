<?php

include ("connection.php");

    



if(isset($_POST['submit'])){
    
    
      $name=$_POST['name'];  
      $img = $_FILES['image']['name'];
        $samename="0";
    
 
    $query2 = "SELECT `name` FROM `category`";
    $result2 = mysqli_query($con,$query2);   
   
    
    while($row=mysqli_fetch_array($result2)){
        if($row['name']== $name){
             
            $samename = "1";
        }
        
    }
    
    
  if($samename =="0"){
    
    $query="INSERT INTO `category`( `name`,`image`) VALUES ('$name','$img') ";
    
    $result=mysqli_query($con,$query);
    
      

        if($result){


            move_uploaded_file($_FILES['image']['tmp_name'],"images/$img");

            echo "sucessfull";

        }else{

          echo mysqli_error($con);

        }

    
    
    
  }else{
      echo "<script type='text/javascript'>alert('same name try another');</script>";
           
        
  }

}
    


?>

<html>
<body>
    
    <form method="post" enctype="multipart/form-data">
        
        
    <input name="name" placeholder="Enter name" type="text" required >
    <br>
     select image:
        <input type="file" name="image" >
        <br>
        <input type="submit" name="submit" value="Submit">
    
    
    </form>
    
    
    
    
    
    
    
    </body>







</html>
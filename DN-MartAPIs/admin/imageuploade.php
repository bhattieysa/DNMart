<?php
     include ("connection.php");
session_start();

if(isset($_POST['submit'])){
    
    
    $name=$_POST['name'];
    $price=$_POST['price'];
    $discount=$_POST['discount'];
    $des=$_POST['des'];
    $quantity=$_POST['quantity'];
    $category=$_POST['category'];
 
   

    
      $img = basename($_FILES['image']['name']);
    
    
  

        $q2= "select 'name' FROM products WHERE 'name' = $name";
        $r2=mysqli_query($con,$q2);

    
    if($r2)
           {
                                                                   
           echo '<script language="javascript">';
             echo 'alert("Allready exsist email try another")';
               echo'</script>';
               
           
               
           }
           if( $name && $price &&  $discount && $des && $quantity && $category)
           {
           $query="INSERT INTO `products`( `name`, `des`, `price`, `discount`, `quantity`, `image`, `category`) VALUES ('$name','$des','$price','$discount','$quantity','$img','$category') ";
            $result=mysqli_query($con,$query);
            if($result)
            {
                  move_uploaded_file($_FILES['image']['tmp_name'],"images/$img");
               echo '<script language="javascript">';
                       echo 'alert("Data  saved")';
                       echo'</script>';  
?>

                        <a href="addProducts.php"> <h1>click here to go back</h1></a>                                       
               
<?php                
                
//                header("location:addProducts.php");

               }
               else
               {
                   echo '<script language="javascript">';
               echo 'alert("Data not saved")';
               echo'</script>';           }
             }
               
 
       }

    
    



?>

<?php

include ("connection.php");



 
     

 


if(isset($_POST['submit'])){
    
    
    $name=$_POST['name'];
    $price=$_POST['price'];
    $discount=$_POST['discount'];
    $des=$_POST['des'];
    $quantity=$_POST['quantity'];
    $category=$_POST['category'];
    
    
   

    
      $img = $_FILES['image']['name'];
  


    
    
    
    
    
    
    
    
    
    
  
    
    $query="INSERT INTO `products`( `name`, `des`, `price`, `discount`, `quantity`, `image`, `category`) VALUES ('$name','$des','$price','$discount','$quantity','$img','$category') ";
    
    $result=mysqli_query($con,$query);
    
      
    
    if($result){
        
        
        move_uploaded_file($_FILES['image']['tmp_name'],"images/$img");
        
        echo "sucessfull";
        
    }else{
        
      echo mysqli_error($con);
        
    }
    
    
    
    
    
    
}
$query="SELECT * FROM `caterogy` ";
$result=mysqli_query($con,$query);





?>

<html>
<body>
    
    
    <form method="post" enctype="multipart/form-data">
        
        
    <input name="name" placeholder="Enter name" type="text" required >
        <input name="price" placeholder="Enter price" type="number" required >
        <input name="discount" placeholder="Enter discount" type="number" required >
        <textarea name="des" placeholder="Enter description" type="text" required ></textarea>
        <input name="quantity" placeholder="Enter quantity" type="text" required >
        <br>
         <select name="category">
        
         <?php while($row=mysqli_fetch_array($result)){ ?>
                                    
                                    
          <option  value="<?php echo $row['id'];?>"><?php echo $row['name'];?></option>
                                
        <?php }?>
        
        
        </select>
      
        <input type="file" name="image" >
        

        <input type="submit" name="submit" value="Submit">
        
    
    
    
    
    </form>
    
    
    
    
    
    
    
    </body>







</html>
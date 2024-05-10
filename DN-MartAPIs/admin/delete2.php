<?php
    
    include ("connection.php");

    $id = $_GET['id'];
    
    if(isset($_GET['id']))
    {
        
        $query = "DELETE FROM `products` WHERE id = '$id'";           
        $result = mysqli_query($con,$query);
        if($result)
        {
            echo '<script language="javascript">';
             echo 'alert("Data deleted successfully")';
               echo'</script>';

            
        }
        
    }   


?>

<html>
    <body>
    
    <a href="viewproducts.php"> <h1>click! GO back to  admin</h1></a>
    </body>
    
</html>

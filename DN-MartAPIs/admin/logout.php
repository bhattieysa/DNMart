<?php


    
include ("connection.php");

session_start();

    if(isset($_SESSION['email']) && isset($_SESSION['password']))
    {       unset($_SESSION['email']);
            unset($_SESSION['password']);
             header("location: login.php");
                exit();
   
        
    }else{
        
        echo '<script language="javascript">';
             echo 'alert("Internet error")';
               echo'</script>';

        
    }
    
    
    


?>
<?php

   session_start();

      
if(isset($_SESSION['email']) && isset($_SESSION['password'])){

}else{
    
    header("location: login.php");
    exit();
    
}
    


include("includes/header.php");
include("includes/navbar.php");


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
  echo "<script type='text/javascript'>alert('sucessfully entered new category');</script>";
    
            echo "sucessfull";

        }else{

          echo mysqli_error($con);

        }

    
    
    
  }else{
      echo "<script type='text/javascript'>alert('same name try another');</script>";
           
        
  }

}
 


?>

   
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Search -->
                     <h1 class="page-header">Category Entery</h1>

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </li>
   <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><?php echo
                    
                    $_SESSION['email']
                    ?></span>
                <img class="img-profile rounded-circle" src="profile.jpg">
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
        
                <a class="dropdown-item" href="logout.php" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Logout
                </a>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->


        
        
        
   
        <!-- Begin Page Content -->
    
          <!-- Page Heading -->
            

         
        
        <!-- /.container-fluid -->

      
      <!-- End of Main Content -->

 <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                              
                       <div class="row">
                <div class="col-lg-10">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            ADD Category
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10">
                                    <form role="form"  method="post"  enctype="multipart/form-data">
                                        <div class="form-group">
                                            <marquee><h2>Add Category for the products</h2></marquee>
    
    <form method="post" enctype="multipart/form-data" >
<table >
       
 
    
 <tr>
<td>Category Name:</td>
<td>
  <input name="name" placeholder="Enter category name " type="text" required >
   </td>
</tr>
  
<tr>
<td>Choose image:</td>
<td>  <input type="file" name="image" required>      
     </td>
    
</tr>

</table>
    

       <input type="submit" value="submit" name="submit">
     
    </form>




   
                                      
    </div>
    </form>    
    </div>
    </div>
    </div>
    </div>
    </div>
    
    </div>
                    
                    
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->
  



        


<?php

include("includes/script.php");
include("includes/footer.php");


?>
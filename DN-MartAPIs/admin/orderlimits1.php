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

    
if(isset($_POST['submit']))
{ 
   
    $Year=$_POST['year'];


$sql= "SELECT * FROM `orderlist` WHERE  YEAR(`date`)='$Year'";

$query = mysqli_query($con,$sql);
    $row1=mysqli_num_rows($query);
    
    
    
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
                     <h1 class="page-header">find Orders in year </h1>

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
                            Yearly order report:
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10">
                                    <form role="form" w method="post"  enctype="multipart/form-data">
                                        <div class="form-group">
                                            
    <form method="post" enctype="multipart/form-data" >
      
      <input type="text" name="year" Placeholder="Enter year(ie.2020)" required><br><br>

      <button  name="submit" class="btn btn-primary btn-user btn-block">submit</button>
              <h1> <?php   
   if(isset($_POST['submit'])){
      echo "Your Entered Year sale is: ".$row1; 
      
   }
                ?></h1>
               
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
<?php
   include ("connection.php");

   session_start();

      
if(isset($_SESSION['email']) && isset($_SESSION['password'])){

}else{
    
    header("location: login.php");
    exit();
    
}
    



        include('includes/header.php');
        include('includes/navbar.php');


// Check connection
if (mysqli_connect_errno())
{
echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$result = mysqli_query($con,"SELECT * FROM products");

function delete($a){
    
    echo "$a Refnes.<br>";
    
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
                     <h1 class="page-header">View products</h1>

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
 
        
   

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Products</h1>
                    
                       <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            products in DN-Mart
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form" method="post" >
                                        <div class="form-group">




<table class="table table-striped">
<tr>
<th>name</th>
<th>image</th>
<th>des</th>
<th>price</th>
<th>discount</th>
<th>quantity</th>
<th>category</th>
<th>DELETE</th>
</tr>
<?php
while($row = mysqli_fetch_array($result))
{
echo "<tr>";
echo "<td>" . $row['name'] . "</td>";
echo "<td>" . $row['image'] . "</td>";
echo "<td>" . $row['des'] . "</td>";
echo "<td>" . $row['price'] . "</td>";
echo "<td>" . $row['discount'] . "</td>";
echo "<td>" . $row['quantity'] . "</td>";
echo "<td>" . $row['category'] . "</td>";



 echo "<td>" ."<a  href=\"delete2.php?id=$row[id]\">"."Delete" ."</a>". "</td>";
                      
echo "</tr>";
}
    ?>
</table>
<!--<button style="color:white;background-color:red">Delete</button>-->
                     
                                        
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

<?php
        session_start();

      
if(isset($_SESSION['email']) && isset($_SESSION['password'])){

}else{
    
    header("location: login.php");
    exit();
    
}
    

        include('includes/header.php');
        include('includes/navbar.php');

         include ("connection.php");

           $query2 = "SELECT * FROM `category`";
            $result2 = mysqli_query($con,$query2);

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
                     <h1 class="page-header">Product Entery</h1>

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
   <!-- End of Main Content -->

 <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-10">
                    
                       <div class="row">
                <div class="col-lg-10">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Add products
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10">
                                    <form role="form" action="imageuploade.php" method="post"  enctype="multipart/form-data">
                                        <div class="form-group">
                                            <marquee><h2>add new items with particular category</h2></marquee>
    
    <form method="post" enctype="multipart/form-data" action="imageuploade.php">
<table >
       
<tr>
<td>Select caterogy:</td>
<td> <select name="category">
        
        <?php    
        while ($row=   mysqli_fetch_array($result2) ){
        
        ?>
          
        <option  value="<?php echo $row['id'];?>"> <?php echo $row['name']; ?> </option> 
     
        <?php } ?>

        </select>
      </td>
</tr>
 
 <tr>
        <td>
    <h5 style="margin-left:2.5em">
           click on link to enter new category: <a href="addcategory.php"> click here.......</a>
        </h5>
     </td>
    </tr>
<tr>
<td>Name:</td>
<td> <input name="name" placeholder="Enter name" type="text" required >
   </td>
</tr>
        
<tr>
<td>Price:</td>
<td>    <input name="price" placeholder="Enter price" type="number" required >
</td>
</tr>

<tr>
<td>Discount:</td>
<td>    <input name="discount" placeholder="Enter discount" type="number" required >
</td>
</tr>

<tr>
<td>Enter Discription of product:</td>
<td>     <textarea name="des" placeholder="Enter description" type="text" required ></textarea>
      
</td>
</tr>

<tr>
<td>Quantity of product:</td>
<td>   <input name="quantity" placeholder="Enter quantity" type="text" required >

</td>
</tr>
    
<tr>
<td>Choose image:</td>
<td>  <input type="file" name="image" >      
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
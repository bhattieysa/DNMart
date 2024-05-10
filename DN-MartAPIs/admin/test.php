<?php
include ("connection.php");
$a=0;
 $d = 0;
 $e = 0;

//echo "$start1";
//    echo "$end1";
if(isset($_POST['submit']))
{ 
    $start=$_POST['start'];
    $end=$_POST['end'];
    
//$start ="2020-feb-1";
//$end ="25-feb-2020";
//$date=date("Y-m-d");


 $startt =  strtotime($start);
     $endt =  strtotime($end);
  
    $startd = date('d ', $startt);
    $endd= date('d ', $endt);
  
      $startm = date('m ', $startt);
    $endm= date('m ', $endt);
  
      $starty = date('Y ', $startt);
    $endy= date('Y ', $endt);
  if($starty==$endy){
echo "year";
  
      if($startm==$endm){
echo "month";
  
$sql= "SELECT * FROM `orderlist` WHERE DAY(`date`) BETWEEN '$startd' AND '$endd'";

$query = mysqli_query($con,$sql);
    $row1=mysqli_num_rows($query);
    echo $row1;
      }else{
          
          
        $sql= "SELECT * FROM `orderlist` WHERE MONTH(`date`) BETWEEN '$startm' AND '$endm'";

        $query = mysqli_query($con,$sql);
        
         while($row2=mysqli_fetch_array($query)){

           
             $tmp =$row2[9];
              $tmpm =  strtotime($tmp);
                $s = date('m ', $tmpm);
             

             if($d != $s){
                        
                      $d =0;
                      
                     if($s==$startm){
 
                          $product="SELECT * FROM `orderlist` WHERE DAY(`date`) >= '$startd' AND MONTH(`date`) = '$startm'AND YEAR(`date`) = '$starty' ";
                        $result1=mysqli_query($con,$product);

                          $roww=mysqli_num_rows($result1);

                         $a=$a+$roww;


                    }
                         if($s==$endm){
                            
                          $product="SELECT * FROM `orderlist` WHERE DAY(`date`) <= '$endd' AND MONTH(`date`) = '$endm'AND YEAR(`date`) = '$endy' ";
                        $result1=mysqli_query($con,$product);

                          $roww=mysqli_num_rows($result1);

                         $a=$a+$roww;
                          



                    }
                         if($s!=$startm && $s!=$endm ){

                          $product="SELECT * FROM `orderlist` WHERE DAY(`date`) AND MONTH(`date`) != '$startm' AND MONTH(`date`) != '$endm'AND YEAR(`date`) = '$endy'  ";
                        $result1=mysqli_query($con,$product);

                          $roww=mysqli_num_rows($result1);

                         $a=$a+$roww;


                    }
            
                    
                     }
               $d = $s;

                }
          echo $a;
  }
    
}else{
//      for diiff yeaar

  
  
  
  
          
        $sql= "SELECT * FROM `orderlist` WHERE YEAR(`date`) BETWEEN '$starty' AND '$endy'";

        $query = mysqli_query($con,$sql);
        
         while($row3=mysqli_fetch_array($query)){

           
            
             $tmp =$row3[9];
              $tmpm =  strtotime($tmp);
                $y = date('Y ', $tmpm);
             
             if($e != $y){
                 $e=0;
                 
//                 for start year's month restriction
                 
                 if($y==$starty){
                            $sql= "SELECT * FROM `orderlist` WHERE MONTH(`date`) >='$startm' AND YEAR(`date`) ='$starty'";

                            $query = mysqli_query($con,$sql);

                             while($row2=mysqli_fetch_array($query)){


                                 $tmp =$row2[9];
                                  $tmpm =  strtotime($tmp);
                                    $s = date('m ', $tmpm);


                                 if($d != $s){

                                          $d =0;

                                         if($s==$startm){
                                                

                                                      
                                        $product="SELECT * FROM `orderlist` WHERE DAY(`date`) >= '$startd' AND MONTH(`date`) = '$startm'
                                        AND YEAR(`date`) = '$y' ";
                                        $result1=mysqli_query($con,$product);

                                          $roww=mysqli_num_rows($result1);

                                         $a=$a+$roww;


                                    }
                                        
                                         if($s!=$startm  ){

                                          $product="SELECT * FROM `orderlist` WHERE DAY(`date`)  AND MONTH(`date`) != '$endm' AND YEAR(`date`) = '$y' ";
                                        $result1=mysqli_query($con,$product);

                                          $roww=mysqli_num_rows($result1);

                                         $a=$a+$roww;


                                    }


                                     }
                               $d = $s;

                                }
                    }
                 
//            for end year month restriction     
                 
                 
                 
                 if($y==$endy){
                        echo "1yesss";

                       
                            $sql= "SELECT * FROM `orderlist` WHERE MONTH(`date`) <='$endm' AND YEAR(`date`) ='$endy'";

                            $query = mysqli_query($con,$sql);

                             while($row2=mysqli_fetch_array($query)){


                                 $tmp =$row2[9];
                                  $tmpm =  strtotime($tmp);
                                    $s = date('m ', $tmpm);


                                 if($d != $s){
                                    

                                          $d =0;

                           
                                     if($s==$endm){
                                         echo "1";

                                      $product="SELECT * FROM `orderlist` WHERE DAY(`date`) <= '$endd' AND MONTH(`date`) = '$endm'  AND YEAR(`date`) = '$y'";
                                    $result1=mysqli_query($con,$product);

                                      $roww=mysqli_num_rows($result1);

                                     $a=$a+$roww;




                                }
                                     if( $s!=$endm ){
                                           echo "2";


                                      $product="SELECT * FROM `orderlist` WHERE DAY(`date`)  AND MONTH(`date`) != '$endm' AND YEAR(`date`) = '$y' ";
                                    $result1=mysqli_query($con,$product);

                                      $roww=mysqli_num_rows($result1);

                                     $a=$a+$roww;


                                }


                                     }
                               $d = $s;

                                }
                    }
                 
//                 for neutrol
                 
                 
                    if($y!=$endy && $y!=$starty){
                            $sql= "SELECT * FROM `orderlist` WHERE  YEAR(`date`) ='$y'";

                            $query = mysqli_query($con,$sql);
                         $roww=mysqli_num_rows($query);

                                     $a=$a+$roww;


                    }
//                 enddddddddddddddddd
                 
                 
                 
                     }

             $e = $y;
             
             
                }
          echo $a;

  
  }

}


//if($query){
//    echo "yes";
//}else{
//    echo"noo";
//}
//    $num = mysqli_num_rows($query);
//
//echo $num;    
 

?>


<html>
    <body >
       
        <form method="post">        
        <input type="text" name="start" Placeholder="Enter start date">
        <input type="text" name="end" Placeholder="Enter end date  ">
 <button  name="submit" class="btn btn-primary btn-user btn-block">submit</button>
                          </form>

    </body>
    
    
    
</html>

<?php
error_reporting(E_ERROR);

$unique_id = $_REQUEST["unique_id"];

include "db_connect.php";
include "config.php";

mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));



        $json = array();
        
        $query = 'SELECT * FROM  officers 
        WHERE unique_id = "'.$unique_id.'"';
        $result = mysqli_query($db, $query);
        $row = mysqli_fetch_array($result, MYSQLI_ASSOC);

        $count = mysqli_num_rows($result);

        if($count > 0){
                    
                $json['success'] = 'Welcome '.$row['firstname'].' '.$row['lastname'];
                $json['user_id'] = $row['officer_id'];
                $json['email'] = $row['email'];
                $json['firstname'] = $row['firstname'];
                $json['lastname'] = $row['lastname'];
                $json['access_level'] = $row['access_level'];
                $json['phone'] = $row['phone'];
            
            }else{

                $json['success'] = 'Check Internet Connecton Please.';
                $json['user_id'] = 0;
                $json['email'] = "";
                $json['firstname'] = "";
                $json['lastname'] = "";
                $json['access_level'] = -1;
                $json['phone'] = "";
            }
  
    print(json_encode($json));


?>
<?php
error_reporting(E_ERROR);
$firstname = $_REQUEST['firstname'];
$lastname = $_REQUEST['lastname'];
$phone = $_REQUEST['phone'];
$user_token = $_REQUEST['user_token'];

include "db_connect.php";
include "config.php";

mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));
$json = array();


$query = 'INSERT INTO civilians(firstname, lastname, phone, user_token)
 VAlUES("'.$firstname.'", "'.$lastname.'", "'.$phone.'", "'.$user_token.'")';

if($db->query($query) == TRUE){


        $json['success'] = 'Welcome to Mukono Police Emergency App, Report your issues anytime.';
        $user_id = $db->insert_id;
        $json['user_id'] = $user_id;

     }else{
        $json['success'] =  'Registration failed, try again.';
        $json['user_id'] = 0;
        echo mysqli_error($db);
     }

     print(json_encode($json));

?>
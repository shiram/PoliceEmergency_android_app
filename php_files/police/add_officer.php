<?php
error_reporting(E_ERROR);

$firstname = $_REQUEST['firstname'];
$lastname = $_REQUEST['lastname'];
$email = $_REQUEST['email'];
$phone = $_REQUEST['phone'];
$unique_id = $_REQUEST['unique_id'];


include 'db_connect.php';
include 'config.php';
mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));


$json = array();

$query = 'INSERT INTO officers(firstname, lastname, email, phone, unique_id) 
                      VALUES ("'.$firstname.'", "'.$lastname.'","'.$email.'", "'.$phone.'", "'.$unique_id.'")';
					  


if($db->query($query) == TRUE){
	 	$json['success'] = 'Police Officer Added Successfully.';

	 }else{
	 	$json['success'] =  'Internet Connection error.';
	 	echo mysqli_error($db);
	 }


print(json_encode($json));

?>
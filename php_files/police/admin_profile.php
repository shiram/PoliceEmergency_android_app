<?php
error_reporting(E_ERROR);


$firstname = "Shirambere";
$lastname = "Edward";
$user_email = "shirambereh@gmail.com";
$user_phone = "+256783619410";
$unique_id = md5('12345');
$access = 1;

include 'db_connect.php';
include 'config.php';
mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));


$query = 'INSERT INTO officers(firstname, lastname, email, phone, unique_id,access_level) 
                      VALUES ("'.$firstname.'","'.$lastname.'", "'.$user_email.'","'.$user_phone.'", "'.$unique_id.'" , "'.$access.'")';
					  
					  if($db->query($query) == TRUE){
	 	$json['success'] = 'Item Added.';

	 }else{
	 	$json['success'] =  'Error Entering Item.';
	 	echo mysqli_error($db);
	 }


print(json_encode($json));
					  

?>
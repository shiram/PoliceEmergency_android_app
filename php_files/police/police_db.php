<?php

include 'db_connect.php';


$query = 'CREATE DATABASE IF NOT EXISTS police_db';

if($db->query($query) == TRUE){
	echo 'Database created Successfully';

//create tables.

include 'config.php';

	mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));


	$query = 'CREATE TABLE IF NOT EXISTS civilians(
	             id     INTEGER UNSIGNED   NOT NULL AUTO_INCREMENT,
	             firstname           VARCHAR(55)       NOT NULL,
				 lastname            VARCHAR(55)       NOT NULL,
				 phone               VARCHAR(55)       NOT NULL,
				 user_token       VARCHAR(255)      NOT NULL,
	             created_at          TIMESTAMP,
	             updated_at          TIMESTAMP,	 
	             PRIMARY KEY(id))
	             ENGINE=MyISAM';

	 if($db->query($query) == TRUE){
	 	echo 'Created civilians table successfully.';
	 }else{
	 	echo 'Server Error while Creating civilians Table:

	 	 '. $db->error;
	 }


	 	$query = 'CREATE TABLE IF NOT EXISTS officers(
	             officer_id     INTEGER UNSIGNED   NOT NULL AUTO_INCREMENT,
	             firstname           VARCHAR(55)       NOT NULL,
				 lastname            VARCHAR(55)       NOT NULL,
				 email               VARCHAR(55)       NOT NULL,
				 phone               VARCHAR(55)       NOT NULL,
				 unique_id           VARCHAR(255)      NOT NULL,
				 access_level        TINYINT NOT NULL DEFAULT 2,
	             created_at          TIMESTAMP,
	             updated_at          TIMESTAMP,	 
	             PRIMARY KEY(officer_id))
	             ENGINE=MyISAM';

	 if($db->query($query) == TRUE){
	 	echo 'Created Officers table successfully.';
	 }else{
	 	echo 'Server Error while Creating Officers Table:

	 	 '. $db->error;
	 }

	 }else{
		 echo 'DATABASE NOT CREATED';
	 } 
?>
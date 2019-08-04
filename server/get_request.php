<?php

	$response = array();

	$input = json_decode($inputJSON, TRUE);

	$allow = (bool) rand(0, 1);
	$response["allow"] = $allow;
	$response["error"] = false;

    echo json_encode($response);
?>
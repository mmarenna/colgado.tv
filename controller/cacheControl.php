<?php
$environment = getenv('VIEW_VERSION');
$parameter= $_GET['v'];
if (array_key_exists('player', $model) || (( $environment != $parameter)) ){
	header("Cache-Control: no-cache, no-store, must-revalidate"); // HTTP 1.1.
	header("Pragma: no-cache"); // HTTP 1.0.
	header("Expires: 0");
}

echo "environment:";
var_dump($environment);
echo "param;";	
var_dump($parameter);

?>
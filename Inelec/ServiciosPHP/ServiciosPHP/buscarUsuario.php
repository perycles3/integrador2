<?php

$codigo = $_REQUEST['id'];

$cnx=new PDO ("mysql:host=192.168.8.108;dbname=inelec.db","RODRIGO","70674357");

$res = $cnx->query("select * from usuario where id = '$codigo'");

$datos=array();

foreach ($res as $row){
$datos[]=$row;
}

echo json_encode($datos);

?>

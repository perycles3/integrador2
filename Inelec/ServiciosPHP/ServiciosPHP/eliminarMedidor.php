<?php
$codigo=$_REQUEST['id'];

$cnx=new PDO("mysql:host=192.168.8.108;dbname=inelec.db","RODRIGO","70674357");

$cnx->query("delete from medidor where id = '$codigo'");
?>

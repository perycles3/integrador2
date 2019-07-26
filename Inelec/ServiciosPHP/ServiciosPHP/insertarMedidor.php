<?php
$codigo=$_REQUEST['id'];
$numeroContrato=$_REQUEST['numeroContrato'];
$nombreTitular=$_REQUEST['nombreTitular'];
$direccion=$_REQUEST['direccion'];
$coordenada=$_REQUEST['coordenada'];
$idEstado=$_REQUEST['idEstado'];

$cnx=new PDO("mysql:host=192.168.8.108;dbname=inelec.db","RODRIGO","70674357");

$cnx->query("insert into medidor (id, numeroContrato, nombreTitular, direccion, coordenada, idEstado)
values('$codigo','$numeroContrato','$nombreTitular','$direccion','$coordenada','$idEstado')");

?>

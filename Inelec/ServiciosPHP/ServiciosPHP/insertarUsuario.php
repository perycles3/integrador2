<?php
$codigo=$_REQUEST['id'];
$nombre=$_REQUEST['nombre'];
$dni=$_REQUEST['dni'];
$correo=$_REQUEST['correo'];
$telefono=$_REQUEST['telefono'];
$clave=$_REQUEST['clave'];
$codigoestado=$_REQUEST['idEstado'];
$cantidadingreso=$_REQUEST['cantidadingreso'];
$codigorol=$_REQUEST['idRol'];

$cnx=new PDO("mysql:host=192.168.8.108;dbname=inelec.db","RODRIGO","70674357");

$cnx->query("insert into usuario (id, nombre, dni, correo, telefono, clave, idEstado, cantidadingreso, idRol)
values('$codigo','$nombre','$dni','$correo','$telefono','$clave','$codigoestado','$cantidadingreso','$codigorol')");

?>


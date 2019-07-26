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

$cnx->query("update usuario set nombre = '$nombre', dni = '$dni', correo = '$correo', telefono = '$telefono', clave = '$clave',
                    idEstado = '$codigoestado' , cantidadingreso = '$cantidadingreso', idRol = '$codigorol' where id = '$codigo'");

?>

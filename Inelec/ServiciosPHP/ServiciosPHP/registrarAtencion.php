<?php
$codigo=$_REQUEST['id'];
$fechaAtencion=$_REQUEST['fechaAtencion'];
$idUsuario=$_REQUEST['idUsuario'];
$descripcion=$_REQUEST['descripcion'];
$idEstado=$_REQUEST['idEstado'];
$idEstado=$_REQUEST['idEstado'];

$cnx=new PDO("mysql:host=192.168.8.108;dbname=inelec.db","RODRIGO","70674357");

$cnx->query("update incidencia set fechaAtencion = '$fechaAtencion', idUsuario = '$idUsuario',
                    descripcion = '$descripcion', idEstado = '$idEstado' where id = '$codigo'");
?>

<?php

$usuario=$_REQUEST['user'];
$clave=$_REQUEST['pass'];

$cnx=new PDO("mysql:host=192.168.8.108;dbname=inelec.db","RODRIGO","70674357");

$res=$cnx->query("select usuario.id, usuario.nombre, roles.nombreRol from usuario inner join
                         roles on roles.id = usuario.idRol where usuario.id= '$usuario' and usuario.clave='$clave'");

$datos=array();

foreach ($res as $row){
$datos[]=$row;
}

echo json_encode($datos);

?>

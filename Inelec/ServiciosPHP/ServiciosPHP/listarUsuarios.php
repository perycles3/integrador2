<?php

$cnx=new PDO("mysql:host=192.168.8.108;dbname=inelec.db","RODRIGO","70674357");

$res=$cnx->query("select usuario.id, usuario.nombre, usuario.dni, roles.nombreRol from usuario inner join
        		roles on usuario.idrol = roles.id");
$datos=array();

foreach ($res as $row){
$datos[]=$row;
}

echo json_encode($datos);
?>

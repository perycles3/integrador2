<?php

$cnx=new PDO("mysql:host=192.168.8.108;dbname=inelec.db","RODRIGO","70674357");

$res=$cnx->query("select tipoincidencia.nombreTipo, medidor.direccion, incidencia.id from incidencia inner join
                         estado on estado.id = incidencia.idestado inner join
                         medidor on medidor.id = incidencia.idmedidor inner join
                         tipoincidencia on tipoincidencia.id = incidencia.idtipo where estado.nombreEstado = 'Activo'");
$datos=array();

foreach ($res as $row){
$datos[]=$row;
}

echo json_encode($datos);
?>

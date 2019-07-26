<?php

$codigo = $_REQUEST['id'];

$cnx=new PDO ("mysql:host=192.168.8.108;dbname=inelec.db","RODRIGO","70674357");

$res = $cnx->query("select tipoincidencia.nombreTipo, medidor.direccion, medidor.numeroContrato, medidor.nombreTitular, incidencia.fechaIncidencia
        		from incidencia inner join
        		medidor on medidor.id = incidencia.idmedidor inner join
        		tipoincidencia on tipoincidencia.id = incidencia.idtipo where incidencia.id = '$codigo'");

$datos=array();

foreach ($res as $row){
$datos[]=$row;
}

echo json_encode($datos);

?>


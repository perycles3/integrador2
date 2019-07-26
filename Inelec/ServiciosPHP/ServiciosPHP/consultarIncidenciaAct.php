<?php

$codigoInc = $_REQUEST['codigo'];

$cnx=new PDO ("mysql:host=192.168.8.108;dbname=inelec.db","RODRIGO","70674357");

$res = $cnx->query("select incidencia.id, tipoincidencia.nombreTipo, medidor.numeroContrato, incidencia.fechaIncidencia, incidencia.fechaAtencion,
			incidencia.descripcion , estado.nombreEstado from incidencia  inner join
			medidor on medidor.id = incidencia.idmedidor inner join
			estado on estado.id = incidencia.idestado inner join
			tipoincidencia on tipoincidencia.id = incidencia.idtipo where incidencia.id = '$codigoInc' or medidor.id =
			(select medidor.id from medidor where medidor.numeroContrato = '$codigoInc')");
			
$datos=array();

foreach ($res as $row){
$datos[]=$row;
}

echo json_encode($datos);

?>

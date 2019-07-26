<?php
$codigo=$_REQUEST['codigo'];

$cnx=new PDO("mysql:host=192.168.8.108;dbname=inelec.db","RODRIGO","70674357");

$res = $cnx->query("select estado.nombreEstado from incidencia inner join
medidor on medidor.id = incidencia.idmedidor inner join
estado on estado.id = incidencia.idestado where incidencia.id = '$codigo' or medidor.id =
(select medidor.id from medidor where medidor.numeroContrato = '$codigo')");

$datos=array();

foreach ($res as $row){
$datos[]=$row;
}

echo json_encode($datos);

?>

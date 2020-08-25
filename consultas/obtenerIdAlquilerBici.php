<?php
include('../conexion.php'); 
//$fecha=date("Y-m-d");
//$nick = "pepe";
//$nick=$_POST['nick'];
$nick=$_GET['usuario'];
//$idpersona=37;

if ($resultset = getSQLResultSet("SELECT
personas.nick,
personas.telefono,
info_bicicletas.dia,
   alquiler_bicicletas.id_alquiler_bicicletas
FROM
alquiler_bicicletas,
info_bicicletas,
personas
WHERE
personas.idpersona = alquiler_bicicletas.idpersona AND alquiler_bicicletas.id_info_bicicletas = info_bicicletas.id_info_bicicletas AND personas.nick = '$nick'")) {
    	while ($row = $resultset->fetch_array(MYSQLI_NUM)) {
    	echo json_encode($row);		
    	
    	}
    	
   }

?>
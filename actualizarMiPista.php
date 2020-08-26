<?php

include('conexion.php'); 

//$idalquiler=$_GET['idalquiler'];
$idalquiler=241;
$id_info_posible_reserva = 379;
$idperson = 2;
$pagado = 'si';
$material = 'si';
$devuelto = 'si';
//$idalquiler = 240;
if ($resultset = getSQLResultSet("UPDATE alquileres SET id_info_posible_reserva = '$id_info_posible_reserva', idpersona = '$idperson', pagado = '$pagado', material = '$material', devuelto = '$material' WHERE idalquiler ='$idalquiler'  ")) {
    	while ($row = $resultset->fetch_array(MYSQLI_NUM)) {
    	echo json_encode($row);
		
    	
    	}
    	
   }
   
?>
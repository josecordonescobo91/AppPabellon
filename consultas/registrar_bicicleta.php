<?php 

	include 'conexionLoginUsuario.php';
//$nick=$_POST['nick'];
//$pass=$_POST['pass'];
/*	$nick = $_POST['nick'];
    $pass = $_POST['pass'];
    $nombre = $_POST['nombre'];
    $apellido1 = $_POST['apellido1'];
    $apellido2 = $_POST['apellido2'];
    $tipo = "usuario";
    $telefono = $_POST['telefono'];
    $email = $_POST['email'];
    $direccion = $_POST['direccion'];
    $localidad = $_POST['localidad'];
    $provincia = $_POST['provincia'];
    $activo = "pendiente";*/
	$id_info_bicicletas=1;
    $idpersona = 2;
    $telefono = 666666661;
  /*  $apellido1 = "sadasd";
    $apellido2 = "sadsad";
    $tipo = "asdsad";
    $telefono = 6666666;
    $email = "sdfasda";
    $direccion = "sadsad";
    $localidad = "sdasdsa";
    $provincia = "sdsadasd";
    $activo = "sadsdasdasd";*/
	//$pass="aaa";

	$sentencia=$conexion->prepare("INSERT INTO personas (id_info_bicicletas, idpersona, telefono) VALUES (?,?,?)");
	$sentencia->bind_param('iii',$id_info_bicicletas,$idpersona,$telefono);
	$sentencia->execute();

	$resultado = $sentencia->get_result();

	$sentencia->close();
	$conexion->close();




 ?>
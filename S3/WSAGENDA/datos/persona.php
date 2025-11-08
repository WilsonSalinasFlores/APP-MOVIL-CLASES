<?php
    require_once './models/cls_conexion.php';
    header ('Access-Control-Allow-Origin: *');
    header ('Access-Control-Allow-Credentials:true');
    header ('Access-Control-Allow-Methods: PUT,GET,POST,DELETE,OPTIONS');
    header ('Access-Control-Allow-Headers: Origin, Content-Type, Authorization, Accept, X-Requested-With, x-xsrf-token');
    header ('ContentType:application/json; charset=utf-8');
    $post=json_decode( file_get_contents('php://input'), true);
    if($post['accion']=="login")
{
    $conex= new Cls_conexion();
    $conex=$conex->conectar();
    $sentencia=sprintf("SELECT cod_persona from persona
    where ci_persona='%s' and clave_persona='%s'",
    $conex->real_escape_string($post['usuario']),
            $conex->real_escape_string($post['clave']));
    $rs= mysqli_query($conex,$sentencia);
    if(mysqli_num_rows($rs)>0)
    {
        $row= mysqli_fetch_assoc($rs);
        $respuesta=json_encode(array("estado"=>true, "codigo"=>$row['cod_persona']));
    }
    else
    {
        $respuesta=json_encode(array("estado"=>false, "mensaje"=>"Usuario o clave incorrecto"));
    }
echo $respuesta;
}
 
    
?>
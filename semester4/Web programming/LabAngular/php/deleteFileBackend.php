<?php
header("Access-Control-Allow-Methods: DELETE, POST, GET, OPTIONS");
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    return 0;
}
require_once "utils/configuration.php";
global $connection;
if (isset($_POST['id']) && !empty(trim($_POST['id']))) {
    $id = $_POST['id'];
    $sql_query = "delete from multimediafiles where id = '$id';";
    $result = mysqli_query($connection, $sql_query);
} else {
    $postdata = file_get_contents("php://input");
 
    $request = json_decode($postdata);

    $id = $request->id;

   

    $sql_query = "delete from multimediafiles where id = '$id';";
    $result = mysqli_query($connection, $sql_query);

}
mysqli_close($connection);

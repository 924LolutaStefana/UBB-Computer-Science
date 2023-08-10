<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "utils/configuration.php";
if (isset($_POST['title']) && !empty(trim($_POST['title']))) {
    $id = $_POST['id'];
    $title = $_POST['title'];
    $format_type = $_POST['format_type'];
    $genre = $_POST['genre'];
    $path = $_POST['path'];
    $sql_query = "update multimediafiles set title='$title', format_type = '$format_type', genre = '$genre', path = '$path' where id = $id";

    global $connection;
    $result = mysqli_query($connection, $sql_query);
    mysqli_close($connection);
} else {
    $postdata = file_get_contents("php://input");
    $request = json_decode($postdata);
    $id = $request->id;
    $title = $request->title;
    $format_type = $request->format_type;
    $genre = $request->genre;
    $path = $request->path;
    
    $sql_query = "update multimediafiles set title='$title', format_type = '$format_type', genre = '$genre', path = '$path' where id = $id";
    global $connection;
    $result = mysqli_query($connection, $sql_query);
    mysqli_close($connection);
}
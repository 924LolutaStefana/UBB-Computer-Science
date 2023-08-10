<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "utils/configuration.php";
if (isset($_POST['title']) && !empty(trim($_POST['title']))) {
    $title= $_POST['title'];
    $format_type = $_POST['format_type'];
    $genre = $_POST['genre'];
    $path = $_POST['path'];
    
    $sql_query = "insert into multimediafiles(title, format_type, genre, path) values ('$title', '$format_type', '$genre', '$path')";
    global $connection;
    $result = mysqli_query($connection, $sql_query);
    mysqli_close($connection);
} else {
    $postdata = file_get_contents("php://input");
    $request = json_decode($postdata);
    $title = $request->title;
    $format_type = $request->format_type;
    $genre = $request->genre;
    $path = $request->path;
   
    $sql_query = "insert into multimediafiles(title, format_type, genre, path) values (?, ?, ?, ?)";
    global $connection;
    $stmt = $connection->prepare($sql_query);
    $stmt->bind_param("ssss", $title, $format_type, $genre, $path);
    $stmt->execute();
   
}
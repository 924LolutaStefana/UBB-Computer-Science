<?php
require_once "config.php";
$title= $_POST['title'];
$format_type = $_POST['format_type'];
$genre = $_POST['genre'];

$path= $_POST['path'];

$sql_query = "insert into multimediafiles(title, format_type, genre, path) values('$title', '$format_type', '$genre', '$path');";
global $conn;
$result = mysqli_query($conn, $sql_query);
if ($result) {
    echo "Your file was added successfully!";
    header("Location: show.html");
} else {
    echo "Something went wrong.";
}
mysqli_close($conn);
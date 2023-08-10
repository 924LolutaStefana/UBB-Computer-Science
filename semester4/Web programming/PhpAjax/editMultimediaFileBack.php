

<?php
require_once "config.php";
$id = $_POST['id'];
$title = $_POST['title'];
$format_type = $_POST['format_type'];
$genre = $_POST['genre'];
$path = $_POST['path'];
$sql_query = "update multimediafiles set title='$title', format_type = '$format_type', genre = '$genre', path = '$path' where id = $id";

global $conn;
$result = mysqli_query($conn, $sql_query);
if ($result) {
    echo "Your user was updated successfully!";
    header("Location: show.html");
} else {
    echo "Oops!Something went wrong and your document cannot be added!Please try again later.";
}
mysqli_close($connection);
<?php
require_once "config.php";
global $conn;
if (isset($_POST['id']) && !empty(trim($_POST['id']))) {
    $id = $_POST['id'];
    $sql_query = "delete from multimediafiles where id = '$id';";
    $result = mysqli_query($conn, $sql_query);
    if ($result) {
        echo "Your file was deleted successfully!";
        header("Location: show.html");
    } else {
        echo "Oops! Something went wrong and your file cannot be deleted! Please try again later.";
    }
}
mysqli_close($conn);
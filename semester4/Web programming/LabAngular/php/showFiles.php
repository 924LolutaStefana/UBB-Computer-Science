<?php
header("Access-Control-Allow-Origin: *");
require_once 'utils/configuration.php';
$sql_query = "SELECT * FROM multimediafiles;";
global $connection;
$result = mysqli_query($connection, $sql_query);

if ($result) {
    $number_of_rows = mysqli_num_rows($result);
    $requested_files = array();
    $title = $_GET["title"];
    $genre = $_GET["genre"];
    for ($i = 0; $i < $number_of_rows; $i++) {
        $row = mysqli_fetch_array($result);
        if (str_contains($row["title"], $title) && str_contains($row["genre"], $genre))
            array_push( $requested_files, array(
                "id" => (int)$row['id'],
                "title" => $row['title'],
                "format_type" => $row['format_type'],
                "genre" => $row['genre'],
                "path" => $row['path'],
            ));
    }
    mysqli_free_result($result);
    echo json_encode($requested_files);
}
mysqli_close($connection);

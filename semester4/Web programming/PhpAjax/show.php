<?php
require_once 'config.php';
$sql_query = "SELECT * FROM multimediafiles;";
global $conn;
$result = mysqli_query($conn, $sql_query);

if ($result) {
    $number_of_rows = mysqli_num_rows($result);
    $requested_files = array();
    $genre = $_GET["genre"];
    $title = $_GET["title"];
    for ($i = 0; $i < $number_of_rows; $i++) {
        $row = mysqli_fetch_array($result);
       

        if (str_contains($row["genre"], $genre) && str_contains($row["title"], $title))
            array_push($requested_files, array($row['id'], $row['title'], $row['format_type'], $row['genre'], $row['path']));
    }
    
    usort($requested_files, function ($a, $b) {
        return $a[3] <=> $b[3];
    });
    
   
    mysqli_free_result($result);
    echo json_encode($requested_files);
}
mysqli_close($conn);
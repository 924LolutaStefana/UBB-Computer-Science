

<?php
require_once "config.php";
global $conn;
$id = "";
$title = "";
$format_type = "";
$genre = "";
$path= "";

if (isset($_GET['id']) && !empty(trim($_GET['id']))) {
    $id = trim($_GET['id']);
    $sql_query = "select * from multimediafiles where id = $id;";
    $result = mysqli_query($conn, $sql_query);
    if ($result) {
        $number_of_rows = mysqli_num_rows($result);
        if ($number_of_rows == 1) {
            $row = mysqli_fetch_array($result);
            $title = $row['title'];
            $format_type= $row['format_type'];
            $genre = $row['genre'];
            
            $path = $row['path'];
            
        } else {
            die("Incorrect  id");
        }
    } else {
        die("Something went wrong");
    }
    mysqli_close($conn);
} else die("Something went wrong ");
?>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="addMultimediaFile.css">
    <title>Edit Multimedia File</title>

</head>

<body>
<div class="container">
    <h1>Update Multimedia File</h1>
    

    <form action="editMultimediaFileBack.php" method="post">
        <input type="hidden" name="id" value="<?php echo trim($_GET['id']); ?>">
        <input type="text" name="title" placeholder="Title:" value="<?php echo $title ?>"> <br>
        <input type="text" name="format_type" placeholder="Format type:" value="<?php echo $format_type ?>"> <br>
        <input type="text" name="genre" placeholder="Genre:" value="<?php echo $genre ?>"> <br>
        <input type="text" name="path" placeholder="Path:" value="<?php echo $path ?>"> <br>
        
        <div class="button_container">
            <button type="submit">Update</button>
            <button type="reset" onclick="window.location.href='show.html'">Cancel</button>
        </div>
    </form>
</div>
</body>

</html>
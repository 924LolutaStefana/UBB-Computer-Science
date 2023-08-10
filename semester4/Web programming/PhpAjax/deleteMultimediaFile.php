<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Delete Multimedia Fiile</title>
    
</head>

<body>
<h1>Delete Multimedia File</h1>

<div class="container">
    <p><b>Are you sure you want to delete this file?</b></p>

    <form action="deleteMultimediaFileBack.php" method="post">
        <input type="hidden" name="id" value="<?php echo trim($_GET['id']); ?>">
        <button type="submit" class="yes">YES</button>
    </form>
    <button class="no" onclick="window.location.href='show.html'">
        NO
    </button>
</div>
</body>

</html>
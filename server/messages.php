<?php
include 'entities.php';

$link = mysqli_connect("***", "***", "***", "***", 0);

if (isset($_GET["mode"])){ 
    if ($_GET["mode"] == "select"){
        $query = "SELECT * FROM Messages WHERE toId=".$_GET["userId"]." OR userId=".$_GET["userId"];
        $result = mysqli_query($link, $query);

        $res = array();

        while ($row = mysqli_fetch_assoc($result)) {
            $msg = new Message((int)$row["id"], (int)$row["userId"], (int)$row["toId"], $row["text"], $row["date"]);
            array_push($res, $msg);
        }

        echo json_encode($res);

    } else if ($_GET["mode"] == "insert"){
        $query = "INSERT INTO Messages (`userId`, `toId`, `text`, `date`) VALUES (".$_GET["userId"].", ".$_GET["toId"].", '".$_GET["text"]."', '".$_GET["date"]."')";
        echo $query;
        $result = mysqli_query($link, $query);
        echo "inserted";
    }
} else {
    echo "Err";
}
mysqli_close($link);

?>
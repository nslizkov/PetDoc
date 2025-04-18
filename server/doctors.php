<?php
include 'entities.php';

$link = mysqli_connect("***", "***", "***", "***", 0);

if (isset($_GET["mode"])){ 
    if ($_GET["mode"] == "delete"){
        $query = "DELETE FROM Doctors WHERE id=".$_GET["id"]." AND name='".$_GET["name"]."' AND docType='".$_GET["docType"]."' AND animalType='".$_GET["animalType"]."' AND date='".$_GET["date"]."' AND price=".$_GET["price"];
        echo $query;
        $result = mysqli_query($link, $query);
        echo "deleted";
    } else if ($_GET["mode"] == "insert"){
        $query = "INSERT INTO `Doctors` (`name`, `docType`, `animalType`, `date`, `price`) VALUES ('".$_GET["name"]."', '".$_GET["docType"]."', '".$_GET["animalType"]."', '".$_GET["date"]."', ".$_GET["price"].")";
        //echo $query;
        $result = mysqli_query($link, $query);
       // echo "inserted";
    }
} else {
    $result = mysqli_query($link, "SELECT * FROM Doctors");
    
    $res = array();

    while ($row = mysqli_fetch_assoc($result)) {
        $doc = new Doctor((int)$row["id"], $row["name"], $row["docType"], $row["animalType"], $row["date"], (int)$row["price"]);
        array_push($res, $doc);
    }

    echo json_encode($res);
}

mysqli_close($link);
?>
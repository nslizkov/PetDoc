<?php

class Doctor
{
    public $name = "";
    public $docType = "";
    public $animalType = "";
    public $date = "";
    public $id = 0;
    public $price = 0;

 
    function __construct($id, $name, $docType, $animalType, $date, $price){
        $this->id = $id;
        $this->name = $name;
        $this->price = $price;
        $this->animalType = $animalType;
        $this->date = $date;
        $this->docType = $docType;
    }
}

class Message
{
    public $id = 0;
    public $text = "";
    public $date = "";
    public $userId = 0;
    public $toId = 0;

 
    function __construct($id, $userId, $toId, $text, $date){
        $this->id = $id;
        $this->text = $text;
        $this->date = $date;
        $this->userId = $userId;
        $this->toId = $toId;
    }
}

?>
<?php
echo '<?xml version="1.0" encoding="UTF-8"?>';
?>

<infos_pays>
    <?php

    require_once "PaysDAO.php";
    $listepays = \donnees\maintenance_donnees\DAO\PaysDAO::listerPays()->fetchAll(PDO::FETCH_OBJ);


    header("Access-Control-Allow-Origin: *");
    header ("Content-Type:text/xml");


    foreach($listepays as $pays) {
    ?>


<pays>
    <nom><?=$pays->pays?></nom>
    <habitants><?=$pays->habitants?></habitants>
    <PIB><?=$pays->PIB?></PIB>
    <moyenneAge><?=$pays->moyenneAge?></moyenneAge>
    <moyenneAgeVie><?=$pays->moyenneAgeVie?></moyenneAgeVie>
    <superficie><?=$pays->superficie?></superficie>
</pays>

    <?php
    }
    ?>

</infos_pays>

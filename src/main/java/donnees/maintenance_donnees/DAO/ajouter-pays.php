<?php

require_once "PaysDAO.php";

if (isset($_POST['pays'], $_POST['habitants'], $_POST['PIB'], $_POST['moyenneAge'], $_POST['moyenneAgeVie'], $_POST['superficie'])) {
    \donnees\maintenance_donnees\DAO\PaysDAO::ajouterPays($_POST['pays'], $_POST['habitants'], $_POST['PIB'], $_POST['moyenneAge'], $_POST['moyenneAgeVie'], $_POST['superficie']);
} else {
    http_response_code(400); // Code de statut HTTP Mauvaise requête
}

<?php

use donnees\maintenance_donnees\Connexion;

if (isset($_POST['pays'], $_POST['habitants'], $_POST['PIB'], $_POST['moyenneAge'], $_POST['moyenneAgeVie'], $_POST['superficie'])) {
    $requete = Connexion::getBDD()->prepare("INSERT INTO infos_pays (pays,habitants,PIB,moyenneAge,moyenneAgeVie,superficie) VALUES (?,?,?,?,?,?)");
    $requete->bindColumn(0, $_POST['pays']);
    $requete->bindColumn(1, $_POST['habitants']);
    $requete->bindColumn(2, $_POST['PIB']);
    $requete->bindColumn(3, $_POST['moyenneAge']);
    $requete->bindColumn(4, $_POST['moyenneAgeVie']);
    $requete->bindColumn(5, $_POST['superficie']);
    if ($requete->execute()) {
        http_response_code(200); // Code de statut HTTP OK
        echo "La requête a été exécutée avec succès.";
    } else {
        http_response_code(500); // Code de statut HTTP Erreur du serveur
        echo "Une erreur est survenue lors de l'exécution de la requête.";
    }
} else {
    http_response_code(400); // Code de statut HTTP Mauvaise requête
    echo "Les paramètres de la requête sont manquants ou invalides.";
}

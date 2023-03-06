<?php

namespace donnees\maintenance_donnees\DAO;

use PDO;

class PaysDAO
{
    public static function getBDD() : PDO {
        error_reporting(E_ALL);
        ini_set('display_errors', 1);

        $usager = 'superadmin';
        $motdepasse = 'n]3Gxdd2#)g6!A.B7*6{8w^Tf2UGW5eZ';
        $hote = 'localhost';
        $base = 'maintenance_donnees';

        $dsn = "mysql:host=$hote;dbname=$base;";
        $basededonnees = new PDO($dsn, $usager, $motdepasse);
        return $basededonnees;
    }

    public static function ajouterPays(string $pays, string $habitants, string $PIB, string $moyenneAge, string $moyenneAgeVie, string $superficie) {
        $pays = filter_var($pays, FILTER_SANITIZE_STRING);
        $habitants = filter_var($habitants, FILTER_SANITIZE_STRING);
        $PIB= filter_var($PIB, FILTER_SANITIZE_STRING);
        $moyenneAge = filter_var($moyenneAge, FILTER_SANITIZE_STRING);
        $moyenneAgeVie = filter_var($moyenneAgeVie, FILTER_SANITIZE_STRING);
        $superficie = filter_var($superficie, FILTER_SANITIZE_STRING);

        $requete = PaysDAO::getBDD()->prepare("INSERT INTO infos_pays (pays,habitants,PIB,moyenneAge,moyenneAgeVie,superficie) VALUES ('".$pays."',".$habitants.",".$PIB.",".$moyenneAge.",".$moyenneAgeVie.",".$superficie.")");

        if ($requete->execute()) {
            http_response_code(200); // Code de statut HTTP OK
        } else {
            http_response_code(500); // Code de statut HTTP Erreur du serveur
        }
    }

    public static function listerPays() {
        $requete = PaysDAO::getBDD()->prepare("SELECT * FROM infos_pays");
        $requete->execute();
        return $requete;
    }
}
<?php

namespace donnees\maintenance_donnees;

use PDO;

class Connexion
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
}
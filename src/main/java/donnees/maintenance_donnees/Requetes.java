package donnees.maintenance_donnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Requetes {

    private static Connection connection;

    public Requetes() {
    }

    public static String[] selectFromCountry(String pays, boolean habitants, boolean PIB, boolean moyenneAge, boolean moyenneAgeVie) {
        connection = DBConnection.getConnection();
        String[] retour = new String[5];

        boolean allTrue = !habitants && !PIB && !moyenneAge && !moyenneAgeVie;

        // Préparation de la requête
        String requete = "SELECT";
        if (habitants) requete += " habitants,";
        if (PIB) requete += " PIB,";
        if (moyenneAge) requete += " moyenneAge,";
        if (moyenneAgeVie) requete += " moyenneAgeVie  ";

        // Si toutes les cases ne sont pas cochées, afficher toutes les informations sur le pays
        if (!habitants && !PIB && !moyenneAge && !moyenneAgeVie) requete += " habitants, PIB, moyenneAge, moyenneAgeVie  ";

        // Retire la virgule de fin
        requete = requete.substring(0, requete.length()-1);
        requete += " FROM infos_pays WHERE pays = '"+pays+"'";

        try {
            PreparedStatement statement = connection.prepareStatement(requete);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                if (habitants || allTrue)
                    retour[0] = rs.getString("habitants");
                if (PIB || allTrue)
                    retour[1] = rs.getString("PIB");
                if (moyenneAge || allTrue)
                    retour[2] = rs.getString("moyenneAge");
                if (moyenneAgeVie || allTrue)
                    retour[3] = rs.getString("moyenneAgeVie");
            }
        } catch (SQLException e) {
            System.out.println("Erreur dans la requête du choix des informations du pays");
            throw new RuntimeException(e);
        }

        return retour;
    }

    public static ArrayList<String> selectCountries() {
        connection = DBConnection.getConnection();
        ArrayList<String> retour = new ArrayList<String>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT pays FROM infos_pays");
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                retour.add(rs.getString("pays"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur dans la requête des pays");
            throw new RuntimeException(e);
        }

        return retour;
    }
}

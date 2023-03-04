package donnees.maintenance_donnees.vue;

import donnees.maintenance_donnees.Requetes;
import donnees.maintenance_donnees.XMLParsing;
import donnees.maintenance_donnees.controleur.controllerValiderPressed;
import donnees.maintenance_donnees.modele.Modele;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VueResultatRecherche extends BorderPane {

    private Modele modele;

    public VueResultatRecherche(Modele modele) {
        this.modele = modele;

        /// Booléens des cases cochées
        boolean boolH = modele.getInformationsCourantes(0);
        boolean boolP = modele.getInformationsCourantes(1);
        boolean boolMA = modele.getInformationsCourantes(2);
        boolean boolMAV = modele.getInformationsCourantes(3);
        boolean boolS = modele.getInformationsCourantes(4);
        // Booléen supplémentaire si toutes les cases sont cochées
        boolean boolAll = !boolH && !boolP && !boolMA && !boolMAV && !boolS;

        Label pays = new Label("Pays choisi : "+modele.getPays());
        pays.setFont(new Font(40));

        VBox informations = new VBox();
        informations.setPadding(new Insets(20,0,20,0));
        informations.setMaxWidth(400);
        informations.setAlignment(Pos.CENTER);
        informations.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(10), null)));
        informations.setBorder(new Border(new javafx.scene.layout.BorderStroke(javafx.scene.paint.Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(3))));

        /// Résultat de la requête suite aux cases cochées
        String[] resultatRequete = XMLParsing.selectFromCountry(modele.getPays(), boolH, boolP,boolMA, boolMAV, boolS);

        // Affichage des informations désirées
        // Habitants
        if (boolH || boolAll) {
            Label habitants = new Label("Nombre d'habitants : "+resultatRequete[0]);
            habitants.setPadding(new Insets(20,0,20,0));
            habitants.setFont(new Font(15));
            informations.getChildren().add(habitants);
        }
        // PIB
        if (boolP || boolAll) {
            Label PIB = new Label("PIB (€) : "+resultatRequete[1]);
            PIB.setPadding(new Insets(20,0,20,0));
            PIB.setFont(new Font(15));
            informations.getChildren().add(PIB);
        }
        // moyenneAge
        if (boolMA || boolAll) {
            Label moyenneAge = new Label("Moyenne d'âge (en années) : "+resultatRequete[2]);
            moyenneAge.setPadding(new Insets(20,0,20,0));
            moyenneAge.setFont(new Font(15));
            informations.getChildren().add(moyenneAge);
        }
        // Habitants
        if (boolMAV || boolAll) {
            Label moyenneAgeVie = new Label("Moyenne d'espérance de vie (en années) : "+resultatRequete[3]);
            moyenneAgeVie.setPadding(new Insets(20,0,20,0));
            moyenneAgeVie.setFont(new Font(15));
            informations.getChildren().add(moyenneAgeVie);
        }

        if (boolS || boolAll) {
            Label superficie = new Label("Superficie ( en km² ) : "+resultatRequete[4]);
            superficie.setPadding(new Insets(20,0,20,0));
            superficie.setFont(new Font(15));
            informations.getChildren().add(superficie);
        }
        Button retourMenu = new Button("Revenir au menu du choix");
        retourMenu.setOnAction(new controllerValiderPressed(modele));

        VBox main_vb = new VBox(pays, informations, retourMenu);
        main_vb.setSpacing(50);
        main_vb.setAlignment(Pos.CENTER);

        this.setCenter(main_vb);
    }

}

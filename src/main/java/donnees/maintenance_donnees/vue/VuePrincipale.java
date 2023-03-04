package donnees.maintenance_donnees.vue;

import donnees.maintenance_donnees.Requetes;
import donnees.maintenance_donnees.XMLParsing;
import donnees.maintenance_donnees.controleur.controllerValiderPressed;
import donnees.maintenance_donnees.modele.Modele;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Iterator;

public class VuePrincipale extends BorderPane {

    private Modele modele;

    public VuePrincipale(Modele modele) {
        this.modele = modele;
        ChoiceBox<String> pays = new ChoiceBox<String>();
        ArrayList<String> listePays = XMLParsing.selectCountries();
        Iterator<String> it = listePays.iterator();
        while (it.hasNext()) {
            pays.getItems().add(it.next());
        }
        pays.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                modele.setPays(pays.getValue());
            }
        });
        CheckBox habitants = new CheckBox("Habitants");
        CheckBox PIB = new CheckBox("PIB");
        CheckBox moyenneAge = new CheckBox("Moyenne d'âge");
        CheckBox moyenneAgeVie = new CheckBox("Moyenne de temps de vie");
        CheckBox superficie = new CheckBox("Superficie");

        /// Remise des informations dans le modèle à false
        modele.setInformationsCourantes(false, 0);
        modele.setInformationsCourantes(false, 1);
        modele.setInformationsCourantes(false, 2);
        modele.setInformationsCourantes(false, 3);
        modele.setInformationsCourantes(false, 4);

        /// Controleurs sur les Checkbox des informations à afficher
        habitants.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                modele.setInformationsCourantes(habitants.isSelected(), 0);
            }
        });
        PIB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                modele.setInformationsCourantes(PIB.isSelected(), 1);
            }
        });
        moyenneAgeVie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                modele.setInformationsCourantes(moyenneAgeVie.isSelected(), 3);
            }
        });
        moyenneAge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                modele.setInformationsCourantes(moyenneAge.isSelected(), 2);
            }
        });

        superficie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                modele.setInformationsCourantes(superficie.isSelected(), 4);
            }
        });

        HBox hb = new HBox(pays,habitants,PIB,moyenneAge,moyenneAgeVie,superficie);
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        // Controleur sur la HBox pour changer les valeurs dans le modèle
        Button valider = new Button("Voir les informations");
        valider.setOnAction(new controllerValiderPressed(modele));
        Label info = new Label("* Si aucune case n'est cochée, toutes les informations sur un pays vont seront affichées");
        info.setFont(new Font(9));
        VBox vb = new VBox(hb,valider,info);
        vb.setAlignment(Pos.CENTER);
        this.setCenter(vb);
        vb.setSpacing(20);
    }

}

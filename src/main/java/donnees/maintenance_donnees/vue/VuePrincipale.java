package donnees.maintenance_donnees.vue;

import donnees.maintenance_donnees.controleur.controllerValiderPressed;
import donnees.maintenance_donnees.interfaces.Observateur;
import donnees.maintenance_donnees.interfaces.Sujet;
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

public class VuePrincipale extends BorderPane implements Observateur {

    private Modele modele;

    public VuePrincipale(Modele modele) {
        this.modele = modele;
        ChoiceBox<String> pays = new ChoiceBox<String>();
        pays.getItems().add("France");
        pays.getItems().add("Canada");
        pays.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                modele.setPays(pays.getValue());
                System.out.println(modele.getPays());
            }
        });
        CheckBox habitants = new CheckBox("Habitants");
        habitants.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        CheckBox PIB = new CheckBox("PIB");
        CheckBox moyenneAge = new CheckBox("Moyenne d'âge");
        CheckBox moyenneAgeVie = new CheckBox("Moyenne de temps de vie");
        HBox hb = new HBox(pays,habitants,PIB,moyenneAge,moyenneAgeVie);
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

    @Override
    public void actualiser(Sujet s) {

    }
}

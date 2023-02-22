package donnees.maintenance_donnees.vue;

import donnees.maintenance_donnees.controleur.controllerValiderPressed;
import donnees.maintenance_donnees.interfaces.Observateur;
import donnees.maintenance_donnees.interfaces.Sujet;
import donnees.maintenance_donnees.modele.Modele;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class VueResultatRecherche extends BorderPane implements Observateur {

    private Modele modele;

    public VueResultatRecherche(Modele modele) {
        this.modele = modele;
        Label pays = new Label();
        Button retourMenu = new Button("Revenir au menu du choix");
        retourMenu.setOnAction(new controllerValiderPressed(modele));
        this.setCenter(retourMenu);
    }

    @Override
    public void actualiser(Sujet s) {

    }
}

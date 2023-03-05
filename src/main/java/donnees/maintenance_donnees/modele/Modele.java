package donnees.maintenance_donnees.modele;

import donnees.maintenance_donnees.vue.VueAjouter;
import donnees.maintenance_donnees.vue.VuePrincipale;
import donnees.maintenance_donnees.vue.VueResultatRecherche;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Modele {

    private ResultSet resultatRequete;
    private String currentScene;
    private Stage currentStage;

    private String pays;
    private boolean[] informationsCourantes;

    public Modele() {
        this.currentScene = "choice";
        this.resultatRequete = null;
        this.informationsCourantes = new boolean[10];
        this.pays = null;
    }

    public void setPays(String p) {
        pays = p;
    }

    public String getPays() {
        return pays;
    }

    public void setInformationsCourantes(boolean informationsCourantes, int index) {
        this.informationsCourantes[index] = informationsCourantes;
    }

    public boolean getInformationsCourantes(int index) {
        return informationsCourantes[index];
    }

    public ResultSet getResultatRequete() {
        return resultatRequete;
    }

    public void setResultatRequete(ResultSet resultatRequete) {
        this.resultatRequete = resultatRequete;
    }

    public void changeScene() {
        Scene scene = null;
        switch (currentScene) {
            case "result":
                VueResultatRecherche vrr = new VueResultatRecherche(this);
                scene = new Scene(vrr,800,600);
                currentStage.setScene(scene);
                break;
            case "add":
                VueAjouter va = new VueAjouter(this);
                scene = new Scene(va, 800, 600);
                currentStage.setScene(scene);
                break;
            default:
                VuePrincipale vp = new VuePrincipale(this);
                scene = new Scene(vp,800,600);
                currentStage.setScene(scene);
                break;
        }
    }

    public void setCurrentScene(String scene) {
        currentScene = scene;
        changeScene();
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

}

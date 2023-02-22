package donnees.maintenance_donnees.modele;

import donnees.maintenance_donnees.interfaces.Observateur;
import donnees.maintenance_donnees.interfaces.Sujet;
import donnees.maintenance_donnees.vue.VuePrincipale;
import donnees.maintenance_donnees.vue.VueResultatRecherche;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Modele implements Sujet {

    private ResultSet resultatRequete;
    private ArrayList<Observateur> observateurs;
    private String currentScene;
    private Stage currentStage;

    private String pays;
    private boolean[] informationsCourantes;

    public Modele() {
        this.currentScene = "choice";
        this.resultatRequete = null;
        this.observateurs = new ArrayList<Observateur>();
        this.informationsCourantes = new boolean[10];
        this.pays = null;
    }

    public void setPays(String p) {
        pays = p;
    }

    public String getPays() {
        return pays;
    }

    public void setInformationsCourantes(boolean[] informationsCourantes) {
        this.informationsCourantes = informationsCourantes;
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
                scene = new Scene(vrr,500,500);
                currentStage.setScene(scene);
                break;
            default:
                VuePrincipale vp = new VuePrincipale(this);
                scene = new Scene(vp,500,500);
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

    /// PARTIE OBSERVATEUR

    /**
     * Ajoute un observateur a la liste
     */
    public void enregistrerObservateur(Observateur o) {

        this.observateurs.add(o);
    }


    /**
     * Supprime un observateur a la liste
     */
    public void supprimerObservateur(Observateur o) {
        int i = this.observateurs.indexOf(o);
        if (i >= 0) {
            this.observateurs.remove(i);
        }
    }


    /**
     * Informe tous les observateurs de la liste des
     * modifications des mesures meteo en appelant leurs methodes actualiser
     */
    public void notifierObservateurs() {
        for (int i = 0; i < this.observateurs.size(); i++) {
            Observateur observer = this.observateurs.get(i);
            observer.actualiser(this);
        }
    }
}

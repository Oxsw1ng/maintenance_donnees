package donnees.maintenance_donnees;

import donnees.maintenance_donnees.modele.Modele;
import donnees.maintenance_donnees.vue.VuePrincipale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Modele modele = new Modele();

        VuePrincipale vp = new VuePrincipale(modele);

        Scene scene = new Scene(vp,800,600);

        stage.setTitle("Countr'Info");
        stage.setScene(scene);
        stage.show();

        modele.setCurrentStage(stage);
    }
}

package donnees.maintenance_donnees.controleur;

import donnees.maintenance_donnees.modele.Modele;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class controllerValiderPressed implements EventHandler<ActionEvent> {

    private Modele modele;

    public controllerValiderPressed(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        if (btn.getText() == "Voir les informations") {
            if (modele.getPays() == null) {
                Stage newWindow = new Stage();
                BorderPane bp = new BorderPane();
                Label lb = new Label("Choississez un pays !");
                bp.setCenter(lb);
                newWindow.setScene(new Scene(bp));
                newWindow.show();
            } else {
                modele.setCurrentScene("result");
            }
        } else {
            modele.setPays(null);
            modele.setCurrentScene("choice");
        }
    }
}

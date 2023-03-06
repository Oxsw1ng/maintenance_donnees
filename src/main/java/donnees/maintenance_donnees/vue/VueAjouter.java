package donnees.maintenance_donnees.vue;

import donnees.maintenance_donnees.controleur.controllerValiderPressed;
import donnees.maintenance_donnees.modele.Modele;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class VueAjouter extends BorderPane {

    private Modele modele;

    public VueAjouter(Modele modele) {
        this.modele = modele;
        Label l_pays = new Label("Entrez un nom de pays : ");
        TextField tf_pays = new TextField("France");
        HBox hb_pays = new HBox(l_pays, tf_pays);
        Label l_habitants = new Label("Entrez un nombre d'habitants : ");
        TextField tf_habitants = new TextField("1000000");
        HBox hb_habitants = new HBox(l_habitants, tf_habitants);
        Label l_PIB = new Label("Entrez le PIB du pays : ");
        TextField tf_PIB = new TextField("1752.50");
        HBox hb_PIB = new HBox(l_PIB, tf_PIB);
        Label l_moyenneAge = new Label("Entrez la moyenne d'âge du pays : ");
        TextField tf_moyenneAge = new TextField("37.7");
        HBox hb_moyenneAge = new HBox(l_moyenneAge, tf_moyenneAge);
        Label l_moyenneAgeVie = new Label("Entrez la moyenne d'âge de vie du pays : ");
        TextField tf_moyenneAgeVie = new TextField("68.3");
        HBox hb_moyenneAgeVie = new HBox(l_moyenneAgeVie, tf_moyenneAgeVie);
        Label l_superficie = new Label("Entrez la superficie du pays : ");
        TextField tf_superficie = new TextField("5888000");
        HBox hb_superficie = new HBox(l_superficie, tf_superficie);
        VBox principale = new VBox(hb_pays,hb_habitants,hb_PIB,hb_moyenneAge,hb_moyenneAgeVie,hb_superficie);
        Button submit = new Button("Ajouter le pays");
        Button retour = new Button("Revenir au choix du pays");
        retour.setOnAction(new controllerValiderPressed(modele));
        HBox hb_boutons = new HBox(retour, submit);
        principale.getChildren().add(hb_boutons);
        // Alignement CENTER pour HBOX et VBOX
        hb_boutons.setAlignment(Pos.CENTER);
        hb_boutons.setPadding(new Insets(20,0,20,0)); hb_boutons.setSpacing(30);
        hb_pays.setAlignment(Pos.CENTER);
        hb_pays.setPadding(new Insets(20,0,20,0));
        hb_habitants.setAlignment(Pos.CENTER);
        hb_habitants.setPadding(new Insets(20,0,20,0));
        hb_PIB.setAlignment(Pos.CENTER);
        hb_PIB.setPadding(new Insets(20,0,20,0));
        hb_superficie.setAlignment(Pos.CENTER);
        hb_superficie.setPadding(new Insets(20,0,20,0));
        hb_moyenneAgeVie.setAlignment(Pos.CENTER);
        hb_moyenneAgeVie.setPadding(new Insets(20,0,20,0));
        hb_moyenneAge.setAlignment(Pos.CENTER);
        hb_moyenneAge.setPadding(new Insets(20,0,20,0));
        principale.setAlignment(Pos.CENTER);
        this.setCenter(principale);

        /// Ajout quand le bouton est cliqué
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                URL url = null;
                try {
                    url = new URL("https://mijatovic.xyz/ajouter-pays.php");
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    conn.setRequestMethod("POST");
                } catch (ProtocolException e) {
                    throw new RuntimeException(e);
                }
                conn.setDoOutput(true);

                //String input = "{\"pays\":\""+tf_pays.getText()+"\", \"habitants\":"+tf_habitants.getText()
                //        +", \"PIB\":"+tf_PIB.getText()+", \"moyenneAge\":"+tf_moyenneAge.getText()+
                //        ", \"moyenneAgeVie\":"+tf_moyenneAgeVie.getText()+", \"superficie\":"+tf_superficie.getText()+"}";

                String input = "pays="+tf_pays.getText()+"&habitants="+tf_habitants.getText()+
                        "&PIB="+tf_PIB.getText()+"&moyenneAge="+tf_moyenneAge.getText()+"" +
                        "&moyenneAgeVie="+tf_moyenneAgeVie.getText()+"&superficie="+tf_superficie.getText();

                OutputStream os = null;
                try {
                    os = conn.getOutputStream();
                    os.write(input.getBytes());
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    // Lire la réponse du serveur
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                } catch (IOException exception) {
                    System.out.println("erreur dans la réponse du serveur");
                }

                modele.setPays(null);
                modele.setCurrentScene("choice");

            }
        });
    }
}

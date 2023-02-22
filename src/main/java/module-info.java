module donnees.maintenance_donnees {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens donnees.maintenance_donnees to javafx.fxml;
    exports donnees.maintenance_donnees;
}
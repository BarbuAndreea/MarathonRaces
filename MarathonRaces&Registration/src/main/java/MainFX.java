import javafx.application.Application;
import ctrl.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import repository.MarathonRaceRepo;
import repository.MarathonRaceRepositoryFile;
import repository.RegistrationRepo;
import repository.RegistrationRepositoryFile;
import services.Service;
import services.ServicesException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UI.fxml"));
            Parent root = loader.load();
            Controller ctrl = loader.getController();
            ctrl.setService(getService());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Test");
            primaryStage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error while starting app " + e);
            e.printStackTrace();
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    static Service getService() throws ServicesException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("MarRegFiles.properties"));
            String FileName1 = properties.getProperty("MarathonRaceFile");
            if (FileName1 == null) {
                FileName1 = "Races.txt";
                System.err.println("MarathonRaceFile file not found. Using default " + FileName1);
            }
            String FileName2 = properties.getProperty("RegistrationFile");
            if (FileName2 == null) {
                FileName2 = "Regist.txt";
                System.err.println("MarathonRaceFile file not found. Using default " + FileName2);
            }
            MarathonRaceRepo ar = new MarathonRaceRepositoryFile(FileName1);
            RegistrationRepo br = new RegistrationRepositoryFile(FileName2, ar);
            return new Service(ar, br);
        } catch (IOException ex) {
            throw new ServicesException("Error starting app " + ex);
        }
    }
}

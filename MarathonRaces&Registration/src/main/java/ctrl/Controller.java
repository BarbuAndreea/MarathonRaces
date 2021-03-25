package ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import model.MarathonRace;
import model.Registration;
import services.Service;
import services.ServicesException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Controller {

    @FXML
    private TextField Name, Type, Distance;
    @FXML
    private Text RaceId;

    @FXML
    private TextField Pname, Phone, Address, Age;

    @FXML
    private TextField racename;

    @FXML
    private TextField racename2;

    @FXML
    private DatePicker Date;

    @FXML
    private TableView<MarathonRace> raceResults;
    @FXML
    private TableView<MarathonRace> raceResults2;

    @FXML
    private TableView<Registration> regResults;


    private Service Services;

    public Controller() {

    }

    public void setService(Service service) {
        this.Services = service;
    }

    @FXML
    public void initialize() {
        StringConverter<LocalDate> converter = new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty())
                        ? LocalDate.parse(string, dateFormatter)
                        : null;
            }
        };

        Date.setConverter(converter);
        Date.setValue(LocalDate.now());
        Date.setEditable(false);
    }

    @FXML
    public void addRaceHandler(ActionEvent actionEvent) {
        String name = Name.getText();
        String type = Type.getText();
        String distanceval = Distance.getText();
        String date = Date.getValue().format(dateFormatter);
        if (checkString(name) && checkString(type) && checkString(distanceval) && checkString(date)) {
            try {
                int distance = Integer.parseInt(Distance.getText());
                int id = Services.addRace(name, type, date, distance);
                RaceId.setText("Race identification number is " + id);
                showNotification("Race successfully added! ", Alert.AlertType.INFORMATION);
            } catch (NumberFormatException ex) {
                showNotification("The distance must be a number! ", Alert.AlertType.ERROR);
                return;
            } catch (ServicesException ex) {
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        } else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }

    @FXML
    public void clearFieldsHandler(ActionEvent actionEvent) {
        Name.setText("");
        Type.setText("");
        Distance.setText("");
    }

    private void showNotification(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private boolean checkString(String s) {
        return s == null || s.isEmpty() ? false : true;
    }

    @FXML
    public void getAllRaces(ActionEvent actionEvent) {
        List<MarathonRace> results = Services.ar.findAllraces();
        raceResults.getItems().clear();
        raceResults.getItems().addAll(results);
    }

    @FXML
    public void addRegisterHandler(ActionEvent actionEvent) {
        int selectedRace = raceResults2.getSelectionModel().getSelectedIndex();
        if (selectedRace < 0) {
            showNotification("You must select a race first! ", Alert.AlertType.ERROR);
            return;
        }
        String pn = Pname.getText();
        String a = Age.getText();
        String ph = Phone.getText();
        String adr = Address.getText();
        if (checkString(ph) && checkString(a) && checkString(pn) && checkString(adr)) {
            try {
                int aval = Integer.parseInt(Age.getText());
                MarathonRace race = raceResults2.getItems().get(selectedRace);
                if (61 < aval || 6 > aval) {
                    showNotification("your age is not valid for this race! ", Alert.AlertType.ERROR);
                    return;
                }
                Services.addRegister(pn,ph,adr, aval,race);
                raceResults2.getItems().remove(selectedRace);
                showNotification("Order successfully added! ", Alert.AlertType.INFORMATION);
            } catch (NumberFormatException ex) {
                showNotification("went wrong ", Alert.AlertType.ERROR);
                return;
            } catch (ServicesException ex) {
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    public void searchraceHandler(ActionEvent actionEvent) {
        String searchName = racename.getText();
        if (!checkString(searchName)) {
            showNotification("You must introduce a name! ", Alert.AlertType.ERROR);
            return;
        }
        List<MarathonRace> results = Services.getRaceByName(searchName);
        raceResults2.getItems().clear();
        raceResults2.getItems().addAll(results);
    }

    @FXML
    public void searchregistHandler(ActionEvent actionEvent) {
        String searchName = racename2.getText();
        if (!checkString(searchName)) {
            showNotification("You must introduce a name! ", Alert.AlertType.ERROR);
            return;
        }
        List<Registration> results = Services.getRegByRace(searchName);
        regResults.getItems().clear();
        regResults.getItems().addAll(results);
    }
}
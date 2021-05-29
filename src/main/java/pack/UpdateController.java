/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bendabizadam
 */
public class UpdateController implements Initializable {

    private final RestTemplate restTemplate = new RestTemplate();
    @FXML
    TextField nCompte = new TextField();
    @FXML
    TextField nCompteEuro = new TextField();
    @FXML
    TextField nBanque = new TextField();
    @FXML
    Button okbtn = new Button();

    /**
     * Initializes the controller class.
     */
    private int nbanqueid = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nbanqueid = MainController.updated.getNbanque();
        nCompte.setText(String.valueOf(MainController.updated.getNcompte()));
        nBanque.setText(String.valueOf(MainController.updated.getNbanque()));
        nCompteEuro.setText(String.valueOf(MainController.updated.getNcompteeuro()));

    }

    @FXML
    public void ok() {
        BankService bankService = new BankService(restTemplate);
        try {
            MainController.updated.setNbanque(Integer.valueOf(nBanque.getText()));
            MainController.updated.setNcompte(Float.valueOf(nCompte.getText()));
            MainController.updated.setNcompteeuro(Float.valueOf(nCompteEuro.getText()));
            bankService.modifierBanque(MainController.updated);
            Stage stage = (Stage) okbtn.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Remplissez les champs correctement","erreur",JOptionPane.ERROR_MESSAGE);
        }
    }

}

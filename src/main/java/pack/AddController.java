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
public class AddController {

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
    
    @FXML
    public void ok(){
        BankService service= new BankService(restTemplate);
        try{

            Bank newBanque = new Bank(Integer.valueOf(nBanque.getText()),Float.valueOf(nCompte.getText()),Float.valueOf(nCompteEuro.getText()));
            if(service.consultBanque(newBanque.getNbanque()) !=null){
                JOptionPane.showMessageDialog(null,"Banque exists already!","erreur",JOptionPane.ERROR_MESSAGE);
                return;
            }
            MainController.array.add(newBanque);
        service.addBanque( newBanque);
        Stage stage = (Stage) okbtn.getScene().getWindow();
        stage.close();
        } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Remplissez les champs correctement","erreur",JOptionPane.ERROR_MESSAGE);
            }
    }
    
}

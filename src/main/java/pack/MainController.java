/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bendabizadam
 */
public class MainController implements Initializable {

    private final BankService bankService = new BankService(new RestTemplate());
    @FXML
    TableView<Bank> tableview = new TableView<>();
    @FXML
    TableColumn<Bank, Integer> nBanque = new TableColumn();
    @FXML
    TableColumn<Bank, Float> nCompte = new TableColumn();
    @FXML
    TableColumn<Bank, Float> nCompteEuro = new TableColumn();

    public static ObservableList<Bank> mylist;

    /**
     * Initializes the controller class.
     */
    public static ArrayList<Bank> array = new ArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        array = new ArrayList(bankService.getAllBanks());
        nBanque.setCellValueFactory(new PropertyValueFactory<>("nbanque"));
        nCompte.setCellValueFactory(new PropertyValueFactory<>("ncompte"));
        nCompteEuro.setCellValueFactory(new PropertyValueFactory<>("ncompteeuro"));
        tableview.getColumns().setAll(nBanque, nCompte, nCompteEuro);
        mylist = FXCollections.observableArrayList(array);
        refreshmytable();
    }
    
    @FXML
    public void addABanque() throws IOException {
        Stage s = new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/Add.fxml"));
        s.setTitle("Ajouter une Banque");
        s.setScene(new Scene(root));
        s.showAndWait();
        refreshmytable();
    }
    public static Bank updated;
    @FXML
    public void editBanque()throws IOException{

        updated = tableview.getSelectionModel().getSelectedItem();
        if(updated==null){
            JOptionPane.showMessageDialog(null,"Please Select an item first");
            return;
        }
        Stage s = new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/Update.fxml"));
        s.setTitle("Updater une Banque");
        s.setScene(new Scene(root));
        s.showAndWait();
        refreshmytable();
        
    }
    @FXML
    public void supprimerBanque(){
        Bank tobedeleted = tableview.getSelectionModel().getSelectedItem();
        if(tobedeleted==null){
            JOptionPane.showMessageDialog(null,"Please Select an item first");
            return;
        }
        int answer = JOptionPane.showConfirmDialog(null, "vous etes sure de supprimer?","suppression", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.YES_OPTION){
        bankService.deleteBank(tobedeleted.getNbanque());
        array.remove(tableview.getSelectionModel().getSelectedItem());
        refreshmytable();
        }
    }
    private void refreshmytable() {
        mylist = FXCollections.observableArrayList(array);
        tableview.setItems(mylist);
        tableview.refresh();
    }

}

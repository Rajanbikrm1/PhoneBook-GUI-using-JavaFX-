/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebookgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Rajan
 */
public class SearchByNumberTableController implements Initializable {

    ObservableList<TableEntry> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<TableEntry, String> nameColumn;
    @FXML
    private TableColumn<TableEntry, String> numberColumn;
    @FXML
    private TableColumn<TableEntry, String> notesColumn;
    @FXML
    private TableView<TableEntry> tableView;
    @FXML
    private Label name;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(PhoneBookConstant.SEARCH_BY_NUMBER_KERWORD);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        StringBuilder numberBuilder = new StringBuilder();
        StringBuilder notesBuilder = new StringBuilder();
        for (Entry entry : PhoneBookGui.entryList) {
            if (entry != null) {
                if (entry.number.toLowerCase().contains(PhoneBookConstant.SEARCH_BY_NUMBER_KERWORD.toLowerCase())) {
                    String name1 = entry.name;
                    String number = entry.number;
                    String[] allNumber = number.split(",");
                    String notes = entry.notes;
                    String[] allNotes = notes.split(",");
                    for (int i = 0; i < allNumber.length; i++) {
                        numberBuilder.append(allNumber[i].trim()).append("\n");
                        notesBuilder.append(allNotes[i].trim()).append("\n");
                    }
                    list.add(new TableEntry(name1, numberBuilder.toString(), notesBuilder.toString()));
                    numberBuilder = new StringBuilder();
                    notesBuilder = new StringBuilder();
                }
            }
        }
        tableView.setItems(list);
    }

}

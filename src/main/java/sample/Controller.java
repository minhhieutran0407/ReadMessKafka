package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TableView<Data> table;

    @FXML
    TableColumn<Data, String> dateColumn;

    @FXML
    TableColumn<Data, String> timeColumn;

    @FXML
    TableColumn<Data, String> tagColumn;

    @FXML
    TableColumn<Data, String> messColumn;

    public static ObservableList<Data> dataList;

    public void initialize(URL location, ResourceBundle resources) {
        dataList = FXCollections.observableArrayList(new Data("a", "b", "c", "d"),
                new Data("x", "y", "z", "k"));

        dateColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("time"));
        tagColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("tag"));
        messColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("mess"));

        table.setItems(dataList);
    }
}

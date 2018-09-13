package com.cuong.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import com.cuong.models.List;
import com.cuong.service.ListService;
import com.cuong.service.impl.ListServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListsManagerController implements Initializable {

	private static final Logger LOGGER = Logger.getLogger(ListsManagerController.class.getName());
	private ListService listService = new ListServiceImpl();

	private java.util.List<List> lists;

	public ListsManagerController() {
		lists = listService.loadAll();
	}

	private void initTable() {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
		updatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
		tableView.setItems(FXCollections.observableArrayList(lists));
	}

	private void deleteCurrentItem() {
		ObservableList<List> selectedItems = tableView.getSelectionModel().getSelectedItems();
		for (List list : selectedItems) {
			listService.deleteListAndRelatedWords(list.getId());
			tableView.getItems().remove(list);
		}
	}

	@FXML
	private TableColumn<List, Long> idColumn;

	@FXML
	private TableColumn<List, String> nameColumn;

	@FXML
	private TableColumn<List, Date> createdAtColumn;

	@FXML
	private TableColumn<List, Date> updatedAtColumn;

	@FXML
	private TableView<List> tableView;

	@FXML
	private Button deleteButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTable();
	}

	@FXML
	private void onActionDeleteList(ActionEvent event) {
		deleteCurrentItem();
	}

}

package com.pos.inventory.controller;

import java.math.BigDecimal;

import com.pos.inventory.model.Product;
import com.pos.inventory.model.ProductInventory;
import com.pos.inventory.model.ProductInventoryDAO;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class viewInventoryCtrl {

    @FXML
    private AnchorPane orgDtlsPane;

    @FXML
    private ImageView orgLogo;

    @FXML
    private TextField productTxtBox;

    @FXML
    private TableView<ProductInventory> inventoryEnquiryTable;

    @FXML
    private TableColumn<ProductInventory, String> inventoryitemCol;

    @FXML
    private TableColumn<ProductInventory, String> productDescCol;

    @FXML
    private TableColumn<ProductInventory, BigDecimal> noOfUnitsCol;

    @FXML
    private TableColumn<ProductInventory, BigDecimal> qtyPerUnitCol;

    @FXML
    private TableColumn<ProductInventory, BigDecimal> totalNoOfItemsCol;

    @FXML
    private TableColumn<ProductInventory, BigDecimal> totalOrderedCol;

    @FXML
    private TableColumn<ProductInventory, BigDecimal> reOrderLvlCol;

    @FXML
    void searchInventory(ActionEvent event) {
    	ObservableList<ProductInventory> inventoryList = ProductInventoryDAO.getInventoryByProduct(productTxtBox.getText());
    	inventoryEnquiryTable.setItems(inventoryList);
    	inventoryEnquiryTable.getColumns().clear();
        
    	inventoryitemCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, String>("proName"));
    	productDescCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, String>("proDesc"));
    	noOfUnitsCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("noOfUnits"));
    	qtyPerUnitCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("qtyPerUnit"));
    	totalNoOfItemsCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("totalQty"));
    	//totalOrderedCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("noOfOrdered"));
    	//reOrderLvlCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("reorderLvl"));
    	inventoryEnquiryTable.getColumns().addAll(
    			inventoryitemCol,
    			productDescCol,
    			noOfUnitsCol,
    			qtyPerUnitCol,
    			totalNoOfItemsCol
    			//totalOrderedCol
    			//reOrderLvlCol
        );

    }

}



package com.pos.inventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class InventoryCtrl {

    @FXML
    private Tab productCategoryTab;

    @FXML
    private TextField productCategoryName;

    @FXML
    private TextArea productCategoryDesc;

    @FXML
    private TextField productCategoryId;

    @FXML
    private TableView<?> productCategoryTable;

    @FXML
    private TableColumn<?, ?> productCategoryNameCol;

    @FXML
    private TableColumn<?, ?> productCategoryDescCol;

    @FXML
    private Tab productTab;

    @FXML
    private TextField productName;

    @FXML
    private TextArea productDesc;

    @FXML
    private ComboBox<?> productCategory;

    @FXML
    private TextField productCost;

    @FXML
    private Label orgShopIdLbl1;

    @FXML
    private TextField orgShopId1;

    @FXML
    private TextField productPrice;

    @FXML
    private Label orgShopIdLbl11;

    @FXML
    private TextField orgShopId11;

    @FXML
    private TableView<?> productTable;

    @FXML
    private TableColumn<?, ?> productNameCol;

    @FXML
    private TableColumn<?, ?> productDecCol;

    @FXML
    private TableColumn<?, ?> productCategoryCol;

    @FXML
    private TableColumn<?, ?> productCostCol;

    @FXML
    private TableColumn<?, ?> productPriceCol;

    @FXML
    private Tab inventoryTab;

    @FXML
    private ComboBox<?> inventoryItem;

    @FXML
    private TextField itemNoOfUnits;

    @FXML
    private TextField itemQtyPerUnit;

    @FXML
    private TextField itemTotal;

    @FXML
    private TextField orgId;

    @FXML
    private TextField itemsOrderedTotal;

    @FXML
    private TextField itemReorderLevel;

    @FXML
    private TextArea inventoryDesc;

    @FXML
    void createCategory(ActionEvent event) {

    }

    @FXML
    void createInventory(ActionEvent event) {

    }

    @FXML
    void createProduct(ActionEvent event) {

    }

    @FXML
    void deleteInventory(ActionEvent event) {

    }

    @FXML
    void deleteProduct(ActionEvent event) {

    }

    @FXML
    void deleteProductCategory(ActionEvent event) {

    }

    @FXML
    void getTableRowData(MouseEvent event) {

    }

    @FXML
    void updateInventory(ActionEvent event) {

    }

    @FXML
    void updateProduct(ActionEvent event) {

    }

    @FXML
    void updateProductCategory(ActionEvent event) {

    }

}

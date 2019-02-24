

package com.pos.inventory.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.pos.database.Database;
import com.pos.inventory.model.Product;
import com.pos.inventory.model.ProductCategory;
import com.pos.inventory.model.ProductCategoryDAO;
import com.pos.inventory.model.ProductDAO;
import com.pos.inventory.model.ProductInventory;
import com.pos.inventory.model.ProductInventoryDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class InventoryCtrl implements Initializable {

    @FXML
    private Tab productCategoryTab;

    @FXML
    private TextField productCategoryName;

    @FXML
    private TextArea productCategoryDesc;

    @FXML
    private TextField productCategoryId;

    @FXML
    private TableView<ProductCategory> productCategoryTable;

    @FXML
    private TableColumn<ProductCategory, String> productCategoryNameCol;

    @FXML
    private TableColumn<ProductCategory, String> productCategoryDescCol;

    @FXML
    private Tab productTab;

    @FXML
    private TextField productName;

    @FXML
    private TextArea productDesc;

    @FXML
    private ComboBox<ProductCategory> productCategoryCB;

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
    private TableView<Product> productTable;
    
    @FXML
    private TextField productId;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, String> productDecCol;

    @FXML
    private TableColumn<Product, String> productCategoryCol;

    @FXML
    private TableColumn<Product, BigDecimal> productCostCol;

    @FXML
    private TableColumn<Product, BigDecimal> productPriceCol;

    @FXML
    private Tab inventoryTab;

    @FXML
    private ComboBox<Product> inventoryItemCB;

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
    private TextField inventoryItemIdCol;
    
    @FXML
    private TableView<ProductInventory> productInventoryTable;
    
    @FXML
    private TableColumn<ProductInventory, Product> inventoryItemCol;

    @FXML
    private TableColumn<ProductInventory, String> inventoryItemDescCol;

    @FXML
    private TableColumn<ProductInventory, BigDecimal> inventoryItemNoOfUnitsCol;

    @FXML
    private TableColumn<ProductInventory, BigDecimal> inventoryItemQtyPerUnitCol;

    @FXML
    private TableColumn<ProductInventory, BigDecimal> inventoryItemTotalNoOfItemsCol;

    @FXML
    private TableColumn<ProductInventory, BigDecimal> inventoryItemOrderedItemsCol;

    @FXML
    private TableColumn<ProductInventory, BigDecimal> inventoryItemOrderLvl;
    
    ProductCategory category;

	private Product product;

	private ProductInventory productInventory;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
    	//initialize product category table
    	ObservableList<ProductCategory> listOfCategories =  FXCollections.observableArrayList(ProductCategoryDAO.getAllCategory());
    	productCategoryTable.setItems(listOfCategories);
    	productCategoryTable.getColumns().clear();
    	productCategoryNameCol.setCellValueFactory(new PropertyValueFactory<ProductCategory, String>("name"));
    	productCategoryDescCol.setCellValueFactory(new PropertyValueFactory<ProductCategory, String>("desc"));
    	productCategoryTable.getColumns().addAll(productCategoryNameCol,productCategoryDescCol);
    	
    	//initialize product category LOV
        productCategoryCB.setItems(listOfCategories);

       //populate product table
        ObservableList<Product> listOfProducts =  FXCollections.observableArrayList(ProductDAO.getAllProducts());
        productTable.setItems(listOfProducts);
        productTable.getColumns().clear();
    	productNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
    	productDecCol.setCellValueFactory(new PropertyValueFactory<Product, String>("desc"));
    	productCategoryCol.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
    	productCostCol.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("cost"));
    	productPriceCol.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("price"));
    	productTable.getColumns().addAll(productNameCol,productDecCol,productCategoryCol,productCostCol,productPriceCol);
    	
    	//initialize product LOV
    	inventoryItemCB.setItems(listOfProducts);
    	
    	//initialize inventory table
    	ObservableList<ProductInventory> listOfInventoryItems = FXCollections.observableArrayList(ProductInventoryDAO.getAllInventories());
    	productInventoryTable.setItems(listOfInventoryItems);
        productInventoryTable.getColumns().clear();
        
        inventoryItemCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, Product>("product"));
        inventoryItemDescCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, String>("proDesc"));
        inventoryItemNoOfUnitsCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("noOfUnits"));
        inventoryItemQtyPerUnitCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("qtyPerUnit"));
        inventoryItemTotalNoOfItemsCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("totalQty"));
        inventoryItemOrderedItemsCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("noOfOrdered"));
        inventoryItemOrderLvl.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("reorderLvl"));
        productInventoryTable.getColumns().addAll(
        		inventoryItemCol,
        		inventoryItemDescCol,
        		inventoryItemNoOfUnitsCol,
        		inventoryItemQtyPerUnitCol,
        		inventoryItemTotalNoOfItemsCol,
        		inventoryItemOrderedItemsCol,
        		inventoryItemOrderLvl
        );


    }

    @FXML
    void createCategory(ActionEvent event) {
    	ProductCategory category = new ProductCategory();
    	category.setName(productCategoryName.getText());
    	category.setDesc(productCategoryDesc.getText());
    	
    	ProductCategoryDAO.createCategory(category);
    	
    	Database.refreshTable(productCategoryTable,ProductCategoryDAO.getAllCategory());
    }

    @FXML
    void createInventory(ActionEvent event) {
    	
    	ProductInventory inventory = new ProductInventory();
    	inventory.setProduct(inventoryItemCB.getSelectionModel().getSelectedItem());
    	inventory.setNoOfUnits(new BigDecimal(itemNoOfUnits.getText()));
    	inventory.setQtyPerUnit(new BigDecimal(itemQtyPerUnit.getText()));
    	inventory.setTotalQty(new BigDecimal(itemTotal.getText()));
    	inventory.setNoOfOrdered(new BigDecimal(itemsOrderedTotal.getText()));
    	inventory.setReorderLvl(new BigDecimal(itemReorderLevel.getText()));
    	inventory.setProDesc(inventoryDesc.getText());
    	
    	ProductInventoryDAO.createInventory(inventory);
    	
    	Database.refreshTable(productInventoryTable,ProductInventoryDAO.getAllInventories());

    }
    

    @FXML
    void createProduct(ActionEvent event) {
    	
    	Product product = new Product();
    	product.setName(productName.getText());
    	product.setDesc(productDesc.getText());
    	product.setCategory(productCategoryCB.getSelectionModel().getSelectedItem());
    	product.setCost(new BigDecimal(productCost.getText()));
    	product.setPrice(new BigDecimal(productPrice.getText()));
    	
    	ProductDAO.createProduct(product);
    	
    	Database.refreshTable(productTable,ProductDAO.getAllProducts());


    	
    }

    @FXML
    void deleteInventory(ActionEvent event) {
    	ProductInventoryDAO.deleteInventory(this.productInventory);
    	Database.refreshTable(productInventoryTable,ProductInventoryDAO.getAllInventories());

    }

    @FXML
    void deleteProduct(ActionEvent event) {
    	ProductDAO.deleteProduct(this.product);
    	Database.refreshTable(productTable,ProductDAO.getAllProducts());

    }

    @FXML
    void deleteProductCategory(ActionEvent event) {
    	ProductCategoryDAO.deleteCategory(this.category);
    	Database.refreshTable(productCategoryTable,ProductCategoryDAO.getAllCategory());
    }

    @FXML
    void getProductCategoryTableRowData(MouseEvent event) {
    	this.category = productCategoryTable.getSelectionModel().getSelectedItem();
    	productCategoryId.setText(category.getId().toString());
    	productCategoryName.setText(category.getName());
    	productCategoryDesc.setText(category.getDesc());
    }
    
    @FXML
    void getProductTableRowData(MouseEvent event) {
    	this.product = productTable.getSelectionModel().getSelectedItem();
    	productId.setText(product.getId().toString());
    	productName.setText(product.getName());
    	productDesc.setText(product.getDesc());
    	productCategoryCB.setValue(ProductCategoryDAO.getCategory(product.getCategory().getId()));
    	productCost.setText(product.getCost().toString());
    	productPrice.setText(product.getPrice().toString());
    }
    
    @FXML
    void getProductInventoryTableRowData(MouseEvent event) {
    	this.productInventory = productInventoryTable.getSelectionModel().getSelectedItem();
    	inventoryItemIdCol.setText(productInventory.getId().toString());
    	inventoryItemCB.setValue(productInventory.getProduct());
    	itemNoOfUnits.setText(productInventory.getNoOfUnits().toString());
    	itemQtyPerUnit.setText(productInventory.getQtyPerUnit().toString());
    	itemTotal.setText(productInventory.getTotalQty().toString());
    	itemsOrderedTotal.setText(productInventory.getNoOfOrdered().toString());
    	itemReorderLevel.setText(productInventory.getReorderLvl().toString());
    	inventoryDesc.setText(productInventory.getProDesc());
    }
    
    

        @FXML
    void updateInventory(ActionEvent event) {
        	productInventory.setId(new BigDecimal(inventoryItemIdCol.getText()));
        	productInventory.setProduct(inventoryItemCB.getSelectionModel().getSelectedItem());
        	productInventory.setNoOfUnits(new BigDecimal(itemNoOfUnits.getText()));
        	productInventory.setQtyPerUnit(new BigDecimal(itemQtyPerUnit.getText()));
        	productInventory.setTotalQty(new BigDecimal(itemTotal.getText()));
        	productInventory.setNoOfOrdered(new BigDecimal(itemsOrderedTotal.getText()));
        	productInventory.setReorderLvl(new BigDecimal(itemReorderLevel.getText()));
        	productInventory.setProDesc(inventoryDesc.getText());
        	
        	ProductInventoryDAO.updateInventory(productInventory);
        	Database.refreshTable(productInventoryTable,ProductInventoryDAO.getAllInventories());

    }

    @FXML
    void updateProduct(ActionEvent event) {
    	this.product.setId(new BigDecimal(productId.getText()));
    	this.product.setName(productName.getText());
    	this.product.setDesc(productDesc.getText());
    	this.product.setCategory(productCategoryCB.getSelectionModel().getSelectedItem());
    	this.product.setCost(new BigDecimal(productCost.getText()));
    	this.product.setPrice(new BigDecimal(productPrice.getText()));
    	ProductDAO.updateProduct(product);
    	
    	Database.refreshTable(productTable,ProductDAO.getAllProducts());

    }

    @FXML
    void updateProductCategory(ActionEvent event) {
    	this.category.setName(productCategoryId.getText());
    	this.category.setName(productCategoryName.getText());
    	this.category.setDesc(productCategoryDesc.getText());
    	ProductCategoryDAO.updateCategory(this.category);
    	
    	Database.refreshTable(productCategoryTable,ProductCategoryDAO.getAllCategory());

    }
    
    @FXML
    void calcNoOfItems(KeyEvent event) {
    	
    	BigDecimal noOfUnits= new BigDecimal(itemNoOfUnits.getText());
    	BigDecimal qtyPerUnits= new BigDecimal(itemQtyPerUnit.getText());
    	this.itemTotal.setText(noOfUnits.multiply(qtyPerUnits).toString());

    }


}

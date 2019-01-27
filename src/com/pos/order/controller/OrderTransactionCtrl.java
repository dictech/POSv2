package com.pos.order.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import com.pos.database.PurchaseCache;
import com.pos.inventory.model.Product;
import com.pos.inventory.model.ProductDAO;
import com.pos.inventory.model.ProductInventory;
import com.pos.inventory.model.ProductInventoryDAO;
import com.pos.order.model.Order;
import com.pos.order.model.OrderDAO;
import com.pos.order.model.Purchase;
import com.pos.order.model.PurchaseDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class OrderTransactionCtrl {

    @FXML
    private TextField productName;

    @FXML
    private TableView<Product> prodSearchResultTbl;

    @FXML
    private TableView<Purchase> purchaseListTbl;

    @FXML
    private TextField totalCostOfPurchase;

    @FXML
    private TextField amtPaidForPurchase;

    @FXML
    private TextField balanceOfPurchase;

    @FXML
    void addItemToCart(ActionEvent event) {

    }

    @FXML
    void dispatchPurchase(ActionEvent event) {
    	
    	
    	Order order =  new Order();
    	order.setOrder_no(order_no);
    	order.setOrder_attd_id(new BigDecimal(1));
    	order.setOrder_date(new Date(0));
    	order.setOrder_time(String.valueOf(new Date(0).getTime()));
    	order.setListOfPurchasedItems(purchaseListTbl.getItems());
    	order.setTotalPriceOfOrder(new BigDecimal(totalCostOfPurchase.getText()));
    	
    	OrderDAO.createOrder(order);

    }

    @FXML
    void searchProduct(ActionEvent event) {
    	    	 
    	List<ProductInventory> inventoryList = ProductInventoryDAO.getInventoryByProduct(productName.getText());
     	 
     	List<Product> listOfProducts = inventoryList.stream()
							     				    .filter(inv -> inv.getNoOfOrdered() != inv.getTotalQty())
							     				    .map(inv -> inv.getProduct())
							     				    .collect(Collectors.toList());
     	displayProductSearch(listOfProducts);
     	
    }
    
    
    void displayProductSearch(List<Product> listOfProducts ) {
    	
    	HashMap<String,TableColumn<Product, ?>> mapOfTableCol = ProductDAO.getProductTableCols();
     
     	prodSearchResultTbl.getColumns().clear();
    	prodSearchResultTbl.setItems(FXCollections.observableArrayList(listOfProducts));
   	    prodSearchResultTbl.getColumns().addAll(mapOfTableCol.values());
   	    
    }
    
    
    @FXML
    void addSelectedProductToCache(MouseEvent event) throws Exception {
    	 	 
      	Product selectedProduct =  prodSearchResultTbl.getSelectionModel().getSelectedItem();
      	
      	Purchase purchase =  new Purchase();
      	purchase.setProduct(selectedProduct);
      	purchase.setQty(new BigDecimal(100));
      	purchase.setTotalPriceOfPurchase(selectedProduct.getCost().multiply(new BigDecimal(100)));
      	
      	//PurchaseDAO.cachePurchase(purchase);
      	
      	PurchaseCache.setPurchase(purchase);
      	PurchaseCache.getCache().get(purchase.getProduct().getName());
      	
      	
      	this.displayCachedPurchases();
      		
    }
    
    
    void displayCachedPurchases() {
    	
      	ConcurrentMap<String,Purchase> cachedPurchases = PurchaseCache.getCache().asMap();
      	Map<String,TableColumn<Purchase, ?>> mapOfTableCol = PurchaseDAO.getPurchaseCols();
      	
    	purchaseListTbl.getColumns().clear();
     	purchaseListTbl.setItems(FXCollections.observableArrayList(cachedPurchases.values()));
     	purchaseListTbl.getColumns().addAll(mapOfTableCol.values());
     	
     	this.calculateTotalCost();
    }
    
    void calculateTotalCost() {
    	//List<Purchase> purchase = purchaseListTbl.getSelectionModel().getSelectedItems();
    	
    	List<Purchase>listOfPurchase = new ArrayList(PurchaseCache.getCache().asMap().values());
    	int totalPrice = listOfPurchase.stream().mapToInt(i->i.getTotalPriceOfPurchase().intValue()).sum();    	
    	this.totalCostOfPurchase.setText(Integer.toString(totalPrice));
    }

}


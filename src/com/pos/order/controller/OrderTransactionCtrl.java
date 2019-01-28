package com.pos.order.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.pos.account.model.Attendant;
import com.pos.database.AttendantCache;
import com.pos.database.PurchaseCache;
import com.pos.inventory.model.Product;
import com.pos.inventory.model.ProductDAO;
import com.pos.inventory.model.ProductInventory;
import com.pos.inventory.model.ProductInventoryDAO;
import com.pos.order.model.Order;
import com.pos.order.model.OrderDAO;
import com.pos.order.model.Purchase;
import com.pos.order.model.PurchaseDAO;
import com.pos.payment.model.Payment;
import com.pos.payment.model.paymentDAO;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    void dispatchPurchase(ActionEvent event) throws ExecutionException {
    	
    	Attendant attendant = AttendantCache.getCache().get("USER");
    	
    	
    	Order order =  new Order();
    	order.setOrder_no("yt5789");
    	order.setOrder_attd_id(attendant.getId());
    	order.setOrder_date(Date.valueOf(LocalDate.now()));
    	order.setOrder_time(Time.valueOf(LocalTime.now()).toString());
    	order.setListOfPurchasedItems(purchaseListTbl.getItems());
    	order.setTotalPriceOfOrder(new BigDecimal(totalCostOfPurchase.getText()));
    	int pk = OrderDAO.createOrder(order);
    	order.setOrder_id(new BigDecimal(pk));
    	
    	
    	
    	
    	Payment payment =  new Payment();	
		payment.setRecipient(attendant);
		payment.setOrder(order);
    	payment.setDescription("Payment for purchased goods");
    	payment.setAmtPaid(Integer.parseInt(amtPaidForPurchase.getText()));
    	payment.setBalance(Integer.parseInt(balanceOfPurchase.getText()));
    	payment.setPrice(Integer.parseInt(totalCostOfPurchase.getText()));
    	payment.setType("C");
    	payment.setTime(Time.valueOf(LocalTime.now()).toString());
    	payment.setDate(Date.valueOf(LocalDate.now()));
    	paymentDAO.createPayment(payment);

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
    
    
    @FXML
    void calculateTotalBalance() {
    	BigDecimal totalCost = new BigDecimal(this.totalCostOfPurchase.getText());
    	BigDecimal balance =  totalCost.subtract(new BigDecimal(this.amtPaidForPurchase.getText()));
    	this.balanceOfPurchase.setText(balance.toString());
    }

}


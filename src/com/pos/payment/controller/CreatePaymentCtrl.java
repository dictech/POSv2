 package com.pos.payment.controller;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.pos.account.controller.LoginCtrl;
import com.pos.account.model.Attendant;
import com.pos.account.model.SystemAccount;
import com.pos.account.model.SystemAccountDAO;
import com.pos.order.model.Order;
import com.pos.order.model.OrderDAO;
import com.pos.payment.model.Payment;
import com.pos.payment.model.paymentDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;


public class CreatePaymentCtrl implements Initializable {



	    @FXML
	    private TextField attendant_id;

	    @FXML
	    private TextField order_id;

	    @FXML
	    private TextField price;

	    @FXML
	    private TextField amount_paid;


	    @FXML
	    private TextField search_order_box;

	    @FXML
	    private TextField t_date;

	    @FXML
	    private TextField t_time;

	    @FXML
	    private TextField p_time;

	    @FXML
	    private TextField p_date;
	    
	    @FXML
	    private TextField balanceOf_pm;

	    @FXML
	    private ComboBox<String> payment_type;

	    @FXML
	    private Button proces_btn;

	    @FXML
	    private Button cancel_btn;

	    @FXML
	    private Button check_order_btn;

	    @FXML
	    private TextField description;
	    
	    private Attendant attd;
	    
	    private Order order;
	    
	        	
	    		private final DateTimeFormatter ftmt = DateTimeFormatter.ofPattern("d MMM uuuu");
	    		private final LocalDateTime now = LocalDateTime.now();
	    		private final String date = now.format(ftmt);
	    		private final String currentTime =  LocalTime.now().toString();
	        
	            
	            

		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			ObservableList<String> pType = FXCollections.observableArrayList();
			pType.addAll("Debit","Credit");
			this.payment_type.setItems(pType);
			this.p_date.setText(this.date);
			this.p_time.setText(this.currentTime);
			this.balanceOf_pm.setDisable(true);
			this.balanceOf_pm.setText(" ");
			
			
			try {
				
			List<BigDecimal> orderIds = OrderDAO.getAllOrderIds();
			TextFields.bindAutoCompletion(search_order_box, orderIds);
			}catch(Exception e) {
				
				e.getMessage();
			}	
		}

		

	    @FXML
	    void getId_row(ActionEvent event)throws Exception {
	    	
	    	       if(search_order_box.getText().isEmpty()) {
	    		   
	    		   Alert alert = new Alert(AlertType.ERROR);
	    		   alert.setContentText("No ID or Order code was inserted !");
	    		   alert.setHeaderText(null);
	    		   alert.setTitle("Invalid ID");
	    		   Optional<ButtonType> action = alert.showAndWait();
	    		   if(action.get() == ButtonType.OK) {
	    			   
	    			   this.search_order_box.setEditable(true);
	    		   }
	    		   
	    		   
	    	   }else{
	    		    
	    		   
	    	  BigDecimal orderId = new BigDecimal(this.search_order_box.getText());
	    	  List<BigDecimal> listOforderIds = OrderDAO.getAllOrderIds();
	    	  
	    	  if(listOforderIds.contains(orderId)) {
	    	  this.order = OrderDAO.getOrder(orderId);
	    	  this.price.setText(this.order.getTotalPriceOfOrder().toPlainString());
	    	  this.attendant_id.setText(this.order.getOrder_attd_id().toPlainString());
	    	  this.order_id.setText(this.order.getOrder_no());
	    	  
	    	  } else {
	    		  
	    		   Alert alert = new Alert(AlertType.ERROR);
	    		   alert.setContentText("No Data is associated with this ID!");
	    		   alert.setHeaderText(null);
	    		   alert.setTitle("Invalid ID");
	    		   Optional<ButtonType> action = alert.showAndWait();  
	    		   if(action.get() == ButtonType.OK)
	    			   System.out.println("user has seen this error !");
	    	  } 
	    	}
	    }
		
		
	    private void createPaymentInstatnce(Payment payment, double totalAmount) {
	    	
	    	payment.setRecipient(this.attd);
	    	payment.setOrder(this.order);
	    	payment.setAmtPaid(Integer.parseInt(this.amount_paid.getText()));
	    	payment.setBalance((int)totalAmount);
	    	payment.setPrice(Integer.parseInt(this.price.getText()));
	    	payment.setType(this.payment_type.getValue());
	    	payment.setDescription(this.description.getText());
	    	payment.setDate(Date.valueOf(LocalDate.now()));
	    	payment.setTime(this.p_time.getText());
	    	paymentDAO.createPayment(payment);
	    }
	    
	    @FXML
	    void processPayment(ActionEvent event) {

	    	this.attd = new Attendant();
	    	this.order = new Order();

    	    this.attd.setId(new BigDecimal(this.attendant_id.getText()));
	    	this.order.setOrder_id(new BigDecimal(this.order_id.getText()));
	    	
	    	if(this.payment_type.getValue() !=null) {	
	    	  String deb = "Debit", cred = "Credit";
	    	  String list = this.payment_type.getSelectionModel().getSelectedItem();
	    	    double productPrice = Integer.parseInt(this.price.getText());
	            double moneyPaid = Integer.parseInt(this.amount_paid.getText());
	    	  
	        if(list.matches(cred)) {
	    		  double balance = productPrice - moneyPaid;
	    		  
	    		   if(balance > 0) {
	    			      		  
                     this.description.setText("Partial Payment");	  
    			     this.balanceOf_pm.setText(new BigDecimal(balance).toPlainString());
    		    	 Payment payment = new Payment();
    		    	 this.createPaymentInstatnce(payment, balance);
    		    	 
	    		          }else if(balance <= 0 ) {
	    		        	  
	    	                     this.description.setText("Completed Payment");	  
	    	    			     this.balanceOf_pm.setText(new BigDecimal(balance).toPlainString());
	    	    		    	 Payment payment = new Payment();
	    	    		    	 this.createPaymentInstatnce(payment, balance); 
	    		          }
	    		          else if(moneyPaid > productPrice) {
	    		        	  
	    	                     this.description.setText("outstanding "+balance);	  
	    	    			     this.balanceOf_pm.setText(new BigDecimal(balance).toPlainString());
	    	    		    	 Payment payment = new Payment();
	    	    		    	 this.createPaymentInstatnce(payment, balance);
	    		          }
	    		  
	    		  }
	    	  
	    	  
	    	  
	    	  
	    	  
	    	       else if
	    	             (list.matches(deb)) {
	    	    	 
	    	    	 double totalAmount = Integer.parseInt(this.price.getText()) - Integer.parseInt(this.amount_paid.getText());
	    	    	 
	    	    	 if(totalAmount <= 0) {
			      		  
			    		   Alert alert = new Alert(AlertType.ERROR);
			    		   alert.setContentText("this type of payment is not an half payment ");
			    		   alert.setHeaderText(null);
			    		   alert.setTitle("Invalid Payment Selection");
			    		   alert.show();  

		    		  }else 
		    		        {
		    			    this.balanceOf_pm.setText(new BigDecimal(totalAmount).toPlainString());
		    		    	Payment payment = new Payment();
		    		    	this.createPaymentInstatnce(payment, totalAmount);
		    		    	
	                             }
	    	    	 
	    	    	 
	    	    	 
	    	    	 
	    	     }else if
	    	             (list.matches(deb)) {
	    	    	 
	    	     int totalAmount = Integer.parseInt(this.price.getText()) - Integer.parseInt(this.amount_paid.getText());
	    	     }
	    	    
	    		
	    	
	    	

	    		}
	    		else {
	    		Alert alert = new Alert(AlertType.ERROR);
	    		alert.setContentText("Please Select a Payment type : FULL PAYMENT/HALF PAYMENT/REFUND");
	    		alert.setHeaderText(null);
	    		alert.setTitle(null);
	    		alert.show();
	    	}
	    	
	    	
	    }


	    @FXML
	    void cancelPayment(ActionEvent event) {

	    }


	    
}

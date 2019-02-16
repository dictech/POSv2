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
import com.pos.account.model.Attendant;
import com.pos.order.model.Order;
import com.pos.order.model.OrderDAO;
import com.pos.payment.model.Payment;
import com.pos.payment.model.paymentDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;



public class CreatePaymentCtrl implements Initializable{



	    @FXML
	    private TextField attendant_id;

	    @FXML
	    private TextField order_id;

	    @FXML
	    private Text pay_id;
	    
	    @FXML
	    private TextField price;

	    @FXML
	    private TextField amount_paid;


	    @FXML
	    private TextField search_order_box;

	    @FXML
	    private TextField order_date;

	    @FXML
	    private TextField order_time;

	    @FXML
	    private TextField payment_time;

	    @FXML
	    private TextField payment_date;

	    
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
	    private Text description;
	    
	    @FXML
	    private TextField lastPaid;
	    
	    private Attendant attd;
	    
	    private Order order;
	    
	    private Payment payment;    
	    
	    		private final DateTimeFormatter ftmt = DateTimeFormatter.ofPattern("d MMM uuuu");
	    		private final LocalDateTime now = LocalDateTime.now();
	    		private final String date = now.format(ftmt);
	    		private final String currentTime =  LocalTime.now().toString();
	            private final String debit = "Debit";
	            private final String credit = "Credit";




		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			ObservableList<String> pType = FXCollections.observableArrayList();
			pType.addAll(debit,credit);
			this.payment_type.setValue(" Select Payment Type");
			this.payment_type.setItems(pType);
			this.balanceOf_pm.setDisable(true);
			this.balanceOf_pm.setText("0");
			this.payment_type.setDisable(true);
			try {
				
			List<BigDecimal> orderIds = OrderDAO.getAllOrderIds();
			TextFields.bindAutoCompletion(search_order_box, orderIds);
			}catch(Exception e) {
				
				e.getMessage();
			}	
		}

		

	    @FXML
	    void getId_row(ActionEvent event)throws Exception {

	    	        Integer number = Integer.valueOf(Integer.parseInt(search_order_box.getText()));
	    	       if(search_order_box.getText().isEmpty() || Double.isNaN(number)) {
	    		   
	    		   Alert alert = new Alert(AlertType.ERROR);
	    		   alert.setContentText("Invalid Reference code or Empty field! ");
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
	    	  this.attendant_id.setText(this.order.getOrder_attd_id().toPlainString());
	    	  this.order_id.setText(this.order.getOrder_no());
	    	  this.order_date.setText(this.order.getOrder_date().toString());
	    	  this.order_time.setText(this.order.getOrder_time());
	    	  this.price.setText(this.order.getTotalPriceOfOrder().toPlainString());
	    	  this.payment_type.setDisable(false);
	    	  
	    	  
	    	  List<BigDecimal> allPayments = paymentDAO.getAllpayOrderIds();
			 Payment maxPay = paymentDAO.getMaxPayment(new BigDecimal(this.search_order_box.getText()));
			  
			  if(allPayments.contains(new BigDecimal(this.search_order_box.getText()))) {
			  this.payment = paymentDAO.getPayment(maxPay.getId());  
			  this.description.setText("Was a "+payment.getDescription());
			  this.amount_paid.setText(new BigDecimal(payment.getAmtPaid()).toPlainString());
			  this.balanceOf_pm.setText(new BigDecimal(payment.getBalance()).toPlainString());
			  this.lastPaid.setText(this.amount_paid.getText());
			  this.amount_paid.setText(null);
			  }
			  else {
				  this.description.setText("No payment was found on this ID");
				  this.lastPaid.setText("0");
			  }
	    	  
	    	  	  
	    	  } else {
	    		  
	    		  this.payment_type.setDisable(true);
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
		

	    @FXML
	    void callPaymentMethod(ActionEvent event) {
	    			this.payment_type.getSelectionModel().getSelectedItem(); 
	    			
	     if(this.payment_type.getValue().matches(credit) || this.payment_type.getValue().matches(debit)) {
	    				
	    	this.description.setText(null);
	    				

	    				
	    			}
	    			
	    		}

	    public void createPaymentInstatnce(Payment payment,double balance,double totalAmountPaid) {
	    	this.order = new Order();
	    	 order.setOrder_id(new BigDecimal(this.search_order_box.getText()));
	    	payment.setId(payment.getId());
	    	payment.setRecipient(this.attd);
	    	payment.setOrder(order);
	    	payment.setAmtPaid((int)totalAmountPaid);
	    	payment.setBalance((int)balance);
	    	payment.setPrice(Integer.parseInt(this.price.getText()));
	    	payment.setType(this.payment_type.getValue());
	    	payment.setDescription(this.description.getText());
	    	payment.setDate(Date.valueOf(LocalDate.now()));
	    	payment.setTime(this.payment_time.getText());
	    	paymentDAO.createPayment(payment);
	    	
	    	 BigDecimal d = payment.getId(); 
	    	this.pay_id.setText(d.toPlainString());
	    }
	    
	    @FXML
	    public void processPayment(ActionEvent event) {

	    	this.attd = new Attendant();
	    	this.order = new Order();

    	    this.attd.setId(new BigDecimal(this.attendant_id.getText()));
	    	this.order.setOrder_id(new BigDecimal(this.order_id.getText()));
	    	
	       
	    	
	    	if(this.payment_type.getValue().equals(this.debit) || this.payment_type.getValue().equals(this.credit)) {
	    		
	    		
		    	  String paymentType = this.payment_type.getSelectionModel().getSelectedItem();
		    	    double productPrice = Integer.parseInt(this.price.getText());
		            int moneyPaid = Integer.parseInt(this.amount_paid.getText());
					this.payment_date.setText(this.date);
					this.payment_time.setText(this.currentTime);
					
					  
					 
					  double balanceOfPm = Integer.parseInt(this.balanceOf_pm.getText());//1500
					  double mp = moneyPaid;//1500
	                  double calPayment =  mp - balanceOfPm;
					
		        if(paymentType.matches(credit)) {
		        	
		        	int addDebit = Integer.parseInt(this.lastPaid.getText()) + moneyPaid;  //3000
		    		 double balance = productPrice - addDebit;  
		    		 
		    		  if(this.amount_paid.equals(" ")) {
		    			  Alert alert = new Alert(AlertType.ERROR);
		    			  alert.setTitle("Invalid Input");
		    			  alert.setContentText("Please Enter A valid Amount paid for this Order!");
		    			  alert.setHeaderText(null);
		    			  alert.show();
		    		  }
		    		  else if(balance > 0) {
		    			      		  
	                     this.description.setText("Partial Payment");	  
	    			     this.balanceOf_pm.setText(new BigDecimal(balance).toPlainString());
	    		    	 Payment payment = new Payment();
	    		    	 this.createPaymentInstatnce(payment, balance,addDebit);
	    		    	 
		    		          }else if(balance == 0 ) {
		    		        	  
		    	                     this.description.setText("Completed Payment");	  
		    	    			     this.balanceOf_pm.setText(new BigDecimal(balance).toPlainString());
		    	    		    	  this.payment = new Payment();
		    	    		    	 this.createPaymentInstatnce(payment,balance,addDebit); 
		    		          }
	                   
		    		       else if(calPayment == 0) {
	                    	
	                         this.balanceOf_pm.setText(new BigDecimal(calPayment).toPlainString());	
	                         this.lastPaid.setText(this.amount_paid.getText());;
	                         this.description.setText("Completed Payment");
	                         this.payment = new Payment();
		    		    	 this.createPaymentInstatnce(payment, calPayment,addDebit);
	                      }
		    		  
		    		          else if(moneyPaid > productPrice) {
		    		        	  
		    	                     this.description.setText("oweing customer: "+balance);	  
		    	    			     this.balanceOf_pm.setText(new BigDecimal(balance).toPlainString());
		    	    		    	 this.payment = new Payment();
		    	    		    	 this.createPaymentInstatnce(payment, balance,addDebit);
		    		          }
		    		  
		    		 
		        
		              }else {
		    			  
		    			  if(paymentType.matches(debit)) {
		    			
		    				 
		    				  
             int subtractPayment = Integer.parseInt(this.price.getText()) - Integer.parseInt(this.amount_paid.getText());
             int refundPayment = subtractPayment;
             
                                
		    				     Payment payment = new Payment();
		    				      this.createPaymentInstatnce(payment, refundPayment, refundPayment);
		    			  }
		    			  
		    		  }
		    	

		    		}else {
		    			
		    			Alert alert = new Alert(AlertType.ERROR);
		    			alert.setHeaderText(null);
		    			alert.setContentText("Please Select a Payment Type !");
		    			alert.setTitle("Invalid Payment Selection");
		    			alert.show();
		    			
		    		}
	    	
	    	// end of ...
	    }

	    

	    @FXML
	    void cancelPayment(ActionEvent event) {

	    }



	


	    
}

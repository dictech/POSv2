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
	    private TextField balanceOf_pm;

	    @FXML
	    private ComboBox<String> payment_type;

	    @FXML
	    private Button proces_btn;

	    @FXML
	    private Button cancel_btn;

	    @FXML
	    private Button check_order_btn;

	    private Attendant attd;
	    
	    private SystemAccount sysact;
	    
	    private LoginCtrl lc;
	        	
	    		private final DateTimeFormatter ftmt = DateTimeFormatter.ofPattern("d MMM uuuu");
	    		private final LocalDateTime now = LocalDateTime.now();
	    		private final String date = now.format(ftmt);
	    		private final String currentTime =  LocalTime.now().toString();
	        

		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			ObservableList<String> pType = FXCollections.observableArrayList();
			pType.addAll("Credit","Debit");
			this.payment_type.setItems(pType);
			this.t_date.setText(this.date);
			this.t_date.setEditable(false);
			this.t_time.setText(this.currentTime);
			this.t_time.setEditable(false);
			this.balanceOf_pm.setEditable(false);
			
			
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
	    	  Order o = OrderDAO.getOrder(orderId);
	    	  this.price.setText(o.getTotalPriceOfOrder().toPlainString());
	    	  this.attendant_id.setText(o.getOrder_attd_id().toPlainString());
	    	  this.order_id.setText(o.getOrder_no());
	    	  
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
		
		
	    
	    @FXML
	    void processPayment(ActionEvent event) {
//	    	this.lc = new LoginCtrl();
//	    	this.attd = new Attendant();
//	    	
//	    	this.sysact = new  SystemAccount();
//	    	sysact.setAccountID(sysact.getAccountID());
//	    	sysact.setActAttendantID(sysact.getActAttendantID());
//	    	sysact.setPassword(sysact.getPassword());
//	    	sysact.setUserName(sysact.getUserName());
//	    	SystemAccountDAO.logInSystemAccount(event, lc.getU_name(), lc.getPass());
    	attd.setId(new BigDecimal(1));
	    	
	    	
	    	
	    	int balanceOwed = Integer.parseInt(this.price.getText()) - Integer.parseInt(this.amount_paid.getText());
	    	this.balanceOf_pm.setText(new BigDecimal(balanceOwed).toPlainString());
	    	
	    	Payment payment = new Payment();
	    	payment.setId(payment.getId());
	    	payment.setRecipient(attd);
	    	payment.setOrder(payment.getOrder());
	    	payment.setAmtPaid(Integer.parseInt(this.amount_paid.getText()));
	    	payment.setBalance(balanceOwed);
	    	payment.setPrice(Integer.parseInt(this.price.getText()));
	    	payment.setType(this.payment_type.getValue());
	    	payment.setDate(Date.valueOf(LocalDate.now()));
	    	payment.setTime(this.currentTime);
	    	paymentDAO.createPayment(payment);
	    	
	    	
	    }


	    @FXML
	    void cancelPayment(ActionEvent event) {

	    }


	    
}

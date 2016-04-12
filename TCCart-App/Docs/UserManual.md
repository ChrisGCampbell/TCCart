#TCCart

###NAME  
TCCart - Payment and Rewards Management System for Tea and Coffee Carts for Android 

###DESCRIPTION  
TCCart is a payment and rewards management system used for a coffee and tea cart.  It runs on an Android platform and uses utilities library tccart-services.jar.

TCCart allows customers to pay using a credit card and manages rewards for loyal customers.

 
The TCCart application includes the following capabilities - printing, QR code scanning, credit card scanning, payment processing, order transaction processing, customer history transaction storage, and email sending.

###OPERATING INSTRUCTIONS

#####Customer

######New Customer
* From Main Menu, press "New Customer" button
* Enter full name and email address
* Press "Submit"

######Existing Customer
* From Main Menu, press "Existing Customer" button
* To Make a Purchase
	* Press "Make Purchase" button
	* To add coffee to the purchase, press the plus button next to "coffee"
	* To add tea to the purchase, press the plus button next to "tea"
	* To remove coffee from the purchase, press the minus button next to "coffee"
	* To remove tea from the purchase, press the minus button next to "tea"
	* Press "Submit" button when order is finalized
* To View Purchase History
	* Press "Purchase History" button
	* Purchase history is displayed. Press an individual purchase to view detailed information about that transaction.
* To Edit Customer Information
	* Press "Edit Customer Information"
	* Make necessary changes in fields
	* Press "Submit" button

#####Manager
* From Main Menu, press "Manager Menu"
* To print customer card, press "Print Customer Card" button
* To edit customer data, press "Edit Customer Data" button
* To reset data, press "Reset Data" button

###Utilites Libraries assisting in app operation and support(location:app/libs)
Printing - public static boolean PrintingService.printCard(Stirng firstname, String lastName, String hexID)
	   returns a printed customer card


QR Code Scanning - Public static String QRCodeService.scanQRCode()
           returns either the ID for one of the three possible customers or "ERR"


Credit-CArd Scanning - CreditCardService.readCreditCArd()
	   returns either the CC information for one of the three possible customers or "ERR"


Payment Processing - PaymentService.processPayment(String firstName, String lastName, String ccNumber, Date expirationDate, String securityCode, double amount)
	   No return value


Email Sending Capabilities - EmailService.sendEmail(String recipient, String subject, String body)
           No return value


Misc methods:


scanQRCode()
           Returns customer information


readCreditCard()
           Returns customer information



####BUGS  
No known bugs.

####AUTHORS 
Kevin Hall (khall49@gatech.edu)  
Tyler Bobik (tbobik91@gatech.edu)  
Chris Campbell (chrisgcampbell@gatech.edu)  
Erin Quinn (equinn8@gatech.edu)  


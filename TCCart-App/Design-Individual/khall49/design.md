# **Assignment 5 TCCart Design Document**
 
##Class Rationale
### Main_System
Responsible for running the menu/GUI interface, loading necessary data, and providing the ability to make transactions for the TCCart system.
### Customer
Holds customer information, VIP status, current credit, and purchase history.
### Purchase
Holds data reguarding any transactions a customer makes which include the items purchased, the amount saved from discount, the amount saved from credit, and the date.
### VIP
Holds the starting date and ending date of VIP services, status is checked simply by validating the dates.
### Credit
Holds the amount of credit and the date it is valid until.
### Item
Holds the data that describes the name of the item, its id, and its value.
### Tea
Represents the tea products sold by TCCart, implements Item class.
### Coffee
Represents the coffee products sold by TCCart, implements Item class.
### External Libraries
#### CardPrinter
Needed for running card printing services and functions.
#### VideoCam
Used to read barcodes of cards printed from CardPrinter.
#### CreditCardScanner
Used to read credit cards and get information needed to process a transaction.
#### PaymentProcessor
Used to issue the payment utilizing the information gotten from the CreditCardScanner.
#### EmailService
Used to quickly send emails reguarding services rendered and qualifications for discounts or credit.
### Utility Libraries
#### Money
Used to hold money information reguarding products sold or customer purchase history.
#### Date
Used to determine validity of VIP service and Credit.
##Requirements

###The tea and coffee cart manager uses the system to (1) add customers, (2) print customer cards, (3) edit customer information, (4) process purchases, and (5) keep track of purchases and rewards. For simplicity, we assume that the manager is the only person working at the cart and using the system.

Implemented in menu(). Inside a loop in menu(), the function can call addCustomer(), printCustomer(), editCustomerData(), doTransaction(). Purchases and rewards are tracked via the Customer class and stored in a data file.

###A customer in the system is characterized by the following information: Name, Email address, 8-digit unique hexadecimal ID

Values stored in Customer class with Name, and email as a String and hex_id as a Long which is simply the integer representation of the hexidecimal id.

###When a customer is added to the system using his/her name and email address, the system automatically generates an ID and prints a card for the customer. The card is printed using a special card printer that is accessed through an external library.

This is handled in the addCustomer() function using the CardPrinter external library and saving the necessary data to the customer object and Customer Data file.

###The customer card contains a QR code that can be read using a videocam attached to the system and encodes the customer's unique ID.

Handled by the VideoCam external library, during scan in which is performed in menu().

###All payments must be performed using a credit card. No cash payments are allowed.

doTransaction() will not proceed without scanning in the credit card or canceling the transaction.

###A credit card scanner attached to the system allows the system to read, when a card is swiped, (1) the cardholder's name, (2) the card's account number, (3) the card's expiration date, and (4) the card's security code.

Handled by the external library CreditCardScanner inside doTransaction().

###All the hardware devices attached to the system (card printer, videocam, and credit card scanner) can be accessed through existing external libraries.

Done as represented in the UML diagram.

###Similarly, external libraries enable the system to (1) connect to a payment-processing service provider that can process credit card transactions and (2) send email messages.

Represented as the libraries PaymentProcessor and EmailService as depicted in the UML diagram.

###Customers can use any credit card they want to perform a purchase (including someone else's card, if that person is present). In other words, (1) the credit card information does not have to be associated with the customer in the system, and (2) the credit card information should not be stored after the transaction is completed.

Any information from credit cards is discarded once doTransaction() is complete.

###The system should send an email receipt to a customer every time he/she completes a purchase

Done at the end of doTransaction() following a successful transaction of the credit card.

###Every time a customer spends $30 or more in a single purchase, he/she gets a $3 credit towards his/her next purchase as a reward. The following rules apply:
* **a. The credit expires after a month.**
  * Handled by the validUntil date value in the Credit class.
* **b. The credit is applied towards the very next purchase; that is, a customer cannot choose to save the credit and use it for a subsequent purchase.**
  * Handled in doTransaction() following product cost calculations.
* **c. To get the $3 credit, the customer must actually pay $30 or more; that is, the amount is computed after any existing discount and existing credit is applied. For example, if a customer buys $32 worth of tea and coffee and has a $3 credit, he/she would actually spend $29 and would therefore not get the $3 credit for that purchase.**
  * Handled after successful credit card payment in doTransaction().
* **d. If the cost C of the purchase is less than the current credit for that customer, the credit is decreased by C. For example, if the current credit is $3, and the cost of the purchase is $2.5, the customer pays $0, and the remaining credit is $0.5**
  * Handled much like (b) following product cost calculations except that we only use as much credit as needed by the purchase.

###The system should send an email to a customer when he/she gets a credit.

Done at the end of doTransaction() following a successful transaction of the credit card at greater then or equal to 30 dollars as defined by the value "credit_gain" in the Main_System class.

###Customers who spend $300 or more in a single calendar year achieve VIP status, which entitles them to a 10% discount on every purchase for the following year.
* **a. The change of status is effective from January 1 of the following year.**
  * Handled at the end of doTransaction() checking the current purchase and history against the vip_gain value in the Main_System class to see if the Customer qualifies, and if so, the renew() function in VIP is used to set the valid year that VIP status starts and lasts until, which is a single year by default. Handling it this way allows an extension in the following year if the Customer spends another $300(default value) without messing up the VIP status for the current year.
 
* **b. The discount is applied before any other discount or credit is applied.**
  * Handled in doTransaction() before other calculations.

###The system should send an email to a customer when he/she achieves VIP status for the following year. This email should be sent as soon as the customer qualifies for the VIP status, even if the change of status won't occur during the current year.

Done at the end of doTransaction() following a successful transaction of the credit card with the current purchase and history of purchases in a single year at a value greater then or equal to 300 dollars as defined by the value "vip_gain" in the Main_System class.

###At any particular point in time, the tea and coffee cart manager should be able to display, for any customer in the system, a complete list of their transactions. For each transaction, the tea and coffee cart manager should be able to see (1) date, (2) amount of purchase before discounts, and (3) whether discounts or credits were applied (i.e., which ones, and for which amount).

Handled inside the menu() function, using the function getCustomerPurchaseHistory() and the values noted are expressed in the Purchase class.

##Assumptions

Since the requirements did not specify how exactly items are purchased (Typed in? Scanned by barcode?), I created an id field similar to customer hex_id to allow items to also have a unique 8 digit hex id that can be scanned in using getItem() function. It is assumed that the item list with the hex id values are created by the owner in a seperate file which will be loaded into the program on startup although that means that the products will also need to have printed barcodes onto the cups to allow scanning. If scanning an item fails, it will also be possible to pull up the item list during transaction and select the product(s) by integer values.
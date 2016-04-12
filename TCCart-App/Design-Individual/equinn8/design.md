#TCC Cart: A Payment and Rewards Management System for Tea and Coffee Carts
##UML Class Diagram Design

###Asumptions and Clarifications

####Making a purchase
When a customer makes a purchase, it does not matter what the item(s) are, just the price(s).

####QR Code
When the manager uses the video camera to verify the customer, the video camera returns the hexadecimal ID for that customer.

####Transaction History
The transaction history for each customer is saved as a dictionary.  This dictionary contains the date of transaction, the amount of purchase before discounts, and discount amounts.  If the discount amount is equal to $0, then the system knows that no discounts were applied. The manager can display this transaction history at any time. 

####AUTHOR 
Contact Erin Quinn (equinn8@gatech.edu) for any additional questions or clarifications. 


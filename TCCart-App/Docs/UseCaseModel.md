# Use Case Model

**Author**: Team84

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |

## 1 Use Case Diagram
![alt text](http://i841.photobucket.com/albums/zz331/tbobik769/Project2Usecase.jpg "Use Case Diagram")

## 2 Use Case Descriptions

| Use Case | Customer obtains customer card | 
| --- | --- |
| Requirements | The main system must print a customer card for the customer. |
| Pre-conditions | The customer must be new or need a replacement card. |
| Post-conditions | The customer card must have printed successfully. |
| Scenarios | 1) A new customer requests a customer card.  The main system adds the customer to the system and uses the card printer to print the customer card.  The card printer prints a customer card with a unique QR code that corresponds to customer hexadecimal ID. 2) An existing customer requests a replacement customer card.  The main system uses the card printer to print a replacement customer card with a unique QR code that corresponds to the customer’s existing hexadecimal ID |   
   

| Use Case | Customer purchases item(s) and pays with credit card | 
| --- | --- |
| Requirements | The main system must be able to process a credit card payment using the credit card scanner and the payment processor |
| Pre-conditions | The customer must already be in the system and have a customer card. The customer must have a credit card or have someone with them who has a credit card. |
| Post-conditions | The customer has been given the item(s).  The customer’s credit card has been charged for the item(s). The customer is emailed a receipt. |
| Scenarios | The customer chooses item(s) to purchase and gives the manager his or her customer card.  The main system uses the video camera to identify the customer. If the customer has VIP status, the purhase amount is discounted by 10%. The customer gives the manager his or her credit card and the main system uses the credit card scanner and payment processor to charge the credit card. The main system uses the email service to email the customer a receipt. |   
   

| Use Case | Customer purchases item(s), has a credit, and pays remainder of balance with a credit card |
| --- | --- |
| Requirements | The main system must be able to process a credit card payment using the credit card scanner and the payment processor |
| Pre-conditions | The customer must already be in the system and have a customer card. The customer must have a credit. The customer must have a credit card or have someone with them who has a credit card. |
| Post-conditions | The customer has been given the item(s).  The customer’s credit balance has been reduced to $0. The customer’s credit card has been charged for the remaining balance for the item(s). The customer is emailed a receipt. |
| Scenarios | The customer chooses item(s) to purchase and gives the manager his or her customer card.  The main system uses the video camera to identify the customer. If the customer has VIP status, the purhase amount is discounted by 10%. The customer has a credit, and the credit discount is applied to the purchase. The customer gives the manager his or her credit card and the main system uses the credit card scanner and payment processor to charge the credit card for the remaining balance. The main system uses the email service to email the customer a receipt.|   
   

| Use Case | Customer purchases item(s) and has a credit that covers total purchase amount |
| --- | --- |
| Requirements | The main system must be able to handle a transaction that uses a credit as the customer’s sole method of payment. |
| Pre-conditions | The customer must already be in the system and have a customer card. The customer must have a credit. |
| Post-conditions | The customer has been given the item(s).  The customer’s credit balance has been reduced to the amount of credit less the amount of the purchase. The customer is emailed a receipt. |
| Scenarios | The customer chooses item(s) to purchase and gives the manager his or her customer card.  The main system uses the video camera to identify the customer. If the customer has VIP status, the purhase amount is discounted by 10%. The customer has a credit, which is equal to or greater than the balance owed and the credit discount is applied to the purchase. The main system uses the email service to email the customer a receipt. |   
   
| Use Case | Customer earns a credit |
| --- | --- |
| Requirements | The main system must be able to add a credit to the customer’s account |
| Pre-conditions | The customer must already be in the system and have a customer card. The customer must have made a purchase in which the final amount was $30 or more |
| Post-conditions | The customer’s credit balance has been increased by $3.00 and the expiration date for the credit is set to one month from the current date. The customer is emailed to be notified of the credit earned. |
| Scenarios | The customer has just made a purchase of at least $30.  The main system adds a credit to the customer’s account and changes the expiration date for the credit to be one month from the current date. The main system uses the email service to email the customer to notify them of the earned credit. |   
   

| Use Case | Customer obtains VIP status |
| --- | --- |
| Requirements | The main system must be able to change a customer’s status to VIP |
| Pre-conditions | The customer must already be in the system and have a customer card. The customer must not already have VIP status for the next year. The customer must have made a purchase which caused their purchase totals for the current year to increase above $300  |
| Post-conditions | The customer’s VIP status is changed to true for the following calendar year.  The customer’s VIP status start date is set to January 1 of the following year and the expiration date is set to December 31 of the following year. The customer is emailed to be notified of their new VIP status. |
| Scenarios | The customer has just made a purchase that casued their purchase totals for the current year to increase above $300.  The main system changes the status of the customer to VIP.  The main system changes the VIP start date to January 1 of the following year and the expiration date to December 31 of the following year. The main system uses the email service to email the customer to notify them of their VIP status. |   
   

| Use Case | Manager views a customer’s transaction history |
| --- | --- |
| Requirements | The main system must be able to display the transaction history for any customer in the system |
| Pre-conditions | The customer must already be in the system. The customer must have made at least one transaction. |
| Post-conditions | The customer’s transaction history is displayed |
| Scenarios | The manager wants to view a customer’s transaction history, so the main system gets the customer’s transaction history and displays it. |   
   
   
| Use Case | Manager edits a customer’s information |
| --- | --- |
| Requirements | The main system must be able to edit an existing customer’s information in the system |
| Pre-conditions | The customer must already be in the system. |
| Post-conditions | The customer’s information was successfully changed |
| Scenarios | A customer’s information has changed, so the manager  needs to edit that customer’s information.  The main system edits the information for that customer. |   
   

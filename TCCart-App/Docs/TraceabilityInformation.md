# Traceability
**Author**: Team84

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |
| V2      | Updated Requirements |

## Introduction
This document describes the traceability in different phases of the system development for the requirements, use cases, and test cases. The document satisfies 100% test coverage for the TCCart application from top leel requirements to implementation. 

## Goal
To make sure that the requirements are addressed/covered in all phases of development. Test cases are traceable ot its requirement specification(DesignDocument.md, TestPlan.md, UsecaseModel.md)

## Testable Requirements include
* Functional Rules
* Use Cases
* Error Messages
* Business Rules


## TCCart - Testing Functional Specification and Requirements:
The users of TCCart can ring up purchases from customers when they click on the "Purchase" button. Clickong on the
purchase button will bring up a menu of items a customer can purchase. New users can be added to the system and 
receive a physical card. Customer information is stored in the form of first name, last name, QR Code, and
email address. Customers can earn awards in the form of credits and VIP status which gives the customer
a discount on future products. 

## Test Description/Scenarios for TCCart 
Req1 - Tests adding customer to system

Req2 - Tests editing customer's name and email address

Req3 - Tests saving a purchase to the customer's history

Req4 - Tests adding credit to customer account

Req5 - Tests successful customer tranaction with $0 credit

Req6 - Tests successful customer transaction of at least $300

Req7 - Tests adding item to purchase

Req8 - Tests purchasing items with more than $0 in credit.

Req9 - Tests saving customer data to file.

Req10 - Tests printing of customer card.

Req11 - Tests scanning/swipping of customer card

Req12 - Tests transactions with customer who has obtained VIP status

Req13 - Tests error message when trying to save customer data without first name, last name and email address.


## Tracebility Test Scenarios for TCCart Matrix

| Requirements |    | Req1     | Req2 | Req3 | Req4 | Req5 | Req6 | Req7 | Req8 | Req9 | Req10 | Req11 | Req12 | Req13 |
| --------|:---------------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|
| Test Cases      | Totals |
| TC1      |  | x
| TC2       | |   | x  |  |  |  |  |  |  | x |
| TC3       | |   | x  |
| TC4       | |   |    |  | x |
| TC5       | |   |    |  |   | x |  | x |  |   |  | x  |
| TC6       | |   |    | x |  |   | x | x | |   |  | x  |
| TC7       | | x |    |   |  |   |   |   | | x | x |
| TC8       | |   |    |   |  |   |   |   | |   |  |    |  x  |   
| TC9       | |   |    |   |  |   |   |   |x |  
| TC10      | |   |  x  |   |  |   |   |   | |   |  |    |    | x |
    
          


## Internal Traceability Data: 
    Purchase History
    Customer Credits
    VIP Status
    Customer Information
    Product codes
    Customer QR Code
    Transaction Number

## External Traceability Data: 
    Credit Card Services

#  UseCases
## UseCase 1
## Customer purchases item(s) and has a credit that covers total purchase amount.
### Flow of Operation (top level)
| Main Success Scenario| Step | Description 
| --------|:---------------:|:---------------:|
| Customer purchases item(s)    | 1| User starts app
|  and has a credit that     | 2| Display Main Screen
|  covers total purchase amount.    | 3| User Scans Customer Card
|       | 4| Display Customer Information Screen
|       | 5| User clicks Purchase Items button
|       | 6| Display Purchase Screen
|       | 7| User enters items purchased
|       | 8| User calculates purchased total
|       | 9| User clicks on Credits and Rewards Screen
|       | 10| Credits/Rewards Screen Displayed
|       | 11| Credits/Rewards discount Calculated into purchased total(if credit available).
|       | 12| Display Total Screen
|       | 13| Customer Purchase Complete Screen
|       | 14| Print Receipt Screen
|       | 15| Confirmation Receipt Sent
|       | 16| Purchase History Saved

### Flow of Operation including external libraries
| Main Success Scenario| Step | Description 
| --------|:---------------:|:---------------:|
| Customer purchases item(s) and has a credit that covers total purchase amount.    | 1| User starts app
|      | 2| Display Main Screen
|      | 3| Scan Customer Card
|       | 4| QRCodeService
|       | 5| Retrieve Customer Info 
|       | 6| Return Customer Information to Customer Information Screen from customer database - (Data Source(s): CustomerQR Code, Customer Information)
|       | 7| Customer Information Screen (Customer Information/data populated)
|       | 8| Customer Purchase Items Process
|       | 9| Total Dollar Value of Items Purchased - (Data Source(s): Product codes)
|       | 10| Customer Credit Calculation Process
|       | 11| Track Customer Credits 
|       | 12|  Customer Credits Retrieved(if existing credits)  
|       | 13| Customer Credit Applied(if available) - (Data Sources(s): Credit, VIP)
|       | 14| Customer Purchase Complete
|       | 15| Record Customer Transaction History Stored & Transaction Number
|       | 16| Email Services.Receipt Sent via Email - (Data Sources(s): Transaction database)

## UseCase 2
## Customer purchases item(s) and pays with credit card 
### Flow of Operation (top level)
| Main Success Scenario| Step | Description 
| --------|:---------------:|:---------------:|
| Customer purchases item(s)    | 1| User starts app
|  and pays with credit card     | 2| Display Main Screen
|      | 3| User Scans Customer Card
|       | 4| Display Customer Information Screen
|       | 5| User clicks Purchase Items button
|       | 6| Display Purchase Screen
|       | 7| User enters items purchased
|       | 8| User calculates purchased total
|       | 9| User clicks on Credits and Rewards Screen
|       | 10| Credits/Rewards Screen Displayed
|       | 11| Credits/Rewards discount Calculated into purchased total(if credit available).
|       | 12| Display Total Screen
|       | 13| User clicks on pay
|       | 14| Display pay screen
|       | 15| User swipes credit card
|       | 16| Display payment in process screen
|       | 17| Customer Purchase Complete Screen
|       | 18| Print Receipt Screen
|       | 19| Confirmation Receipt Sent
|       | 20| Purchase History Saved

### Flow of Operation including external libraries
| Main Success Scenario| Step | Description 
| --------|:---------------:|:---------------:|
| Customer purchases item(s) and has a credit that covers total purchase amount.    | 1| User starts app
|      | 2| Display Main Screen
|      | 3| Scan Customer Card
|       | 4| QRCodeService
|       | 5| Retrieve Customer Info 
|       | 6| Return Customer Information to Customer Information Screen from customer database - (Data Source(s): CustomerQR Code, Customer Information)
|       | 7| Customer Information Screen (Customer Information/data populated)
|       | 8| Customer Purchase Items Process
|       | 9| Total Dollar Value of Items Purchased - (Data Source(s): Product codes)
|       | 10| Customer Credit Calculation Process
|       | 11| Track Customer Credits 
|       | 12|  Customer Credits Retrieved(if existing credits) 
|       | 13| Customer Credit Applied(if available) - (Data Sources(s): Credit, VIP)
|       | 14| Display pay screen
|       | 15| User swipes credit card
|       | 16| User clicks on pay
|       | 14| Display pay screen
|       | 15| User swipes credit card
|       | 16| Display payment in process screen - CreditCardService.readCreditCard - Credit Card Data Processed - (Data Source(s): Credit Card Services)	
|       | 17| Customer Purchase Complete
|       | 18| Print Receipt Screen 
|       | 19| Confirmation Receipt Sent  - Email Services.Receipt Sent via Email 
|       | 20| Purchase History Saved - (Data Sources(s): Transaction database)
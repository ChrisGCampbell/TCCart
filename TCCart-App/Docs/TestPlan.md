# Test Plan

**Author**: Team84

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |
| V2      | Updated Test Cases|
| V3	  | Finalized Test Cases|

## 1 Testing Strategy

### 1.1 Overall strategy

Our current plan is to use all the basic testing strategies to validate our design for the android application.

We will be manually testing individual components of the system. We plan to test each individual non-insignificant function in every class before moving on to integration testing. 

At integration testing, we plan to group test units together using the bottom-up testing strategy since our implementation of the android application relies on some very simple classes and a couple of complex classes.

At system testing, we plan to use android emulators that match the most common android device on the market and make sure that the components that pass integration testing don't exhibit incorrect behavior on the emulated hardware.

We will also perform regression testing after implementation of the physical hardware libraries but we likely won't need to test very much since the hardware shouldn't be completely intertwined in the design.

### 1.2 Test Selection

At the current time, our team will be selecting test cases using partitioning for black box testing cases and control flow graphs for whitebox testing when we feel it is needed. As the design is in pre-prototype stage, this section is subject to change if the design dictates needing different testing methods.

### 1.3 Adequacy Criterion

Our team is going to assess the quality of our test cases by using the coverage notion known as "Statement Coverage" in which testers are required to generate test cases which cover all the statements in a program at least once. Through this test set, the adequacy criterion should be met.

### 1.4 Bug Tracking

Bugs and enhancement requests will be tracked and logged as needed by the team using GitHub's built in Issue Tracker.

## 2 Test Cases

| Test Case | Purpose | Steps | Expected Result | Actual Result | Pass/Fail | Additional Information |
| --- | --- | --- | --- | --- | --- | --- |
| **Add Customer** | Capability to add a customer to system | Select "New Customer" in menu and enter in customer name and email address | Customer is added to the system | Customer is added to the system | Pass | N/A |
| **Edit Customer Data** | Capability to change to customer name and email address | Select "Existing Customer" in application, select "Edit Customer Information", change the name and/or email fields | Customer name and email address are edited, as entered | Customer name and email address are edited, as entered | Pass | N/A |
| **Save Purchase** | Capability to save customer purchases to purchase history | Do a successful customer transaction | The purchase is saved to the customer's history |The purchase is saved to the customer's history | Pass | N/A |
| **Add Credit** | Allows customer to obtain credit | Do a successful customer transaction of at least $30 | Appropriate amount of money is added to the customer's credit amount | Appropriate amount of money is added to the customer's credit amount | Pass | N/A |
| **Use Credit** | Allows customer to use credit to reduce purchase cost | Have more than $0 credit and do successful customer transaction | Depletes credit from customer as needed and reduces total cost of transaction | Depleted credit from customer as needed and reduced total cost of the transaction | Pass | N/A |
| **Renew VIP Status** | Grants valid VIP status for reducing cost of future purchases | Do a successful customer transaction of at least $300  | Customer's VIP status is active for the following year | Customer's VIP status was activated for the following year. | Pass | N/A |
| **Add Item** | Allows a customer to have a transaction of multiple items | Customer purchases more than 1 item | Adds multiple items to purchase | Added multiple items to purchase | Pass | N/A |
| **Apply Discount** | Reduces total cost of customer purchase | Do a customer transaction where the customer has VIP status | Modifies the discount to reflect VIP discount and reduced the total purchase by that amount | Modified the discount to reflect VIP discount and reduced total purchase by that amount | Pass | N/A |
| **Apply Credit** | Reduces total cost of customer purchase | Do a customer transaction where the customer has more than $0 credit | Modifies item_credit and reduces total cost | Modified item_credit and reduced total cost | Pass | N/A |
| **Update Customer Data** | Updates customer data in separate file | Do any action that modifies the customer data/history | Saves customer data to file | Saved customer data to file | Pass | N/A |
| **Print Customer Card** | Allows a customer to have a card used for purchases | Printed following customer being added to system or as needed | Prints out customer card when new customer is added as well as from manager menu as needed | Printed out customer card when a new customer is added, and printed from Manager Menu | Pass | N/A |
| **Do Transaction** | Allows a customer to buy items from cart | Scan customer card, select transaction, scan/select item(s), then swipe customer credit card | System completes transaction correctly | System completeed transaction correctly | Pass| N/A |

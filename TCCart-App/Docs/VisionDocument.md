# Vision Document

**Author**: Team84

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |

## 1 Introduction

TCCart is a Android application designed for managers that allows them to manage customer purchases and rewards, and complete transactions with the application acting as a register.

## 2 Business Needs/Requirements

Our team is building this product as part of a business transaction with Brad and Janet, whose Tea and Coffee business has picked up momentum. At the current time, they can only handle customers that use cash and have no way of easily managing rewards for returning customers. In the current market, consumers continue to gravitate toward solutions that allow them buy or purchase services without being forced to carry cash on them or pull cash out at the time of purchase. They have found that in order for their business to grow further, they must have a way to allow customers to use credit cards for accessibility and be able to reward their loyal customers. In addition to these two main requirements, there are number of smaller requirements that Brad and Janet have also provided that should be planned for carefully in order to streamline the development of this product.

## 3 Product / Solution Overview

The solution our team came up for their business problem is to build them a Android application that can essentially act a register by allowing the device the application is running on to hook up to physical devices that will allow them scan and print customer cards, and swipe credit cards for transactions. It also allows the client to add customers, allows them to view previous transactions including the items ordered, date, total, rewards used and discounts applied. A file itself in the storage of the device itself will store customer data, which includes their name, email address, store credit, and VIP status which the application can load as needed. This solution allows us to accomplish the two major requirements which was to provide a way for the customer to use credit cards and the ability to reward customer loyalty. This covers the current plans but if develop certain aspects of the application carefully, we can potentially implement features that allow the owners of the application more control over their business like the ability to set different levels of rewards based on money the customer spent, additional VIP benefits, special deals on certain items like specific tea’s and coffee’s and more.

## 4 Major Features

* Application processes transactions with credit cards
* Application remembers customer purchase history in case of disputes, returns, ect.
* Application stores customer data in order to keep track of customer bonuses
* Application can reward store credit to customers purchasing above a certain money level set by the manager in a given purchase
* Application can reward VIP status to customers purchasing above a certain money level set by the manager in a given year
* Ability to define sellable items by name, price in a separate file stored in device
* Application can produce customer cards through a card printer hooked up to the device running the application
* Application can send emails to customers about their purchases as well as benefits that they qualify for

## 5 Scope and Limitations

### Scope
* Application is intended to be used for transactions involving the purchase of Tea’s and Coffee’s and will not directly support the sale of items outside of these defined types
* Application will store only the information necessary to provide benefits and rewards overtime to customers and not any sensitive data beyond a transaction such as credit card information
* Application will only provide VIP benefits once every qualifying year (example: You cannot get a second year of VIP by purchasing 600 dollars worth of items in a single year)
* This application is intended for Android platforms, and does not support non-android platforms.

### Limitations
* Application is limited to credit card transactions only
* Application does not allow customer to specify amount of credit they can spend toward a purchase
* Application does not provide management of item inventory
* Application does not include the physical devices that can be hooked up to the device running the application itself
* Application at the current time does not support the removal of a customer from the application outside of directly modifying the customer file.
* Application does not provide any safety checks for ensuring the price you are selling an item is correctly set in the stored item file. (example: $300 instead of $30)
* It is up to the manager to ensure items are correctly set to appropriate costs to prevent issues with customers and potentially credit card companies.
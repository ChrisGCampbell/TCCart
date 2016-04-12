#Design Discussion


#Evaluation of Individual Design UML Class Design From Each Team Member
####Design1 (Tyler)
![alt text](http://i841.photobucket.com/albums/zz331/tbobik769/designTyler.jpg "Design1")
#####Pros:
* Efficient.
* Good Order class that connects with the Vip and Rewards class.
* Transactions class contains an array-list and all necessary elements to keep track of each transaction.
* Clear connections between classes make the diagram readable and user-friendly
* First and last name are separate attributes of the customer instead of having one "name" attribute
* Good mapping of objects and entities

#####Cons:
* Could be cleaned up a bit.
* Could have the Customer class contain Vip and Rewards as objects instead of having them in the Order class to help consolidate the information for better ease of retrieving.
* Should move the Transactions class inside the Customer class to help consolidate the program no need to have as separate class.
* Describes too much of external classes (not necessary)
* Should move all External so they all connect to one class.
* Missing external email library and CreditCardScanner isn't labeled as external
* PaymentProcessing class has sendEmail function, so it should have an association with the customer
* No need for a function to delete credit card information

####Design2 (Chris)
![alt text](http://i841.photobucket.com/albums/zz331/tbobik769/designChris.jpg "Design2")
#####Pros:
* Very detailed.
* The Vip and Credits Classes are very robust and fully built.
* Clear labels of each of the relationships.
* Handles transaction failures
* Good handling of relationships between classes regarding many to many, many to one, etc.

#####Cons:
* While the Vip and Credits Classes are built well, you could have the Customer store objects for them instead of having a separate Class track them.
* Good idea of having a Cart Manager Interface but think it over complicates the program for what 
we need it to accomplish.
* Should move all External so they all connect to one class.
* Missing external email library
* Describes too much of external classes (not necessary)
* The card printer does not need to print anything but the QRCode, so it has extraneous actions
* The video camera has action ScanStudentCard(), but it probably should be renamed scanCustomerCard instead. 

####Design3 (Erin)
![alt text](http://i841.photobucket.com/albums/zz331/tbobik769/designErin.jpg "Design3")
#####Pros:
* Clear labels of each of the relationships.
* Having the Customer class store the VIP status, Credit amount as and the transaction History as a dict.
* Excellent manager class as one class accessing all external Libraries makes it cleaner by consolidating the separate external Libraries.
* Shows relationships as being many to one, one to many, etc
* Excelent class diagrams expressing the static structure of the system
* Model is easy to understand, even for non-programmers

#####Cons:
* Could add a order class.
* Should instead have credit as a separate class with creditAmount and creditExpiration as attributes to make diagram more clear
* Missing payment Processor and email external libraries
* creditCard class probably isn't necessary, we should be able to pass the data to the payment processor object/class.
* Doesn't have coffee and tea classes that are connected to an "item" class

####Design4 (Kevin)
![alt text](http://i841.photobucket.com/albums/zz331/tbobik769/designKevin.png "Design4")
#####Pros:
* Having the Customer class store the VIP status and Credit amount as objects.
* Having the Customer class store the purchases as an dict.
* Having an item class allows for different methods of entering in a purchase
* Very robust, very clear, and well organized.
* Entities and objects of the system can be realized and built into a functional system.

#####Cons:
* Don't need to define the external libraries as attributes inside the Main_System class because you show a connection.
* No actions are defined on relationships between classes.

#Team Design 
![alt text](http://i841.photobucket.com/albums/zz331/tbobik769/finalDesign.png "TeamDesign")

###Commonalities and Differences between Individual designs

####Commonalities:
######Design1 (Tyler)
	* Both have a purchase class connected to his customer class.
	* Both have a separate class for Vip and Credit.
######Design2 (Chris)
	* Both have separate class for Vip and Credit.
######Design3 (Erin)
	* Uses a separate Manager class to connect to the external libraries.
	* Has transaction class that handles the transactions outside the Customer class and stores it in the Customer class.
######Design4 (Kevin)
	* Uses a separate Main_System class to connect to all external libraries.

####Differences:
######Design1(Tyler)
	* Did not have a Class connecting all the external libraries.
	* Has separate Transaction class that stores the transactions instead of having them stored inside the Customer class.
######Design2 (Chris)
	* Extraneous actions in external classes.
 	* Cart Manager Interface Object added. 
######Design3 (Erin)
	* Does not include an Item class.
######Design4 (Kevin)
	* External library attributes inside main system class.
####Justification For Final Design Decision
The final design solution was implemented based on the requirements document, the context of the system, system specific characteristics, and the desired functionality of the system. 
#####Things we took into consideration and concentrated on are listed below:
	* The types of data and information flowing over objects and classes
	* The nature of the interface to the managers of the system
	* An efficient system
	* To be scalable and easily maintainable in the future.
	* To have external classes to facilitate transactions
#Summary
This document is a technical design document for use by developers and managers that wish to analyze the TCCart, the payment and rewards management system. This document provides guidance, insight, and the decision making processes of the TCCart design throughout its development phase. This design document is intended to provide Brad and Janet, the two owners, future developers, and stakeholders background information on the development and management of the TCCart Management System.
Important issues that we took into consideration include the architecture of the system, relationships to system objects, best approach to designing an efficient Object Oriented System, code re-use, and the desire to make the system scalable for adding new features in the future.

Our team learned a lot about the different ways you can put together a UML diagram. When looking at everyone's design, we found that they could all most likely realize the finished program, but it wasn't always clear in some diagrams which systems did what or how they connected. We also got to see how differently team members decided to handle purchases input by the Manager. One design was minimalistic and handled the bare necessities, another design would hardcode the tea and coffee values and from the given information, neither of these designs were wrong but they did force our team to ask the question "How should we allow the manager to input purchases and how simple should we make it for the manager to use?". We decided on a class that holds item information in order to allow us to create a list format that the manager can use in order to minimize human errors and allow expansion of the item list in the future if need be. Through this information, we felt that the Design4 UML was the closest to realizing the type of system we discussed and thus became the base that we would use to improve on and clarify relationships and requirements. With the base team design established, we improved readability and worked to reduce redundancy in naming resulting in a much more cohesive design that should both show and realize the program that Brad and Janet have hired us to create.


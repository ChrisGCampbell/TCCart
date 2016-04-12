# Design Document
**Author**: 

Team84


Kevin Hall (khall49@gatech.edu)  
Tyler Bobik (tbobik91@gatech.edu)  
Chris Campbell (chrisgcampbell@gatech.edu)  
Erin Quinn (equinn8@gatech.edu)

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |

## 1 Design Considerations

### 1.1 Assumptions

* We assume client will be using a smartphone that runs on an Android operating system.
* We assume they will be running an operational environment with a minimum of Android SDK IceCream.
* We assume that the client will have at minimum a basic understanding of how to work an Android app.
* We assume that the client will be using this application as a customer record and payment processing device. 
* We assume data, QR scanning, emailing, and card printing will handle conditions to function correctly via external libraries.
* We assume the system will send an email receipt to a customer each time he/she completes a purchase.
* We assume the system will automatically print a card with the QR code and assign the customer a hexadeicmal number when a new customer is added.
* We assume this software will be used by TCCart Managers and that TCCart Managers understand the TCCart Payment & Rewards Management System's application control flow. 
* We assume the external database libraries such as the database server, database software, and database management system will store the history of credits, rewards, purchases, and transactions are maintained in external libraries.
* We assume any software failures and/or errors will be handled by exception handling algorithm and an error report sent to developer in form of an email. 
* We assume if the user exits the program, the program will delete anything that was not saved and the user will have to restart.

### 1.2 Constraints

* Only one screen should appear at a time.
* Need to take into consideration that the client will be using this on a mobile phone so we have to take into account a smaller screen size.
* Android devices have limited RAM compared to desktop devices so we have to keep that in mind.
* We have to be mindful of the size of the program because mobile devices have a relatively small and limited storage.
* The user must have a basic understanding of QR scanner, card printer and credit card proccessing. 
* Device does not need internet access because we send emails and process the payments with an external system, and its the external system that needs to be connected to the internet.
* We have to mindful of battery life of the Android device, poorly written code can deplete a device's battery quickly.
* Programming language is limited to using Java SDK 1.7 and Android.
* User should have a smartphone and operating system should include Android operating system API 15 or later.
* System has fault tolerant algorithm to catch an error and recover application without crashing the system. This constraint logs the error and rebuilds the database from the last checkpoint.
* User needs to understand the English language or acquire a translational software device as the TCCart software has no language translation and is made for the English language.
* User agrees to not to distrubute software to other parties without authorization.

### 1.3 System Environment

* The software the client must run is API 15: Android 4.0.3 (IceCreamSandwich)
* The device must have at least 512 MB RAM and also a decent GPU for 2D drawing, minimum of a 1 Ghz processor to run IceCreamSandwich.
* The absolute minimum hardware requirements for Android Android 4.0.3 (IceCreamSandwich)
* The device the client runs must be able to connect to external devices.
* The external devices consist of a video camera, card printer, credit card scanner, payment processor, and an email service.

## 2 Architectural Design

### 2.1 Component Diagram

![alt text](http://i841.photobucket.com/albums/zz331/tbobik769/Componant%20Design.png "Component Diagram")

### 2.2 Deployment Diagram

![alt text](http://i841.photobucket.com/albums/zz331/tbobik769/Deployment%20Diagram.png "Deployment Diagram")

## 3 Low-Level Design

### 3.1 Class Diagram

![alt text](http://i841.photobucket.com/albums/zz331/tbobik769/finalDesign.png "Class Diagram")

## 4 User Interface Design
![alt text](http://i841.photobucket.com/albums/zz331/tbobik769/UI%20mockup.png "UI mockup")

* TCCart is a GUI-based systems.
* Graphical mockups are provided.
* User selects commands by selecting a choice on the GUI menu.
* The GUI is designed for managers possibly working in a fast-paced environment wishing to provide customers with prompt and quality service. Thus, we created a GUI layout that is simple to navigate, simple to use, and simple to understand. 
* The buttons on the GUI are big and bold, accommodating users with small and big fingers. 
* There is no room for error in selecting the wrong button as buttons are aligned vertically with sufficient space between them.
* GUI was designed to accommodate human hand/finger gestures. Thus most of the buttons on the GUI are located half screen so there is no need for finger stretching to click a button.
* The GUI inspires confidence and the bold yellow and black colors inspires use. Colors are consistent.
* The GUI intergrated functions and libraries effectively give the managers access to outside services such as email and payment processing.
* There are no extraneous information or images on the GUI to detract from the sole purpose of the software which is to manage customer accounts.
* GUI keeps the user in control of the application state when errors occur by issuing a subtle error message notifying and reminding the user to the attention of the error. Effort has been taken to insure that GUI will not stall or produce a "freeze screen"
* User can anticipate what the next interaction should be by the GUI design layout. Design is fluid and non-confusing.








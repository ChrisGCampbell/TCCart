* VideoCamera, CardPrinter and PaymentProcessing are external libraries. 
* I took the liberty of making an Order class that accesses the Rewards and Vip class and it calculates the total order if there is a reward or if the customer has vip status or not. It also will update the Rewards and Vip instance variables, which I might make global variables in the future.
* After the Order class checks the VIP and Rewards Class, it sends this information with the recordTransaction method to the Transaction class, where the Transaction class withh use an arrayList to keep track of the date and details of each order. The Transaction class will also be accessable for search with the Customer class. 



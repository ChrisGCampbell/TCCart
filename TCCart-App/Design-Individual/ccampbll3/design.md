#Design Document

####NAME  
       TCCart Payment and Rewards Management System for Tea and Coffee Cart Design Document(design.md) 

####DESCRIPTION  
       Object Class Diagram that deplicts a visual representation of the relationships and 
       dependencies among classes in the TCCart Payment and Rewards Management System. 

####ASSUMPTIONS/RATIONALE FOR DESIGN  
       -Assumed Manager is using an interface called "Cart Manager Interface" to select create customer,
	Get Info on a selected customer, and to ringup purchses.
	-Assume utility classes are used to handle payment processesing, read in data on customer card,
	print customer cards, and read credit card
	-Assumed that Rewards and Discounts are discounted after the total of purchased is calculated. 
	i.e.(Manager gets the total of 2 coffees and 2 teas, then applies discounts if any)
	-Used short notes/descriptions where needed to emphasis transitions in states (state changes).
	i.e.(manager moves from adding a customer to new state of ringing up purchases)
	-Demonstrated cardinality(Multiplicity) between classes.
	-Demostrated relationships, associations, and inheritance between class relationships.

####AUTHORS 
       Chris Campbell (chrisgcampbell@gatech.edu) ccampbll3  


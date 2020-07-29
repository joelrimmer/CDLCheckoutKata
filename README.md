#CheckoutKata

###General

This program implements code for a checkout system that 
handles pricing schemes such as “apples cost 50 pence, three apples cost £1.30.”

The pricing and offers for products is supplied from a CSV file supplied 
by the user in the root.

For demo purposes the prices used are those outlined in the brief:

| Item | Unit Price (pence) | Special Price (pence) |
|:------:|:------------------:|:---------------------:|
|A|	50|	3 for 130|
|B|	30|	2 for 45
|C|	20|	
|D|	15|	

###Using the program
Upon running the program the pricing and offers will display to the user.

Following the display of the pricing, users will be prompted in the console to input a single shopping item to 'scan'.

Upon entering a product, the current items in the transaction & the current running total£ will display to users.

A user can continue to enter products 1by1 into the console and continue to see a running total displayed. 

An option to enter 'checkout' will be displayed in a message after each product is scanned to advise the user how to finish the program. 

Upon a user entering 'checkout' the program will finish and display a final cost.


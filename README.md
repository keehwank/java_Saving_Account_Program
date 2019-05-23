# java_Saving_Account_Program
Design and abstract class named BankAccount to hold the data such as balance, number of deposits this month, number of withdrawals, 
annual interest rate. The class should have following methods:
1. Constructor: the constructor should accept arguments for the balance and annual interest rate.
2. Deposit: A method that accepts an argument for the amount of the deposit. 
   The method should add the argument to the account balance. It should also increment the variable holding th number of deposits.
3. CalcInterest: A method that updates the balance by calculating the monthly interest earned by the account, and adding this interest 
   to the balance. 
4. MonthlyProcess: A method that calls the calcInterest method, and then sets the variables that hold the number of withdrawals, 
   number of deposits to zero.

Next, design a SavingsAccount class that extends the BankAccount class. The SavingsAccount class should have a status field to represent 
an active or inactive account. If the balance of a savings account falls below $25, it becomes inactive. No more withdrawals may be made
until the balance is raised above $25. at which time the account becomes active again. The savings account class should have the following 
methods:
1. Withdraw: A method that determines whether the account is inactive before a withdrawal is made. No withdrawal will be alloowed 
   if the account is not active. A withdrawal is then made by calling the superclass version of the method. After the superclass method 
   is called, this method checks the number of withdrawals. If the number of withdrawals for the month is more than 4, a service charge 
   of $1 for each withdrawal above 4 is from the balance. Don't forget to check the account balance after the service charge is taken. 
   If the balance falls below $25, the account becomes inactive.
2. Deposit: A deposit is then made by calling the superclass version of the method. If the account is inactive and the deposit brings 
   the balance above $25, the account becomes active again.
3. MonthlyProcess: Calls the super class monthly process and displays the final balance.

Finally, make sure that your classes throw appropriate exceptions when an attempt is made to insert invalid data.

import java.io.*;
import java.util.Scanner;

public class SavingsAccountDemo
{
	public static abstract class BankAccount
	{
		private double balance, annualInterestRate;
		private int numberDeposit = 0, numberWithdrawal = 0;
		
		public BankAccount(double bal, double rate)
		{
			balance = bal;
			annualInterestRate = rate;
		}
		
		public void deposit(double amtDeposit)
		{
			balance += amtDeposit;
			numberDeposit++;
		}
		
		public void withdraw(double amtWithdrawal)
		{
			balance -= amtWithdrawal;
			numberWithdrawal++;
		}
		
		public void calcInterest()
		{
			double monthlyInterest;
			monthlyInterest = balance * annualInterestRate / 12;
			balance += monthlyInterest;
		}
		
		public abstract void monthlyProcess();
				
		public void setBalance(double bal)
		{
			balance += bal;
		}
		
		public void setNumberDeposit(int num)
		{
			numberDeposit = num;
		}
		
		public void setNumberWithdrawal(int num2)
		{
			numberWithdrawal = num2;
		}
		
		public double getBalance()
		{
			return balance;
		}
		
		public double getAnnualInterestRate()
		{
			return annualInterestRate;
		}

		public int getNumberDeposit()
		{
			return numberDeposit;
		}
		
		public int getNumberWithdrawal()
		{
			return numberWithdrawal;
		}
	}	
	
	public static class SavingsAccount extends BankAccount
	{
		private boolean active;
		
		public SavingsAccount(double bal, double rate)
		{
			super(bal, rate);
						
			if (super.getBalance() > 25)
				active = true;
			else
				active = false;
		}
		
		public void withdraw(double amtWithdrawal)
		{
			if (active == true && super.getBalance() >= amtWithdrawal)
			{	
				super.withdraw(amtWithdrawal);
				if (super.getBalance() <= 25)
				{	
					System.out.println("Your balance is less than minimum balance. Your account is now INACTIVE ");
					active = false;
				}	
				if (super.getNumberWithdrawal() > 4)
				{
					System.out.println("You have exceeded monthly limit of withdrawals. Fee of $1 charged\n");
					super.setBalance(-1);
				}
			}	
			else
			{
				System.out.println("ERROR: Transaction declined!! This transaction will cause overdraft or zero balance");
			}
		}
		
		public void deposit(double amtDeposit)
		{
			super.deposit(amtDeposit);
			if (active == false && super.getBalance() > 25)
			{
				active = true;
				System.out.println("Your account is now ACTIVE\n");
			}
		}
		
		public void monthlyProcess()
		{
			super.calcInterest();
			super.setNumberDeposit(0);
			super.setNumberWithdrawal(0);
			System.out.printf("Your Balance after Monthly process is: %.2f", super.getBalance());
			System.out.println();
		}
		
		public boolean getActive()
		{
			return active;
		}
	}	
	
	public static class NegativeInput extends Exception
	{
		public NegativeInput()
		{
			super("Error: Must enter positive value\n");
		}
		
		public NegativeInput(boolean act)
		{
			super("Error: Your account is INACTIVE\n");
		}
	}
		
	public static void displayMenu()
	{
		System.out.println("Enter D to deposit");
		System.out.println("Enter W to Withdraw");
		System.out.println("Enter B for Balance");
		System.out.println("Enter M for Monthly Process");
		System.out.println("Enter E to Exit");
		
	}
	
	public static void main(String[] args) throws Exception
	{
		double balance, annualInterestRate, amtDeposit, amtWithdrawal;
		String choice;
				
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter beginning balance :$");
		balance = sc.nextDouble();
		
		System.out.print("Enter interest rate(whole number) :%");
		annualInterestRate = sc.nextDouble() / 100;
		sc.nextLine();
		
		SavingsAccount savingsAccount = new SavingsAccount(balance, annualInterestRate);
		
		do
		{			
			do
			{
				displayMenu();
				choice = sc.nextLine().toUpperCase();
				if (choice.charAt(0) != 'D' && choice.charAt(0) != 'W' && choice.charAt(0) != 'B' && choice.charAt(0) != 'M' &&choice.charAt(0) != 'E')
					System.out.println("Invalid choice. Try again\n");
			} while (choice.charAt(0) != 'D' && choice.charAt(0) != 'W' && choice.charAt(0) != 'B' && choice.charAt(0) != 'M' &&choice.charAt(0) != 'E');
			
			
			switch (choice)
			{
				case "D":
					System.out.print("Enter the amount you want to Deposit :$");
					amtDeposit = sc.nextDouble();
					sc.nextLine();
					try
					{
						if (amtDeposit <= 0)
							throw new NegativeInput();
						savingsAccount.deposit(amtDeposit);
					}
					catch(NegativeInput e)
					{
						System.out.println(e.getMessage());
					}
					break;
					
				case "W":
					try
					{
						if (savingsAccount.getActive() == false)
							throw new NegativeInput(false);
					}
					catch(NegativeInput e)
					{
						System.out.println(e.getMessage());
						break;
					}
					System.out.print("Enter the amount you want to withdraw :$");
					amtWithdrawal = sc.nextDouble();
					sc.nextLine();
					try
					{
						if (amtWithdrawal <= 0)
							throw new NegativeInput();
						savingsAccount.withdraw(amtWithdrawal);
					}
					catch(NegativeInput e)
					{
						System.out.println(e.getMessage());
					}
					break;
				
				case "B":
					System.out.printf("Your Balance is: %.2f", savingsAccount.getBalance());
					System.out.println();
					break;
					
				case "M":
					savingsAccount.monthlyProcess();
					break;
					
				case "E":
					System.out.printf("Balance : $%.2f", savingsAccount.getBalance());
					System.out.println("\nThank you. Bye");
					break;
				
				default:
					break;
			}
		} while (choice.charAt(0) != 'E');	
	}
}
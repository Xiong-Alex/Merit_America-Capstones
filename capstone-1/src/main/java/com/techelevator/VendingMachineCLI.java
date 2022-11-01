package com.techelevator;

import com.techelevator.util.PurchaseLog;
import com.techelevator.view.Menu;
import com.techelevator.Inventory;

import java.io.File;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class VendingMachineCLI {

	// Menu options on the main menu put into an array to be displayed and used by methods in menu class.
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };


	// Menu options on the Purchase menu put into an array to be displayed and used by methods in menu class.
	private static final String PURCHASE_OPTIONS_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_OPTIONS_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_OPTIONS_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_OPTIONS = { PURCHASE_OPTIONS_FEED_MONEY, PURCHASE_OPTIONS_SELECT_PRODUCT, PURCHASE_OPTIONS_FINISH_TRANSACTION };


	Inventory stocks = new Inventory();
	List<Purchasable> stock = stocks.stockingInventory(); // a list containing all inventory info currently in vending machine

	private Menu menu;
	Money money = new Money();
	DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
	String dateString2 = dateFormat2.format(new Date()).toString();



	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	/*
	 * A method that will start and end the vending machine. It will display options to the user and take input from the
	 * user to execute different methods depending on what user wants to do. Will have two different default menus: Main
	 * Menu and Purchase Menu. 3 options to chose from on each screen. Main Menu's purchase option should direct user to
	 * Purchase Menu.
	 */

	public void run() {
		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS); // Takes the choice input from user.


			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				displayPurchasableItems();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase

				while(true) {
					choice = (String) menu.getPurchaseChoiceFromOptions(PURCHASE_OPTIONS, money);

					if (choice.equals(PURCHASE_OPTIONS_FEED_MONEY)) {
						feedMoney();
					} else if (choice.equals(PURCHASE_OPTIONS_SELECT_PRODUCT)) {
						selectProduct();
					} else if (choice.equals(PURCHASE_OPTIONS_FINISH_TRANSACTION)) {
						finishTransaction();
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}
		}
	}

	/*
	 * Allows user to put money into vending machine and adds that money to current balance on machine.
	 */

	public void feedMoney(){

		Scanner cashInput = new Scanner(System.in);
		System.out.println("Enter 0 to Stop Feeding\nPlease enter money amount (only $1, $2, $5, $10):");
		double moneyEntered = cashInput.nextDouble();


		if(moneyEntered == money.getONE()){
			money.addOne();
			PurchaseLog.log(dateString2 + " FEED MONEY: " + money.formatMoney(money.getONE()) + " " + money.formatMoney(money.getMoney()));
			money.displayBalance();
			feedMoney();
		} else if(moneyEntered == money.getTWO()){
			money.addTwo();
			PurchaseLog.log(dateString2 + " FEED MONEY: " + money.formatMoney(money.getTWO()) + " " + money.formatMoney(money.getMoney()));
			money.displayBalance();
			feedMoney();
		} else if(moneyEntered == money.getFIVE()){
			money.addFive();
			PurchaseLog.log(dateString2 + " FEED MONEY: " + money.formatMoney(money.getFIVE()) + " " + money.formatMoney(money.getMoney()));
			money.displayBalance();
			feedMoney();
		} else if(moneyEntered == money.getTEN()){
			money.addTen();
			PurchaseLog.log(dateString2 + " FEED MONEY: " + money.formatMoney(money.getTEN()) + " " + money.formatMoney(money.getMoney()));
			money.displayBalance();
			feedMoney();
		} else if(moneyEntered == 0){ //ends if statement and returns to purchase screen.
		} else{
			System.out.println("Not a valid bill");
		}
	}

	/*
	 * Displays complete list of items in vending machine to user and prompts user to select a slot location for
	 * purchase of item. Will dispense item if user has enough money feed into machine and item is in stock. Will
	 * display different messages depending on situation of user input.
	 */

	public void selectProduct(){
		Scanner slotInput = new Scanner(System.in);
		int count = 0; // used to determine if item slot provided by user is included in vending machine. 0 is false.

		displayPurchasableItems();
		System.out.println("Enter item slot");
		String selectedSlot = slotInput.nextLine();

		// iterate through list to see if item slot is in vending machine. increase count if user input matches slot in list.
		for(Purchasable x : stock){
			if(selectedSlot.equalsIgnoreCase(x.getSlot())){
				count++;
			}
		}

		if(count == 0){
			System.out.println("Item Does not Exist");
		}

		for (Purchasable x : stock){

			if(!(x.getSlot().equalsIgnoreCase(selectedSlot))){
				continue;
			}

			//the item selected by user exists, has stock, and user has enough money to buy item. Purchase item.
			if(x.getSlot().equalsIgnoreCase(selectedSlot) && x.getStock() > 0 && money.getMoney() > x.getPrice()){
				money.setMoney(money.getMoney() - x.getPrice());
				System.out.println(x.getName() + " " +
						money.formatMoney(x.getPrice()) +
						"\nBalance Remaining: " + money.formatMoney(money.getMoney()));
				x.takeStock();
				x.printMessage();
				PurchaseLog.log(dateString2 + " " + x.getName() + " " + x.getSlot() + " " +
						money.formatMoney(money.getMoney()) +" "  + money.formatMoney(money.getMoney() - x.getPrice()));
				break;
			} else if(x.getStock() == 0){
				System.out.println("Item SOLD OUT");
			} else if(money.getMoney() < x.getPrice()){
				System.out.println("Insufficient Funds");
			}
		}
	}

	/*
	 * Once user is done with purchase user can choose to finish and this method gives remaining balance back to user
	 * in only coins. Quarters, Dimes, and Nickels.
	 */

	public void finishTransaction(){
		System.out.println(money.returnChange(money.getMoney()));
		PurchaseLog.log(dateString2 + " GIVE CHANGE: " + money.formatMoney(money.getMoney()) + " $0.00");
		money.setMoney(0);
	}

	// Will display items available for purchase and tell user if an item is sold out.
	public void displayPurchasableItems(){

		for(Purchasable x: stock) {
			if (x.getStock() <= 0) {
				System.out.println(x.getSlot() + " " + x.getName() + " " +
						money.formatMoney(x.getPrice())  + " " + x.getType() + " Stock: SOLD OUT");
			} else {
				System.out.println(x.getSlot() + " " + x.getName() + " " +
						money.formatMoney(x.getPrice())  + " " + x.getType() + " Stock: " + x.getStock());

			}
		}
	}


	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();


	}
}

package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);
    private Scanner input = new Scanner(System.in);


    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

//    Why do we have these two methods?
//    public int promptForInt(String prompt) { //are we using this?
//        System.out.print(prompt);
//        while (true) {
//            try {
//                return Integer.parseInt(scanner.nextLine());
//            } catch (NumberFormatException e) {
//                System.out.println("Please enter a number.");
//            }
//        }
//    }
//
//    public BigDecimal promptForBigDecimal(String prompt) {
//        System.out.print(prompt);
//        while (true) {
//            try {
//                return new BigDecimal(scanner.nextLine());
//            } catch (NumberFormatException e) {
//                System.out.println("Please enter a decimal number: ");
//            }
//        }
//    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }


    //

    public void printBalance(BigDecimal balance) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Account Details");
        System.out.println("--------------------------------------------");
        if (balance == null) {
            System.out.println("No Account to print");
        } else {
            System.out.println("Your Current Balance is: $" + balance);
        }
    }

    public void printUsers(User[] users){ //takes in UserList
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Users");
        System.out.println("ID          Name");
        System.out.println("--------------------------------------------");
        //print out id and name
        for (User user : users) {
            String formatString = "%-12s%1s";
            System.out.printf(formatString, user.getId(), user.getUsername());
            System.out.println();
//            System.out.println(user.getId() + "        " + user.getUsername());
        }

        System.out.println("---------");
        System.out.println();

        System.out.print("Enter ID of user you are sending to (0 to cancel): ");


    }



    public void printTransferHistory(Transfer[] transfers, String baseUrl, AuthenticatedUser currentUser) {
        System.out.println("-------------------------------------------");
        System.out.println("Transfers");
        System.out.println("ID          From/To                 Amount");
        System.out.println("-------------------------------------------");

        UserService userService = new UserService(baseUrl);
        userService.setUser(currentUser);
        for (Transfer transfer : transfers) {
            String formatString = "%-12s%-6s%-10s%15s";//spacing format

            if(transfer.getAccountTo().equals(userService.getAccountByUserId(currentUser.getUser().getId()))){
                String usernameAccountFrom = userService.getUsername(transfer.getAccountFrom());

                System.out.printf(formatString, transfer.getTransferId(), "From:", usernameAccountFrom, "$ " + transfer.getAmount());
                System.out.println();

//                System.out.println(transfer.getTransferId() + "          From: " + usernameAccountFrom + "          " + "$ " + transfer.getAmount());
            } else {
                String usernameAccountTo = userService.getUsername(transfer.getAccountTo());
                System.out.printf(formatString, transfer.getTransferId(), "To:", usernameAccountTo, "$ " + transfer.getAmount());
                System.out.println();
//                System.out.println(transfer.getTransferId() + "          To:   " + usernameAccountTo + "          " + "$ " + transfer.getAmount());
            }
        }
        System.out.println("---------");
        System.out.print("Please enter transfer ID to view details (0 to cancel): ");

    }

    public void printTransferDetails(Transfer transfer, String baseUrl, AuthenticatedUser currentUser) {

        UserService userService = new UserService(baseUrl);
        userService.setUser(currentUser);
        String usernameAccountFrom = userService.getUsername(transfer.getAccountFrom());
        String usernameAccountTo = userService.getUsername(transfer.getAccountTo());

        System.out.println("--------------------------------------------");
        System.out.println("Transfer Details");
        System.out.println("--------------------------------------------");
        System.out.println("Id: " + transfer.getTransferId());
        System.out.println("From: " + usernameAccountFrom);
        System.out.println("To: " + usernameAccountTo);
        System.out.println("Type: " + transfer.getTransferType());
        System.out.println("Status: " + transfer.getTransferStatus());
        System.out.println("Amount: " + transfer.getAmount());
    }

    public void printPendingRequest(Transfer[] pendingTransfers, String baseUrl, AuthenticatedUser currentUser) {
        System.out.println("-------------------------------------------");
        System.out.println("Pending Transfers");
        System.out.println("ID          To                     Amount");
        System.out.println("-------------------------------------------");


        UserService userService = new UserService(baseUrl);
        userService.setUser(currentUser);

        for (Transfer transfer : pendingTransfers) {

            String formatString = "%-12s%-15s%16s";
            System.out.printf(formatString, transfer.getTransferId(), userService.getUsername(transfer.getAccountTo()), transfer.getAmount());
            System.out.println();

//            System.out.println(transfer.getTransferId() + "          " + userService.getUsername(transfer.getAccountTo()) + "                 $ "+ transfer.getAmount());
        }

        System.out.println("---------");
        System.out.print("Please enter transfer ID to approve/reject (0 to cancel): ");
    }

    public void promptTransferAction(){
        System.out.println("1: Approve");
        System.out.println("2: Reject");
        System.out.println("0: Don't approve or reject");
        System.out.println("---------");
        System.out.print("Please choose an option: ");
    }

    public int transferChoice(){
        boolean run = true;
        int choice = inputInt();

        while (run) {

            switch (choice) {

                case 0 :
                    run = false;
                    break;
                case 1 :
                    run = false;
                    choice = 2;
                    break;
                case 2 :
                    run = false;
                    choice = 3;
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid option");
                    promptTransferAction();
                    choice = inputInt();
                    break;
            }

        }

        return choice;
    }

    public int inputInt(){
        int inputInt;

        while (true) {
            try {
                inputInt = Integer.parseInt(scanner.nextLine());
                break;
            }catch (Exception e) {
                System.out.println();
                System.out.print("Please enter a Valid Number: ");
                System.out.println();
            }
        }

        return inputInt;
    }

    public Long inputLong() {
        Long inputLong;

        while (true) {
            try {
                inputLong = Long.parseLong(scanner.nextLine());
                break;
            }catch (Exception e) {
                System.out.println();
                System.out.print("Please enter a valid Id: ");
            }
        }

        return inputLong;
    }

    public BigDecimal promptAmount() {
        BigDecimal amount;

        System.out.print("Enter amount: ");

        while (true) {

            try {
                amount = BigDecimal.valueOf(scanner.nextDouble());
                break;
            } catch (InputMismatchException ex) {
                System.out.println();
                System.out.print("Enter a Valid Amount:");
            }
        }

        return amount;
    }

}

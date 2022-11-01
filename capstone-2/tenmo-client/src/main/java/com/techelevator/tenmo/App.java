package com.techelevator.tenmo;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.UserService;
import com.techelevator.util.BasicLogger;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private AuthenticatedUser currentUser;
    private final UserService userService = new UserService(API_BASE_URL);


    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();

        if (authenticationService.register(credentials)) {
            System.out.println();
            System.out.println("Registration successful");
        } else try {
            System.out.println("\nThat username is already taken, please try again.");
        } catch (NullPointerException e) {
            System.out.println("\nAn error has occured.. please try again.");
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        userService.setUser(currentUser);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
        Long accountId = userService.getAccountByUserId(currentUser.getUser().getId());//retrieves accountId
        BigDecimal balance = userService.getBalance(accountId);//retrieves balance w/userID
        consoleService.printBalance(balance);//format and prints balance
	}


	private void viewTransferHistory() {
		// TODO Auto-generated method stub
        Long accountId = userService.getAccountByUserId(currentUser.getUser().getId());
        Transfer[] transferHistory = userService.transferHistory(accountId);

        if(transferHistory.length > 0) {
            consoleService.printTransferHistory(transferHistory, API_BASE_URL, currentUser);

            Long transferId = consoleService.inputLong();
            Transfer transfer = getTransferFromList(transferHistory, transferId);

            if(transferId != 0 && transfer != null){
                    consoleService.printTransferDetails(transfer, API_BASE_URL, currentUser);
            } else if (transfer == null && transferId != 0) {
                System.out.println("Transfer not found");
            }//else is assumed to be zero (cancel)

        }
        else {
            System.out.println("\nNo Current Transfer History");
        }

	}

    private Transfer getTransferFromList(Transfer[] transfers, Long transferId) {
        Transfer targetTransfer = null;

        for (Transfer transfer: transfers) {
            if(transfer.getTransferId().equals(transferId)){
                targetTransfer = transfer;
                break;
            }
        }
        return targetTransfer;
    }

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
        Long accountId = userService.getAccountByUserId(currentUser.getUser().getId());
        Transfer[] pendingTransfers = userService.pendingTransfers(accountId);

        if(pendingTransfers.length > 0) {
            consoleService.printPendingRequest(pendingTransfers, API_BASE_URL, currentUser);

            Long transferId = consoleService.inputLong();
            Transfer currentTransfer = getTransferFromList(pendingTransfers, transferId);

            if (transferId != 0 && currentTransfer != null) {
                consoleService.promptTransferAction();
                int statusId = consoleService.transferChoice();

                if (statusId == 2) {

                    BigDecimal myBal = userService.getBalance(accountId);
                    BigDecimal amount = currentTransfer.getAmount();
                    Long toAccountId = currentTransfer.getAccountTo();

                    if (myBal.compareTo(amount) >= 0) { //update balances
                        userService.updateBalance(myBal.subtract(amount), accountId);
                        userService.updateBalance(userService.getBalance(toAccountId).add(amount), toAccountId);
                        userService.updateTransfer(statusId, currentTransfer);
                    } else {
                        System.out.println("Insufficient funds in account.");
                    }

                } else if (statusId == 3) {
                    userService.updateTransfer(statusId, currentTransfer);
                }

            } else if (currentTransfer == null && transferId != 0) {
                System.out.println("Transfer not found");
            }

        } else {
            System.out.println("\nNo Current Pending Request");
        }
	}

    private void sendBucks() {
        User[] users = userService.findUsersNotYou(currentUser);
        Transfer transfer = new Transfer();

        consoleService.printUsers(users);
        //^need to check if userToSend Exists

        Long userId = consoleService.inputLong();
        Boolean userExists = userExist(users, userId);

        if(userExists == true) {
            Long toAccountId = userService.getAccountByUserId(userId);
            Long fromAccountId = userService.getAccountByUserId(currentUser.getUser().getId());
            if (toAccountId != 0) {
                BigDecimal amount = consoleService.promptAmount();//prompt for amount and set amount
                BigDecimal myBal = userService.getBalance(fromAccountId);
                //^Need to check if amount is <= than balance || amt == 0
                if (myBal.compareTo(amount) >= 0 && amount.compareTo(BigDecimal.ZERO) != 0) {
                    userService.updateBalance(myBal.subtract(amount), fromAccountId);
                    transfer.defaultTransfer(fromAccountId, toAccountId, amount);
                    //set parameters for new transfer
                    userService.updateBalance(userService.getBalance(toAccountId).add(amount), toAccountId);
                    userService.addTransfer(transfer);
                    //pushes new transfer out
                }
            } else {
                System.out.println("\nCanceled transaction.");
            }
        } else {
            System.out.println("Account does not exist");
        }

    }

    private boolean userExist(User[] users, Long input){
        boolean userExists = false;

        for (User user : users) {
            if (user.getId().equals(input)){
                userExists = true;
            }
        }
        return userExists;
    }

	private void requestBucks() {
        User[] users = userService.findUsersNotYou(currentUser);
        Transfer transfer = new Transfer();

        consoleService.printUsers(users);
        Long toAccountId = userService.getAccountByUserId(consoleService.inputLong());
        Long fromAccountId = userService.getAccountByUserId(currentUser.getUser().getId());

        BigDecimal amount = consoleService.promptAmount();
        transfer.defaultTransferRequest(toAccountId, fromAccountId, amount);
        userService.addTransfer(transfer);
		
	}

}

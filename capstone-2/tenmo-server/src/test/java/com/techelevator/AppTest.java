package com.techelevator;

import java.math.BigDecimal;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.UserService;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private AuthenticatedUser currentUser;
    private final UserService userService = new UserService(API_BASE_URL);

    @Test
    public void viewCurrentBalance() {
        currentUser = authenticationService.login(new UserCredentials("TestUser","TestPassword")); //login test account
        userService.setUser(currentUser);

        Long accountId = userService.getAccountByUserId(currentUser.getUser().getId()); //retrieves accountId
        BigDecimal balance = userService.getBalance(accountId); //retrieves balance w/userID
        Assert.assertEquals(balance.toString(), "800.00");
    }

    @Test
    public void viewPastTransfers() {
        currentUser = authenticationService.login(new UserCredentials("TestUser","TestPassword")); //login test account
        userService.setUser(currentUser);

        Long accountId = userService.getAccountByUserId(currentUser.getUser().getId());
        Transfer[] transferHistory = userService.transferHistory(accountId);
        consoleService.printTransferHistory(transferHistory, API_BASE_URL, currentUser);
        Assert.assertEquals(transferHistory.length, 1);
    }
}
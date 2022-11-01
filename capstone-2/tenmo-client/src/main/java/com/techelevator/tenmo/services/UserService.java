package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {
    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser user;

    public  UserService(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }

    public void setUser(AuthenticatedUser user)
    {
        this.user = user;
    }

    //Returns all users that we can send to
    public User[] findUsersNotYou(AuthenticatedUser currentUser) {
        User[] users = null;

        try {
            ResponseEntity<User[]> response =
                restTemplate.exchange(baseUrl + "/users/" + currentUser.getUser().getId(),
                        HttpMethod.GET, makeAuthEntity(), User[].class);
            users = response.getBody();
            //users = restTemplate.getForObject(baseUrl + "users/" + currentUser.getUser().getId(), User[].class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return users;
    }

    //Returns the current users transfer history
    public Transfer[] transferHistory(Long accountId) {
        Transfer[] transfers = null;
        int pendingStatusId = 1;

        try {
            ResponseEntity<Transfer[]> response =
                    restTemplate.exchange(baseUrl + "/transfer/history/" + accountId,
                            HttpMethod.GET, makeAuthEntity(), Transfer[].class);
            transfers = response.getBody();
            //transfers = restTemplate.getForObject(baseUrl + "/transfer/history/" + accountId, Transfer[].class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }

    //Returns the current users pending history
    public Transfer[] pendingTransfers(Long accountId) {
        Transfer[] transfers = null;
        int transferStatus = 1;

        try {
            ResponseEntity<Transfer[]> response =
                    restTemplate.exchange(baseUrl + "/transfer/" + transferStatus + "/" + accountId,
                            HttpMethod.GET, makeAuthEntity(), Transfer[].class);
            transfers = response.getBody();
            //transfers = restTemplate.getForObject(baseUrl + "/transfer/" + transferStatus + "/" + accountId, Transfer[].class);
        } catch (RestClientResponseException | ResourceAccessException e) {
        BasicLogger.log(e.getMessage());
        }

        return transfers;
    }

    //Gets the users account using the userId
    public Long getAccountByUserId(Long userId){
        Long accountId = null;
        try {
            ResponseEntity<Long> response =
                    restTemplate.exchange(baseUrl + "user/account/" + userId,
                            HttpMethod.GET, makeAuthEntity() ,Long.class);
            accountId = response.getBody();
            //accountId = restTemplate.getForObject(baseUrl + "user/account/" + userId, Long.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }

        return accountId;
    }

    //Gets the balance of a user
    public BigDecimal getBalance(Long userID){
        BigDecimal balance = null;
        try {
            ResponseEntity<BigDecimal> response =
                    restTemplate.exchange(baseUrl + "account/balance/" + userID,
                            HttpMethod.GET, makeAuthEntity() ,BigDecimal.class);
            balance = response.getBody();
            //balance = restTemplate.getForObject(baseUrl + "account/balance/" + userID, BigDecimal.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }

        return balance;
    }

    //To view a specific transfer in transfer history
    public Transfer getTransfer(Long transferId) {
        Transfer transfer = null;
        try {
            ResponseEntity<Transfer> response =
                    restTemplate.exchange(baseUrl + "transfer/" + transferId,
                            HttpMethod.GET, makeAuthEntity() ,Transfer.class);
            transfer = response.getBody();
            //transfer = restTemplate.getForObject(baseUrl + "transfer/" + transferId, Transfer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }

        return transfer;
    }

    //Gets the username of the user
    public String getUsername(Long accountId) {
        String username = null;

        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(baseUrl + "user/username/" + accountId,
                            HttpMethod.GET, makeAuthEntity() ,String.class);
            username = response.getBody();
            //username = restTemplate.getForObject(baseUrl + "/user/username/" + accountId, String.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return username;
    }

    //Adds a new transfer for when sending or requesting bucks
    public Transfer addTransfer(Transfer newTransfer)
    {

        HttpEntity<Transfer> entity = makeTransferEntity(newTransfer);
        Transfer transfer = null;
        try
        {
            transfer = restTemplate.postForObject(baseUrl + "transfer/", entity, Transfer.class);
        }
        catch (ResourceAccessException e) {
            // Handle network I/O errors
            System.out.println(e.getMessage());
            transfer = null;
        }
        catch (RestClientResponseException e) {
            // Handle response status codes 4xx and 5xx
            System.out.println(e.getRawStatusCode());
            transfer = null;
        }

        return transfer;
    }

    //Updates the transfer if it is approved or rejected
    public boolean updateTransfer(int transferStatusId, Transfer transfer) {
        boolean success = false;
        try {
            restTemplate.put(baseUrl + "/transfer/" + transferStatusId + "/" + transfer.getTransferId(), makeTransferEntity(transfer));
            success = true;
        } catch (RestClientResponseException e) {
            // Handle response status codes 4xx and 5xx
            System.out.println(e.getRawStatusCode());
        }

        return success;
    }

    //Updates user balance when sending bucks
    public boolean updateBalance(BigDecimal newBalance, Long accountId) {
        boolean success = false;

        try {
            restTemplate.put(baseUrl + "/account/update/balance/" + newBalance + "/" + accountId, Account.class);
            success = true;
        } catch (RestClientResponseException e) {
            // Handle response status codes 4xx and 5xx
            System.out.println(e.getRawStatusCode());
        }

        return success;
    }

    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(user.getToken());
        return new HttpEntity<>(headers);
    }

    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(user.getToken());
        return new HttpEntity<>(transfer, headers);
    }

    public User[] findAllUsers() {
        User[] users = null;

        try {
            users = restTemplate.getForObject(baseUrl + "users/", User[].class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return users;
    }



}

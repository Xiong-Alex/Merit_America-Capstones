package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransferController {

    @Autowired
    private final TransferDao transferDao;

    public TransferController(TransferDao transferDao){
        this.transferDao = transferDao;
    }

    //Adds a transfer to our db
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/transfer")
    public void addTransfer(@RequestBody Transfer transfer) {
        transferDao.addTransfer(transfer);
    }

    //List all transfers
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/transfer")
    public List<Transfer> getAllTransfers() {
        return transferDao.getAllTransfers();
    }

    //To view transfer history using the accountId
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/transfer/history/{accountId}")
    public List<Transfer> getAllTransfersFromAccount(@PathVariable Long accountId) {
        return transferDao.getAllTransfersFromAccount(accountId);
    }

    //Get all transfers
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/transfer/{transferStatusId}/{accountFrom}")
    public List<Transfer> getTransferByStatusId(@PathVariable Long transferStatusId, @PathVariable Long accountFrom) {
        return transferDao.getTransferByStatusId(transferStatusId, accountFrom);
    }

    //Gets a transfer using the transferId
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/transfer/{transferId}")
    public Transfer getTransfer(@PathVariable Long transferId) {
        return transferDao.getTransfer(transferId);
    }

    //Updates transfer status when accepting or rejecting a pending request
    @PutMapping(path = "/transfer/{transferStatusId}/{transferId}")
    public Transfer updateTransferStatus(@PathVariable int transferStatusId, @RequestBody Transfer transfer){
        return transferDao.updateTransferStatus(transferStatusId, transfer);
    }

}
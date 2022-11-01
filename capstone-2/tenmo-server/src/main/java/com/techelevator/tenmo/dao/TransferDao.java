package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    void addTransfer(Transfer transfer);

    List<Transfer> getAllTransfers();

    List<Transfer> getTransferByStatusId(Long statusId, Long accoundTo);

    List<Transfer> getAllTransfersFromAccount(Long accountId);

    Transfer getTransfer(Long transferId);

    Transfer updateTransferStatus(int transferStatusId, Transfer transfer);
}

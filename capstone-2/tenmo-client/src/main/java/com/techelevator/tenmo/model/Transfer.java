package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {

    private Long transferId;
    private Long transferTypeId;
    private Long transferStatusId;
    private Long accountFrom;
    private Long accountTo;
    private BigDecimal amount;

    public Transfer() {
    }

    public Transfer(Long transferTypeId, Long transferStatusId, Long accountFrom, Long accountTo, BigDecimal amount) {
        this.transferTypeId = transferTypeId;
        this.transferStatusId = transferStatusId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public void defaultTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        setTransferTypeId(2L);//send by default
        setTransferStatusId(2L);//approved by default
        setAccountFrom(fromAccountId);
        setAccountTo(toAccountId);
        setAmount(amount);
    }

    public void defaultTransferRequest(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        setTransferTypeId(1L);//Request by default
        setTransferStatusId(1L);//Pending by default
        setAccountFrom(fromAccountId);
        setAccountTo(toAccountId);
        setAmount(amount);
    }

    public String getTransferType(){
        if (getTransferTypeId() == 1) {
            return "Request";
        }
        else
        {
            return "Send";
        }
    }

    public String getTransferStatus(){
        if (getTransferStatusId() == 1) {
            return "Pending";
        }
        else if(getTransferStatusId() == 2)
        {
            return "Approved";
        }
        else
        {
            return "Rejected";
        }
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public Long getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(Long transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public Long getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(Long transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public Long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Long accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

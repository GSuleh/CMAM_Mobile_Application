package com.example.cmamapplication;

public class Request {
    Long requesting_committee,requested_committee, amount;
    String serial_number,accepted;

    public Request() {
    }

    public Long getRequesting_committee() {
        return requesting_committee;
    }

    public void setRequesting_committee(Long requesting_committee) {
        this.requesting_committee = requesting_committee;
    }

    public Long getRequested_committee() {
        return requested_committee;
    }

    public void setRequested_committee(Long requested_committee) {
        this.requested_committee = requested_committee;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public Request(Long requesting_committee, Long requested_committee, String serial_number, Long amount, String accepted) {
        this.requesting_committee = requesting_committee;
        this.requested_committee = requested_committee;
        this.serial_number = serial_number;
        this.amount = amount;
        this.accepted = accepted;
    }
}

package com.parabank.context;

public class TestContext {

    private String username;
    private String password;
    private int accountId;
    private int fromAccountId;
    private int toAccountId;
    private double transferAmount;
    private double initialBalance;
    private double uiBalance;
    private double apiBalance;
    private int customerId;
    private int apiStatusCode;
    private String apiResponseBody;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public double getUiBalance() {
        return uiBalance;
    }

    public void setUiBalance(double uiBalance) {
        this.uiBalance = uiBalance;
    }

    public double getApiBalance() {
        return apiBalance;
    }

    public void setApiBalance(double apiBalance) {
        this.apiBalance = apiBalance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getApiStatusCode() {
        return apiStatusCode;
    }

    public void setApiStatusCode(int apiStatusCode) {
        this.apiStatusCode = apiStatusCode;
    }

    public String getApiResponseBody() {
        return apiResponseBody;
    }

    public void setApiResponseBody(String apiResponseBody) {
        this.apiResponseBody = apiResponseBody;
    }
}
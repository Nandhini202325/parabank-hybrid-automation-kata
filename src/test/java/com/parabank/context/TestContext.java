package com.parabank.context;

public class TestContext {

    private String username;
    private String password;
    private String accountId;
    private String fromAccountId;
    private String toAccountId;
    private String transferAmount;
    private String initialBalance;
    private String uiBalance;
    private String apiBalance;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(String initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getUiBalance() {
        return uiBalance;
    }

    public void setUiBalance(String uiBalance) {
        this.uiBalance = uiBalance;
    }

    public String getApiBalance() {
        return apiBalance;
    }

    public void setApiBalance(String apiBalance) {
        this.apiBalance = apiBalance;
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
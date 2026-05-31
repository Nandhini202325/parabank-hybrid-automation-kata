package com.parabank.context;

public class TestContext {

    private String accountId;
    private String uiBalance;
    private String apiBalance;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
}
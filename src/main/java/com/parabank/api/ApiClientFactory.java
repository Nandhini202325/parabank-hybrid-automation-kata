package com.parabank.api;

public final class ApiClientFactory {

    private static final CustomerApiClient CUSTOMER_API_CLIENT = new CustomerApiClient();
    private static final AccountApiClient ACCOUNT_API_CLIENT = new AccountApiClient();
    private static final TransferApiClient TRANSFER_API_CLIENT = new TransferApiClient();

    private ApiClientFactory() {
    }

    public static CustomerApiClient customer() {
        return CUSTOMER_API_CLIENT;
    }

    public static AccountApiClient account() {
        return ACCOUNT_API_CLIENT;
    }

    public static TransferApiClient transfer() {
        return TRANSFER_API_CLIENT;
    }
}
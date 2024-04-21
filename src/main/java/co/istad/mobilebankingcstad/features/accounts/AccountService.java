package co.istad.mobilebankingcstad.features.accounts;

import co.istad.mobilebankingcstad.features.accounts.dto.AccountRequest;
import co.istad.mobilebankingcstad.features.accounts.dto.AccountResponse;

import java.util.List;

public interface AccountService {
    List<AccountResponse> getAllAccounts();
    AccountResponse createAccount(AccountRequest accountRequest);
    AccountResponse findAccountById(String id);
    AccountResponse findByAccountNumber(String accountNumber);
    List<AccountResponse> findAccountsByCustomerId(String customerId);
}

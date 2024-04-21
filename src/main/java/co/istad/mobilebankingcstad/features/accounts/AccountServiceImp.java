package co.istad.mobilebankingcstad.features.accounts;

import co.istad.mobilebankingcstad.domain.Account;
import co.istad.mobilebankingcstad.domain.UserAccount;
import co.istad.mobilebankingcstad.features.accounts.dto.AccountRequest;
import co.istad.mobilebankingcstad.features.accounts.dto.AccountResponse;
import co.istad.mobilebankingcstad.features.feature.AccountTypeRepository;
import co.istad.mobilebankingcstad.features.user.UserRepository;
import co.istad.mobilebankingcstad.features.useraccount.UserAccountRepository;
import co.istad.mobilebankingcstad.mapper.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImp implements  AccountService{

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserAccountRepository userAccountRepository;

    @Override
    public List<AccountResponse> getAllAccounts() {

        return userAccountRepository.findAll()
                .stream().map(accountMapper::mapUserAccountToAccountResponse).toList();
    }

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        // check account type
        var accountType = accountTypeRepository.findByName(accountRequest.accountType())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account "+accountRequest.accountType() +" type not found"));
        var owner = userRepository.findById(accountRequest.userId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"User id "+ accountRequest.userId()+ " not found"));

//        check if user already has 5 accounts already
        if (userAccountRepository.countAccountByUserId(accountRequest.userId()) >= 5){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User is "+accountRequest.userId() + "  already has 5 accounts");
        }

        var account = accountMapper.mapRequestToEntity(accountRequest);
        account.setAccountType(accountType);
        UserAccount userAccount = new UserAccount().
                setAccount(account).
                setUser(owner).
                setIsDisable(false);
        userAccountRepository.save(userAccount);

        return   accountMapper.mapUserAccountToAccountResponse(userAccount);
    }

    @Override
    public AccountResponse findAccountById(String id) {
        var accountForResponse =    userAccountRepository.findByAccount_Id(id).
        orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account id "+ id + " not found"));
        return accountMapper.mapUserAccountToAccountResponse(accountForResponse);
    }

    @Override
    public AccountResponse findByAccountNumber(String accountNumber) {
        System.out.println(accountNumber);
        return userAccountRepository.findByAccount_AccountNumber(accountNumber)
                .map(accountMapper::mapUserAccountToAccountResponse)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account number "+ accountNumber + " not found"));
    }

    @Override
    public List<AccountResponse> findAccountsByCustomerId(String customerId) {
        return null;
    }
}

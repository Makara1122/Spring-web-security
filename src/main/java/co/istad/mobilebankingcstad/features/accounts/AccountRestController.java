package co.istad.mobilebankingcstad.features.accounts;

import co.istad.mobilebankingcstad.features.accounts.dto.AccountRequest;
import co.istad.mobilebankingcstad.features.accounts.dto.AccountResponse;
import co.istad.mobilebankingcstad.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    @PostMapping("")
    public BaseResponse<AccountResponse>
        createAccount(@RequestBody AccountRequest accountRequest) {
        return BaseResponse.<AccountResponse>createSuccess().
        setPayload(accountService.createAccount(accountRequest));
    }

    @GetMapping("")
    public BaseResponse<List<AccountResponse>> getAllAccounts(){
        return BaseResponse.<List<AccountResponse>>getAllSuccess().
                setPayload(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by id")
    public BaseResponse<AccountResponse> getAccountWithId(@Parameter(
            description =  "Account Id",
            required = true,
            example = "1f6fda39-5f3a-4c6c-a0f6-5a8a2e6c5b1b"
    )@PathVariable String id){
        System.out.println(id);
        return BaseResponse.<AccountResponse>ok().setPayload(accountService.findAccountById(id));

    }

    @GetMapping("/by-number/{accountNumber}")
    @Operation(summary = "Get account by account number")
    public BaseResponse<AccountResponse> getAccountByAccountNumber(@Parameter(
            description = "Account Number",
            required = true,
            example = "1234567890"
    )@PathVariable String accountNumber){
        return BaseResponse.<AccountResponse>ok().setPayload(accountService.findByAccountNumber(accountNumber));
    }
}

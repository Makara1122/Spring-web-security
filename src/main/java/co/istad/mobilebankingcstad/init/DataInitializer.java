package co.istad.mobilebankingcstad.init;

import co.istad.mobilebankingcstad.domain.AccountType;
import co.istad.mobilebankingcstad.domain.Role;
import co.istad.mobilebankingcstad.features.feature.AccountTypeRepository;
import co.istad.mobilebankingcstad.features.roles.RoleRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


// populate database ( role with some data )
@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final AccountTypeRepository accountTypeRepository;
    @PostConstruct
    void roleInit(){
        List<String> roles = List.of("ADMIN","STUFF","USER");
        if(roleRepository.findAll().isEmpty()){
            for(var role : roles){
                var roleObj = new Role();
                roleObj.setName(role);
                roleRepository.save(roleObj);
            }
        }

    }

    @PostConstruct
    void accoutTypeInit(){
        List<AccountType> accountTypes = new ArrayList<>(){{
            add(new AccountType().setName("SAVINGS").setDescription("Savings Account"));
            add(new AccountType().setName("PAYROLL").setDescription("Payroll Account"));
            add(new AccountType().setName("Card").setDescription("Allow you to create different card" +
                    " types like credit card, debit card, etc"));
        }};
        if(accountTypeRepository.findAll().isEmpty()){
            accountTypeRepository.saveAll(accountTypes);
        }

    }
}

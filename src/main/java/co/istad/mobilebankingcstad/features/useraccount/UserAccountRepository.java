package co.istad.mobilebankingcstad.features.useraccount;

import co.istad.mobilebankingcstad.domain.UserAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String>{
//    @Modifying
//    @Transactional
    @Query("SELECT COUNT(ua) FROM user_account_tbl ua WHERE ua.user.id = ?1")
    int countAccountByUserId(@Param("userId") String userId);

    Optional<UserAccount> findByAccount_Id(String id);
    Optional<UserAccount> findByAccount_AccountNumber(String accountNumber);
}

package co.istad.mobilebankingcstad.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.security.Timestamp;

@Entity(name = "user_account_tbl")
@Data
@Accessors(chain = true)
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id")
    private Account account;
    private Boolean isDisable;
    private Timestamp createdAt;

}

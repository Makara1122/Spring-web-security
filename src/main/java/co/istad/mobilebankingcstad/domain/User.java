package co.istad.mobilebankingcstad.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_tbl")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;
    @Column(unique = true,nullable = false)
    private String username;
    private String fullName;
    private String gender ;
    private String pin;
    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    Set<Role> roles;
    private String profileImage;
    private String phoneNumber;
    private String cityOrProvince;
    private String khanOrDistrict;
    private String sangkatOrCommune;
    private String employeeType;
    private String companyName;
    private String mainSourceOfIncome;
    private BigDecimal monthlyIncomeRange;
    private String oneSignalId; // for notification
    private String studentIdCard;
    private LocalDateTime createdAt;
    private boolean isAccountExpired;

    private Boolean isAccountLocked;
    private Boolean isCredentialsExpired;
    private Boolean isBlocked ;
    private Boolean isDeleted ;

}

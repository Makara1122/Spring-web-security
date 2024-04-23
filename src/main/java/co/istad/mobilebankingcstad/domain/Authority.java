package co.istad.mobilebankingcstad.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@RequiredArgsConstructor

@Table(name = "authority_tbl")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
}

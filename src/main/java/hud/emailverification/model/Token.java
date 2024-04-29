package hud.emailverification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "token")
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime verifiedAt;
    private LocalDateTime expiredAt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "appUser", referencedColumnName = "id")
    private User user;

}



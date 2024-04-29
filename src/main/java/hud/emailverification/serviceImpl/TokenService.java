package hud.emailverification.serviceImpl;

import hud.emailverification.model.Token;
import hud.emailverification.repo.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;


    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    public boolean verifyToken(Token token){
        return token.getExpiredAt().isAfter(LocalDateTime.now()) && token.getVerifiedAt() == null;
    }
}

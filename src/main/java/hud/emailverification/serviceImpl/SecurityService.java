package hud.emailverification.serviceImpl;

import hud.emailverification.email.EmailService;
import hud.emailverification.exception.InvalidTokenException;
import hud.emailverification.exception.UserAlreadyExists;
import hud.emailverification.model.Token;
import hud.emailverification.model.User;
import hud.emailverification.repo.TokenRepository;
import hud.emailverification.repo.UserRepository;
import hud.emailverification.requests.AuthenticationRequest;
import hud.emailverification.requests.RegisterRequest;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final JwtService jwtService;
    private final EmailService emailService;

    private final String baseUrl = "localhost:8080/auth/verification?token=";

    public String register(RegisterRequest registerRequest) throws MessagingException {
        Optional<User> userOptional = userRepository.findByEmail(registerRequest.getEmail());
        if (userOptional.isPresent()){
            throw new UserAlreadyExists("Username Exists");
        }
        var user = User.builder()
                .fullName(registerRequest.getFullName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        var token = Token.builder()
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusHours(24L))
                .user(user)
                .token(tokenService.generateToken())
                .build();

        tokenRepository.save(token);
        String confirmationUrl = baseUrl.concat(token.getToken());
        emailService.sendEmail(registerRequest.getEmail(),"Confirmation", "hbuhero@gmail.com", token.getToken(), confirmationUrl);
        return "Registered successfully. Token: " + token.getToken();
    }




    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        var user = userRepository.findByEmail(authenticationRequest.getUsername()).get();
        String jwt = jwtService.generateToken(user);
        return new ResponseEntity<>("Jwt Token: " +jwt, HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<?> verify(String requestToken) {
        Token token = tokenRepository.findByToken(requestToken).orElseThrow(()-> new InvalidTokenException("Token Invalid or Not Found"));
        if (!tokenService.verifyToken(token)){
            return new ResponseEntity<>(new InvalidTokenException("Token is Invalid").getMessage(), HttpStatusCode.valueOf(500));
        }
        token.setVerifiedAt(LocalDateTime.now());
        var user = token.getUser();
        user.setEnabled(true);
        tokenRepository.save(token);
        String jwt = jwtService.generateToken(user);
        return new ResponseEntity<>("Jwt Token: " +jwt, HttpStatusCode.valueOf(200));
    }
}

package hud.emailverification.controller;

import hud.emailverification.requests.AuthenticationRequest;
import hud.emailverification.requests.RegisterRequest;
import hud.emailverification.serviceImpl.SecurityService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private final SecurityService securityService;

    public AuthController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) throws MessagingException {
        return securityService.register(registerRequest);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return securityService.authenticate(authenticationRequest);
    }

    @GetMapping("/verification")
    public ResponseEntity<?> verify(@RequestParam String token){
        return securityService.verify(token);
    }


}

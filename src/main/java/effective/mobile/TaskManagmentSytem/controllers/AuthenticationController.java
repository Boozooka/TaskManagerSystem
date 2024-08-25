package effective.mobile.TaskManagmentSytem.controllers;

import effective.mobile.TaskManagmentSytem.configs.jwt.JWTUtils;
import effective.mobile.TaskManagmentSytem.dto.UserDetailsImpl;
import effective.mobile.TaskManagmentSytem.dto.requests.LoginRequest;
import effective.mobile.TaskManagmentSytem.dto.requests.SignupRequest;
import effective.mobile.TaskManagmentSytem.dto.responses.JWTResponse;
import effective.mobile.TaskManagmentSytem.dto.responses.MessageResponse;
import effective.mobile.TaskManagmentSytem.models.User;
import effective.mobile.TaskManagmentSytem.repositories.UserRepository;
import effective.mobile.TaskManagmentSytem.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class AuthenticationController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTUtils jwtUtils;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authorizationUser(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(
                new JWTResponse(
                        jwtToken,
                        userDetails.getId(),
                        userDetails.getEmail()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email is already taken"));
        }

        User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User created"));
    }
}

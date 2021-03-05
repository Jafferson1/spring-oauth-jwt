package spring.oauth.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.oauth.jwt.dto.UserDTO;
import spring.oauth.jwt.model.User;
import spring.oauth.jwt.security.JwtTokenProvider;
import spring.oauth.jwt.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody UserDTO dto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(token);
	}

	@PostMapping("/signup")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO dto) {
		
		User user = dto.convertDTOToEntity();
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userService.save(user);
		
		return ResponseEntity.ok(dto);
	}

}

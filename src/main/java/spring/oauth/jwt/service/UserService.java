package spring.oauth.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.oauth.jwt.model.User;
import spring.oauth.jwt.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Transactional
	public UserDetails loadUserById(Long id) throws Exception {
		return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
	}

	public UserDetails save(User user) {
		return userRepository.save(user);
	}
	

}

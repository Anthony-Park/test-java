package org.springframework.samples.petclinic.follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findAmud() {
		return userRepository.findOneByName("Tony99").block();
	}

	public Mono<User> createUser(User user) {
		return userRepository.save(user);
	}

	public Mono<User> getUserByUserid(Integer userid) {
		return userRepository.findOneByUserid(userid)
			.switchIfEmpty(Mono.error(() -> new NotFoundException("User not found with id: " + userid)));
	}

	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	// public Mono<User> updateUser(Long userid, User updatedUser) {
	// User existingUser = userRepository.findById(userid)
	// .orElseThrow(() -> new NotFoundException("User not found with id: " + userid));
	// existingUser.setName(updatedUser.getName());
	// existingUser.setEmail(updatedUser.getEmail());
	// return userRepository.save(existingUser);
	// }
	public Mono<User> updateUser(Long userid, User updatedUser) {
		return userRepository.findById(userid)
			.switchIfEmpty(Mono.error(() -> new NotFoundException("User not found with id: " + userid)))
			.map(existingUser -> {
				existingUser.setName(updatedUser.getName());
				existingUser.setEmail(updatedUser.getEmail());
				return existingUser;
			})
			.flatMap(userRepository::save);
	}

	public void deleteUser(Long userid) {
		userRepository.deleteById(userid);
	}

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {

	public NotFoundException(String message) {
		super(message);
	}

}

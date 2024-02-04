package org.springframework.samples.petclinic.follow.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findAmud() {
	//	return userRepository.findOneByName("Tony99").block(); // reactive
		return userRepository.findByName("Tony99");
	}

//	public Mono<User> createUser(User user) {
	public User createUser(User user) {
		return userRepository.save(user);
	}

//	public Mono<User> getUserByUserid(Long userid) { // reactive
	public User getUserByUserid(Long userid) {
	//	return userRepository.findOneByUserid(userid) // reactive
	//		.switchIfEmpty(Mono.error(() -> new NotFoundException("User not found with id: " + userid)));
		return userRepository.findOneByUserId(userid);
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
/*	public Mono<User> updateUser(Long userid, User updatedUser) {
		return userRepository.findById(userid)
			.switchIfEmpty(Mono.error(() -> new NotFoundException("User not found with id: " + userid)))
			.map(existingUser -> {
				existingUser.setName(updatedUser.getName());
				existingUser.setEmail(updatedUser.getEmail());
				return existingUser;
			})
			.flatMap(userRepository::save);
	}
*/
	public Optional<User> updateUser(Long userid, User updatedUser) {
		return userRepository.findById(userid);
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

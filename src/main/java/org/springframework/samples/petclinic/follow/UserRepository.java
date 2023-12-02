package org.springframework.samples.petclinic.follow;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveNeo4jRepository<User, Long> {

	Mono<User> findOneByName(String name);

	Mono<User> findOneByUserid(Integer userid);

	Mono<User> findById(Long id);
	// List<User> findByUsersName(String name);

}

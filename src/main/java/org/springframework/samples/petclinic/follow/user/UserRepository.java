package org.springframework.samples.petclinic.follow.user;

import org.springframework.stereotype.Repository;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;
/* // non-blocking reactive
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveNeo4jRepository<User, Long> {
	Mono<User> findOneByName(String name);
	Mono<User> findOneByUserid(Long userid);

	Mono<User> findById(Long id);
}
*/

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

	User findOneByName(String name);

	User findOneByUserid(Long userid);

	Optional<User> findById(Long id);
	// List<User> findByUsersName(String name);

}

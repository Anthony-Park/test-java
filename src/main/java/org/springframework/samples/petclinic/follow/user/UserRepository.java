package org.springframework.samples.petclinic.follow.user;

import org.springframework.data.neo4j.repository.query.Query;
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

	User findByName(String name);
//	User findOneByName(String name);

	User findOneByUserId(Long id);

	Optional<User> findById(Long id);
	// List<User> findByUsersName(String name);

	@Query("MATCH (u:User {userId:$id}) - [:FOLLOW_USER] -> (f:User) RETURN COUNT(f)")
	int getFollowingCount(Long id);

	@Query("MATCH (u:User {userId:$id}) <- [:FOLLOW_USER] - (f:User) RETURN COUNT(f)")
	int getFollowerCount(Long id);

	@Query(	"MATCH (u:User {userId:$id}), (i:Item {itemId:$itemId}) " + //WHERE a.name = ""Shikar Dhawan"" AND b.name = ""India" +
			"CREATE (u)-[r:FOLLOW_ITME]->(i)")
	void followItem(Long id, Long itemId);

	@Query(	"MATCH (u:User {userId:$userId}), (f:Place {placeId:$placeId}) " +
			"CREATE (u)-[r:FOLLOW_PLACE]->(f)")
	void followPlace(Long userId, Long placeId);

	@Query(	"MATCH (u:User {userId:$userId}), (f:Meet {meetId:$meetId}) " +
			"CREATE (u)-[r:FOLLOW_MEET]->(f)")
	void followMeet(Long userId, Long meetId);
}

package org.springframework.samples.petclinic.follow.idol;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.samples.petclinic.follow.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdolRepository extends Neo4jRepository<Idol, Long> {

	Idol findByName(String name);

	Idol findByIdolId(Long id);

	Optional<Idol> findById(Long id);
	// List<User> findByUsersName(String name);

	@Query("MATCH (u:Idol {idolId:$id}) - [:FOLLOW_USER] -> (f:User) RETURN COUNT(f)")
	int getFollowingCount(Long id);

	@Query("MATCH (u:Idol {idolId:$id}) <- [:FOLLOW_IDOL] - (f:User) RETURN COUNT(f)")
	int getFollowerCount(Long id);
}

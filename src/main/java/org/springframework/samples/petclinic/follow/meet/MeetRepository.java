package org.springframework.samples.petclinic.follow.meet;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.samples.petclinic.follow.item.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetRepository extends Neo4jRepository<Meet, Long> {
	Meet findByMeetId(Long meetId);
	void deleteByMeetId(Long meetId);

	@Query("MATCH (i:Meet {meetId:$id}) <- [:FOLLOW_MEET] - (f:User) RETURN COUNT(f)")
	int getFollowerCount(Long id);
}

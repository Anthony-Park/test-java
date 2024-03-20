package org.springframework.samples.petclinic.follow.place;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
//public interface PlaceRepository extends ReactiveNeo4jRepository<Place, Long> {
public interface PlaceRepository extends Neo4jRepository<Place, Long> {
//	Mono<Place> findByPlaceid(Long placeId);
	Place findByPlaceId(Long placeId);
	Place findOneByPlaceId(Long placeId);

	void deleteByPlaceId(Long placeId);
//	@Query("MATCH (place:Place) RETURN place")
//
//	public Mono<Place> findOneByPlaceId(Long placeId);

//	Mono<Place> findOneByName(String name);
//	public Mono<Place> findById(Long id);
//	public Flux<Place> findAll(Sort sort);
//	List<User> findByUsersName(String name);
	@Query("MATCH (p:Place {placeId:$id}) <- [:FOLLOW_PLACE] - (f:User) RETURN COUNT(f)")
	int getFollowerCount(Long id);
}

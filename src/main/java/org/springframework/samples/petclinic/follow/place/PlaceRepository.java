package org.springframework.samples.petclinic.follow.place;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

//public interface PlaceRepository extends Neo4jRepository<Place, Long> {
public interface PlaceRepository extends ReactiveNeo4jRepository<Place, Long> {
	Mono<Place> findByPlaceid(Long placeId);

	void deleteByPlaceid(Long placeId);
//	@Query("MATCH (place:Place) RETURN place")
//
//	public Mono<Place> findOneByPlaceId(Long placeId);

//	Mono<Place> findOneByName(String name);
//	public Mono<Place> findById(Long id);
//	public Flux<Place> findAll(Sort sort);
//	List<User> findByUsersName(String name);
}

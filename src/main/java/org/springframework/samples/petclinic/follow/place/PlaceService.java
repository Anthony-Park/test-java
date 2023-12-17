package org.springframework.samples.petclinic.follow.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

	@Autowired
	private PlaceRepository placeRepository;

//	public Place findAmud() {
//		return placeRepository.findOneByName("Tony99").block();
//	}

	public Mono<Place> createPlace(Place place) {
		return placeRepository.save(place);
	}

	public Mono<Place> getPlaceByPlaceId(Long placeId) {
		return placeRepository.findByPlaceid(placeId)
			.switchIfEmpty(Mono.error(() -> new org.springframework.samples.petclinic.follow.place.NotFoundException("User not found with id: " + placeId)));
	}

	public List<Place> getAllPlaces() {
		return (List<Place>) placeRepository.findAll();
	}

	public Mono<Place> updatePlace(Long id, Place updatedPlace) {
		return placeRepository.findById(id);
//			.switchIfEmpty(Mono.error(() -> new NotFoundException("Place not found with id: " + id)))
//			.map(existingPlace -> {
//				existingPlace.setPlaceid(updatedPlace.getPlaceid());
//				existingPlace.setName(updatedPlace.getName());
//				return existingPlace;
//			})
//			.flatMap(placeRepository::save);
	}

	public void deletePlace(Long placeId) {
		placeRepository.deleteByPlaceid(placeId);
	}

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}

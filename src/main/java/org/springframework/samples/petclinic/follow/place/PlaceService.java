package org.springframework.samples.petclinic.follow.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/*
@Service
public class PlaceService {

	@Autowired
	private PlaceRepository placeRepository;

//	public Place findAmud() {
//		return placeRepository.findOneByName("Tony99").block();
//	}

//	public Mono<Place> createPlace(Place place) {
	public Place createPlace(Place place) {
		return placeRepository.save(place);
	}

//	public Mono<Place> getPlaceByPlaceId(Long placeId) {
	public Place getPlaceByPlaceId(Long placeId) {
//		return placeRepository.findByPlaceid(placeId)
//			.switchIfEmpty(Mono.error(() -> new org.springframework.samples.petclinic.follow.place.NotFoundException("User not found with id: " + placeId)));
		return placeRepository.findByPlaceId(placeId);
	}

	public List<Place> getAllPlaces() {
		return placeRepository.findAll();
	}

//	public Mono<Place> updatePlace(Long id, Place updatedPlace) {
	public Place updatePlace(Long id, Place updatedPlace) {
		Place place = placeRepository.findByPlaceId(id);
		if (place != null) {
			place.setPlaceId(updatedPlace.getPlaceId());
			place.setName(updatedPlace.getName());
			place.setAddress(updatedPlace.getAddress());
		//	place.setStartDate(updatedPlace.getStartDate());
		//	place.setEndDate(updatedPlace.getEndDate());
		}

		return place;
//			.switchIfEmpty(Mono.error(() -> new NotFoundException("Place not found with id: " + id)))
//			.map(existingPlace -> {
//				existingPlace.setPlaceid(updatedPlace.getPlaceid());
//				existingPlace.setName(updatedPlace.getName());
//				return existingPlace;
//			})
//			.flatMap(placeRepository::save);
	}

	public void deletePlace(Long placeId) {
		placeRepository.deleteByPlaceId(placeId);
	}

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}
*/

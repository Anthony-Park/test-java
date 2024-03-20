package org.springframework.samples.petclinic.follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.follow.item.Item;
import org.springframework.samples.petclinic.follow.item.ItemRepository;
import org.springframework.samples.petclinic.follow.idol.Idol;
import org.springframework.samples.petclinic.follow.idol.IdolRepository;
import org.springframework.samples.petclinic.follow.meet.Meet;
import org.springframework.samples.petclinic.follow.meet.MeetRepository;
import org.springframework.samples.petclinic.follow.place.Place;
import org.springframework.samples.petclinic.follow.place.PlaceRepository;
import org.springframework.samples.petclinic.follow.user.User;
import org.springframework.samples.petclinic.follow.user.UserRepository; // follow object
import org.springframework.stereotype.Service;

@Service
public class FollowService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IdolRepository idolRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private MeetRepository meetRepository;
	@Autowired
	private PlaceRepository placeRepository;

	public void deleteAll() { // delete all nodes
		userRepository.deleteAll();
		idolRepository.deleteAll();
		itemRepository.deleteAll();
		meetRepository.deleteAll();
		placeRepository.deleteAll();
	}

	// add user
	public boolean addUser(Long userId, String name, String email) {
		User check = userRepository.findOneByUserId(userId);
		if (check != null) return false; // exist

		User user = new User(userId, name, email);
		User ret = userRepository.save(user);
		if (ret == null) return false;

		return true;
	}

	// following user
	public boolean followUser(Long followerId, Long targetId) {
		User follower = userRepository.findOneByUserId(followerId);
		User target   = userRepository.findOneByUserId(targetId);
		if (follower == null) return false;
		if (target   == null) return false;

		follower.followWith(target);
		userRepository.save(follower);
		return true;
	}

	// get user's follower count
	public int getUserFollowerCount(Long userId) {
		User user = userRepository.findOneByUserId(userId);
		if (user == null) return 0;

		return userRepository.getFollowerCount(userId);
	}

	// get user's following count
	public int getUserFollowingCount(Long userId) {
		User user = userRepository.findOneByUserId(userId);
		if (user == null) return 0;

		return userRepository.getFollowingCount(userId);
	}

	// add idol
	public boolean addIdol(Long idolId, String name, Boolean unit, Boolean gender) {
		Idol check = idolRepository.findOneByIdolId(idolId);
		if (check != null) return false; // exist

		Idol idol = new Idol(idolId, name, unit, gender);
		Idol ret = idolRepository.save(idol);
		if (ret == null) return false;

		return true;
	}

	public boolean addArtist(Long idolId, String name, Boolean unit, Boolean gender) {
		return addIdol(idolId, name, unit, gender);
	}

	// following idol
	public boolean followIdol(Long userId, Long idolId) {
		User user = userRepository.findOneByUserId(userId);
		Idol idol = idolRepository.findByIdolId(idolId);
		if (user == null || idol == null) return false;

		user.followWith(idol);
		userRepository.save(user);
		return true;
	}

	// get idol's follower count
	public int getIdolFollowerCount(Long idolId) {
		Idol idol = idolRepository.findByIdolId(idolId);
		if (idol == null) return 0;

		return idolRepository.getFollowerCount(idolId);
	}

	// add place
	public boolean addPlace(Long placeId, String name, String address, Long idolId) {
		Place check = placeRepository.findOneByPlaceId(placeId);
		if (check != null) return false; // exist

		Place place = new Place(placeId, name, address, idolId);
		Place ret = placeRepository.save(place);
		if (ret == null) return false;

		return true;
	}

	// following place
	public boolean followPlace(Long userId, Long placeId) {
		User user = userRepository.findOneByUserId(userId);
		Place place = placeRepository.findOneByPlaceId(placeId);
		if (user == null || place == null) return false;

		//user.followWith(place);
		//userRepository.save(user);
		userRepository.followPlace(userId, placeId);
		return true;
	}

	// get place's follower count
	public int getPlaceFollowerCount(Long placeId) {
		Place place = placeRepository.findByPlaceId(placeId);
		if (place == null) return 0;

		return placeRepository.getFollowerCount(placeId);
	}

	// add item; goods
	public boolean addItem(Long itemId, String name, Integer category, Long idolId, Long userId) {
		Item check = itemRepository.findByItemId(itemId);
		if (check != null) return false; // exist

		Item item = new Item(itemId, name, category, idolId, userId);
		Item ret = itemRepository.save(item);
		if (ret == null) return false;

		return true;
	}

	public boolean addGoods(Long itemId, String name, Integer category, Long idolId, Long userId) {
		return addItem(itemId, name, category, idolId, userId);
	}

	// following item
	public boolean followItem(Long userId, Long itemId) {
		User user = userRepository.findOneByUserId(userId);
		Item item = itemRepository.findByItemId(itemId);
		if (user == null || item == null) return false;

		//user.followWith(item);
		//userRepository.save(user);
		userRepository.followItem(userId, itemId);
		return true;
	}

	// get item's follower count
	public int getItemFollowerCount(Long itemId) {
		Item item = itemRepository.findByItemId(itemId);
		if (item == null) return 0;

		return itemRepository.getFollowerCount(itemId);
	}

	// add meet; social-ing
	public boolean addMeet(Long meetId, String name, Long idolId, Long userId) {
		Meet check = meetRepository.findByMeetId(meetId);
		if (check != null) return false; // exist

		Meet meet = new Meet(meetId, name, idolId, userId);
		Meet ret = meetRepository.save(meet);
		if (ret == null) return false;

		return true;
	}

	public boolean addSocailing(Long meetId, String name, Long idolId, Long userId) {
		return addMeet(meetId, name, idolId, userId);
	}

	// following meet
	public boolean followMeet(Long userId, Long meetId) {
		User user = userRepository.findOneByUserId(userId);
		Meet meet = meetRepository.findByMeetId(meetId);
		if (user == null || meet == null) return false;

//		user.followWith(meet);
//		userRepository.save(user);
		userRepository.followMeet(userId, meetId);
		return true;
	}

	// get meet's follower count
	public int getMeetFollowerCount(Long meetId) {
		Meet meet = meetRepository.findByMeetId(meetId);
		if (meet == null) return 0;

		return meetRepository.getFollowerCount(meetId);
	}
}

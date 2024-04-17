package org.springframework.samples.petclinic.kpop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

	@Autowired
//	@Qualifier("PlaceRepository1")
    private final PlaceRepository placeRepository;

    //선택한 이벤트/장소 상세 조회
    @Transactional
    public Boolean selectPlaceOne(Long id) {
        Place place = placeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("장소 ID가 존재하지 않습니다."));

        //만약 해당 장소가 삭제되었다면("Y"로 표시되어 있다면) 예외를 던짐
        if(place.getEventId().equals(id) && place.getDelYN().equals("Y")) {
            throw new IllegalArgumentException("삭제된 장소입니다.");
        }

        //조회수 +1
        int currentCount = place.getCount();
        place.setCount(currentCount + 1);

        return true;
    }

}


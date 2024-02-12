package org.springframework.samples.petclinic.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.artist.Artist;
import org.springframework.samples.petclinic.artist.ArtistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calRepository;
    private final ArtistRepository artistRepository;

    @Transactional
    public Optional<Calendar> create(CalendarDto dto) { // Create
		System.out.println("artRepo calling");
    	Optional<Artist> optArtist = artistRepository.findById(dto.getArtistId());
    	if (optArtist.isEmpty()) return null; // failed
		System.out.println("artRepo called");

		Calendar calendar = Calendar.builder()
				.id(dto.getId()) //ignore
				.name(dto.getName())
				.artist(optArtist.get())
				.start(dto.getStart()).end(dto.getEnd())
				.link(dto.getLink()).meta(dto.getMeta()).build();
		System.out.println("calendar built");

		System.out.println("calRepo calling");
//		calendar.getId()
        return Optional.of(calRepository.save(calendar));
    }

    public Optional<Calendar> read(Long calId) // Read
	{
//		Optional<Calendar> cal = calRepository.findById(calId);
		Long c = calRepository.count();
		System.out.println("Repo Count " + c);


		return calRepository.findById(calId);
    }

    public Boolean update(Long calId, CalendarDto dto) { // Update
        Optional<Calendar> optCal = calRepository.findById(calId);
        if (optCal.isEmpty()) return false;
        System.out.printf("findById %d\n", calId);
        Calendar cal = optCal.get();

        if (dto.getArtistId() != cal.getArtist().getArtistId()
			) return false; // calId != cal.getId() checked by findById()

        Calendar calendar = Calendar.builder()
                .id(calId) 				 // use calId not dto's id
				.artist(cal.getArtist()) // reuse original artist
                .name(dto.getName())	 // next items can be modified
                .start(dto.getStart())
                .end(dto.getEnd())
                .link(dto.getLink())
                .meta(dto.getMeta())
                .build();
		System.out.printf("Calendar builder %d\n", dto.getId());
        calRepository.save(calendar);
		System.out.printf("Calendar Repo saved\n");
        return true;
    }

    public Boolean delete(Long calId) { // Delete
        Optional<Calendar> cal = calRepository.findById(calId);
        if (cal.isEmpty()) return false;

        calRepository.deleteById(calId);
        return true;
    }
}

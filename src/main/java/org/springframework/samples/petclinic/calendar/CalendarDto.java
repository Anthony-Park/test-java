package org.springframework.samples.petclinic.calendar;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class CalendarDto {

    private Long id; // id (pk)
//  private Artist artist; // artist id (fk)
	private Long artistId; // artist id (fk)

    private String name; // name

    private Date start; // start date, time
    private Date end; // end date, time

    private String link; // external link
    private String meta; // meta data

	CalendarDto(Calendar cal) {
		id = cal.getId();
		artistId = cal.getArtist().getArtistId();
		name = cal.getName();
		start = cal.getStart();
		end = cal.getEnd();
		link = cal.getLink();
		meta = cal.getMeta();
	}
}

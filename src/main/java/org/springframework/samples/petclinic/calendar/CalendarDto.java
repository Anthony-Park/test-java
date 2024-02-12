package org.springframework.samples.petclinic.calendar;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Schema(name = "Calendar Record", description = "Calendar Common Data Transfer Object(DTO)")
public class CalendarDto {

	@Schema(name = "calendar Id", example = "100", description = "unique primary key number for Calendar table")
    private Long id;
	@Schema(name = "artist Id", example = "100", description = "unique foreign key number by Artist table")
//  private Artist artist; // artist id (fk)
	private Long artistId;

	@Schema(name = "Schedule Name", example = "Fan Meeting", description = "Record and scheudle name")
    private String name;

	@Schema(name = "Start Date", example = "2021/12/24 23:59:59", description = "Start of schedule including date & time")
    private Date start;
	@Schema(name = "End Date", example = "2021/12/25 00:00:01", description = "Start of schedule including date & time")
    private Date end;

	@Schema(name = "External Link", example = "http://christmas.org", description = "link string for schedule")
    private String link;
	@Schema(name = "meta data", example = "nothing", description = "reserved for expansion")
    private String meta;

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

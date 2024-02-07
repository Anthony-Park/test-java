package org.springframework.samples.petclinic.calendar;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
public class CalendarResponseDto {

	public Boolean success;
	public Integer statusCode;
	public String message;

	private Integer planCount;
	private List<CalendarDto> plans;
//	private Long id; // id (pk)
//	private Long artistId; // artist id (fk)
//	private String name; // name
//	private Date start; // start date, time
//	private Date end; // end date, time
//	private String link; // external link
//	private String meta; // meta data
}

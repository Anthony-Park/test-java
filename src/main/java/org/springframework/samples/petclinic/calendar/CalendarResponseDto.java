package org.springframework.samples.petclinic.calendar;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "Common Response Object")
public class CalendarResponseDto {

	@Schema(description = "Success(1), Fail(0)")
	public Boolean success;

//	@JsonValue
	@Schema(description = "Http Status Code")
	public HttpStatus code;
	public Integer statusCode;

	@Schema(description = "Response Message")
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

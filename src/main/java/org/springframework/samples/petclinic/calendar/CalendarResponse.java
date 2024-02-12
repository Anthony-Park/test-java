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
@Schema(name = "Calendar Common Response", description = "Calendar Common ResponseBody")
public class CalendarResponse {

	@Schema(name = "Success or not", example = "1, 0", description = "Success(1), Fail(0)")
	public Boolean success;
//	@JsonValue
	@Schema(name = "Http status", example = "200", description = "Http Status Code in number")
	public Integer statusCode;
	@Schema(name = "Response Message", example = "Query Operation Success", description = "Text message for each response result")
	public String message;

	@Schema(name = "Response count", example = "0, 1 or many", description = "Number of records for response body's Calendar DTO")
	private Integer planCount;
//	@Schema(name = "Array of Calendar DTOs", example = "{100, 1, 'BTS', '2021/12/24 23:59:59', '2021/12/25 00:00:01', 'http:christmas', 'nothing'", description = "array of Calendar DTO")
	@Schema(name = "Array of Calendar DTOs", description = "array of Calendar DTO")
	private List<CalendarDto> plans;
//		Long id;		// id (pk)
//		Long artistId;	// artist id (fk)
//		String name;	// name
//		Date start;		// start date, time
// 		Date end;		// end date, time
//		String link;	// external link
//		String meta;	// meta data
}

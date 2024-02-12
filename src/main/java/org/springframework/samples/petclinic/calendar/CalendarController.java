package org.springframework.samples.petclinic.calendar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.artist.Artist;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@RequestMapping("/v1/calendar")
@RestController
@RequiredArgsConstructor
//@Api(tags = "Some API")
@Tag(name = "Calendar", description = "Artists' schedule CRUD service")
public class CalendarController {

    private final CalendarService calService;

    @PostMapping("/artists/{artistId}") // Create
	@Operation(summary = "Create a schedule", description = "Register a schedule of an artist")
	@ApiResponse(responseCode = "201", description = "CREATED",					content = @Content(schema = @Schema(implementation = CalendarResponse.class)))
	@ApiResponse(responseCode = "404", description = "NOT FOUND",				content = @Content(schema = @Schema(implementation = CalendarResponse.class)))
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",	content = @Content(schema = @Schema(implementation = CalendarResponse.class)))
    public ResponseEntity<CalendarResponse>  create(
		@Parameter(description = "Artist ID of creating schedule's owner")
		@PathVariable("artistId") Long artistId,

		@Parameter(name = "calendarDto", description = "schedule Data Transfer Object (DTO), id will be ignored")
		@RequestBody CalendarDto calendarDto) {

		calendarDto.setArtistId(artistId);
		Optional<Calendar> optCal = calService.create(calendarDto);
		System.out.println("Update Failed");

		CalendarResponse response;
		HttpStatus httpStatus;

		if (optCal.isPresent()) {

			httpStatus = HttpStatus.OK;
			response = CalendarResponse.builder()
				.success(true)
				.statusCode(httpStatus.value())
				.message("조회 성공")
				.plans(Arrays.asList(new CalendarDto(optCal.get())))
				.planCount(1).build();
		} else {

			System.out.println("Create Failed");

			httpStatus = HttpStatus.NOT_FOUND;
			response = CalendarResponse.builder()
				.success(false)
				.statusCode(httpStatus.value())
				.message("요청한 항목을 찾을 수 없습니다.")
				.plans(Collections.emptyList())
				.planCount(0).build();
		}

		return new ResponseEntity<>(response, httpStatus);
    }

	@GetMapping("/artists/{artistId}/week") // Read
	@Operation(summary = "Read this week's schedule", description = "Read this week's schedules of an artist")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
    public ResponseEntity<CalendarResponse> readWeek(
		@Parameter(description = "Artist ID of reading schedules' owner")
		@PathVariable("artistId") Long idolId) {

		CalendarResponse response = new CalendarResponse(); // TODO: fill in response
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/artists/{artistId}/month") // Read
	@Operation(summary = "Read this month's schedule", description = "Read this month's schedules of an artist")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	public ResponseEntity<CalendarResponse> readMonth(
		@Parameter(description = "Artist ID of reading schedules' owner")
		@PathVariable("artistId") Long idolId) {

		CalendarResponse response = new CalendarResponse(); // TODO: fill in response
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/artists/{artistId}/weekfrom") // Read
	@Operation(summary = "Read a week's schedule from date", description = "Read a week's schedules of an artist from date")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	public ResponseEntity<CalendarResponse> readWeekFrom(
		@Parameter(description = "Artist ID of reading schedules' owner")
		@PathVariable("artistId") Long idolId,

		@Parameter(name = "startDate", description = "Start date of a week schedules")
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate) {

		CalendarResponse response = new CalendarResponse(); // TODO: fill in response
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/artists/{artistId}/monthfrom") // Read
	@Operation(summary = "Read a month's schedule from date", description = "Read a month's schedules of an artist from date")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")

	public ResponseEntity<CalendarResponse> readMonthFrom(
		@Parameter(description = "Artist ID of reading schedules' owner")
		@PathVariable("artistId") Long idolId,

		@Parameter(name = "startDate", description = "Start date of a month schedules")
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate) {

		CalendarResponse response = new CalendarResponse();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/plans/{planId}") // Read
	@Operation(summary = "Read", description = "Read a schedule by its id")
	@ApiResponse(responseCode = "200", description = "OK",
		content = @Content(schema = @Schema(implementation = CalendarResponse.class)))
	@ApiResponse(responseCode = "404", description = "NOT FOUND",
		content = @Content(schema = @Schema(implementation = CalendarResponse.class)))
	public ResponseEntity<CalendarResponse> readPlan(
		@Parameter(description = "Plan(Schedule) ID of schedule")
		@PathVariable("planId") Long planId) {

		CalendarResponse response;
		HttpStatus httpStatus;

		Optional<Calendar> optPlan = calService.read(planId);

		if (optPlan.isPresent()) {
			CalendarDto calDto = new CalendarDto(optPlan.get());

			httpStatus = HttpStatus.OK;
			response = CalendarResponse.builder()
				.success(true)
				.statusCode(httpStatus.value())
				.message("사용자 조회 성공")
				.plans(Arrays.asList(calDto))
				.planCount(1).build();
		} else {

			httpStatus = HttpStatus.NOT_FOUND;
			response = CalendarResponse.builder()
				.success(false)
				.statusCode(httpStatus.value())
				.message("사용자를 찾을 수 없습니다.")
				.plans(Collections.emptyList())
				.planCount(0).build();
		}

		return new ResponseEntity<>(response, httpStatus);
	}

	@PatchMapping(value = "/plans/{planId}") // Update
	@Operation(summary = "Update", description = "Modify a schedule by its id")
	@ApiResponse(responseCode = "200", description = "OK",						content = @Content(schema = @Schema(implementation = CalendarResponse.class)))
	@ApiResponse(responseCode = "400", description = "BAD REQUEST",				content = @Content(schema = @Schema(implementation = CalendarResponse.class)))
	@ApiResponse(responseCode = "404", description = "NOT FOUND",				content = @Content(schema = @Schema(implementation = CalendarResponse.class)))
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",	content = @Content(schema = @Schema(implementation = CalendarResponse.class)))
	public ResponseEntity<CalendarResponse>  update(
		@Parameter(description = "Plan(Schedule) ID of schedule")
		@PathVariable("planId") Long planId,

		@Parameter(name = "calendarDto", description = "schedule Data Transfer Object (DTO), id will be ignored")
		@RequestBody CalendarDto calendarDto) {

		CalendarResponse response;
		HttpStatus httpStatus;
		if (calService.update(planId, calendarDto)) {
		//	Optional<Calendar> optCal = calService.read(planId);
		//	Calendar cal = optCal.get(); cal.setId(planId) ...
			calendarDto.setId(planId); // re-query for changes is burden

			httpStatus = HttpStatus.OK;
			response = CalendarResponse.builder()
				.success(true)
				.statusCode(httpStatus.value())
				.message("조회 성공")
				.plans(Arrays.asList(calendarDto))
				.planCount(1).build();
		} else {
			System.out.println("Update Failed");
			Optional<Calendar> optPlan = calService.read(planId);

			if (optPlan.isEmpty()) {
				System.out.println("can't find planId" + planId);

				httpStatus = HttpStatus.NOT_FOUND;
				response = CalendarResponse.builder()
					.success(false)
					.statusCode(httpStatus.value())
					.message("요청한 항목을 찾을 수 없습니다.")
					.plans(Collections.emptyList())
					.planCount(0).build();
			}
			else {
				System.out.println("found planId" + planId);

				httpStatus = HttpStatus.BAD_REQUEST;
				response = CalendarResponse.builder()
					.success(false)
					.statusCode(httpStatus.value())
					.message("잘못된 요청입니다.")
					.plans(Collections.emptyList())
					.planCount(0).build();
			}
		}

		return new ResponseEntity<>(response, httpStatus);
	}

	@DeleteMapping("/plans/{planId}") // Delete
	@Operation(summary = "Delete a schedule", description = "Delete a schedule")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	public ResponseEntity<CalendarResponse> delete(
		@Parameter(description = "Plan(Schedule) ID of schedule")
		@PathVariable("planId") Long planId) {

		CalendarResponse response = new CalendarResponse();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

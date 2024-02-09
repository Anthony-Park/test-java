package org.springframework.samples.petclinic.calendar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.calendar.CalendarDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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
	@ApiResponse(responseCode = "201", description = "CREATED")
//	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
    public ResponseEntity<CalendarResponseDto>  create(
		@Parameter(description = "Artist ID of creating schedule's owner")
		@PathVariable("artistId") Long artistId,

    	@Parameter(description = "schedule Data Transfer Object (DTO), id will be ignored")
		@RequestParam(required = true) @RequestBody CalendarDto calendarDto) {

//    	calService.create(calendarDto); // TODO: modify service & repository
//      return "calendar is registered";
		CalendarResponseDto response = new CalendarResponseDto(); // TODO: fill in response
		if (false) // TODO: check input
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

	@GetMapping("/artists/{artistId}/week") // Read
	@Operation(summary = "Read this week's schedule", description = "Read this week's schedules of an artist")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
    public ResponseEntity<CalendarResponseDto> readWeek(
		@Parameter(description = "Artist ID of reading schedules' owner")
		@PathVariable("artistId") Long idolId) {
        //return calService.read(calId);
//    	calService.create(calendarDto); // TODO: modify service & repository
//      return "calendar is registered";
		CalendarResponseDto response = new CalendarResponseDto(); // TODO: fill in response
		if (false) // TODO: check input
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		if (response.getPlanCount() == 0)
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/artists/{artistId}/month") // Read
	@Operation(summary = "Read this month's schedule", description = "Read this month's schedules of an artist")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	public ResponseEntity<CalendarResponseDto> readMonth(
		@Parameter(description = "Artist ID of reading schedules' owner")
		@RequestParam(required = true) @PathVariable("artistId") Long idolId) {
		//return calService.read(calId);
//    	calService.create(calendarDto); // TODO: modify service & repository
//      return "calendar is registered";
		CalendarResponseDto response = new CalendarResponseDto(); // TODO: fill in response
		if (false) // TODO: check input
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		if (response.getPlanCount() == 0)
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
//	@GetMapping("/artists/{calId}") // Read
//	@Operation(summary = "Some API", description = "설명")
//	@ApiResponses(value = {
//		@ApiResponse(responseCode = "success", description = "성공"),
//		@ApiResponse(responseCode = "fail")})
//	public Optional<Calendar> read(@PathVariable("calId") Long calId) {
//		return calService.read(calId);
//	}

	@GetMapping("/artists/{artistId}/weekfrom") // Read
	@Operation(summary = "Read a week's schedule from date", description = "Read a week's schedules of an artist from date")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	public ResponseEntity<CalendarResponseDto> readWeekFrom(
		@Parameter(description = "Artist ID of reading schedules' owner")
		@PathVariable("artistId") Long idolId,

		@Parameter(description = "Start date of a week schedules")
		@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate) {
		//return calService.read(calId);
//    	calService.create(calendarDto); // TODO: modify service & repository
//      return "calendar is registered";
		CalendarResponseDto response = new CalendarResponseDto(); // TODO: fill in response
		if (false) // TODO: check input
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		if (response.getPlanCount() == 0)
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/artists/{artistId}/monthfrom") // Read
	@Operation(summary = "Read a month's schedule from date", description = "Read a month's schedules of an artist from date")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")

	public ResponseEntity<CalendarResponseDto> readMonthFrom(
		@Parameter(description = "Artist ID of reading schedules' owner")
		@PathVariable("artistId") Long idolId,

		@Parameter(description = "Start date of a month schedules")
		@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate) {
		//return calService.read(calId);
//    	calService.create(calendarDto); // TODO: modify service & repository
//      return "calendar is registered";
		CalendarResponseDto response = new CalendarResponseDto(); // TODO: fill in response
		if (false) // TODO: check input
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		if (response.getPlanCount() == 0)
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

//	@ApiResponses(value = {
//		@ApiResponse(responseCode = "200", description = "게시글 조회 성공", content = @Content(schema = @Schema(implementation = CalendarResponseDto.class))),
//		@ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CalendarResponseDto.class))) })
//	@Operation(summary = "게시글 조회", description = "id를 이용하여 posts 레코드를 조회합니다.")

	@Operation(summary = "게시글 조회", description = "id를 이용하여 posts 레코드를 조회합니다.", responses = {
		@ApiResponse(responseCode = "200", description = "게시글 조회 성공", content = @Content(schema = @Schema(implementation = CalendarResponseDto.class))),
		@ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CalendarDto.class)))
	})
	@GetMapping("/plans/{planId}") // Read
//	@Operation(summary = "Read a schedule", description = "Read a detail schedule")
//	@ApiResponse(responseCode = "200", description = "OK")
//	@ApiResponse(responseCode = "404", description = "NOT FOUND")
//	@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CalendarResponseDto.class)))
//	@ApiResponses(value = {
//		@ApiResponse(responseCode = "200", description = "Okay, requested schedule found", useReturnTypeSchema = true),
//		@ApiResponse(responseCode = "404", description = "schedule Not Found, invalid plan id",
//			content = @Content(schema = @Schema(implementation = CalendarResponseDto.class)))
//	})
	public ResponseEntity<CalendarResponseDto> readPlan(
		@Parameter(description = "Plan(Schedule) ID of schedule")
		@PathVariable("planId") Long planId) {

    	System.out.println("PlanId " + planId);
		CalendarResponseDto response = new CalendarResponseDto();
		System.out.println("DTO created");

		Optional<Calendar> optPlan = calService.read(planId);
//    	calService.create(calendarDto); // TODO: modify service & repository
//      return "calendar is registered";

		if (optPlan.isPresent()) {
			System.out.println("Plan exist");
			CalendarDto calDto = new CalendarDto(optPlan.get());
			response = CalendarResponseDto.builder()
				.statusCode(HttpStatus.OK.value())
				.code(HttpStatus.OK) // TODO: choice one
				.message("사용자 조회 성공")
				.plans(Arrays.asList(calDto))
				.planCount(1).build();

		} else {
			System.out.println("Plan isn't exist");
			response = CalendarResponseDto.builder()
//				.code(HttpStatus.NOT_FOUND.value())
				.code(HttpStatus.NOT_FOUND)
				.message("사용자를 찾을 수 없습니다.")
				.plans(Collections.emptyList())
				.planCount(0).build();

		}

		return new ResponseEntity<>(response, response.getCode());
	}

//    @GetMapping("/date") // temp.
//    public ResponseEntity<String> date(@RequestParam("date") Date date) {
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
//
//        return ResponseEntity.ok(dateFormat.format(date));
//    }

//    public Boolean update(@PathVariable("planId") Long calId, @RequestBody CalendarDto calendarDto) {
//        return calService.update(calId, calendarDto);
//    }

	@PatchMapping(value = "/plans/{planId}") // Update
	@Operation(summary = "Update a schedule", description = "Update or modify a schedule")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	public ResponseEntity<CalendarResponseDto>  update(
		@Parameter(description = "Plan(Schedule) ID of schedule")
		@RequestParam(required = true) @PathVariable("planId") Long planId,

		@Parameter(description = "schedule Data Transfer Object (DTO), id will be ignored")
		@RequestParam(required = true) @RequestBody CalendarDto calendarDto) {

//    	calService.create(calendarDto); // TODO: modify service & repository
//      return "calendar is registered";
		CalendarResponseDto response = new CalendarResponseDto(); // TODO: fill in response
		if (false) // TODO: check input
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		if (false) // TODO: check input
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/plans/{planId}") // Delete
	@Operation(summary = "Delete a schedule", description = "Delete a schedule")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	public ResponseEntity<CalendarResponseDto> delete(
		@Parameter(description = "Plan(Schedule) ID of schedule")
		@RequestParam(required = true) @PathVariable("planId") Long planId) {
		//return calService.read(calId);
//    	calService.create(calendarDto); // TODO: modify service & repository
//      return "calendar is registered";
		CalendarResponseDto response = new CalendarResponseDto(); // TODO: fill in response
		if (false) // TODO: check input
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

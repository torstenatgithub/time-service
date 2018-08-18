package com.ergo.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {

	@RequestMapping("/time")
	public Time time() {
		String timestamp = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
		Time time = new Time(timestamp);
		return time;
	}
}

package com.meeting.reservation.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meeting.reservation.model.dto.MeetingRoomDto;
import com.meeting.reservation.service.MeetingReservationService;


@CrossOrigin
@RestController
@RequestMapping(value= "/meeting")
public class MeetingReservationController {

	@Autowired
    private MeetingReservationService service;
	
	@GetMapping(value = "/find/{start}-{end}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MeetingRoomDto>> findAllAvailable(@PathVariable LocalDate start, @PathVariable LocalDate end) {
       return ResponseEntity.ok(service.findAllAvailable(start, end));
    }
	
	@PostMapping(value = "/create", consumes = MediaType. APPLICATION_JSON_VALUE, produces = MediaType. APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> create(@Valid @RequestBody MeetingRoomDto dto){
		try {
			this.service.createRoom(dto);
			return ResponseEntity.ok(dto.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
	
	@PostMapping(value = "/create-with-media", consumes = MediaType. APPLICATION_JSON_VALUE, produces = MediaType. APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createWithMedia(@Valid @RequestBody MeetingRoomDto dto) {
		try {
			this.service.createRoom(dto);
			return ResponseEntity.ok(dto.getId());			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
	
	@GetMapping(value = "/close/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> closeRoom(long id) {
		try {			
			service.cleanUpRoom(id);
			return ResponseEntity.ok(HttpEntity.EMPTY);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping(value = "/media/{pathMedia}", produces = MediaType. APPLICATION_JSON_VALUE)
	public ResponseEntity<UrlResource> getFullVideo(@PathVariable String pathMedia) {
		try {
			UrlResource media = new UrlResource(pathMedia);
			return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
					 .contentType(MediaTypeFactory
							 .getMediaType(media)
							 .orElse(MediaType.APPLICATION_OCTET_STREAM))
				 	 .body(media);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
}

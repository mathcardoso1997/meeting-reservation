package com.meeting.reservation.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.meeting.reservation.model.dto.MeetingRoomDto;
import com.meeting.reservation.model.entity.MeetingRoom;
import com.meeting.reservation.model.mapper.MeetingRoomMapper;
import com.meeting.reservation.repository.MeetingReservationRepository;

@Service
public class MeetingReservationService {
	
	@Autowired
    private MeetingReservationRepository repository;
	
	@Autowired
	private MeetingRoomMapper mapper;
	
	private MeetingRoom room;
	
	@Transactional(readOnly = true)
    public List<MeetingRoomDto> findAllAvailable(LocalDate start, LocalDate end) {
		if (start == null) {
			start = LocalDate.now();
		}
		if (end == null) {
			end = LocalDate.now().plusDays(1);
		}
		return mapper.toDto(repository.findAllAvaibleByDate(start, end).get());		
    }
	
	@Transactional
	public void createRoom(MeetingRoomDto dto) throws Exception {
		if (isValidRequest(dto)) {
			throw new Exception("MeetingRoomDto is incorrect", new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		}
		setRoom(mapper.ToEntity(dto));
		
		repository.save(getRoom());
		startTimer(getRoom().getTime());
	}
	
	private boolean isValidRequest(MeetingRoomDto dto) {
		return dto == null || (0 >= dto.getAllocation() && 0 >= dto.getMaxAllocation() && dto.getMaxAllocation() <= dto.getAllocation());
	}
	
	private void startTimer(long minutes) {
		long start, stop;
        start = System.currentTimeMillis();
        
        do {
            stop = System.currentTimeMillis();
    	} while ((stop - start) < ((minutes * 60) * 1000)) ;
        
        cleanUpRoom(getRoom().getId());
	}
	
	@Transactional
	public void cleanUpRoom(long id) {
		repository.deleteById(id);
		setRoom(null);
	}

	public MeetingRoom getRoom() {
		return room;
	}

	public void setRoom(MeetingRoom room) {
		this.room = room;
	}

}

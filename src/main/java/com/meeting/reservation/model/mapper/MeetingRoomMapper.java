package com.meeting.reservation.model.mapper;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.meeting.reservation.model.dto.MeetingRoomDto;
import com.meeting.reservation.model.entity.MeetingRoom;

@Component
public class MeetingRoomMapper {

	public MeetingRoom ToEntity(MeetingRoomDto dto) {
        MeetingRoom room = new MeetingRoom();
        room.setName(dto.getName());
		room.setAllocation(dto.getAllocation());
		room.setMaxAllocation(dto.getMaxAllocation());
		room.setStartDate(dto.getStartDate());
		room.setTime(5 + room.getAllocation());
		LocalDate endDate = dto.getStartDate();
		room.setEndDate(endDate.plus(Duration.ofMinutes(room.getTime())));
		room.setHasMultimediaCapabilities(false);
        return room;
    }

    public MeetingRoomDto toDto(MeetingRoom room) {
        MeetingRoomDto dto = new MeetingRoomDto();
        dto.setId(room.getId());
        dto.setName(room.getName());
        dto.setAllocation(room.getAllocation());
        dto.setMaxAllocation(dto.getMaxAllocation());
        dto.setTime(room.getTime());
        return dto;
    }

    public List<MeetingRoomDto> toDto(List<MeetingRoom> rooms){
        return rooms.stream().map(this::toDto).collect(Collectors.toList());
    }
    
}

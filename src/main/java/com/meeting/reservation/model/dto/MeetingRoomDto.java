package com.meeting.reservation.model.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MeetingRoomDto {
	
	private long id;

	@NotNull
	private String name;
	
	@NotNull
	private long allocation; 
	
	@NotNull
	private long maxAllocation;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy")
	private LocalDate startDate;
	
	private long time;
	
	private boolean hasMultimediaCapabilities;
	
	private String media;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getAllocation() {
		return allocation;
	}
	
	public void setAllocation(long allocation) {
		this.allocation = allocation;
	}
	
	public long getMaxAllocation() {
		return maxAllocation;
	}
	
	public void setMaxAllocation(long maxAllocation) {
		this.maxAllocation = maxAllocation;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public boolean isHasMultimediaCapabilities() {
		return hasMultimediaCapabilities;
	}
	
	public void setHasMultimediaCapabilities(boolean hasMultimediaCapabilities) {
		this.hasMultimediaCapabilities = hasMultimediaCapabilities;
	}
	
	public String getMedia() {
		return media;
	}
	
	public void setMedia(String media) {
		this.media = media;
	}
	
}

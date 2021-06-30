package com.meeting.reservation.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.core.io.Resource;

@Entity
@Table(name = "tb_meeting_reservation")
public class MeetingRoom {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name= "name")
	private String name;
	
	@Column(name= "allocation")
	private long allocation; 
	
	@Column(name= "maxAllocation")
	private long maxAllocation;
	
	@Column(name= "startDate")
	private LocalDate startDate;
	
	@Column(name= "endDate")
	private LocalDate endDate;

	@Column(name= "time")
	private long time;
	
	private boolean hasMultimediaCapabilities;
	
	private Resource media;
	
	public long getId() {
		return id;
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
	
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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
	
	public Resource getVideo() {
		return media;
	}
	
	public void setVideo(Resource media) {
		this.media = media;
	}

}

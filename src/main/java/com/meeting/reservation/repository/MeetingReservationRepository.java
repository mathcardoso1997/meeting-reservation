package com.meeting.reservation.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meeting.reservation.model.entity.MeetingRoom;

@Repository
public interface MeetingReservationRepository extends JpaRepository<MeetingRoom, Long> {

	@Query("SELECT room " +
           "FROM MeetingRoom room " +
           "WHERE room.startDate >= :start")
    Optional<List<MeetingRoom>> findByDate(LocalDate start);
	
	@Query("SELECT room " 
			+ "FROM MeetingRoom room " 
			+ "WHERE room.startDate >= :start " 
			+ "AND room.endDate =< :end"
			+ "AND room.maxAllocation < room.allocation")
	    Optional<List<MeetingRoom>> findAllAvaibleByDate(LocalDate start, LocalDate end);

}

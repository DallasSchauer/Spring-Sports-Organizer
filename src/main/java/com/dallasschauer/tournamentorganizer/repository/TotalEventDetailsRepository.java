//package com.dallasschauer.tournamentorganizer.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.dallasschauer.tournamentorganizer.model.TotalEventDetails;
//
//public interface TotalEventDetailsRepository extends JpaRepository<TotalEventDetails, Integer>{
//	@Query(value="select * from Event e join League_details l on e.id=l.event_id where e.event_type=0",
//			nativeQuery=true)
//	List<TotalEventDetails> findAllLeagues();
//	
//	@Query(value="select * from Event e join tournament_details t on e.id=t.event_id where e.event_type=1",
//			nativeQuery=true)
//	List<TotalEventDetails> findAllTournaments();
//}

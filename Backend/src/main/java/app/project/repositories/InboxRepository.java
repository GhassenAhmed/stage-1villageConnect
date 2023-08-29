package app.project.repositories;

import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.Inbox;
import app.project.entities.User;

public interface InboxRepository extends JpaRepository<Inbox, Long> {
	 @Query(value="select * from inbox where user_envoi=:envoi and user_recu=:recu or user_envoi=:recu and user_recu=:envoi",nativeQuery=true)
	 List<Inbox> getMessageprivate(long envoi,long recu);

}

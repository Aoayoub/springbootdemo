package net.projet.ayoub.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.projet.ayoub.model.Status;
@Repository 
public interface StatusRepository extends JpaRepository<Status,Long> {

}

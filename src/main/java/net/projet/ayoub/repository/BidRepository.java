package net.projet.ayoub.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.projet.ayoub.model.Bid;
@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {

}

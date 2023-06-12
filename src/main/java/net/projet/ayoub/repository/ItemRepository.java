package net.projet.ayoub.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.projet.ayoub.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}

package net.projet.ayoub.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.projet.ayoub.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}

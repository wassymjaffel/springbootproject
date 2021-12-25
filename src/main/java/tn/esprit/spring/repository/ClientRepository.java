/**
 * 
 */
package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.categorieClient;

/**
 * @author HP OMEN
 *
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

	List<Client> findByCategorieClient(categorieClient CategorieClient);

	Client findByNom(String nom);

}

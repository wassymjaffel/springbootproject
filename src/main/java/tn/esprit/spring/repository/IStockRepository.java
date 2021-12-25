package tn.esprit.spring.repository;


import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.entities.Stock;

@Repository
public interface IStockRepository extends CrudRepository<Stock, Long> {

	
   //DeleteData
	
	@Modifying
	@Query(value = "delete from Stock s where s.idStock= :id")
	public void deleteReqById(@Param("id") Long id) ;
	
	//InsertDatatoDataBase
	
	@Modifying
	@Query(value = "INSERT INTO Stock (idStock, qte,qteMin,libelleStock) VALUES (:idStock,:qte, :qteMin, :libelleStock, )",
	nativeQuery = true)
	Stock addStock(@Param("idStock")Long idStock ,@Param("qte")int qte,@Param("qteMin")int qteMin,@Param( "libelleStock")String libelleStock );
	
    //SelectAllStocks
	
	@Query(value = "SELECT s FROM Stock s")
	List<Stock> getAll();
	
	

    //GetById
	
	@Query(value = "select s from Stock s where s.idStock =:id")
	Stock retrieveStockByid(@Param("id") int id);
	
	
	
		
		
	//retrieveStatus
	@Query("SELECT s from Stock s where s.qte< s.qteMin")
	List<Stock> retrieveStatusStock();
	
	
	
	
	
        	@Query("SELECT p FROM Produit p WHERE p.stock.idStock=:id")
        	List<Produit> findProduit(@Param("id") Long id);
            

        	@Query("SELECT p FROM Produit p WHERE p.stock=null")
        	List<Produit> findProduitWithoutStock(); 

}

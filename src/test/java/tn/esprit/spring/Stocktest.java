package tn.esprit.spring;




import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import com.sun.xml.messaging.saaj.packaging.mime.internet.ParseException;

import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.entities.Stock;
import tn.esprit.spring.service.StockService;




@RunWith(SpringRunner.class)
@SpringBootTest
public class Stocktest {
	@Autowired
	StockService stockService;

	
	
	Stock s = new Stock();
	
	@Test
	public void testAddStock() throws ParseException {
		s.setLibelleStock("libelleStock");
		s.setQte(5);
		s.setQteMin(6);
		stockService.addStock(s);
	}
	
	
	/*@Test
	public void testFINDById() {
		stockService.findStock(4L);
	}*/

	/*@Test
	public void testretrieveStatusStock() {
	String Stock  = stockService.retrieveStatusStock();
	}


	
	
	
	/*Stock u = new Stock (12L);
	@Test 
	public void testUpdateStock() {
		
	
		
		u.setIdStock(12L);
		u.setLibelleStock("am i wrong!! ");
		u.setQte(68);
		u.setQteMin(75);
		stockService.updateStock(u);
		
	
	
	}*/
	
	
	/*@Test 
	public void testgetsAllStocks() {
	List<Stock> stocks = stockService.getsAllStocks();

	}*/
	
	
	/*@Test 
	public void testDELETEStock() {
	stockService.deleteStock(34);

	}*/
	/*Produit p = new Produit (5L);
	@Test
	public void testfindproduit() {
	List<Produit> produit =  stockService.findProduit(5L);
	
	}*/
	
	/*@Test
	public void testretrieveStatusStock(){
	     String Stock = stockService.retrieveStatusStock();  
	}*/
}


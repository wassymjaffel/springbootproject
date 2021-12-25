package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import tn.esprit.spring.service.StockService;
import tn.esprit.spring.repository.IStockRepository;
import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.entities.Stock;

@RestController
@Api(tags = "Stock management")
@RequestMapping("/stock")
@CrossOrigin(origins = "http://localhost:4200")
public class StockRestController {

	@Autowired
	StockService stockService;
	@Autowired
	IStockRepository isr;
	// http://localhost:8089/SpringMVC/stock/get-all-stocks
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get-all-stocks")
	@ResponseBody
	@ApiOperation(value = "Récupérer la liste des stocks")
	public List<Stock> getStocks() {
	List<Stock> listStocks = stockService.getsAllStocks();
	return listStocks;
	}
	// http://localhost:8089/SpringMVC/stock/retrieveStatusStock
	@GetMapping("/retrieveStatusStock")
	@ResponseBody
	@ApiOperation(value = "retrieveStatusStock")
	public List<Stock> retrieveStatusStock() {
		
	return isr.retrieveStatusStock();
	}
	
	
	// http://localhost:8089/SpringMVC/stock/add-stock
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add-stock")
	@ResponseBody
	public Stock addStock(@RequestBody Stock s)
	{
	Stock stock = stockService.addStock(s);
	return stock;
	}
	
	
	// http://localhost:8089/SpringMVC/stock/remove-stock/{stock-id}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/remove-stock/{stock-id}")
	@ResponseBody
	public String removeStock(@PathVariable("stock-id") int stockId) {
	stockService.deleteStock(stockId);
	return "succes";
	}
	
	// http://localhost:8089/SpringMVC/stock/modify-stock
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/modify-stock")
	@ResponseBody
	public Stock modifyStock(@RequestBody Stock stock) {
	return stockService.updateStock(stock);
	}
	// http://localhost:8089/SpringMVC/stock/select/{i}
	@GetMapping("/select/{i}")
	@ResponseBody
	@ApiOperation(value = "findbyid")
    public Stock findStock(@PathVariable("i")Long i) {
		
	return stockService.findStock(i);
}
	
	// http://localhost:8089/SpringMVC/stock/patch-stock
	@PatchMapping("/patch-stock")
	@ResponseBody
	@ApiOperation(value = "mise à jour partielle-stock ")
	public Stock patchStock(@RequestBody Stock stock) {
		return stockService.patchStock(stock);
}

	// http://localhost:8089/SpringMVC/stock/produitsStock/{stock-id}
	@ResponseBody
	@GetMapping("/produitsStock/{stock-id}")
	@ApiOperation(value= "findproduit")
	public List<Produit> findProduit (@PathVariable("stock-id") Long id) {
		return (List<Produit>) stockService.findProduit(id);
	}
	
	
	
	// http://localhost:8089/SpringMVC/stock/ProduitWithoutstock
	@ResponseBody
	 @GetMapping("/ProduitWithoutstock")
	 @ApiOperation(value = "produit without stock")
	 public List<Produit> findProduitWithoutStock(){
			return (List<Produit>) stockService.findProduitWithoutStock();
		}
	

	// http://localhost:8089/SpringMVC/stock/update-stock-Withproduit
	@PostMapping("/update-stock-Withproduit")
	@ApiOperation(value ="ajouter un stock")
	@ResponseBody
	public Stock addStockwithproduit(@RequestBody Stock s)
	{
	return  stockService.updateStockwithProduit(s);
	//return Stock;
	}
}

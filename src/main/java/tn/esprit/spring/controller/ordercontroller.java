package tn.esprit.spring.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.service.ClientServiceImpl;
import tn.esprit.spring.service.OrderService;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Order;


@RestController
@RequestMapping("/Orders")
public class ordercontroller {
	@Autowired
	OrderService os;
	@Autowired
	ClientServiceImpl cs;
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/addOrder")
	public void add(@RequestBody Order o)
	{
		os.add(o);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/OrderProducts/{i}/{i1}")
	public void orderproducts(@PathVariable("i") int i,@PathVariable("i1") int i1,@RequestBody Order o)
	{
		os.OrderProducts(i, i1, o);;
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/deleteOrder/{id}")
	public String delete(@PathVariable("id") int i)
	{
		os.delete(i);
	return "success";
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/updateorder/{i}")
	public void update(@RequestBody Order o,@PathVariable int i)
	{
		os.update(o,i);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/updateorder2/{i}/{i1}")
	public void update2(@PathVariable int i,@PathVariable int i1){
		os.assigncustomertoOrder(i, i1);
		
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/updateorder3/{i}/{i1}")
	public void update3(@PathVariable int i,@PathVariable int i1){
		os.asignproducttoOrder(i, i1);
		
	}
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/displayorders")
	public List<Order> display()
	{
		return os.findall();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/select/{i}")
	public Order select(@PathVariable int i)
	{
		return os.Select(i);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/count1")
	public List<Object>countordersbyday()
	{  
		//List<String>ls=new ArrayList<>();
		
		return os.countOrdersByDate();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/count2")
	public List<Map<String,Object>> countordersbystatus()
	{
		return os.countOrdersByStatus();
	}	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/count3")
	public List<Map<String,Object>> countordersbydateandstatus()
	{
		return os.countOrdersByStatusandDate();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/count4")
	public List<Object []> countcompletedordersbydate()
	{
		return os.countcompletedOrdersbydate();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/count5")
	public List<Map<String,Object>> count5()
	{
		return os.countOrdersbymonth();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/count6")
	public List<Object[]> count6()
	{
		return os.countOrdersbycustomer();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/count8")
	public List<Object> count7()
	{
	    
	   
		return os.countOrdersByCountry();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/count7/{i}")
	public int count7(@PathVariable long i)
	{
		return os.countcompletedordersbycustumer(i);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/retrivecompletedOrders/{i}")
	public List<Order> retrieveorders(@PathVariable int i)
	{
		return os.retrievecompletedordersbycustumer(i);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/retriveOrdersPerMonth/{i}")
	public List<Order> retrieveorderspermonth(@PathVariable int i)
	{
		return os.retrievecompletedOrdersByMonth(i);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/retriveOrdersPerCountry/{country}")
	public List<Order> retrieveorderspercountry(@PathVariable String country)
	{
		return os.retrievecompletedOrdersByCountry(country);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getorder")
	public Object getorder2()
	{
		return os.getorder();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/sendmail/{i}")
	public List<Order> sendEmail(@PathVariable long i) {
		Client c=cs.retrieveClient(i);
	   os.sendmail(c);
	   return os.retrievecompletedordersbycustumer(i);  
	}
	
	

}

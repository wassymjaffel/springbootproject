package tn.esprit.spring.service;


//import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.Message;

import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Order;
import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.IProduitRepository;
import tn.esprit.spring.repository.OrderRepository;


//import org.springframework.mail.javamail.JavaMailSender;
@Service
public class OrderService {
	@Autowired
	OrderRepository or;
	@Autowired
	ClientRepository cr;
	@Autowired
	IProduitRepository pr;
	
	public void add(Order o)
	{
		or.save(o);
	}
	public void update(Order o,int i)
	{
		Order order =or.findById(i).get();
		order.setOrder_address(o.getOrder_address());
		order.setOrder_date(o.getOrder_date());
		order.setOrder_status(o.isOrder_status());
		order.setOrder_id(o.getOrder_id());
		order.setProducts(o.getProducts());
		//order.setCustomer(o.getCustomer());
		
		or.save(order);
	}
	public void delete(int i)
	{   
		or.truncorder(i);
		or.deleteById(i);
	}
	public List<Order> findall()
	{
		List<Order>lo=new ArrayList<>();
		or.findAll().forEach(e->lo.add(e));
		return lo;
	}
	public void assigncustomertoOrder(long idc,int ido )
	{
		
		Client c=cr.findById(idc).get();
		Order  o=or.findById(ido).get();
		o.setCustomer(c);
		this.update(o, o.getOrder_id());
	}
	public Order Select(int i)
	{
		return  or.findById(i).get();
		}
	public List<Object>countOrdersByDate()
	{
		return or.countOrdersByDate();
	}
	
	public List<Map<String,Object>>countOrdersByStatus()
	{
		return or.countOrdersByStatus();
	}
	public List<Map<String,Object>>countOrdersByStatusandDate()
	{
		return or.countOrdersByStatusanddate();
	}
	public Object getorder()
	{
		return or.getOrder();
	}
	public List<Object[]>countcompletedOrdersbydate()
	{
		return or.countcompletedOrdersbydate();
	}
	public List<Map<String,Object>>countOrdersbymonth()
	{
		return or.countordersbymonth();
	}
	public List<Object[]>countOrdersbycustomer()
	{
		return or.countordersbycustomer();
	}
	public int countcompletedordersbycustumer(Long i)
	{
		Client c=cr.findById(i).get();
		
		return or.countcompletedOrdersByCustomer(c);
	}
	public List<Order> retrievecompletedordersbycustumer(long i)
	{
		Client c=cr.findById(i).get();	
		return or.retrievecompletedOrdersByCustomer(c);
	}
	public List<Object> countOrdersByCountry()
	{
		return or.countordersbycountry();
	}
	public void OrderProducts(int custid,int prdId,Order o)
	{
		or.save(o);
		this.assigncustomertoOrder(custid, o.getOrder_id());
		this.asignproducttoOrder(prdId,o.getOrder_id() );
		
	}
	private static final Logger log = LogManager.getLogger(OrderService.class);
    public void asignproducttoOrder(long pid,int oid)
    {
    	Order o=or.findById(oid).get();
    	Produit p=pr.findById(pid).get(); 
    	Set<Produit> sp=o.getProducts();
    	sp.add(p);
    	p.getOrders().add(o);
    	this.update(o, o.getOrder_id());
	    log.info(o.getProducts());
    }
    public List<Order>retrievecompletedOrdersByMonth(int month)
    {
         return or.retrievecompletedOrdersByMonth(month);	
    }
    public List<Order>retrievecompletedOrdersByCountry(String country){
    	return or.retrievecompletedOrdersByCountry(country);
    }
    private JavaMailSender javaMailSender;

    public void EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendmail(Client c)  
    {
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	mailSender.setHost("smtp.gmail.com");
    	mailSender.setPort(587);
    	mailSender.setUsername("baissahmed@gmail.com");
    	mailSender.setPassword("2XTAPL20C");
    	 
    	Properties properties = new Properties();
    	properties.setProperty("mail.smtp.auth", "true");
    	properties.setProperty("mail.smtp.starttls.enable", "true");
    	 
    	mailSender.setJavaMailProperties(properties);
    	String from = mailSender.getUsername();
    	String to = c.getEmail();
    	 
    	SimpleMailMessage message = new SimpleMailMessage();
    	 
    	message.setFrom(from);    	
    	message.setTo(to);
    	message.setSubject("This is a plain text email");
    	List<Order>pl=new ArrayList<>();
        or.retrieveOrdersByCustomer(c).stream().count();
    	or.retrievecompletedOrdersByCustomer(c).stream().count();
    	
    	long pending=or.retrieveOrdersByCustomer(c).stream().count()-or.retrievecompletedOrdersByCustomer(c).stream().count();
    	
   /* or.retrieveOrdersByCustomer(c).stream().forEach(e->e.getProducts().stream().mapToDouble(x->x.getPrice()).sum());*/
        
   pl=or.retrieveOrdersByCustomer(c);
   double sum=0;
   for(int i=0;i<pl.size();i++)
   {
	  sum=sum+pl.get(i).getProducts().stream().mapToDouble(x->x.getPrixUnitaire()).sum();
	  System.out.println(pl);
   }
    	message.setText("you have"+or.retrievecompletedOrdersByCustomer(c).stream().count() +"completed Orders and"+
    			pending+"pending"+"  "+"total amount="+sum
    			
    			);
    	
    	mailSender.send(message);
    	
    }
}

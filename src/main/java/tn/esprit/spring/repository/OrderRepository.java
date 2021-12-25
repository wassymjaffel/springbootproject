package tn.esprit.spring.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Order;



public interface OrderRepository extends CrudRepository<Order, Integer> {
	
	
	
	@Query(value="SELECT o.order_status as completed,count(o.order_id)as number_of_orders  from Order  o group by o.order_status")
	List<Map<String,Object>> countOrdersByStatus();
	@Query(value="SELECT o.order_status as completed,o.order_date as day,count(o.order_id) as number_of_orders  from Order o group by o.order_status,o.order_date")
	List<Map<String,Object>> countOrdersByStatusanddate();
	@Query(value="SELECT o.order_date ,count(o.order_id)   from Order o where o.order_status=1 group by o.order_status,o.order_date")
	List<Object[]> countcompletedOrdersbydate();
	@Query(value = "SELECT count(`order_id`) as number_of_orders,concat(month(`order_date`),'/',year(`order_date`)) "
			+ "as month FROM `orders` GROUP by month(`order_date`),year(`order_date`)",
			nativeQuery = true)
	
			List<Map<String,Object>> countordersbymonth();
	
	
	@Query(value="SELECT count(o.order_id)as number_of_orders from  Order o where o.customer=:customer and o.order_status=1 group by o.customer ")
	int countcompletedOrdersByCustomer(@Param("customer") Client
  			customer);
	@Transactional
	@Modifying
	@Query(value="DELETE FROM `product_orders` WHERE `product_id`=:order_id",nativeQuery=true)
	void truncorder(@Param("order_id") int order_id);
	/**************************************************jpql******************************************************************/
	@Query(value="SELECT o from  Order o where o.customer=:customer and o.order_status=1")
	List<Order>retrievecompletedOrdersByCustomer(@Param("customer") Client
			customer);
	
	@Query(value="SELECT * FROM `orders` WHERE `order_id`=(SELECT MAX(`order_id`) FROM `orders`)",nativeQuery=true)
	Object getOrder();
	@Query(value="SELECT o from  Order o where o.customer=:customer")
	List<Order>retrieveOrdersByCustomer(@Param("customer") Client
			customer);
	@Query(value="SELECT o  FROM Order o where month(o.order_date)=:month and o.order_status=1")
	List<Order>retrievecompletedOrdersByMonth(@Param("month") int month);
	@Query(value="SELECT o FROM Order o WHERE Locate(?1,o.order_address)!=0")
	List<Order>retrievecompletedOrdersByCountry(String country);
	@Query(value="SELECT SUBSTRING(o.order_address,length(SUBSTRING_INDEX(o.order_address,',', 3))+2 "
			+ ",length(o.order_address)-length(SUBSTRING_INDEX(o.order_address,',', 3))+2) "
			+ ",count(o.order_id)  from Order o GROUP by "
			+ "SUBSTRING(o.order_address,length(SUBSTRING_INDEX(o.order_address,',', 3))+2 "
			+ ",length(o.order_address)-length(SUBSTRING_INDEX(o.order_address,',', 3))+2) ")
	List <Object> countordersbycountry();
	@Query(value="SELECT o.order_date,count(o.order_id)  from Order  o group by o.order_date")
	List<Object> countOrdersByDate();
	@Query(value="SELECT o.customer.nom,count(o.order_id) from  Order o group by o.customer")
	List <Object[]> countordersbycustomer();
/*	@Query(value="")
	List<Bill>retrieveBills();*/
	
	
			
	
    
	
	

}

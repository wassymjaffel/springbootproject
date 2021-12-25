package tn.esprit.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Rayon;
import tn.esprit.spring.service.RayonService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Rayontest {

	@Autowired
	RayonService rayonService;
	
	
	Rayon r = new Rayon();
	@Test
	public void testAddRayon() {
		rayonService.patchRayon(r);
	
		

	}

	/*@Test
	public void testDELETERayon() {
	rayonService.deleteRayon(12);

	}*/
	
}

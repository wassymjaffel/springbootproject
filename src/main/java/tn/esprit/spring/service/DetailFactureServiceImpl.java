package tn.esprit.spring.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import tn.esprit.spring.entities.detailFacture;
import tn.esprit.spring.repository.DetailFactureRepository; 


@Slf4j
@Service
public class DetailFactureServiceImpl implements DetailFactureService{

	
	@Autowired
	DetailFactureRepository detailFactureRepository;

	@Override
	public List<detailFacture> retrieveAllDetailFactures() {
		// TODO Auto-generated method stub
		ArrayList<detailFacture> f=new ArrayList<detailFacture>();
		detailFactureRepository.findAll().forEach(detailFacture->f.add(detailFacture));
		return f;
	}

	@Override
	public detailFacture addDetailFacture(detailFacture f) {
		// TODO Auto-generated method stub
		return detailFactureRepository.save(f) ;
	}

	@Override
	public void deleteDetailFacture(Long id) {
		// TODO Auto-generated method stub
		 detailFactureRepository.deleteById(id);
	}

	@Override
	public detailFacture updateDetailFacture(detailFacture f) {
		// TODO Auto-generated method stub
		return detailFactureRepository.save(f);
	}

	@Override
	public detailFacture retrieveDetailFacture(Long id) {
		// TODO Auto-generated method stub
		return detailFactureRepository.findById(id).get() ;
	}


	

	
	
	/**
	public String getChiffreAffaireMagasin(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss.SSS");
		Date now = new Date();
		String msgDate = sdf.format(now);
		Float totalprix = detailFactureRepository.getChiffreAffaireMagasin();
		String finalMessage = "Chiffre d'affaire de magasin : " + msgDate + " = " + totalprix;
		//log.info(finalMessage);
		return finalMessage;
	}
	*/

}

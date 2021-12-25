package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ClientRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private ClientRepository repository;
    
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String nom) throws UsernameNotFoundException {
		Client user = repository.findByNom(nom) ;
		CustomUserDetails userDetails=new CustomUserDetails(); 
		userDetails.setUser(user); System.out.println(user);
		
		if (user == null) { 
			throw new UsernameNotFoundException("User not found with name :" + nom);
			
		} else{user.setActive(true);}
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
		
		return new org.springframework.security.core.userdetails.User(user.getNom(),
				user.getPassword(),user.getActive(), true, true, true, authorities);
	}

	public List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Role role : userRoles) {
		roles.add(new SimpleGrantedAuthority(role.getName().name())); }
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
		}
	
}

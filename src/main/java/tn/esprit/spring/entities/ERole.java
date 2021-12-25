package tn.esprit.spring.entities;

import org.springframework.security.core.GrantedAuthority;

public enum ERole implements GrantedAuthority{
	ROLE_USER,
    ROLE_ADMIN,
    ROLE_SUPERADMIN;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return  "ROLE_" + name();
	}
}

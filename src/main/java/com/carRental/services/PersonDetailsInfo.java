package com.carRental.services;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.carRental.entities.Person;


public class PersonDetailsInfo implements UserDetails {
    @Serial
	private static final long serialVersionUID = 1L;

	
	private final Person person;


	public PersonDetailsInfo(Person person) {
		super();
		this.person = person;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + person.getRole()));
	}

	@Override
	public String getPassword() {
		
		return person.getPassword();
	}

	@Override
	public String getUsername() {
	
		return person.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	

	@Override
	public boolean isEnabled() {
		return true;
	}
}
package com.interior.shivaniInteriorproject.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.interior.shivaniInteriorproject.model.User;

public class CustUserDetails extends User implements UserDetails {

		public CustUserDetails(User user) {
			super(user);
		}

	private static final long serialVersionUID = 1L;

	public CustUserDetails() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	List<GrantedAuthority> authorityList = new ArrayList<>();
	super.getRoles().forEach(role -> {
		authorityList.add(new SimpleGrantedAuthority(role.getName()));
		});
		return authorityList;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getEmail();
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


//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
////		HashSet<SimpleGrantedAuthority> userSet = new HashSet<SimpleGrantedAuthority>();
////		userSet.add(new SimpleGrantedAuthority(user.getRoles()));
////	//	userSet.addAll(new SimpleGrantedAuthority(user.getRoles());
////		return userSet;
//		return getAuthorities();
//		
//	}

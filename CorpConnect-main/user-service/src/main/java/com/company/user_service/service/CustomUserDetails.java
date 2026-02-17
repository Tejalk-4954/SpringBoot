package com.company.user_service.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.company.user_service.entity.User;

public class CustomUserDetails implements UserDetails{
	
	private User user;
	private final Collection<? extends GrantedAuthority> authorities;
	
	 public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
	        this.user = user;
	        this.authorities = authorities;
	    }
	

	 @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return authorities;
	    }

	public String getDepartment() {
        return user.getDepartment();   // 🔥 DEPARTMENT ACCESS HERE
    }
	
	@Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

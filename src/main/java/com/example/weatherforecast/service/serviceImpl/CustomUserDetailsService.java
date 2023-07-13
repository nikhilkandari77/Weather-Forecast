package com.example.weatherforecast.service.serviceImpl;

import com.example.weatherforecast.entity.User;
import com.example.weatherforecast.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u=userRepo.findByEmail(username);
        if(u!=null&&u.getRole()!=null&&u.getRole().equals("ADMIN")) {
            u.setWeatherUpdates(null);
            return u;
        }
        else throw new UsernameNotFoundException("username not found or not active");

    }
}

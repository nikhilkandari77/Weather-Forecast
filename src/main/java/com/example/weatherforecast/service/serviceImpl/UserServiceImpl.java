package com.example.weatherforecast.service.serviceImpl;

import com.example.weatherforecast.entity.User;
import com.example.weatherforecast.repository.UserRepo;
import com.example.weatherforecast.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Override
    public void postUser(User user) {
        userRepo.save(user);
    }
}

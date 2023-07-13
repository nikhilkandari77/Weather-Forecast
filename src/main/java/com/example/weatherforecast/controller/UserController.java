package com.example.weatherforecast.controller;

import com.example.weatherforecast.dto.RequestDTO;
import com.example.weatherforecast.dto.UserDTO;
import com.example.weatherforecast.dto.WeatherHistoryDTO;
import com.example.weatherforecast.entity.User;
import com.example.weatherforecast.entity.WeatherHistory;
import com.example.weatherforecast.service.LocationService;
import com.example.weatherforecast.service.WeatherHistoryService;
import com.example.weatherforecast.service.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.websocket.Session;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private WeatherHistoryService historyService;
    private UserServiceImpl userService;


    @GetMapping("/login")
    public String login(Model model,HttpSession session){
        session.setAttribute("hasLogin",false);
        model.addAttribute("user" ,new User());
        return "login";
    }
    @PostMapping("/login")
    public String postlogin(Model model){
        model.addAttribute("user" ,new User());
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user",new User());
        return "signup";
    }
    @PostMapping("/signup")
    public String doSignup(@Valid User user, Model model){
        model.addAttribute("user",new User());
        System.out.println(user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.postUser(user);
        return "signup";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response,HttpSession session){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("hasLogin",false);
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }

        return "redirect:/user/login";
    }

    @GetMapping("/admin")
    public String getAdmin(Model model, Principal principal, RequestDTO requestDTO, HttpSession session){
        session.setAttribute("hasLogin",true);
        List<WeatherHistoryDTO> historyList;
        historyList = historyService.
                getAllByDate(Objects.requireNonNullElseGet(requestDTO, () -> new RequestDTO(LocalDate.now())));
        model.addAttribute("historyList",historyList);
        System.out.println(principal);
        return "admin";
    }

    @GetMapping("/admin/message")
    public String getAdminMessage( Model model,@Valid RequestDTO requestDTO){
        System.out.println(requestDTO);
        model.addAttribute("id",requestDTO.getId());
        return "writeMessage";
    }
    @PostMapping("/admin/write")
    public String postMessage( Model model, Principal principal,@Valid RequestDTO requestDTO){
        System.out.println(requestDTO);
        historyService.postWeatherHistory(requestDTO,principal.getName());
        model.addAttribute("id",requestDTO.getId());
        return "writeMessage";
    }

}
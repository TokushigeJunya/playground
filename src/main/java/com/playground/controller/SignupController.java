package com.playground.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.playground.entity.MUser;
import com.playground.form.GroupOrder;
import com.playground.form.SignupForm;
import com.playground.service.UserService;

@Controller 
@RequestMapping("/user") 
public class SignupController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/signup") 
    public String getSignup(Model model, @ModelAttribute SignupForm form) { 
        return "user/signup"; 
    }

    @PostMapping("/signup")
    public String postSignup(Model model, @ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult) { 
    	
    	if (bindingResult.hasErrors()) { 
    		return getSignup(model, form);
    	}
    	
    	MUser user = modelMapper.map(form, MUser.class); 
    	
    	if(userService.signup(user)) {
    		return "redirect:/login"; 
    	} else {
    		model.addAttribute("duplicateKey", true);
    		return "user/signup";
    	}
    	
    } 
}

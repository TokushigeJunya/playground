package com.playground.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.playground.entity.MUser;
import com.playground.form.GroupOrder;
import com.playground.form.UserDetailForm;
import com.playground.service.UserService;

@Controller
@RequestMapping("/user")
public class UserDetailController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping("/mydetail")
    public String getMe(Model model, UserDetailForm form) {
    	
    	if(form.getPassword()==null) {

    	    String userId = SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	    MUser user = userService.getUserOne(userId);
            user.setPassword(null);

            form = modelMapper.map(user, UserDetailForm.class);

            model.addAttribute("userDetailForm", form);	
    	
    	}
        return "user/mydetail";
    }

    @PostMapping(value = "/updateMe")
    public String updateMe(Model model, @ModelAttribute @Validated(GroupOrder.class) UserDetailForm form, BindingResult bindingResult) {

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
    	
        if(!userId.equals(form.getUserId())) {
            return "redirect:/logout";
    	}
    	
        if (bindingResult.hasErrors()) { 
    		return getMe(model, form);
    	}
    	
    	MUser user = modelMapper.map(form, MUser.class);
    	
        userService.updateUserOne(user);

        return "redirect:/logout";
    }
    
    @PostMapping(value = "/deleteMe")
    public String deleteMe(Model model, UserDetailForm form, BindingResult bindingResult) {

    	if (bindingResult.hasErrors()) { 
    		return "redirect:/logout";
    	}
    	
    	String userId = SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	if(!userId.equals(form.getUserId())) {
            return "redirect:/logout";
    	}
    	
        userService.deleteUserOne(form.getUserId());

        return "redirect:/logout";
    }
    
    @GetMapping("/detail/{userId:.+}")
    public String getUser(Model model, UserDetailForm form,  @PathVariable("userId") String userId) {

    	if(form.getPassword()==null) {
    		
    	    MUser user = userService.getUserOne(userId);
            user.setPassword(null);

            form = modelMapper.map(user, UserDetailForm.class);

            model.addAttribute("userDetailForm", form);
    	}
    	
        return "user/detail";
    }
    
    @PostMapping(value = "/update")
    public String updateUser(Model model, @ModelAttribute @Validated(GroupOrder.class) UserDetailForm form, BindingResult bindingResult) {

    	if (bindingResult.hasErrors()) { 
    		return getUser(model, form, form.getUserId());
    	}
    	
    	MUser user = modelMapper.map(form, MUser.class);
    	
        userService.updateUserOne(user);

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        
        if(userId.equals(form.getUserId())) {
    		return "redirect:/logout";
    	} else {
            return "redirect:/user/list";
    	}
    }

    @PostMapping(value = "/delete")
    public String deleteUser(Model model, @ModelAttribute UserDetailForm form, BindingResult bindingResult) {
    	
    	if (bindingResult.hasErrors()) { 
    		return "redirect:/logout";
    	}
    	
    	userService.deleteUserOne(form.getUserId());
    	
    	String userId = SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	if(userId.equals(form.getUserId())) {
    		return "redirect:/logout";
    	} else {
            return "redirect:/user/list";
    	}
    }
}
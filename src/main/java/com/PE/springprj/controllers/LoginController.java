package com.PE.springprj.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@GetMapping("/login")
	public ModelAndView showLoginForm(
			@RequestParam(name="error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {

		ModelAndView modelAndView = new ModelAndView("/login");
		modelAndView.addObject("error", error);
		modelAndView.addObject("logout", logout);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
			return modelAndView;
		return new ModelAndView("redirect:/");
	}
}
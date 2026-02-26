package com.courier.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	String view = "index";

	@GetMapping(value = "/")
	public String showIndexPage() {

		return view;
	}

	@GetMapping(value = "index")
	public String showHomePage() {

		return view;
	}

	@GetMapping(value = "logout")
	public String showLogOut() {

		return view;
	}

	@GetMapping(value = "accessDenied")
	public String showAccessDenied() {

		return "accessDenied";
	}
}

package com.courier.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {

	@GetMapping("/")
	public String showIndexPage() {

		return "index";
	}

	@GetMapping("index")
	public String showHomePage() {

		return "index";
	}

	@GetMapping("logout")
	public String showLogOut() {

		return "login";
	}

	@GetMapping("login")
	public String showIndex() {

		return "login";
	}

	@GetMapping("accessDenied")
	public String showAccessDenied() {

		return "accessDenied";
	}

}

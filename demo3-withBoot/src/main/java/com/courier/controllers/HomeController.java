package com.courier.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {

	private static final String VIEW_INDEX = "index";

	@GetMapping("/")
	public String showIndexPage() {

		return VIEW_INDEX;
	}

	@GetMapping("index")
	public String showHomePage() {

		return VIEW_INDEX;
	}

	@GetMapping("logout")
	public String showLogOut() {

		return VIEW_INDEX;
	}

	@GetMapping("login")
	public String showIndex() {

		return VIEW_INDEX;
	}
}

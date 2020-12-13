package com.fluidcodes.crm.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fluidcodes.crm.dao.UsersRepo;
import com.fluidcodes.crm.entities.Offices;
import com.fluidcodes.crm.entities.Users;
import com.fluidcodes.crm.services.OfficesService;
import com.fluidcodes.crm.services.SecurityUtils;
import com.fluidcodes.crm.services.UsersService;

@Controller
public class UsersController {

	// wire all the repos to be used
	@Autowired
	private OfficesService officesservice;
	@Autowired
	private UsersService usersservice;

	@Autowired
	private UsersRepo usersrepo;

	public UsersController(UsersService usersservice) {
		this.usersservice = usersservice;
	}

	// for the custom login page container
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

	// issuing a new user
	@RequestMapping("newuser")
	public String adduser(Model modelUsers, Model modelOffices, Model office) {
		// object of Users
		Users newUser = new Users();
		System.out.println("new user before add" + newUser);
		// find all the offices available to assign user
		List<Offices> officeInfo = officesservice.findAll();
		// container for all the offices listOfficesAva
		modelOffices.addAttribute("listOfficesAva", officeInfo);
		// container for new user
		modelUsers.addAttribute("newUser", newUser);
		// containing office for user
		Offices off = new Offices();
		off.setOfficeId(1);
		office.addAttribute("off", off);
		System.out.println("off id " + off.getOfficeId());
		// find office by id
		officesservice.findById(off.getOfficeId());
		System.out.println("new user after  add" + newUser);
		// goto userform.html
		return "userform";
	}

	// editing user via user ID
	@RequestMapping("edituser")
	public String editUser(@RequestParam("userId") Long userId, Model modelUsers, Model modelOffices, Model office) {
		// find all offices available
		List<Offices> officeInfo = officesservice.findAll();
		modelOffices.addAttribute("listOfficesAva", officeInfo);
		// find user by ID
		Users editUser = usersservice.findById(userId);
		Offices userOffice = editUser.getOffice();
		office.addAttribute("off", userOffice);
		System.out.println("ID for User Edit: " + userId);
		System.out.println("On User edit form" + editUser);
		// contain all offices available
		modelOffices.addAttribute("listOfficesAva", officeInfo);
		// contain new edited information
		modelUsers.addAttribute("newUser", editUser);
		// goto userform.html
		return "userform";
	}

	// user login session to change their information
	@RequestMapping("settings")
	public String editCurrentUser(Model user, Model office) {
		// create an empty user object
		Users currentUser = null;
		// check if user exsit in db via the session
		Optional<Users> o = usersrepo.findByUserEmail(SecurityUtils.getUserName());
		// if user exist assign the user to currentUser else print no user
		if (o.isPresent())
			currentUser = o.get();
		else
			System.out.println("No user!");
		// get user office
		Offices userOffice = currentUser.getOffice();
		System.out.println("AuthenticationPrincipal" + currentUser);
		// container for current user in newUser
		user.addAttribute("newUser", currentUser);
		// show user office
		office.addAttribute("off", userOffice);

		// goto currentuserform.html
		return "currentuserform";
	}

	// save edited or new user
	@PostMapping("saveuser")
	public String saveUser(@Valid @ModelAttribute("newUser") Users newUser, BindingResult bind, Model modelOffices,
			@ModelAttribute("off") Offices off) {
		// check if user exist
		Users now = new Users();
		Optional<Users> o = usersrepo.findByUserEmail(SecurityUtils.getUserName());
		if (o.isPresent()) {
			now = o.get();
		}

		System.out.println(newUser);
		// check if any form error
		if (bind.hasErrors()) {
			System.out.println("error count:" + bind.getErrorCount());
			System.out.println(bind.getFieldErrors());
			List<Offices> officeInfo = officesservice.findAll();
			modelOffices.addAttribute("listOfficesAva", officeInfo);
			System.out.println("Error after submit button: " + newUser);
			// if edit is by admin go to userform if by user go to currentuserform
			if (now.getUserRole().equals("ROLE_ADMIN"))
				return "userform";
			else
				return "currentuserform";
		}

		// save user to db
		usersservice.save(newUser, off.getOfficeId());
		// goto admin.html but user will be redirected because of no permission
		return "redirect:/admin";
	}

	// delete user by id
	@GetMapping("deleteuser")
	public String deleteUser(@RequestParam("userId") Long userId) {
		usersservice.deleteById(userId);

		// goto admin.html
		return "redirect:/admin";
	}
}

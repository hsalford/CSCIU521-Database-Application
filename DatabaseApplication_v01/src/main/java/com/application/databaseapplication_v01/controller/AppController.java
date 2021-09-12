package com.application.databaseapplication_v01.controller;

import com.application.databaseapplication_v01.entity.Instructor;
import com.application.databaseapplication_v01.entity.Student;
import com.application.databaseapplication_v01.repository.UserRepository;
import com.application.databaseapplication_v01.service.InstructorService;
import com.application.databaseapplication_v01.service.StudentService;
import com.application.databaseapplication_v01.service.UserService;
import com.application.databaseapplication_v01.entity.Role;
import com.application.databaseapplication_v01.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {

	@Autowired
	private UserService service;

	@Autowired
	private StudentService studentservice;

	@Autowired
	private InstructorService instructorService;

	private UserRepository userRepository;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		service.registerDefaultUser(user);

		return "register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = service.get(id);
		List<Role> listRoles = service.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}

	@PostMapping("/users/save")
	public String saveUser(User user) {
		service.save(user);

		return "redirect:/users";
	}

	@GetMapping("/users/register_student")
	public String registerStudentForm(Model model, @AuthenticationPrincipal UserDetails currentUser) {
		model.addAttribute("student", new Student());
		//User user = (User) userRepository.findByUsername(currentUser.getUsername());
		//model.addAttribute("currentStudent", user);

		return "register_student_form";
	}

	@PostMapping("/users/process_student_register")
	public String processStudentRegister(Student student) {
		studentservice.registerStudent(student);

		return "redirect:/users";
	}

	@GetMapping("/users/register_instructor")
	public String registerInstructorForm(Model model, @AuthenticationPrincipal UserDetails currentUser) {
		model.addAttribute("instructor", new Instructor());
		//User user = (User) userRepository.findByUsername(currentUser.getUsername());
		//model.addAttribute("currentStudent", user);

		return "register_instructor_form";
	}

	@PostMapping("/users/process_instructor_register")
	public String processInstructorRegister(Instructor instructor) {
		instructorService.registerStudent(instructor);

		return "redirect:/users";
	}
}

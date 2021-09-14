package com.application.databaseapplication_v01.controller;

import com.application.databaseapplication_v01.entity.Instructor;
import com.application.databaseapplication_v01.entity.Student;
import com.application.databaseapplication_v01.service.InstructorService;
import com.application.databaseapplication_v01.service.StudentService;
import com.application.databaseapplication_v01.service.UserService;
import com.application.databaseapplication_v01.entity.Role;
import com.application.databaseapplication_v01.entity.User;
import com.application.databaseapplication_v01.service.ValidationService;
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
	private UserService userService;

	@Autowired
	private StudentService studentservice;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private ValidationService validationService;


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
		userService.registerDefaultUser(user);

		return "register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = userService.get(id);
		List<Role> listRoles = userService.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}

	@PostMapping("/users/save")
	public String saveUser(User user) {
		userService.save(user);

		return "redirect:/users";
	}

	@GetMapping("/users/register_student")
	public String registerStudentForm(Model model, @AuthenticationPrincipal UserDetails currentUser) {

		User user = (User) userService.findByUsername(currentUser.getUsername());
		List<Student> students = studentservice.studentList();
		String err = validationService.checkStudentExist(students, user);
		if (!err.isEmpty()) {
			model.addAttribute("errorMessageNewStudent", err);
			List<User> listUsers = userService.listAll();
			model.addAttribute("listUsers", listUsers);
			return "users";
		}

		model.addAttribute("student", new Student());
		return "register_student_form";
	}

	@PostMapping("/users/process_student_register")
	public String processStudentRegister(Student student, @AuthenticationPrincipal UserDetails currentUser) {
		User user = (User) userService.findByUsername(currentUser.getUsername());
		student.setUser(user);
		studentservice.registerStudent(student);

		return "register_student_success";
	}

	@GetMapping("/users/register_instructor")
	public String registerInstructorForm(Model model, @AuthenticationPrincipal UserDetails currentUser) {
		User user = (User) userService.findByUsername(currentUser.getUsername());
		List<Instructor> instructors = instructorService.instructorList();
		String err = validationService.checkInstructorExist(instructors, user);
		if (!err.isEmpty()) {
			model.addAttribute("errorMessageNewInstructor", err);
			List<User> listUsers = userService.listAll();
			model.addAttribute("listUsers", listUsers);
			return "users";
		}

		model.addAttribute("instructor", new Instructor());

		return "register_instructor_form";
	}

	@PostMapping("/users/process_instructor_register")
	public String processInstructorRegister(Instructor instructor, @AuthenticationPrincipal UserDetails currentUser) {
		User user = (User) userService.findByUsername(currentUser.getUsername());
		instructorService.registerInstructor(instructor);
		instructor.setUser(user);

		return "register_instructor_success";
	}
}

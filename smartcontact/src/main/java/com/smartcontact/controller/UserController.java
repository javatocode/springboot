package com.smartcontact.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smartcontact.dao.ContactRepository;
import com.smartcontact.dao.UserRepository;
import com.smartcontact.helper.Message;
import com.smartcontact.models.Contact;
import com.smartcontact.models.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@ModelAttribute
	public void commondata(Model model , Principal principal) {
        String username = principal.getName();
		User user = userRepository.getUserByUserName(username);
		model.addAttribute("user", user);
	}

	@RequestMapping("/index")
	public String userindex(Model model , Principal principal) {

		model.addAttribute("title","Dashboard");
		return "/authuser/index";
	}
	
	@RequestMapping("/showcontacts/{page}")
	public String showcontacts(@PathVariable("page")Integer page,  Model m , Principal principal) {
		m.addAttribute("title","View Contacts");
		String username = principal.getName();
		User user = this.userRepository.getUserByUserName(username);
		PageRequest pageRequest = PageRequest.of(page, 5);
     	Page<Contact> contacts = this.contactRepository.findContactsByUser(user.getId(),pageRequest);
		m.addAttribute("contacts", contacts);
		m.addAttribute("currentPage", page);
		m.addAttribute("totalPages", contacts.getTotalPages());
     	return "authuser/showcontacts";
	}
	
	@GetMapping("/addcontact")
	public String openaddcontactform(Model m) {
		m.addAttribute("title", "Add Contact");
		m.addAttribute("contact", new Contact());
		return "/authuser/addcontact";
	}
	
	
	// processing of contact
		@PostMapping("/process-contact")
		public String processofcontact(@ModelAttribute Contact contact, @RequestParam("profileimg") MultipartFile file, Principal principal,
				Model m , HttpSession session) {
			
			try {
				String name = principal.getName();
				User user = this.userRepository.getUserByUserName(name);
				if(file.isEmpty()) {
					System.out.println("file is empty");
					contact.setCimageurl("contact.png");
				}
				else {
					contact.setCimageurl(file.getOriginalFilename());
					File savefile = new ClassPathResource("static/assets/img").getFile();
					
					Path path = Paths.get(savefile.getAbsolutePath()+File.separator+file.getOriginalFilename());
					Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Image is uploaded ");
				}
				
				user.getContacts().add(contact);
				contact.setUser(user);
				this.userRepository.save(user);
				m.addAttribute("title", "Add Contact");
				session.setAttribute("message", new Message("alert-success","Successfully Added !! "));
               return "authuser/addcontact";

			} catch (Exception e) {
				e.printStackTrace();
				m.addAttribute("title", "Add Contact");
				m.addAttribute("contact",contact);
				session.setAttribute("message", new Message("alert-danger","Something went wrong !! " + e.getMessage()));

			}
			return "authuser/addcontact";
			
		}
	
}

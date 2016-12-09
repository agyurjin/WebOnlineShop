package edu.webonlineshop.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

import edu.webonlineshop.dal.entity.Account;
import edu.webonlineshop.dal.entity.User;
import edu.webonlineshop.dal.repository.AccountDAOImpl;
import edu.webonlineshop.dal.repository.UserDAOImpl;
import edu.webonlineshop.dal.repository.impl.AccountDAO;
import edu.webonlineshop.dal.repository.impl.UserDAO;
import edu.webonlineshop.configuration.AppConfiguration;
import edu.webonlineshop.entity.WebForm;
import edu.webonlineshop.entity.WebLogin;
import edu.webonlineshop.model.Messanger;

@Controller
public class RegistrationController {


	public RegistrationController() {
//		System.out.println("Start Registration!");
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public ModelAndView register(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("registration");
		return mav; 
	}

//	@Transactional
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute WebLogin weblogin) {
		ModelAndView mav = new ModelAndView();
		
		if(weblogin.getEmailaddress() != "" && weblogin.getPassword() != "") {
			ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
			UserDAO userDaoImpl = (UserDAO) context.getBean("userDAOImpl",UserDAOImpl.class);

			User user = userDaoImpl.searchByEmail(weblogin.getEmailaddress());
			
			if(user.geteMailAddress().equals("admin@admin") &&  user.getPassword().equals(weblogin.getPassword())) {
				mav.setViewName("redirect:/admin");
			}
			else if(user.geteMailAddress().equals(weblogin.getEmailaddress()) && user.getPassword().equals(weblogin.getPassword())) {
				String email = user.geteMailAddress();
				mav.addObject("email", email);
				mav.setViewName("redirect:/user");
			}
			else {
				Messanger model1 = new Messanger();
				model1.setMessage("ERROR: Email or password are incorrect!");
				mav.addObject("model",model1);
				mav.setViewName("message");
			}
		}
		else {
			mav.setViewName("registration");
			
		}
		return mav;
	}

	@RequestMapping(value="/form",method=RequestMethod.GET)
	public ModelAndView fillform(Model model) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}

	@RequestMapping(value="/form",method=RequestMethod.POST)
	public ModelAndView filledform(@ModelAttribute WebForm webform) {
		ModelAndView mav = new ModelAndView();
			
		if(webform.getBalance() != 0 && webform.getCardNumber() != 0 && webform.geteMailAddress() != "" &&
				webform.getPassword() != "" && webform.getFirstName() != "" && webform.getLastName() != "" && 
				webform.getLatitude() != 0 && webform.getLongitude() != 0) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
			UserDAO userDaoImpl = (UserDAO) context.getBean("userDAOImpl",UserDAOImpl.class);
			AccountDAO accountDaoImpl = (AccountDAO) context.getBean("accountDAOImpl", AccountDAOImpl.class);
			
			User user = new User();
			user.setFirstName(webform.getFirstName());
			user.setLastName(webform.getLastName());
			user.seteMailAddress(webform.geteMailAddress());
			user.setPassword(webform.getPassword());
			user.setLatitude(webform.getLatitude());
			user.setLongitude(webform.getLongitude());
			user.setUserID(-1);

			userDaoImpl.addUser(user);
			user = userDaoImpl.searchByEmail(webform.geteMailAddress());

			Account account = new Account();
			account.setUserID(user.getUserID());
			account.setAccountNumber(webform.getCardNumber());
			account.setBalance(webform.getBalance());
			
			accountDaoImpl.addAccount(account);
			
			mav.addObject("email", user.geteMailAddress());
			mav.setViewName("redirect:/user");
		}
		else {
			System.out.println("ERROR");
			Messanger model1 = new Messanger();
			model1.setMessage("ERROR: Please fill all fields!");
			mav.addObject("model",model1);
			mav.setViewName("message");
		}
		return mav;
	}
}
package edu.webonlineshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.webonlineshop.configuration.AppConfiguration;
import edu.webonlineshop.dal.entity.Account;
import edu.webonlineshop.dal.entity.Order;
import edu.webonlineshop.dal.entity.Product;
import edu.webonlineshop.dal.entity.Profile;
import edu.webonlineshop.dal.entity.User;
import edu.webonlineshop.dal.repository.AccountDAOImpl;
import edu.webonlineshop.dal.repository.OrderDAOImpl;
import edu.webonlineshop.dal.repository.ProductDAOImpl;
import edu.webonlineshop.dal.repository.UserDAOImpl;
import edu.webonlineshop.dal.repository.impl.AccountDAO;
import edu.webonlineshop.dal.repository.impl.OrderDAO;
import edu.webonlineshop.dal.repository.impl.ProductDAO;
import edu.webonlineshop.dal.repository.impl.UserDAO;
import edu.webonlineshop.entity.WebForm;
import edu.webonlineshop.entity.WebOrder;
import edu.webonlineshop.model.OrderList;
import edu.webonlineshop.model.Messanger;

@Controller
public class UserController {

	private Profile profile;
	
	Profile getProfile() {
		return profile;
	}
	public UserController() {
//		System.out.println("User Controller!");
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ModelAndView register(Model model, String email) {
		ModelAndView mav = new ModelAndView();

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		UserDAO userDaoImpl = (UserDAO) context.getBean("userDAOImpl",UserDAOImpl.class);
		AccountDAO accountDaoImpl = (AccountDAO) context.getBean("accountDAOImpl", AccountDAOImpl.class);

		if(email != null){
			User user = userDaoImpl.searchByEmail(email);
			Account account = accountDaoImpl.searchByID(user.getUserID());
		
			this.profile = new Profile(user, account);
		}
		
		Messanger model1 = new Messanger();
		model1.setMessage(profile.getFirstName());
		model.addAttribute("model", model1);
		mav.setViewName("user");
		return mav;
	}

	@RequestMapping(value="/user/purchase", method=RequestMethod.GET)
	public ModelAndView purchaseBegin(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("purchaseproduct");
		return mav; 
	}

	@RequestMapping(value="/user/purchase", method=RequestMethod.POST)
	public ModelAndView purchaseEnd(@ModelAttribute WebOrder weborder) {
		ModelAndView mav = new ModelAndView();
		if(weborder.getNumber() != 0 && weborder.getProductid() != 0) {
			Profile profile = getProfile();

			ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
			OrderDAO orderDaoImpl = (OrderDAO) context.getBean("orderDAOImpl",OrderDAOImpl.class);
			AccountDAO accountDaoImpl = (AccountDAO) context.getBean("accountDAOImpl", AccountDAOImpl.class);
			ProductDAO productDaoImpl = (ProductDAO) context.getBean("productDAOImpl",ProductDAOImpl.class);

			Product product = productDaoImpl.searchByID(weborder.getProductid());
			Account account = accountDaoImpl.searchByID(profile.getUserID());
			
			double newBalance = account.getBalance() - product.getPrice()*weborder.getNumber();
			int newQuantity = product.getQuantity() - weborder.getNumber();
			
			if(newBalance >= 0) {
				Order order = new Order();
				order.setOrderID(-1);
				order.setProductID(weborder.getProductid());
				order.setProductNumber(weborder.getNumber());
				order.setStatus("ordered");
				order.setUserID(profile.getUserID());
				
				orderDaoImpl.addOrder(order);
				accountDaoImpl.doPayment(profile.getUserID(), newBalance);
				productDaoImpl.reduceNumber(weborder.getProductid(), newQuantity);

				Messanger model1 = new Messanger();
				model1.setMessage("Your order was successfully!");
				mav.addObject("model",model1);
				double moneyPaied = product.getPrice()*weborder.getNumber();
				mav.addObject("var", moneyPaied);
				mav.setViewName("message");

			}
			else{
				Messanger model1 = new Messanger();
				model1.setMessage("Sorry you don't have enought money!");
				mav.addObject("model",model1);
				mav.setViewName("message");

			}
		}
		else {
			mav.setViewName("purchaseproduct");

		}
		return mav; 
	}

	@RequestMapping(value="/user/productlist", method=RequestMethod.GET)
	public ModelAndView productList(Model model) {
		ModelAndView mav = new ModelAndView();
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		ProductDAO productDaoImpl = (ProductDAO) context.getBean("productDAOImpl",ProductDAOImpl.class);

		List<Product> productlist = productDaoImpl.allProducts(); 
		mav.addObject("productlist", productlist);
		mav.setViewName("productlist");
		return mav; 
	}
	@RequestMapping(value="/user/history", method=RequestMethod.GET)
	public ModelAndView orderHistory(Model model) {
		ModelAndView mav = new ModelAndView();
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		OrderDAO orderDaoImpl = (OrderDAO) context.getBean("orderDAOImpl",OrderDAOImpl.class);
		ProductDAO productDaoImpl = (ProductDAO) context.getBean("productDAOImpl",ProductDAOImpl.class);

		Profile profile = getProfile();
		List<Order> orderlist = orderDaoImpl.searchByUserID(profile.getUserID());
		List<Product> productlist = new  ArrayList<Product>();
		
		if(orderlist.size() != 0){
			for(Order i : orderlist){
				productlist.add(productDaoImpl.searchByID(i.getProductID()));
			}
		}
		List<OrderList> modellist = new ArrayList<OrderList>();

		for(int i = 0; i < orderlist.size(); i++){
			OrderList orderlistmodel = new OrderList();
					
			orderlistmodel.setNumber(orderlist.get(i).getProductNumber());
			orderlistmodel.setStatus(orderlist.get(i).getStatus());
			orderlistmodel.setName(productlist.get(i).getName());
			modellist.add(orderlistmodel);
		}
		mav.addObject("modellist", modellist);
		mav.setViewName("orderhistory");
		return mav; 
	}

	@RequestMapping(value="/user/delete", method=RequestMethod.GET)
	public ModelAndView deleteProfile(Model model) {
		ModelAndView mav = new ModelAndView();

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		UserDAO userDaoImpl = (UserDAO) context.getBean("userDAOImpl",UserDAOImpl.class);
		AccountDAO accountDaoImpl = (AccountDAO) context.getBean("accountDAOImpl", AccountDAOImpl.class);
		OrderDAO orderDaoImpl = (OrderDAO) context.getBean("orderDAOImpl",OrderDAOImpl.class);

		Profile profile = getProfile();
		
		userDaoImpl.deleteUser(profile.getUserID());
		accountDaoImpl.deleteAccount(profile.getUserID());
		orderDaoImpl.deleteOrder(profile.getUserID());

		mav.setViewName("deleteprofile");
		return mav; 
	}
}
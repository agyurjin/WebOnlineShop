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
import edu.webonlineshop.dal.entity.Order;
import edu.webonlineshop.dal.entity.Point;
import edu.webonlineshop.dal.entity.Product;
import edu.webonlineshop.dal.entity.User;
import edu.webonlineshop.dal.repository.OrderDAOImpl;
import edu.webonlineshop.dal.repository.ProductDAOImpl;
import edu.webonlineshop.dal.repository.UserDAOImpl;
import edu.webonlineshop.dal.repository.impl.OrderDAO;
import edu.webonlineshop.dal.repository.impl.ProductDAO;
import edu.webonlineshop.dal.repository.impl.UserDAO;
import edu.webonlineshop.entity.WebProduct;
import edu.webonlineshop.optimize.Optimize;

@Controller
public class AdminController {

	public AdminController() {
//		System.out.println("Admin Controller!");
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public ModelAndView register(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin");
		return mav; 
	}

	@RequestMapping(value="/admin/deliver", method=RequestMethod.GET)
	public ModelAndView startDeliver() {
		ModelAndView mav = new ModelAndView();
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		OrderDAO orderDaoImpl = (OrderDAO) context.getBean("orderDAOImpl",OrderDAOImpl.class);
		UserDAO userDaoImpl = (UserDAO) context.getBean("userDAOImpl",UserDAOImpl.class);

		List<Order> orderlist = orderDaoImpl.searchByStatus("ordered");
		List<User> userlist = new ArrayList<User>();
		List<Point> pointList = new ArrayList<Point>();
		
		for(Order i : orderlist) {
			userlist.add(userDaoImpl.searchById(i.getUserID()));
		}
		
		for(int i =0; i < orderlist.size(); i++) {
			Point point =  new Point();
			point.setId(orderlist.get(i).getOrderID());
			point.setLatitude(userlist.get(i).getLatitude());
			point.setLongitude(userlist.get(i).getLongitude());
			pointList.add(point);
		}
		
		Optimize optimize = new Optimize(pointList, orderDaoImpl);
		List<Point> deliveredList = optimize.optimiztaion();
		mav.addObject("deliveredList", deliveredList);
		mav.setViewName("deliver");
		return mav;
	}

	@RequestMapping(value="/admin/orderlist", method=RequestMethod.GET)
	public ModelAndView orderList() {
		ModelAndView mav = new ModelAndView();

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		OrderDAO orderDaoImpl = (OrderDAO) context.getBean("orderDAOImpl",OrderDAOImpl.class);
		List<Order> orderlist = orderDaoImpl.searchByStatus("ordered");
		
		mav.addObject("orderlist", orderlist);
		mav.setViewName("orderlist");
		return mav;
	}

	@RequestMapping(value="/admin/addproduct", method=RequestMethod.GET)
	public ModelAndView addProductstart() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("addproduct");
		return mav;
	}

	@RequestMapping(value="/admin/addproduct", method=RequestMethod.POST)
	public ModelAndView addProductstop(@ModelAttribute WebProduct webproduct) {
		ModelAndView mav = new ModelAndView();
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		ProductDAO productDaoImpl = (ProductDAO) context.getBean("productDAOImpl",ProductDAOImpl.class);
		
		Product product = new Product();
		product.setName(webproduct.getProductName());
		product.setPrice(webproduct.getPrice());
		product.setQuantity(webproduct.getQuantity());
		product.setProductID(-1);
		
		productDaoImpl.addProduct(product);
		mav.setViewName("addproduct");
		return mav;
	}

}

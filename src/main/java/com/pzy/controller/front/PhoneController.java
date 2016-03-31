package com.pzy.controller.front;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pzy.entity.Category;
import com.pzy.entity.Item;
import com.pzy.entity.Order;
import com.pzy.entity.Project;
import com.pzy.entity.User;
import com.pzy.service.CategoryService;
import com.pzy.service.OrderService;
import com.pzy.service.ProjectService;
import com.pzy.service.SellerService;
import com.pzy.service.UserService;
/***
 * 前台，首页各种连接登陆等
 * @author qq:263608237
 *
 */
@Controller
@RequestMapping("/phone")
public class PhoneController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SellerService sellerService;
	@InitBinder  
	protected void initBinder(HttpServletRequest request,   ServletRequestDataBinder binder) throws Exception {   
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true)); 
	}  
	/***
	 * 跳转到首页
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("likes",sellerService.findTop());
		return "phone/index";
	}
	@RequestMapping("about")
	public String about(Model model) {
		return "phone/about";
	}
	@RequestMapping("category")
	public String category(Model model,Long id) {
		model.addAttribute("sellers",sellerService.findByCategory(id));
		return "phone/category";
	}
	
	@RequestMapping("seller")
	public String seller(Model model,Long id) {
		model.addAttribute("seller",sellerService.find(id));
		model.addAttribute("projects",projectService.findBySeller(id));
		return "phone/seller";
	}
	/***
	 * 我的订单
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("myorder")
	public String myorder(Model model,HttpSession httpSession) {
		User user=(User) httpSession.getAttribute("user");
		model.addAttribute("ordertodo",orderService.findByUser(user, null));
		model.addAttribute("orderdone",orderService.findByUser(user, "已结束"));
		return "phone/myorder";
	}
	@RequestMapping("order/{pid}")
	public String order(Model model,@PathVariable Long pid) {
		model.addAttribute("project",projectService.find(pid));
		return "phone/order";
	}
	@RequestMapping("docart")
	public String docart(Model model,HttpSession httpSession) {
		model.addAttribute("order",(Order)httpSession.getAttribute("order"));
		return "phone/docart";
	}
	@RequestMapping("sumbitcart")
	public String sumbitcart(Model model,HttpSession httpSession) {
		Order order=(Order)httpSession.getAttribute("order");
		orderService.save(order);
		httpSession.removeAttribute("order");
		model.addAttribute("tip","提交成功，我们正在拼命为您制作配送！");
		User user=(User) httpSession.getAttribute("user");
		model.addAttribute("ordertodo",orderService.findByUser(user, null));
		model.addAttribute("orderdone",orderService.findByUser(user, "已结束"));
		return "phone/myorder";
	}
	/***
	 * 加入购物车
	 * 
	 * @param model
	 * @param pid
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("cart")
	@ResponseBody
	public Map<String, Object> cart(Model model, Long pid,HttpSession httpSession) {
		Map map=new HashMap<String, Object>();
		User user=(User)httpSession.getAttribute("user");
		if(user==null){
			map.put("tip", "请登录");
			return map;
		}
		Order order=(Order)httpSession.getAttribute("order");
		if(order==null){
			order=new Order();
			order.setAddr(user.getAddress());
			order.setCreateDate(new Date());
			order.setOrderDate(new Date());
			order.setState("已提交");
			order.setToalprice(0d);
			order.setUser(user);
			order.setSendprice(0d);
		}
		Project project=projectService.find(pid);
		order.addPorject(project);
		order.sets
		httpSession.setAttribute("order", order);
		map.put("tip", "加入成功！");
		return map;
	}
	
	@RequestMapping("doorder/{pid}")
	public String doorder(HttpSession httpSession,Model model,@PathVariable Long pid,String year,String month,String day,String remark,String addr,String tel) throws ParseException {
		String daystr=year+"-"+month+"-"+day;
		Date date=DateUtils.parseDate(daystr, "yyyy-MM-dd");
		User user=(User) httpSession.getAttribute("user");
		
		Order order=new Order();
		order.setAddr(addr);
		order.setCreateDate(new Date());
		order.setOrderDate(date);
		
		
		Project bean=projectService.find(pid);
		order.setCreateDate(new Date());
		order.setState("待审核");
		order.setUser(user);
		
		order.setToalprice(bean.getPrice());
		orderService.save(order);
		model.addAttribute("project",projectService.find(pid));
		model.addAttribute("tip","预订成功!");
		return "phone/myorder";
	}
	@RequestMapping("center")
	public String center(Model model,HttpSession httpSession) {
		User user=(User) httpSession.getAttribute("user");
		if(user==null){
			model.addAttribute("tip","快来加入我们把，首次加入送5元现金红包!");
    		return "phone/register";
		}else{
			return "phone/center";
		}
		
	}
	
	@RequestMapping("store")
	public String store(Model model,HttpSession httpSession,Long id) {
		return "phone/store";
	}
	@RequestMapping("loginout")
	public String loginout(HttpSession httpSession,Model model) {
		httpSession.removeAttribute("user");
		model.addAttribute("tip","已注销！");
		return "phone/login";
	}
	
	/***
	 * 点击注册
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("doregister")
	public String register(User user,Model model) {
		user.setCreateDate(new Date());
		model.addAttribute("tip","注册成功请登录！");
		userService.save(user);
		return "phone/login";
	}
	@RequestMapping("login")
	public String login(Model model) {
		return "phone/login";
	}
	@RequestMapping("register")
	public String register(Model model) {
		return "phone/register";
	}
	@RequestMapping("dologin")
	public String dologin(User user,HttpSession httpSession,Model model) {
		User loginuser=userService.login(user.getUsername(), user.getPassword());
    	if(loginuser!=null){
    		httpSession.setAttribute("user", loginuser);
    		model.addAttribute("user",loginuser);
            return "phone/center"; 
    	}
    	else{
    		httpSession.removeAttribute("user");
    		model.addAttribute("tip","登陆失败 不存在此用户名或密码!");
    		return "phone/login";
    	}
	}
	
	/*@RequestMapping("myorder")
	public String myorder(HttpSession httpSession,Model model) {
		User user=(User)httpSession.getAttribute("user");
		model.addAttribute("orders",orderService.findByUser(user));
		return "phone/myorder";
	}*/
}


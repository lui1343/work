package web.servlet.client;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import domain.Record;
import domain.Product;
import domain.User;
import exception.FindProductByIdException;
import exception.ListProductException;
import service.ProductService;
import service.RecordService;
import service.UserService;
import utils.IdUtils;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取登录页面输入的用户名与密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 2.调用service完成登录操作。
		UserService service = new UserService();
		try {
			User user = service.login(username, password);
			
			// 3.登录成功，将用户存储到session中.
			request.getSession().setAttribute("user", user);
			

			Record Record = new Record();
			try {
				BeanUtils.populate(Record, request.getParameterMap());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			Record.setId(IdUtils.getUUID());// 封装订单id
			Record.setUser(user);// 封装用户信息到订单.
			Record.setPid("0");
			System.out.println(Record);
			// 4.调用service中添加订单操作.
			RecordService rservice = new RecordService();
			rservice.addRecord(Record);
			
			
			List<Record> rs = rservice.findRecordByUser2(user);
			ProductService pservice = new ProductService();
			List<Product> fps = pservice.listAll();
			for (Record x : rs) { 
					Product product = pservice.findProductById(x.getPid());
					fps.add(product);
	        }
			Collections.shuffle(fps);
			request.getSession().setAttribute("fps", fps);
			
			// 获取用户的角色，其中用户的角色分普通用户和超级用户两种
			String role = user.getRole();
			// 如果是超级用户，就进入到网上书城的后台管理系统；否则进入我的账户页面
			if ("超级用户".equals(role)) {
				response.sendRedirect(request.getContextPath() + "/listProduct");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/client/myAccount.jsp");
				return;
			}
		} catch (LoginException e) {
			// 如果出现问题，将错误信息存储到request范围，并跳转回登录页面显示错误信息
			e.printStackTrace();
			request.setAttribute("register_message", e.getMessage());
			request.getRequestDispatcher("/client/account.jsp").forward(request, response);
			return;
		} catch (ListProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FindProductByIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

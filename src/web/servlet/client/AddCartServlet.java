package web.servlet.client;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import domain.Product;
import domain.Record;
import domain.User;
import exception.FindProductByIdException;
import service.ProductService;
import service.RecordService;
import utils.IdUtils;

public class AddCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.得到商品id
		String id = request.getParameter("id");
		// 2.调用service层方法，根据id查找商品
		ProductService service = new ProductService();
		try {
			Product p = service.findProductById(id);
			//3.将商品添加到购物车
			//3.1获得session对象
			HttpSession session = request.getSession();
			
			User user = (User) session.getAttribute("user");
			Record Record = new Record();
			try {
				BeanUtils.populate(Record, request.getParameterMap());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			Record.setId(IdUtils.getUUID());
			Record.setUser(user);
			Record.setPid(id);
			System.out.println(Record);
			RecordService rservice = new RecordService();
			rservice.addRecord(Record);
			
			//3.2从session中获取购物车对象
			Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
			//3.3如果购物车为null,说明没有商品存储在购物车中，创建出购物车
			if (cart == null) {
				cart = new HashMap<Product, Integer>();
			}
			//3.4向购物车中添加商品
			Integer count = cart.put(p, 1);
			//3.5如果商品数量不为空，则商品数量+1，否则添加新的商品信息
			if (count != null) {
				cart.put(p, count + 1);
			}			
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}
}

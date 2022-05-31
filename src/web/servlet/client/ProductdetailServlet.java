package web.servlet.client;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

public class ProductdetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.得到商品id
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		
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
		
		// 2.调用service层方法，根据id查找商品
		ProductService service = new ProductService();
		try {
			Product p = service.findProductById(id);
			
			session.setAttribute("p", p);
			if (type == null) {
				response.sendRedirect(request.getContextPath() + "/client/product-detail.jsp");
				return;
			}
			
			request.getRequestDispatcher("/admin/products/edit.jsp").forward(request,response);
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}
}

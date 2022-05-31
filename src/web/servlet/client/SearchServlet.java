package web.servlet.client;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.PageBean;
import domain.Product;
import service.ProductService;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取前台页面搜索框输入的值
		String searchfield = req.getParameter("textfield");
		//如果搜索框中没有输入值，则表单传递的为默认值，此时默认查询全部商品目录
		if("搜索".equals(searchfield)){
			req.getRequestDispatcher("/products").forward(req, resp);
			return;
		}
		//调用service层的方法，通过书名模糊查询，查找相应的图书
		ProductService service = new ProductService();
		List<Product> ps = service.findBookByName(searchfield);
		
		// 将数据存储到request范围，跳转到products.jsp页面展示
		req.setAttribute("ps", ps);
		req.getRequestDispatcher("/client/products.jsp").forward(req, resp);
	}
}

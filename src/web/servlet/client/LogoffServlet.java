package web.servlet.client;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import domain.User;
import exception.RegisterException;
import service.UserService;

public class LogoffServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取session对象.
		HttpSession session = request.getSession();
		// 获取用户名
		User user = (User) request.getSession().getAttribute("user");
		// 调用service完成注册操作。
		UserService service = new UserService();
		try {
			service.logoff(user);
		} catch (RegisterException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
			return;
		}
		// 销毁session
		session.invalidate();
		// flag标识
		String flag = request.getParameter("flag");
		if (flag == null || flag.trim().isEmpty()) {
			// 重定向到首页
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}
}
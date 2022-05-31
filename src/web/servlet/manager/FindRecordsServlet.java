package web.servlet.manager;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import domain.Record;
import domain.User;
import service.RecordService;
import utils.IdUtils;
//查找所有订单
public class FindRecordsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		System.out.println(Record);
		RecordService rservice = new RecordService();
		rservice.addRecord(Record);
		
		// 创建Service层对象
		RecordService service = new RecordService();
		// 调用Service层对象的findAllOrder()方法查询订单列表
		List<Record> records = service.findAllRecord();
		//将查询到的订单信息添加到request作用域
		request.setAttribute("records", records);
		// 将请求转发到list.jsp页面
		request.getRequestDispatcher("/admin/records/list.jsp").forward(request,response);
	}
}

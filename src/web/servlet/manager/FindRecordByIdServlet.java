package web.servlet.manager;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
/**
 * 通过id查询记录
 * @author admin
 *
 */
public class FindRecordByIdServlet extends HttpServlet {
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
		
		//1.获取用户类型
		String type=request.getParameter("type");		
		//2.得到要查询的记录的id
		String id = request.getParameter("id");
		//3.根据id查找记录
		RecordService service = new RecordService();
		Record record = service.findRecordById(id);
        //4.将查询出的记录信息添加到request作用域中
		request.setAttribute("record", record);	
		request.getRequestDispatcher("/admin/records/view.jsp").forward(request, response);
		return;		
	}

}

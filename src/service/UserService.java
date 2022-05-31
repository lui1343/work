package service;
import java.sql.SQLException;
import javax.security.auth.login.LoginException;
import dao.UserDao;
import domain.User;
import exception.RegisterException;
//import utils.MailUtils;

public class UserService {
	private UserDao dao = new UserDao();
	// 注册操作
	public void register(User user) throws RegisterException {
		// 调用dao完成注册操作
		try {
			dao.addUser(user);
			// 发送激活邮件
//			String emailMsg = "感谢您注册bilibili商城";
//			MailUtils.sendMail(user.getEmail(), emailMsg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("注冊失败");
		}
	}
	
	public void logoff(User user) throws RegisterException {
		// 调用dao完成注册操作
		try {
			dao.delUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("注冊失败");
		}
	}

	// 登录操作
	public User login(String username, String password) throws LoginException {
		try {
			//根据登录时表单输入的用户名和密码，查找用户
			User user = dao.findUserByUsernameAndPassword(username, password);
//			if (user != null) {
//				return user;
//			}
			if (user == null) {
				throw new LoginException("用户名或密码错误!!!");
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("登录失败");
		}
	}
}

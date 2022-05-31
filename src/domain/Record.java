package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Record {

	private String id; // 记录编号
	private Date recordtime; // 下单时间
    private String pid="0"; //商品id
	private User user; // 记录所属用户

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(Date recordtime) {
		this.recordtime = recordtime;
	}
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", recordtime=" + recordtime + "pid=" + pid + ", user=" + user + "]";
	}

}

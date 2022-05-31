package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import domain.Record;
import domain.User;
import utils.DataSourceUtils;
/**
 * 记录
 * @author admin
 *
 */
public class RecordDao {
	/**
	 *  生成记录
	 * @param Record
	 * @throws SQLException
	 */
	public void addProduct(Record Record) throws SQLException {
		// 1.生成Sql语句
		String sql = "insert into records(id,pid,user_id) values(?,?,?)";
		// 2.生成执行sql语句的QueryRunner,不传递参数
		QueryRunner runner = new QueryRunner();
        // 3.执行update()方法插入数据
		runner.update(DataSourceUtils.getConnection(), sql, Record.getId(),Record.getPid(), Record.getUser().getId());
	}
	/**
	 * 查找用户所属记录
	 */
	public List<Record> findRecordByUser(final User user) throws SQLException {
		String sql = "select * from records where user_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<List<Record>>() {
			public List<Record> handle(ResultSet rs) throws SQLException {
				List<Record> records = new ArrayList<Record>();
				while (rs.next()) {
					Record Record = new Record();
					Record.setId(rs.getString("id"));
					Record.setPid(rs.getString("records.pid"));
					Record.setRecordtime(rs.getDate("recordtime"));
					Record.setUser(user);
					records.add(Record);
				}
				return records;
			}
		}, user.getId());
	}
	
	public List<Record> findRecordByUser2(final User user) throws SQLException {
		String sql = "select * from records where user_id=? and pid != 0";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<List<Record>>() {
			public List<Record> handle(ResultSet rs) throws SQLException {
				List<Record> records = new ArrayList<Record>();
				while (rs.next()) {
					Record Record = new Record();
					Record.setId(rs.getString("id"));
					Record.setPid(rs.getString("records.pid"));
					Record.setRecordtime(rs.getDate("recordtime"));
					Record.setUser(user);
					records.add(Record);
				}
				return records;
			}
		}, user.getId());
	}
	
	/**
	 * 根据id查找记录信息
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Record findRecordById(String id) throws SQLException {
		String sql = "select * from records,user where records.user_id=user.id and records.id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<Record>() {
			public Record handle(ResultSet rs) throws SQLException {
				Record Record = new Record();
				while (rs.next()) {
					Record.setId(rs.getString("records.id"));
					Record.setPid(rs.getString("records.pid"));
					Record.setRecordtime(rs.getDate("records.recordtime"));

					User user = new User();
					user.setId(rs.getInt("user.id"));
					user.setEmail(rs.getString("user.email"));
					user.setPassword(rs.getString("user.password"));
					user.setRegistTime(rs.getDate("user.registtime"));
					user.setRole(rs.getString("user.role"));
					user.setUsername(rs.getString("user.username"));
					Record.setUser(user);
				}
				return Record;
			}
		}, id);
	}
	/**
	 *  查找所有记录
	 * @return
	 * @throws SQLException
	 */
	public List<Record> findAllRecord() throws SQLException {
		//1.创建sql
		String sql = "select records.*,user.* from records,user where user.id=records.user_id order by records.user_id";
		//2.创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        //3.返回QueryRunner对象query()方法的查询结果
		return runner.query(sql, new ResultSetHandler<List<Record>>() {			
			public List<Record> handle(ResultSet rs) throws SQLException {
				//创建记录集合
				List<Record> records = new ArrayList<Record>();
                //循环遍历记录和用户信息
				while (rs.next()) {
					Record Record = new Record();
					Record.setId(rs.getString("records.id"));
					Record.setRecordtime(rs.getDate("records.recordtime"));
					Record.setPid(rs.getString("records.pid"));
					records.add(Record);

					User user = new User();
					user.setId(rs.getInt("user.id"));
					user.setEmail(rs.getString("user.email"));
					user.setPassword(rs.getString("user.password"));
					user.setRegistTime(rs.getDate("user.registtime"));
					user.setRole(rs.getString("user.role"));
					user.setUsername(rs.getString("user.username"));
					Record.setUser(user);
				}
				return records;
			}
		});
	}
	/**
	 *  多条件查询
	 * @param id
	 * @param receiverName
	 * @return
	 * @throws SQLException
	 */
	public List<Record> findRecordByManyCondition(String id, String receiverName)
			throws SQLException {
		//1.创建集合对象
		List<Object> objs = new ArrayList<Object>();
		//2.定义查询sql
		String sql = "select records.*,user.* from records,user where user.id=records.user_id ";
		//3.根据参数拼接sql语句
		if (id != null && id.trim().length() > 0) {
			sql += " and records.id=?";
			objs.add(id);
		}
		if (receiverName != null && receiverName.trim().length() > 0) {
			sql += " and receiverName=?";
			objs.add(receiverName);
		}
		sql += " Record by records.user_id";
		//4.创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//5.返回QueryRunner对象query方法的执行结果
		return runner.query(sql, new ResultSetHandler<List<Record>>() {
			public List<Record> handle(ResultSet rs) throws SQLException {
				List<Record> records = new ArrayList<Record>();
               //循环遍历出记录和用户信息
				while (rs.next()) {
					Record Record = new Record();
					Record.setId(rs.getString("records.id"));
					Record.setPid(rs.getString("records.pid"));
					Record.setRecordtime(rs.getDate("records.recordtime"));
					records.add(Record);
					User user = new User();
					user.setId(rs.getInt("user.id"));
					user.setEmail(rs.getString("user.email"));
					user.setPassword(rs.getString("user.password"));
					user.setRegistTime(rs.getDate("user.registtime"));
					user.setRole(rs.getString("user.role"));
					user.setUsername(rs.getString("user.username"));
					Record.setUser(user); 

				}

				return records;
			}
		}, objs.toArray());
	}
	/**
	 * 根据id删除记录
	 * @param id
	 * @throws SQLException
	 */
	public void delRecordById(String id) throws SQLException {
		String sql="delete from records where id=?";		
		QueryRunner runner = new QueryRunner();		
		runner.update(DataSourceUtils.getConnection(),sql,id);		
	}
}

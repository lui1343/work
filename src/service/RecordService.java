package service;
import java.sql.SQLException;
import java.util.List;
import dao.RecordDao;
import dao.ProductDao;
import domain.Record;
import domain.User;
import utils.DataSourceUtils;
public class RecordService {
	private RecordDao rdao = new RecordDao();
	private ProductDao pdao = new ProductDao();
	// 1.添加记录
	public void addRecord(Record Record) {
		try {
			// 1.开启事务
			DataSourceUtils.startTransaction();
			// 2.完成操作
			// 2.1向Records表中添加数据
			rdao.addProduct(Record);

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback(); // 事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				// 关闭，释放以及提交事务
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 根据用户查找记录
	public List<Record> findRecordByUser(User user) {
		List<Record> Records = null;
		try {
			// 查找出记录信息
			Records = rdao.findRecordByUser(user);

			// // 查找出记录项信息.
			// for (Record Record : Records) {
			// List<RecordItem> items = oidao.findRecordItemByRecord(Record);
			// //查找到记录中的记录项信息
			//
			// Record.setRecordItems(items);
			// }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Records;
	}
	
	public List<Record> findRecordByUser2(User user) {
		List<Record> Records = null;
		try {
			// 查找出记录信息
			Records = rdao.findRecordByUser2(user);

			// // 查找出记录项信息.
			// for (Record Record : Records) {
			// List<RecordItem> items = oidao.findRecordItemByRecord(Record);
			// //查找到记录中的记录项信息
			//
			// Record.setRecordItems(items);
			// }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Records;
	}
	
	// 根据id查找记录
	public Record findRecordById(String id) {
		Record Record = null;
		try {
			Record = rdao.findRecordById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Record;
	}
	// 查找所有记录
	public List<Record> findAllRecord() {
		List<Record> Records = null;
		try {
			// 查找出记录信息
			Records = rdao.findAllRecord();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Records;
	}

	// 多条件查询记录信息
	public List<Record> findRecordByManyCondition(String id, String receiverName) {
		List<Record> Records = null;
		try {
			Records = rdao.findRecordByManyCondition(id, receiverName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Records;
	}
	//根据id删除记录 管理员删除记录
	public void delRecordById(String id) {			
		try {
			DataSourceUtils.startTransaction();//开启事务
			rdao.delRecordById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	//普通用户删除记录
	public void delRecordByIdWithClient(String id) {
		try {
			DataSourceUtils.startTransaction();//开启事务
			//从记录项中查找商品购买数量
			Record Record=new Record();
			Record.setId(id);
			rdao.delRecordById(id); //删除记录
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
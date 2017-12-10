package utils.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.jdbc.Page;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class JDBCUtils {
	/**
	 * 释放连接
	 * @param connection
	 */
	public static void releaseConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static DataSource dataSource = null;

	static {
		// dataSource资源只能初始化一次
		dataSource = new ComboPooledDataSource("mvcApp");
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 初始化page，通过传入的数据库名称初始化page中的数据总条数
	 * @param sqlname
	 * @param pageNo
	 * @return
	 */
	private static Page initPage(String sqlname,int pageNo) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Page page = new Page<>(pageNo);
		Long totalItemNumber = null;
		try {
			totalItemNumber = (Long) qr.query("select count(*) from " + sqlname, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setTotalItemNumber(totalItemNumber);
		return page;
	}
	
	/**
	 * 分页查询，需传入数据库表名,当前查询页码及对应的实体的class
	 * （注：实体中的属性名称需要与数据库中字段名称一致）
	 * 如要带条件查询可将查询跟在数据库表名内
	 * 例如：查询性别为男，可将sqlname的穿入值写作   "数据库表名 where 性别='男'"
	 * @param sqlname
	 * @param pageNo
	 * @param class1
	 * @return
	 */
	public static Page select(String sqlname,int pageNo,Class class1){
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Page page = initPage(sqlname, pageNo);
		List list = null;
		try {
			int start = (page.getPageNo()-1) * page.getPageSize();
			int end = start + page.getPageSize();
			if(end > page.getTotalItemNumber()) {
				end = page.getTotalPageNumber();
			}
			list = qr.query("SELECT * from "+ sqlname + " LIMIT "+ start +"," + end ,  new BeanListHandler<>(class1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setList(list);
		return page;
	}
	
}
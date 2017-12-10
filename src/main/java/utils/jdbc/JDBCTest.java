package utils.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import utils.entity.Employee;

public class JDBCTest {
	public static void main(String[] args) {
		new JDBCTest().test1();
	}
	
	// 需求：向user表插入一条数据
	public void test1() {
		// 第一步：创建queryRunner对象，用来操作sql语句
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		// 第二步：创建sql语句
		String sql = "insert into employee values(null,?,?)";
		// 第三步：执行sql语句,params:是sql语句的参数
		// 注意，给sql语句设置参数的时候，按照user表中字段的顺序
		try {
			int update = qr.update(sql, "狗蛋1", "123456");
			System.out.println(update);
			update = qr.update(sql, "狗蛋2", "123456");
			System.out.println(update);
			update = qr.update(sql, "狗蛋3", "123456");
			System.out.println(update);
			update = qr.update(sql, "狗蛋4", "123456");
			System.out.println(update);
			update = qr.update(sql, "狗蛋5", "123456");
			System.out.println(update);
			update = qr.update(sql, "狗蛋6", "123456");
			System.out.println(update);
			update = qr.update(sql, "狗蛋7", "123456");
			System.out.println(update);
			update = qr.update(sql, "狗蛋8", "123456");
			System.out.println(update);
			update = qr.update(sql, "狗蛋9", "123456");
			System.out.println(update);
			update = qr.update(sql, "狗蛋10", "123456");
			System.out.println(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 需求：修改id==7的数据
	public void test2() {
		// 第一步：创建queryRunner对象，用来操作sql语句
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		// 第二步：创建sql语句
		String sql = "update user set emp_name = ? where emp_id = 4";

		// 第三步：执行sql语句,params:是sql语句的参数
		// 注意，给sql语句设置参数的时候，按照user表中字段的顺序
		try {
			int update = qr.update(sql, "柳岩", 1);
			System.out.println(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 需求：删除id==7的数据
	public void test3() {
		// 第一步：创建queryRunner对象，用来操作sql语句
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		// 第二步：创建sql语句
		String sql = "delete from employee where emp_id = ?";
		// 第三步：执行sql语句,params:是sql语句的参数
		// 注意，给sql语句设置参数的时候，按照user表中字段的顺序
		try {
			int update = qr.update(sql, 4);
			System.out.println(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void test4() {
		try {
			Connection con = JDBCUtils.getConnection(); // 声明Connection对象
			PreparedStatement sql = con.prepareStatement("select * from employee"); // 声明PreparedStatement对象
			ResultSet res = sql.executeQuery(); // 声明ResultSet对象
			while(res.next()) {
				System.out.println(res.getString(1) + "---" + res.getString(2) + "---" + res.getString(3));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test05() {
		Page<Employee> page = JDBCUtils.select("employee where emp_password='123456'", 1, Employee.class);
		for(Employee employee : page.getList()) {
			System.out.println(employee);
		}
		
 	}
	
	
}

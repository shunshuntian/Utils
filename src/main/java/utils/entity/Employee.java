package utils.entity;

public class Employee {
	private int emp_id;
	private String emp_name;
	private String emp_password;
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	public Employee(int emp_id, String emp_name, String emp_password) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.emp_password = emp_password;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_password=" + emp_password + "]";
	}
	
}

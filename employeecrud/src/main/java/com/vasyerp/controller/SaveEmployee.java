package com.vasyerp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vasyerp.model.Employee;

@WebServlet("/SaveEmployee")
public class SaveEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveEmployee() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String flag=request.getParameter("flag");

		if(flag.equals("AddEmployee")) {
			String employeeName=request.getParameter("employeeName");
			String empSalary=request.getParameter("employeeSalary");
			long employeeSalary=Long.parseLong(empSalary);

			saveEmployee(employeeName, employeeSalary, request, response);
		}
		
		else if (flag.equals("ViewEmployee")) {
			getAllEmployee(request, response);
		} 
		else if (flag.equals("UpdateEmployee")) {
			String employeeid = request.getParameter("employeeId");
			long employeeId=Long.parseLong(employeeid);
			getRecordByEmployeeId(employeeId, request, response);
		}
		else if (flag.equals("DeleteEmployee")) {
			String employeeid = request.getParameter("employeeId");
			long employeeId=Long.parseLong(employeeid);
			delete(employeeId, request, response);
			getAllEmployee(request, response);
		}
		else if (flag.equals("Update")) {
			String employeeid = request.getParameter("employeeId");
			long empId=Long.parseLong(employeeid);			
			String employeeName=request.getParameter("employeeName");
			String empSal=request.getParameter("employeeSalary");
			long employeeSalary=Long.parseLong(empSal);
		updateEmployee(empId,employeeName,employeeSalary, request, response);
		getAllEmployee(request, response);
		} 
		else if (flag.equals("HomePage")) {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}

	}
	public Connection getConnection() {
		Connection con = null;

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employeedb", "postgres", "postgres");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	private void saveEmployee(String employeeName,long employeeSalary,HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con=getConnection();
			String sql="insert into employee(employee_name,employee_salary) values(?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,employeeName);
			ps.setLong(2, employeeSalary);
			int rowCount=ps.executeUpdate();

			getAllEmployee(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void getAllEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Employee> employeelist = new ArrayList<Employee>();
		HttpSession session = request.getSession();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from employee");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Employee employee=new Employee();
				employee.setEmployeeId(rs.getLong(1));
				employee.setEmployeeName(rs.getString(2));
				employee.setEmployeeSalary(rs.getLong(3));
				employeelist.add(employee);
			}
			session.setAttribute("employeelist", employeelist);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("ViewEmployee.jsp").include(request, response);
	}

	private void delete(long employeeId, HttpServletRequest request, HttpServletResponse response) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("delete from employee where employee_id=?");
			ps.setLong(1, employeeId);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void getRecordByEmployeeId(long employeeId, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from employee where employee_id=?");
			ps.setLong(1, employeeId);
			ResultSet rs = ps.executeQuery();
			Employee employee=new Employee();
			if (rs.next()) {
				employee.setEmployeeId(rs.getLong(1));
				employee.setEmployeeName(rs.getString(2));
				employee.setEmployeeSalary(rs.getLong(3));
			}
			session.setAttribute("employee", employee);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		request.getRequestDispatcher("EditEmployee.jsp").include(request, response);
	}

	private void updateEmployee(long employeeId,String employeeName,long employeeSalary, HttpServletRequest request, HttpServletResponse response) {
		int status = 0;

		try {
			Connection con = getConnection();
			String sql="update employee set employee_name=?,employee_salary=? where employee_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,employeeName);
			ps.setLong(2, employeeSalary);
			ps.setLong(3,employeeId);
			ResultSet rs;
			status = ps.executeUpdate();
			con.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}

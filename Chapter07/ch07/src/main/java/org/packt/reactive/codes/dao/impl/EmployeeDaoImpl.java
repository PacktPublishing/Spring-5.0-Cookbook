package org.packt.reactive.codes.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.packt.reactive.codes.dao.EmployeeDao;
import org.packt.reactive.codes.model.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SimpleJdbcInsert jdbcInsert;

	
	@Override
	public List<Employee> getEmployees() {
		String sql = "SELECT * FROM employee";
		List<Employee> employees = jdbcInsert.getJdbcTemplate().query(sql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee emp = new Employee();

				emp.setId(rs.getInt("id"));
				emp.setEmpId(rs.getInt("empId"));
				emp.setFirstName(rs.getString("firstName"));
				emp.setLastName(rs.getString("lastName"));
				emp.setBirthday(rs.getDate("birthday"));
				emp.setAge(rs.getInt("age"));
				emp.setEmail(rs.getString("email"));
				emp.setDeptId(rs.getInt("deptId"));

				return emp;
			}
		});
		return employees;
	
	}

	@Override
	public List<Employee> getEmployeeDept(Integer deptId) {
		String sql = "SELECT * FROM employee WHERE deptId=?";
		List<Employee> employees = jdbcInsert.getJdbcTemplate().query(sql, new Object[]{ deptId}, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee emp = new Employee();

				emp.setId(rs.getInt("id"));
				emp.setEmpId(rs.getInt("empId"));
				emp.setFirstName(rs.getString("firstName"));
				emp.setLastName(rs.getString("lastName"));
				emp.setBirthday(rs.getDate("birthday"));
				emp.setAge(rs.getInt("age"));
				emp.setEmail(rs.getString("email"));
				emp.setDeptId(rs.getInt("deptId"));

				return emp;
			}
		});
		return employees;
	}

	@Override
	public Employee getEmployee(Integer id) {
		String sql = "SELECT * FROM employee WHERE id = ?";
		return jdbcInsert.getJdbcTemplate().queryForObject(sql, new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

				Employee emp = new Employee();

				emp.setId(rs.getInt("id"));
				emp.setEmpId(rs.getInt("empId"));
				emp.setFirstName(rs.getString("firstName"));
				emp.setLastName(rs.getString("lastName"));
				emp.setBirthday(rs.getDate("birthday"));
				emp.setAge(rs.getInt("age"));
				emp.setEmail(rs.getString("email"));
				emp.setDeptId(rs.getInt("deptId"));
				return emp;
			}
		}, id);
	}

	@Override
	public void addEmployeeBySJI(Employee emp) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		jdbcInsert.withTableName("employee");
		jdbcInsert.usingGeneratedKeyColumns("id");

		parameters.put("empid", emp.getEmpId());
		parameters.put("firstName", emp.getFirstName());
		parameters.put("lastName", emp.getLastName());
		parameters.put("age", emp.getAge());
		parameters.put("email", emp.getEmail());
		parameters.put("deptId", emp.getDeptId());
		parameters.put("birthday", emp.getBirthday());

		jdbcInsert.execute(parameters);

	}

	@Override
	public void addEmployeeByJT(Employee emp) {
		String sql = "insert into employee(empId, firstName, lastName, age, birthday, email, deptId) values (?,?,?,?,?,?,?)";
		jdbcInsert.getJdbcTemplate().update(sql, emp.getEmpId(), emp.getFirstName(), emp.getLastName(), emp.getAge(),
				emp.getBirthday(), emp.getEmail(), emp.getDeptId());
	}

	@Override
	public void updateEmployee(Employee emp) {
		String sql = "UPDATE employee SET empId=?, firstName=?, lastName=?, age=?, birthday=?, email=?, deptId=? where id=?";
		jdbcInsert.getJdbcTemplate().update(sql, emp.getEmpId(), emp.getFirstName(), emp.getLastName(),
				emp.getAge(), emp.getBirthday(), emp.getEmail(), emp.getDeptId(), emp.getId());
	}

	@Override
	public void delEmployee(Integer empId) {
		String sql = "DELETE FROM employee WHERE empId=?";
		jdbcInsert.getJdbcTemplate().update(sql, empId);

	}
	
}

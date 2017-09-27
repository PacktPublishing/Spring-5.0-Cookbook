package org.packt.secured.mvc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.packt.secured.mvc.model.data.Department;
import org.packt.secured.mvc.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{
	
	@Autowired
	private SimpleJdbcInsert jdbcInsert;

	@Override
	public List<Department> getDepartments() {
		String sql = "SELECT * FROM department";
		List<Department> depts = jdbcInsert.getJdbcTemplate().query(sql, new RowMapper<Department>() {

			@Override
			public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
				Department dept = new Department();
				dept.setId(rs.getInt("id"));
				dept.setDeptId(rs.getInt("deptId"));
				dept.setName(rs.getString("name"));
				return dept;
			}
		});
		return depts;
	}

	@Override
	public Department getDepartmentData(Integer id) {
		String sql = "SELECT * FROM department WHERE id = ?";
		return jdbcInsert.getJdbcTemplate().queryForObject(sql, new RowMapper<Department>() {
			@Override
			public Department mapRow(ResultSet rs, int rowNum) throws SQLException {

				Department dept = new Department();
				dept.setId(rs.getInt("id"));
				dept.setDeptId(rs.getInt("deptId"));
				dept.setName(rs.getString("name"));
				return dept;
			}
		}, id);
	}

	@Override
	public void addDepartmentBySJI(Department dept) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		jdbcInsert.withTableName("department");
		jdbcInsert.usingGeneratedKeyColumns("id");

		parameters.put("deptId", dept.getDeptId());
		parameters.put("name", dept.getName());
		jdbcInsert.execute(parameters);
		
	}

	@Override
	public void addDepartmentByJT(Department dept) {
		String sql = "insert into department (deptId, name) values (?,?)";
		jdbcInsert.getJdbcTemplate().update(sql, dept.getDeptId(), dept.getName());
		
	}

	@Override
	public void updateDepartment(Department dept) {
		String sql = "UPDATE department SET deptId=?, name=? WHERE id=?";
		jdbcInsert.getJdbcTemplate().update(sql, dept.getDeptId(), dept.getName(), dept.getId());
		
	}

	@Override
	public void delDepartment(Integer deptId) {
		String sql = "DELETE FROM department  WHERE deptId=?";
		jdbcInsert.getJdbcTemplate().update(sql,  deptId);
		
	}

}

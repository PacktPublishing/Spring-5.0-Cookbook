package org.packt.secured.mvc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.packt.secured.mvc.dao.LoginDao;
import org.packt.secured.mvc.model.data.AccountLogin;
import org.packt.secured.mvc.model.data.Department;
import org.packt.secured.mvc.model.data.Permission;
import org.packt.secured.mvc.model.data.Role;
import org.packt.secured.mvc.model.data.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl implements LoginDao {
	

	@Autowired
	private SimpleJdbcInsert jdbcInsert;

	
	@Override
	public List<Role> getUserRoles() {
		String sql = "SELECT * FROM role";
		List<Role> roles = jdbcInsert.getJdbcTemplate().query(sql, new RowMapper<Role>() {

			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				Role role = new Role();
                role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDescription(rs.getString("description"));

				return role;
			}
		});
		
		return roles;
	    
	}

	@Override
	public List<RolePermission> getUserGrantedAuthority(int userId) {
		String sql = "SELECT * FROM role_permission WHERE userId=?";
		List<RolePermission> roleperms = jdbcInsert.getJdbcTemplate().query(sql, new Object[]{userId}, new RowMapper<RolePermission>() {

			@Override
			public RolePermission mapRow(ResultSet rs, int rowNum) throws SQLException {
				RolePermission roleperm = new RolePermission();
				roleperm.setId(rs.getInt("id"));
				roleperm.setRoleId(rs.getInt("roleId"));
				roleperm.setPermissionId(rs.getInt("permissionId"));
				roleperm.setUserId(rs.getInt("userId"));
				
				return roleperm;
			}
		});
		return roleperms;
	}


	@Override
	public List<AccountLogin> getUsers() {
		String sql = "SELECT * FROM logindetails";
		List<AccountLogin> accounts = jdbcInsert.getJdbcTemplate().query(sql,  new RowMapper<AccountLogin>() {

			@Override
			public AccountLogin mapRow(ResultSet rs, int rowNum) throws SQLException {
				AccountLogin account = new AccountLogin();
				account.setId(rs.getInt("id"));
				account.setUsername(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				account.setEncPassword(rs.getString("enc_password"));

				return account;
			}
		});
		
		return accounts;
	}

	@Override
	public AccountLogin getUser(String username) {
		String sql = "SELECT * FROM logindetails WHERE username=?";
		return jdbcInsert.getJdbcTemplate().queryForObject(sql, new RowMapper<AccountLogin>() {
			@Override
			public AccountLogin mapRow(ResultSet rs, int rowNum) throws SQLException {

				AccountLogin account = new AccountLogin();
				account.setId(rs.getInt("id"));
				account.setUsername(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				account.setEncPassword(rs.getString("enc_password"));

				return account;
			}
		}, username);
	}

	@Override
	public Role getUserRole(int id) {
		String sql = "SELECT * FROM role WHERE id=?";
		return jdbcInsert.getJdbcTemplate().queryForObject(sql, new RowMapper<Role>() {
			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

				Role role = new Role();
                role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDescription(rs.getString("description"));

				return role;
			}
		}, id);
	}

	@Override
	public List<Permission> getPermissions() {
		String sql = "SELECT * FROM permission";
		List<Permission> perms = jdbcInsert.getJdbcTemplate().query(sql,  new RowMapper<Permission>() {

			@Override
			public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
				Permission perm = new Permission();
				perm.setId(rs.getInt("id"));
				perm.setName(rs.getString("name"));
			    perm.setDescription(rs.getString("description"));

				return perm;
			}
		});
		
		return perms;
	}

	@Override
	public Permission getPermission(int id) {
		String sql = "SELECT * FROM permission WHERE id=?";
		return jdbcInsert.getJdbcTemplate().queryForObject(sql, new RowMapper<Permission>() {
			@Override
			public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {

				Permission perm = new Permission();
				perm.setId(rs.getInt("id"));
				perm.setName(rs.getString("name"));
			    perm.setDescription(rs.getString("description"));

				return perm;
			}
		}, id);
	}

	

}

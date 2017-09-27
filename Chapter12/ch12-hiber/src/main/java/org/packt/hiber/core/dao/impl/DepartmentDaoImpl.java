package org.packt.hiber.core.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.packt.hiber.core.dao.DepartmentDao;
import org.packt.hiber.core.model.data.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Cacheable("hazeldept")
	@Override
	public List<Department> getAllDepts() {
		return sessionFactory.openSession().createQuery("select d from Department d", Department.class).getResultList();
	}

	@Cacheable("hazeldept")
	@Override
	public List<Department> getDeptsByName(String name) {
		return sessionFactory.openSession().createQuery("select d from Department d where d.name LIKE '%:name%'", Department.class).setParameter("name", name).getResultList();
	}

	@Cacheable("hazeldept")
	@Override
	public List<Department> getDeptsByDeptid(Integer deptid) {
		return sessionFactory.openSession().createQuery("select d from Department d where d.deptid = :deptid", Department.class).setParameter("deptid", deptid).getResultList();
	}

	@Cacheable("hazeldept")
	@Override
	public Department getDeptById(Integer id) {
		return sessionFactory.openSession().createQuery("select d from Department d where d.id = :id", Department.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public void saveDept(Department dept) {
		sessionFactory.openSession().persist(dept);
	}
}

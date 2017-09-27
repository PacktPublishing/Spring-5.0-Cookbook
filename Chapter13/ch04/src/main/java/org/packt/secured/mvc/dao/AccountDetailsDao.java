package org.packt.secured.mvc.dao;

import java.util.List;

import org.packt.secured.mvc.model.data.AccountDetails;

public interface AccountDetailsDao {
	
	public void setUserAccount(AccountDetails account);
	public AccountDetails getUserAccount(Integer id);
	public List<AccountDetails> getAllUsers();

}

package DAO;

import VO.Account;

public interface IAccountDAO {
	public Account logincheck(Account a);
	public Account changeAcc(Account a );
	public String AccAllDel(Integer acc,String table);
	public String AccAllAdd(Integer acc,String table);
	public String AccAdmAdd(Account a );
	
}

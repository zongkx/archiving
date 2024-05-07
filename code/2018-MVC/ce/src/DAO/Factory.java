package DAO;

public class Factory {
	public IAccountDAO getAccountInstance() {
		return  new AccountDAOImpl();
	}
	public IStudentDAO getStudentInstance() {
		return new StudentDAOImpl();
		
	}
	public IAdmDAO getAdmInstance() {
		return new AdmDAOImpl();
		
	}
	public ICompanyDAO getCompanyInstance() {
		return new CompanyDAOImpl();
		
	}
	public ITeacherDAO getTeacherInstance() {
		return new TeacherDAOImpl();
		
	}
	
}

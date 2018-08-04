package application.cdms.service.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import application.cdms.db.entities.ApplicationUser;
import application.cdms.hibernate.utility.HibernateUtils;
import application.cdms.models.LoginUser;
import application.cdms.service.LoginService;

public class LoginServiceImpl extends GenericServiceImpl implements LoginService{

	private static volatile LoginService loginService; 
	
	private LoginServiceImpl() {
		super();
	}
	
	public static LoginService getInstance(){
		if(loginService==null){
			synchronized (LoginServiceImpl.class) {
				if(loginService==null){
					loginService=new LoginServiceImpl();
				}
			}
		}
		return loginService;
	}
	
	@Override
	public LoginUser validateCredential(LoginUser loginUsr){
		Criteria cr = HibernateUtils.getCustomeTrasationManager().initTx().createCriteria(ApplicationUser.class);
		cr.add(Restrictions.eq("userNm", loginUsr.getUsername()));
		cr.add(Restrictions.eq("password", loginUsr.getPassword()));
		ApplicationUser appUser = (ApplicationUser) cr.uniqueResult();
		if(appUser==null){
			return null;
		}
		loginUsr.setRole(appUser.getRole());
		HibernateUtils.getCustomeTrasationManager().commitTx();
		HibernateUtils.CloseCustomeTransationManager();
		return loginUsr;
		}
}
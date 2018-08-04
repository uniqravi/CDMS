package application.cdms.service;

import application.cdms.models.LoginUser;

public interface LoginService {
	LoginUser validateCredential(LoginUser user);
}

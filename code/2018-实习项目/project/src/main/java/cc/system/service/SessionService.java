package cc.system.service;

import java.util.List;

import cc.system.domain.UserOnline;

public interface SessionService {

	List<UserOnline> list();

	boolean forceLogout(String sessionId);
}

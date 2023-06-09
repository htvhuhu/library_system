package mpp.dao;

import java.util.HashMap;
import java.util.Map;

public class DataAccessFactory {
	private DataAccessFactory() {

	}

	private static final Map<Class<? extends DataAccess>, DataAccess> map = new HashMap<>();

	static {
		map.put(LoginDao.class, LoginDao.getInstance());
		map.put(BookDao.class, BookDao.getInstance());
		map.put(AuthorDao.class, AuthorDao.getInstance());
		map.put(MemberDao.class, MemberDao.getInstance());
	}

	public static DataAccess getDataAccess(Class<? extends DataAccess> daoClass) {
		if (map.containsKey(daoClass)) return map.get(daoClass);
		return null;
	}
}

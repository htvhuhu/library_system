package mpp.dao;

import java.util.HashMap;
import java.util.Map;

public class DataAccessFactory {
	private DataAccessFactory() {

	}

	private static final Map<Class<? extends DataAccess>, DataAccess> map = new HashMap<>();

	static {
		map.put(LoginDao.class, new LoginDao());
		map.put(BookDao.class, new BookDao());
	}

	public static DataAccess getDataAccess(Class<? extends DataAccess> daoClass) {
		if (map.containsKey(daoClass)) return map.get(daoClass);
		return null;
	}
}

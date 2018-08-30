package cn.caijiajia.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import cn.caijiajia.domain.PageInfo;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args= {Connection.class,Integer.class})})
public class PagePlugin<T> implements Interceptor{

	private static ThreadLocal<PageInfo> LOCAL_PAGE = new ThreadLocal<PageInfo>();
	
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
		String sql = (String) metaObject.getValue("delegate.boundSql.sql");
		Integer pageNum = null;
		Integer pageSize = null;
		if (LOCAL_PAGE.get() != null) {
			pageNum = LOCAL_PAGE.get().getPageNum() ;
			pageSize = LOCAL_PAGE.get().getPageSize();
		}
		
		if (pageNum != null && pageSize != null) {
			sql = sql+" limit "+(pageNum-1)*pageSize+","+pageSize+"";	
		}
		System.out.println(sql);
		// TODO Auto-generated method stub
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		
	}

	public static void startPage(Integer pageNum,Integer pageSize) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		LOCAL_PAGE.set(pageInfo);
		
	}
	
}

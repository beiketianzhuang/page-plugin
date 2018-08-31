package cn.caijiajia.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import cn.caijiajia.domain.Time;
import cn.caijiajia.interceptor.PagePlugin;
import cn.caijiajia.mapper.BaseMapper;

public class TestMapper {
	
	
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =
		  new SqlSessionFactoryBuilder().build(inputStream);
		System.out.println(sqlSessionFactory);
		
		SqlSession session = sqlSessionFactory.openSession();
		BaseMapper baseMapper = session.getMapper(BaseMapper.class);
		PagePlugin.startPage(1, 3);
		List<Time> times = baseMapper.selectBase(1);
		System.out.println(times);
		
	}

}

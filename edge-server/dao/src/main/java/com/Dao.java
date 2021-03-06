package com;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName Dao
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/11
 * @Version V1.0
 **/

@Component
public class Dao {


    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    /*@PostConstruct*/
    public void init() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        System.out.println(Resources.getResourceAsFile(resource).getPath());
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
        resource = null;
    }

    public void init(Configuration config){

        sqlSessionFactory = new DefaultSqlSessionFactory(config);
    }




}

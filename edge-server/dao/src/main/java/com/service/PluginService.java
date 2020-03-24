package com.service;

import com.Dao;
import com.mapper.PluginMapper;
import com.model.PluginDo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName PluginService
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/11
 * @Version V1.0
 **/
@Component
public class PluginService {


    @Autowired
    private Dao dao;


    public PluginDo selectById(Integer id) {
        try (SqlSession sqlSession = dao.getSqlSessionFactory().openSession()) {
            PluginDo pluginDo =  sqlSession.getMapper(PluginMapper.class).selectById(id);
            sqlSession.close();
            return pluginDo;
        }
    }
}




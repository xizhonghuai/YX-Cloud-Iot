package com.service;

import com.Dao;
import com.mapper.DeviceMsgMapper;
import com.model.DeviceMsgDO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName DeviceMsgService
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/25
 * @Version V1.0
 **/
@Component
public class DeviceMsgService {


    @Autowired
    private Dao dao;



    public void insert(DeviceMsgDO deviceMsgDO) {
        try (SqlSession sqlSession = dao.getSqlSessionFactory().openSession()) {
            sqlSession.getMapper(DeviceMsgMapper.class).insert(deviceMsgDO);
            sqlSession.commit();
            sqlSession.close();
        }

    }


    public List<DeviceMsgDO> select(HashMap<String,Object> map) {

        try (SqlSession sqlSession = dao.getSqlSessionFactory().openSession()) {
             List<DeviceMsgDO> list = sqlSession.getMapper(DeviceMsgMapper.class).select(map);
             sqlSession.close();
             return list;
        }
    }
}

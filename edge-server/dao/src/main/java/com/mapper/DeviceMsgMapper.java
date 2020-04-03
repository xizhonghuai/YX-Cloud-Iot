package com.mapper;

import com.model.DeviceMsgDO;

import java.util.HashMap;
import java.util.List;

public interface DeviceMsgMapper {

    void insert(DeviceMsgDO deviceMsgDO);
    List<DeviceMsgDO> select(HashMap<String,Object> map);

    List<DeviceMsgDO> selectAll();
}

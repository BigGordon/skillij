package com.zyc.cloud.service;

import com.zyc.cloud.dto.EditNodesDto;

import java.util.List;

/**
 * Created on 2018/4/18.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public interface EditService {
    List<EditNodesDto> getNodes(String user);
}

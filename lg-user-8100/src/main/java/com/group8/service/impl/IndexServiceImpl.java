package com.group8.service.impl;

import com.group8.dao.IndexDao;
import com.group8.entity.LgGroup;
import com.group8.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author laiyong
 * @date 2022/2/23 09:56 星期三
 * @apiNote
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired(required = false)
    IndexDao indexDao;

    @Override
    public List<LgGroup> featuredGroup() {
        return indexDao.featuredGroup();
    }
}

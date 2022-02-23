package com.group8.service.impl;

import com.group8.dao.LgComboDao;
import com.group8.dao.LgGroupDao;
import com.group8.entity.LgGroup;
import com.group8.service.LgGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 跟团游项目(LgGroup)表服务实现类
 *
 * @author makejava
 * @since 2022-02-19 10:15:21
 */
@Service
public class LgGroupServiceImpl implements LgGroupService {
    @Resource
    private LgGroupDao lgGroupDao;

    @Resource
    LgComboDao lgComboDao;

    /**
     * 通过ID查询单条数据
     *
     * @param groupId 主键
     * @return 实例对象
     */
    @Override
    public LgGroup queryById(Integer groupId) {
        return this.lgGroupDao.queryById(groupId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<LgGroup> queryAllByLimit(int offset, int limit) {
        return this.lgGroupDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<LgGroup> queryAll() {
        return lgGroupDao.queryAll();
    }


    /**
     * 新增数据
     *
     * @param lgGroup 实例对象
     * @return 实例对象
     */
    @Override
    public LgGroup insert(LgGroup lgGroup) {
        this.lgGroupDao.insert(lgGroup);
        return lgGroup;
    }

    /**
     * 修改数据
     *
     * @param lgGroup 实例对象
     * @return 实例对象
     */
    @Override
    public LgGroup update(LgGroup lgGroup) {
        this.lgGroupDao.update(lgGroup);
        return this.queryById((int) lgGroup.getGroupId());
    }

    /**
     * 通过主键删除数据
     *
     * @param groupId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer groupId) {
        return this.lgGroupDao.deleteById(groupId) > 0;
    }

//    @Override
//    public int inserts(GroupAndComboDto groupAndComboDto) {
//        lgComboDao.insert(groupAndComboDto.getLgCombo());
//        long groupId = groupAndComboDto.getLgGroup().getGroupId();
//        LgCombo lgCombo = new LgCombo();
//        lgCombo.setGroupId(groupId);
//        lgComboDao.insert(lgCombo);
//        return 1;
//    }

}
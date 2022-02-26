package com.group8.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.group8.dao.LgComboDao;
import com.group8.dao.LgGroupDao;
import com.group8.dto.GroupAndComboDto;
import com.group8.entity.LgDailyStock;
import com.group8.entity.LgGroup;
import com.group8.service.LgGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
        LgGroup group = lgGroupDao.queryById(groupId);
        if(group != null){
            List<LgDailyStock> dailyStockList = lgGroupDao.getDailyStock(groupId);
            group.setDailyStockList(dailyStockList);
            // 获取group最低价格设置回对象中
            int[] priceArr = new int[group.getDailyStockList().size()];
            // 获取去重后的所有日期
            Set<String> dateSet = new HashSet<>();
            if(!group.getDailyStockList().isEmpty()){
                for (int i = 0; i < group.getDailyStockList().size(); i++) {
                    priceArr[i] = group.getDailyStockList().get(i).getPrice();
                    dateSet.add(group.getDailyStockList().get(i).getDailyStockDate().toString());
                }
                int min = ArrayUtil.min(priceArr);
                group.setLowestPrice(min);
                group.setDateSet(dateSet);
            }
        }
        return group;
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
    public List<LgGroup> queryAll(String keyword) {
        return lgGroupDao.queryAll(keyword);
    }


    /**
     * 新增数据
     *
     * @param dto 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(GroupAndComboDto dto) {
        lgGroupDao.insert(dto);
        long groupId = dto.getLgGroup().getGroupId();
        for (int i = 0; i < dto.getLgCombo().size(); i++) {
            dto.getLgCombo().get(i).setGroupId(groupId);
        }

        lgComboDao.insert(dto.getLgCombo());
        System.out.println(dto);
    }

    /**
     * 修改数据
     *
     * @param lgGroup 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(LgGroup lgGroup) {

        return lgGroupDao.update(lgGroup);
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

    @Override
    public int upates(Integer pid) {
        return lgGroupDao.upates(pid);
    }

    @Override
    public List<LgGroup> featuredGroup(String currentSortType) {
        List<LgGroup> groupList = null;
        switch (currentSortType) {
            case "default":
                groupList = lgGroupDao.queryByCollectedDesc();
                break;
            case "score":
                groupList = lgGroupDao.queryByScoreDesc();
                break;
            case "sales":
                groupList = lgGroupDao.queryBySlodDesc();
                break;
        }
        if(!groupList.isEmpty()){
            // 获取group最低价格设置回对象中
            for (LgGroup group : groupList) {
                int[] priceArr = new int[group.getDailyStockList().size()];
                if(!group.getDailyStockList().isEmpty()){
                    for (int i = 0; i < group.getDailyStockList().size(); i++) {
                        priceArr[i] = group.getDailyStockList().get(i).getPrice();
                    }
                    int min = ArrayUtil.min(priceArr);
                    group.setLowestPrice(min);
                }
            }
        }
        return groupList;
    }

    @Override
    public List<LgGroup> searchByKeyword(String keyword) {
        return lgGroupDao.searchByKeyword(keyword);
    }


}
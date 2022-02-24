package com.group8.service;

import com.group8.dto.GroupAndComboDto;
import com.group8.entity.LgGroup;

import java.util.List;

/**
 * 跟团游项目(LgGroup)表服务接口
 *
 * @author makejava
 * @since 2022-02-19 10:15:20
 */
public interface LgGroupService {

    /**
     * 通过ID查询单条数据
     *
     * @param groupId 主键
     * @return 实例对象
     */
    LgGroup queryById(Integer groupId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<LgGroup> queryAllByLimit(int offset, int limit);

    List<LgGroup> queryAll(String keyword);

    /**
     * 新增数据
     *
     * @param lgGroup 实例对象
     * @return 实例对象
     */
    void insert(GroupAndComboDto dto);

    /**
     * 修改数据
     *
     * @param lgGroup 实例对象
     * @return 实例对象
     */
    Integer update(LgGroup lgGroup);

    /**
     * 通过主键删除数据
     *
     * @param groupId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer groupId);

    int upates(Integer pid);

    List<LgGroup> featuredGroup(String currentSortType);
}
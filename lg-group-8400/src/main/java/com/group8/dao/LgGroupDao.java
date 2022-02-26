package com.group8.dao;

import com.group8.dto.GroupAndComboDto;
import com.group8.entity.LgGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 跟团游项目(LgGroup)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-19 10:15:19
 */
public interface LgGroupDao {

    /**
     * 通过ID查询单条数据
     *
     * @param groupId 主键
     * @return 实例对象
     */
    LgGroup queryById(Integer groupId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<LgGroup> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param lgGroup 实例对象
     * @return 对象列表
     */
    List<LgGroup> queryAll(@Param("keyword") String keyword);

    /**
     * 新增数据
     *
     * @param lgGroup 实例对象
     * @return 影响行数
     */
    int insert(GroupAndComboDto comboDto);

    /**
     * 修改数据
     *
     * @param lgGroup 实例对象
     * @return 影响行数
     */
    int update(LgGroup lgGroup);

    /**
     * 通过主键删除数据
     *
     * @param groupId 主键
     * @return 影响行数
     */
    int deleteById(Integer groupId);

    //Integer avgmark(Integer pid);
    int avgmark(Integer pid);

    int upates(Integer pid);

    LgGroup sorting(Integer groupSold);

    List<LgGroup> queryByCollectedDesc();

    List<LgGroup> queryByScoreDesc();

    List<LgGroup> queryBySlodDesc();

    List<LgGroup> searchByKeyword(@Param("keyword") String keyword);
}
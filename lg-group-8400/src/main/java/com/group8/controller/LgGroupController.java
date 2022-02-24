package com.group8.controller;

import com.group8.dao.LgComboDao;
import com.group8.dto.GroupAndComboDto;
import com.group8.entity.LgCombo;
import com.group8.entity.LgGroup;
import com.group8.entity.ResponseEntity;
import com.group8.service.LgComboService;
import com.group8.service.LgGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 跟团游项目(LgGroup)表控制层
 *
 * @author makejava
 * @since 2022-02-19 10:15:22
 */
@RestController
@RequestMapping("group")
public class LgGroupController {
    /**
     * 服务对象
     */
    @Resource
    private LgGroupService lgGroupService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne/{id}")
    public ResponseEntity<LgGroup> selectOne(@PathVariable Integer id) {
        LgGroup lgGroup = lgGroupService.queryById(id);
        return new ResponseEntity<>(200, "查询成功！", lgGroup);
    }

    @GetMapping("queryAll")
    public ResponseEntity<List<LgGroup>> queryAll(@RequestParam("keyword") String keyword) {
        List<LgGroup> lgGroups = lgGroupService.queryAll(keyword);
        if (lgGroups != null) {
            return new ResponseEntity<>(200, "查询成功！", lgGroups);
        } else {
            return new ResponseEntity<>(500, "查询失败！", lgGroups);
        }
    }

    /**
     * 按条件查询商品集合
     * @param currentSortType 前端传递的条件
     * @return 集合
     */
    @GetMapping("/featuredGroup")
    public ResponseEntity<List<LgGroup>> featuredGroup(@RequestParam("currentSortType") String currentSortType){
        List<LgGroup> groupList = lgGroupService.featuredGroup(currentSortType);
        if(!groupList.isEmpty()){
            return new ResponseEntity<>(200, "查询成功！", groupList);
        }else {
            return new ResponseEntity<>(500, "查询失败！", null);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<LgGroup> update(@RequestBody LgGroup lgGroup) {
        Integer update = lgGroupService.update(lgGroup);
        if (update != null) {
            return new ResponseEntity<>(200, "更新成功", lgGroup);
        } else {
            return new ResponseEntity<>(500, "更新失败", lgGroup);
        }
    }

    @DeleteMapping("/deleteById/{groupId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer groupId) {
        boolean b = lgGroupService.deleteById(groupId);
        if (b) {
            return new ResponseEntity<>(200, "删除成功！", b);
        } else {
            return new ResponseEntity<>(500, "删除失败！", b);
        }
    }
    //同时新增2个表的数据

    @PostMapping("/insert")
    public ResponseEntity<GroupAndComboDto> insert(@RequestBody GroupAndComboDto dto) {
        lgGroupService.insert(dto);
        if (dto != null) {
            return new ResponseEntity<>(200, "新增成功", dto);
        } else {
            return new ResponseEntity<>(500, "新增失败", dto);
        }
    }

    //计算平均分
    @PostMapping("/upates/{pid}")
    public ResponseEntity<Integer> upates(@PathVariable Integer pid) {
        int i = lgGroupService.upates(pid);
        return new ResponseEntity<>(200, "cg", i);
    }
}
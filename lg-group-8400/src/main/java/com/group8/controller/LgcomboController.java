package com.group8.controller;

import com.group8.entity.LgCombo;
import com.group8.entity.ResponseEntity;
import com.group8.service.LgComboService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * description: LgcomboController <br>
 * date: 2022/2/21 4:12 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("combo")
public class LgcomboController {
    @Resource
    LgComboService lgComboService;

    @PostMapping("/insert")
    public ResponseEntity<Integer> insert(LgCombo lgCombo) {
        int i = lgComboService.insert(lgCombo);
        if (i != 0) {
            return new ResponseEntity<Integer>(200, "新增成功", i);
        } else {
            return new ResponseEntity<>(400, "新增失败", i);
        }
    }

    @GetMapping("/selectAll")
    public ResponseEntity<List<LgCombo>> selectAll(LgCombo lgCombo) {
        List<LgCombo> lgCombos = lgComboService.selectAll(lgCombo);
        if (lgCombos != null) {
            return new ResponseEntity<>(200, "查询成功", lgCombos);
        } else {
            return new ResponseEntity<>(400, "查询失败", lgCombos);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Integer> update(@RequestBody LgCombo lgCombo) {
        int i = lgComboService.update(lgCombo);
        System.out.println(i);
        if (i != 0) {
            return new ResponseEntity<>(200, "更新成功", i);
        } else {
            return new ResponseEntity<>(400, "更新失败", i);
        }
    }

    @DeleteMapping("/deleteById/{comboId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable int comboId) {
        boolean b = lgComboService.deleteById(comboId);
        if (b = true) {
            return new ResponseEntity<>(200, "删除成功", b);
        } else {
            return new ResponseEntity<>(400, "删除失败", b);
        }
    }

    @GetMapping("/selectById/{comboId}")
    public ResponseEntity<LgCombo> selectById(@PathVariable Integer comboId) {
        LgCombo id = lgComboService.selectById(comboId);
        if (id != null) {
            return new ResponseEntity<>(200, "查询成功", id);
        } else {
            return new ResponseEntity<>(200, "查询失败", id);
        }
    }
}

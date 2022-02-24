package com.group8.service;

import com.group8.entity.LgCombo;

import java.util.List;

/**
 * description: LgComboService <br>
 * date: 2022/2/21 2:54 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
public interface LgComboService {
    int insert(LgCombo lgCombo);

    List<LgCombo> selectAll(LgCombo lgCombo);

    int update(LgCombo lgCombo);

    boolean deleteById(int comboId);

    LgCombo selectById(Integer comboId);

}

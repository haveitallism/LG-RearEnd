package com.group8.dao;

import com.group8.entity.LgCombo;

import java.util.List;

/**
 * description: LgCombo <br>
 * date: 2022/2/19 4:24 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
public interface LgComboDao {
    int insert(LgCombo lgCombo);

    LgCombo selectById(Integer comboId);

    List<LgCombo> selectAll(LgCombo lgCombo);

    int update(LgCombo lgCombo);

    boolean deleteById(int comboId);
}

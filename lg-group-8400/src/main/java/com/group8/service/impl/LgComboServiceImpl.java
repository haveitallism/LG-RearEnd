package com.group8.service.impl;

import com.group8.dao.LgComboDao;
import com.group8.entity.LgCombo;
import com.group8.service.LgComboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * description: LgComboService <br>
 * date: 2022/2/21 2:54 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@Service
public class LgComboServiceImpl implements LgComboService {
    @Resource
    LgComboDao lgComboDao;

    @Override
    public int insert(LgCombo lgCombo) {

        return lgComboDao.insert((List<LgCombo>) lgCombo);
    }

    @Override
    public List<LgCombo> selectAll(LgCombo lgCombo) {
        return lgComboDao.selectAll(lgCombo);
    }

    @Override
    public int update(LgCombo lgCombo) {
        return lgComboDao.update(lgCombo);
    }

    @Override
    public boolean deleteById(int comboId) {
        return lgComboDao.deleteById(comboId);
    }

    @Override
    public LgCombo selectById(Integer comboId) {
        return lgComboDao.selectById(comboId);
    }
}

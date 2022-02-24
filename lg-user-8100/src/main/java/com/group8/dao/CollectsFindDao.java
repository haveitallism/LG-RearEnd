package com.group8.dao;

import com.group8.entity.LgGroup;
import com.group8.entity.LgScenicspot;
import com.group8.entity.LgTravelnotes;

/**
 * @author QY
 * @create 2022-02-21 19:07
 */

public interface CollectsFindDao {

    LgGroup groupFindById(int groupId);

    LgTravelnotes notesFindById(int notesId);

    LgScenicspot scenicFindById(int scenicId);

}

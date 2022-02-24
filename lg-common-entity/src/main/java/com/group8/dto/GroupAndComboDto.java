package com.group8.dto;

import com.group8.entity.LgCombo;
import com.group8.entity.LgGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description: GroupAndComboDto <br>
 * date: 2022/2/21 8:25 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupAndComboDto {
    private LgGroup lgGroup;
    private List<LgCombo> lgCombo;
}

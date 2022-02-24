package com.group8.dto;

import com.group8.entity.LgTourOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFindByPage {
    private int page;
    private int limit;
    private LgTourOrder lgTourOrder;

}

package com.group8.Inventory;

import com.group8.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;

@Slf4j
public class InventoryChange extends QuartzJobBean {

    @Autowired
    OrderService orderService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        orderService.updateInventory();
    }
}

package com.neuedu.schedule;

import com.neuedu.service.IOrderservice;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Date;

@Component
public class CloseOrder {

    @Value("${order.close.timeout}")
    private int OrderTimeOut;

    @Autowired
    IOrderservice iOrderservice;

    @Scheduled(cron = "* * */2 * * *")
    public void closeOrder(){
        System.out.println("============定时器==================");
        Date closeOrderTime=DateUtils.addHours(new Date(),-OrderTimeOut);
        iOrderservice.CloseOrder(com.neuedu.utils.DateUtils.dateToString(closeOrderTime));

    }
}

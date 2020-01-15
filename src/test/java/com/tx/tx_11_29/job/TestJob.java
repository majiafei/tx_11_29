package com.tx.tx_11_29.job;

import com.tx.tx_11_29.Tx1129ApplicationTests;
import com.tx.tx_11_29.order.processer.OrderProcesser;
import com.tx.tx_11_29.taskframework.entity.JobInfo;
import com.tx.tx_11_29.taskframework.pool.PendingPool;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: TestJob
 * @Auther: admin
 * @Date: 2020/1/15 11:49
 * @Description:
 */
public class TestJob extends Tx1129ApplicationTests {

    @Autowired
    private PendingPool<Integer, Integer> pendingPool;

    @Autowired
    private OrderProcesser processer;

    @Test
    public void test() throws InterruptedException {
        pendingPool.registerJob("订单", processer);

        for (int i = 0; i < 400; i++) {
            pendingPool.putTask("订单", i);
        }

        Thread.sleep(3000);

        pendingPool.getProcess("订单");
    }

}

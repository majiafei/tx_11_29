package com.tx.tx_11_29.order.processer;

import com.tx.tx_11_29.taskframework.entity.TaskResult;
import com.tx.tx_11_29.taskframework.enums.ResultEnum;
import com.tx.tx_11_29.taskframework.processer.IJobProcesser;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>
 *     订单的具体处理类
 * </p>
 * @ClassName: OrderProcesser
 * @Auther: admin
 * @Date: 2020/1/15 11:07
 * @Description:
 */
@Service
public class OrderProcesser implements IJobProcesser<Integer, Integer> {
    @Override
    public TaskResult<Integer> execute(Integer data) {
        try {
            Random random = new Random();
            int i = random.nextInt(1000);
            if (i < 100) {
                System.out.println("执行成功");
                return new TaskResult<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), i + data);
            } else {
                return TaskResult.result(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage());
            }
        } catch (Exception e) {
            return TaskResult.result(ResultEnum.EXCEPTION.getCode(), ResultEnum.EXCEPTION.getMessage());
        }
    }
}

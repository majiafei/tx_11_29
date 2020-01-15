package com.tx.tx_11_29.taskframework.entity;

import com.baomidou.mybatisplus.extension.api.R;
import com.tx.tx_11_29.taskframework.enums.ResultEnum;
import com.tx.tx_11_29.taskframework.processer.IJobProcesser;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *     一类任务的抽象
 * </p>
 * @ClassName: JobInfo
 * @Auther: admin
 * @Date: 2020/1/15 11:16
 * @Description:
 */
public class JobInfo<R, T> {

    private String jobName;

    // 成功的数量
    private AtomicInteger successCount;

    // 处理的数量
    private AtomicInteger processCount;

    // 处理器
    private IJobProcesser<R, T> iJobProcesser;

    private LinkedBlockingDeque<TaskResult<R>> linkedBlockingDeque = new LinkedBlockingDeque<>();

    public JobInfo(String jobName, IJobProcesser<R, T> iJobProcesser) {
        this.jobName = jobName;
        successCount = new AtomicInteger(0);
        processCount = new AtomicInteger(0);
        this.iJobProcesser = iJobProcesser;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public AtomicInteger getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(AtomicInteger successCount) {
        this.successCount = successCount;
    }

    public AtomicInteger getProcessCount() {
        return processCount;
    }

    public void setProcessCount(AtomicInteger processCount) {
        this.processCount = processCount;
    }

    public IJobProcesser<R, T> getiJobProcesser() {
        return iJobProcesser;
    }

    public void setiJobProcesser(IJobProcesser<R, T> iJobProcesser) {
        this.iJobProcesser = iJobProcesser;
    }

    // 检查结果并统计
    public void putResult(TaskResult<R> taskResult) {
        if (Objects.equals(taskResult.getCode(), ResultEnum.SUCCESS.getCode())) {
            successCount.incrementAndGet();
        } else { // 失败或者异常放入
            linkedBlockingDeque.addLast(taskResult);
        }
        processCount.incrementAndGet();
    }

    public void getProcess() {
        System.out.println("成功:" + successCount.get() + "   =============总：" + processCount.get());
    }
}

package com.tx.tx_11_29.taskframework.pool;

import com.baomidou.mybatisplus.extension.api.R;
import com.tx.tx_11_29.taskframework.entity.JobInfo;
import com.tx.tx_11_29.taskframework.entity.TaskResult;
import com.tx.tx_11_29.taskframework.enums.ResultEnum;
import com.tx.tx_11_29.taskframework.processer.IJobProcesser;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * <p>
 *     封装线程池的类，主要用来提交任务，执行任务
 * </p>
 * @ClassName: PendingPool
 * @Auther: admin
 * @Date: 2020/1/15 11:06
 * @Description:
 */
@Component
public class PendingPool<R, T> {

    // 线程的数量
    private static final int THREAD_NUM = Runtime.getRuntime().availableProcessors();

    //队列，线程池使用，用以存放待处理的任务
    private static BlockingQueue<Runnable> taskQueue
            = new ArrayBlockingQueue<Runnable>(5000);

    // 线程池
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(THREAD_NUM, THREAD_NUM, 0,
                                                                                  TimeUnit.MILLISECONDS, taskQueue);

    private static ConcurrentHashMap<String, JobInfo> jobInfoMap = new ConcurrentHashMap<>();

    private static class PendingTask<R, T> implements Runnable {

        private JobInfo jobInfo;

        private T data;

        public PendingTask(JobInfo jobInfo, T data) {
            this.jobInfo = jobInfo;
            this.data = data;
        }

        @Override
        public void run() {
            TaskResult<R> taskResult = null;
            try {
                IJobProcesser<R, T> iJobProcesser = (IJobProcesser<R, T>)jobInfo.getiJobProcesser();
                taskResult = iJobProcesser.execute(data);

                if (taskResult == null) {
                    taskResult = TaskResult.result(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
                taskResult = TaskResult.result(ResultEnum.EXCEPTION.getCode(), ResultEnum.EXCEPTION.getMessage());
            } finally {
                // 存放结果
                jobInfo.putResult(taskResult);
            }
        }
    }

    public void putTask(String jobName, T data) {
        JobInfo jobInfo = jobInfoMap.get(jobName);
        if (jobInfo == null) {
            throw new RuntimeException(jobName + "没有注册");
        }
        threadPoolExecutor.execute(new PendingTask(jobInfo, data));
    }

    public void registerJob(String jobName, IJobProcesser<R, T> iJobProcesser) {
        JobInfo jobInfo = new JobInfo(jobName, iJobProcesser);
        if (jobInfoMap.putIfAbsent(jobName, jobInfo) != null) {
            throw new RuntimeException(jobName + "任务已经存在");
        }
    }

    public void getProcess(String jobName) {
        JobInfo jobInfo = jobInfoMap.get(jobName);
        if (jobInfo == null) {
            throw new RuntimeException(jobName + "没有注册");
        }

        jobInfo.getProcess();
    }

}

package com.loaves.tool_cabinet.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * 描述：多线程工具类。批量传入多线程处理数据，和每条线程处理数的分组大小，方法自动分多线程执行并返回执行结果
 * 初始化-》传入待处理数据和分组处理的每组处理数
 * 执行时-》传入多线程任务名称和多线程方法（需要继承 ThreadBase）
 *
 * @author LBF
 * @date 2021/10/18 11:41
 **/
@Slf4j
@Data
public class ThreadUtils<T> {

    /**
     * 默认单个线程每次处理20条
     */
    private static final int DEF_PAGE_SIZE = 20;
    /**
     * 多线程放入队列的数量
     */
    private static final int THREAD_QUEUE_SIZE = 1000;
    /**
     * 多线程放入队列的数量
     */
    private static final int THREAD_MAX_POOL_SIZE = 20;

    /**
     * 每个线程处理数据量
     */
    private int listPageSize;

    /**
     * 多线程处理的元数据
     */
    private List<T> threadDates;

    /**
     * 初始化一个线程池
     */
    private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(5,
            20,
            2,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(THREAD_QUEUE_SIZE));

    /**
     * 自定义私有构造方法
     *
     * @param listPageSize 每个线程处理数据量
     * @param threadDates  多线程处理的元数据
     */
    private ThreadUtils(int listPageSize, List<T> threadDates) {
        this.listPageSize = listPageSize;
        this.threadDates = threadDates;
    }

    /**
     * 对原数据进行分组处理
     *
     * @return 分组后数据
     */
    private List<List<T>> getPage() {
        return EntityUtils.pageList(this.threadDates, this.listPageSize);
    }

    /**
     * 获取一个多线程操作工具方法
     *
     * @param listPageSize 每个线程处理数据量
     * @param threadDates  多线程处理的元数据
     * @return 线程操作工具方法
     */
    public static <T> ThreadUtils<T> threadUtilsInit(int listPageSize, List<T> threadDates) {
        // 没有设置每个线程处理数据长度时默认每组处理20条
        listPageSize = listPageSize == 0 ? DEF_PAGE_SIZE : listPageSize;
        threadDates = Optional.ofNullable(threadDates).orElseGet(ArrayList::new);
        return new ThreadUtils<>(listPageSize, threadDates);
    }

    /**
     * 多线程执行方法
     *
     * @param taskName 线程任务名称
     * @param fClass   多线程执行方法
     * @param <E>      多线程执行返回值
     * @param <F>      多线程执行方法类
     * @return 多线程执行结果
     * @throws NoSuchMethodException     多线程任务执行异常
     * @throws IllegalAccessException    多线程任务执行异常
     * @throws InvocationTargetException 多线程任务执行异常
     * @throws InstantiationException    多线程任务执行异常
     * @throws InterruptedException      多线程任务执行异常
     * @throws ExecutionException        多线程任务执行异常
     */
    public <E, F extends ThreadBase<T, E>> List<E> doThreadTask(String taskName, Class<F> fClass) throws NoSuchMethodException
            , IllegalAccessException, InvocationTargetException, InstantiationException, InterruptedException, ExecutionException {
        log.info("多线程进程①：开始执行多线程任务【{}】，开始时间：{}", taskName, DateUtils.nowToStr());

        //定义多线程执行结果收集
        CompletionService<E> completionService = new ExecutorCompletionService<>(POOL_EXECUTOR);
        List<Future<E>> futureList = new ArrayList<>(16);

        List<List<T>> pageData = this.getPage();

        //如果处理的数量过大重新调整下分组（异常状态导致线程等待数据过多）
        if (pageData.size() > THREAD_QUEUE_SIZE + THREAD_MAX_POOL_SIZE) {
            //重新分配下每组的处理数量，数量加1是为了容错
            this.setListPageSize(this.threadDates.size() / (THREAD_QUEUE_SIZE + THREAD_MAX_POOL_SIZE) + 1);
            pageData = this.getPage();
        }

        //实例化多线程执行任务，并把待执行数据传入进去
        Constructor<F> constructor = fClass.getConstructor();
        for (int i = 0; i < pageData.size(); i++) {
            ThreadBase<T, E> thread = constructor.newInstance();
            thread.setDelList(pageData.get(i));
            thread.setNo(i);
            futureList.add(completionService.submit(thread));
        }

        log.info("多线程进程②：多线程任务【{}】分组归集完成，共{}条-分{}组-{}条/组",
                taskName,
                getThreadDates().size(),
                futureList.size(),
                getListPageSize());
        //多线程执行结果收集
        List<E> retList = new ArrayList<>();
        for (int i = 0; i < pageData.size(); i++) {
            E result = completionService.take().get();
            retList.add(result);
        }

        log.info("多线程进程③：多线程任务【{}】执行完毕，结束时间：{}", taskName, DateUtils.nowToStr());
        return retList;
    }

}

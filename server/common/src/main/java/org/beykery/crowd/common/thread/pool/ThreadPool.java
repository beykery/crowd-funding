/**
 * 线程池
 */
package org.beykery.crowd.common.thread.pool;

/**
 *
 * @author beykery
 */
public class ThreadPool
{

    /**
     * 初始化
     */
    public ThreadPool()
    {
        init(3);
    }

    /**
     * 初始化
     *
     * @param size
     */
    public ThreadPool(int size)
    {
        init(size);
    }

    /**
     * 池
     */
    private WorkerPool pool;

    /**
     * 初始化
     */
    private void init(int size)
    {
        pool = new WorkerPool(size);
    }

    /**
     * 添加任务
     *
     * @param run
     */
    public void put(Runnable run)
    {
        pool.execute(run);
    }
}

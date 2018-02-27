/**
 * 线程池
 */
package org.beykery.crowd.common.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WorkerPool implementation
 *
 * @author grro@xsocket.org
 */
public class WorkerPool extends ThreadPoolExecutor
{

    /**
     * 默认构造
     */
    public WorkerPool()
    {
        this(3);
    }

    /**
     * constructor
     *
     * @param maxSize max worker size
     */
    public WorkerPool(int maxSize)
    {
        this(0, maxSize, Integer.MAX_VALUE, TimeUnit.SECONDS, false);
    }

    public WorkerPool(int maxSize, String name)
    {
        this(0, maxSize, Integer.MAX_VALUE, TimeUnit.SECONDS, false);
        this.setPre(name);
    }

    /**
     * constructor
     *
     * @param minSize min worker size
     * @param maxSize max worker size
     */
    public WorkerPool(int minSize, int maxSize)
    {
        this(minSize, maxSize, Integer.MAX_VALUE, TimeUnit.SECONDS, false);
    }

    /**
     * constructor
     *
     * @param minSize min worker size
     * @param maxSize max worker size
     * @param taskqueuesize the task queue size
     */
    public WorkerPool(int minSize, int maxSize, int taskqueuesize)
    {
        this(minSize, maxSize, Integer.MAX_VALUE, TimeUnit.SECONDS, taskqueuesize, false);
    }

    /**
     * constructor
     *
     * @param minSize min worker size
     * @param maxSize max worker size
     * @param keepalive the keepalive
     * @param timeunit the timeunit
     * @param isDaemon true, if worker threads are daemon threads
     */
    public WorkerPool(int minSize, int maxSize, long keepalive, TimeUnit timeunit, boolean isDaemon)
    {
        this(minSize, maxSize, keepalive, timeunit, Integer.MAX_VALUE, isDaemon);
    }

    /**
     * constructor
     *
     * @param minSize min worker size
     * @param maxSize max worker size
     * @param keepalive the keepalive
     * @param timeunit the timeunit
     * @param taskqueuesize the task queue size
     * @param isDaemon true, if worker threads are daemon threads
     */
    public WorkerPool(int minSize, int maxSize, long keepalive, TimeUnit timeunit, int taskqueuesize, boolean isDaemon)
    {
        super(minSize, maxSize, keepalive, timeunit, new WorkerPoolAwareQueue(taskqueuesize));
        DefaultThreadFactory tf = new DefaultThreadFactory(isDaemon);
        this.setThreadFactory(tf);
        ((WorkerPoolAwareQueue) getQueue()).init(this);
    }

    /**
     * 设置前置
     *
     * @param name
     */
    private void setPre(String name)
    {
        DefaultThreadFactory tf = (DefaultThreadFactory) this.getThreadFactory();
        tf.setPre(name);
    }

    @SuppressWarnings("serial")
    private static final class WorkerPoolAwareQueue extends LinkedBlockingQueue<Runnable>
    {

        private WorkerPool workerPool;

        public WorkerPoolAwareQueue(int capacity)
        {
            super(capacity);
        }

        public void init(WorkerPool workerPool)
        {
            this.workerPool = workerPool;
        }

        @Override
        public boolean offer(Runnable task)
        {
            // active count smaller than pool size?
            if (workerPool.getActiveCount() < workerPool.getPoolSize())
            {
                // add the task to the queue. The task should be handled immediately
                // because free worker threads should be available (all threads of 
                // the workerpool listens the queue for new tasks)
                return super.offer(task);
            }
            // max size reached?
            if (workerPool.getPoolSize() >= workerPool.getMaximumPoolSize())
            {
                return super.offer(task);
            } else
            {
                // return false, which forces starting a new thread. The new
                // thread performs the task and will be added to workerpool
                // after performing the task 
                // As result the workerpool is increased. If the thread reach
                // its keepalive timeout (waiting for new tasks), the thread 
                // will terminate itself
                return false;
            }
        }
    }

    private static class DefaultThreadFactory implements ThreadFactory
    {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private String namePrefix;
        private final boolean isDaemon;

        DefaultThreadFactory(boolean isDaemon)
        {
            this.isDaemon = isDaemon;
            this.namePrefix = "线程池-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r)
        {
            Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
            t.setDaemon(isDaemon);
            if (t.getPriority() != Thread.NORM_PRIORITY)
            {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }

        private void setPre(String pre)
        {
            this.namePrefix = pre + "-" + poolNumber.getAndIncrement() + "-thread-";
        }
    }
}

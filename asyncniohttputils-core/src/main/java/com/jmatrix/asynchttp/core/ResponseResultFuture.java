package com.jmatrix.asynchttp.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jmatrix
 * @date 16/2/19
 */
public class ResponseResultFuture implements ResultFuture<AsyncHttpResponse> {

    private final Lock lock = new ReentrantLock();

    private final Condition notifyCondition = lock.newCondition();

    private AsyncHandler asyncHandler;

    private AsyncHttpResponse asyncHttpResponse;

    private volatile boolean reqSucc = false;

    public ResponseResultFuture() {
        asyncHandler = new DefaultAsyncHandler();
        asyncHttpResponse = new DefaultAsyncHttpResponse();
    }

    public ResponseResultFuture(AsyncHandler asyncHandler) {
        this.asyncHttpResponse = new DefaultAsyncHttpResponse();
        this.asyncHandler = asyncHandler;
        if (this.asyncHandler == null) {
            this.asyncHandler = new DefaultAsyncHandler();
        }
    }

    public void receiveResponseBody(ResponseBodyPart bodyPart) {
        asyncHandler.onReceiveResponseBodyPart(bodyPart);
        asyncHttpResponse.addResponseBodyPart(bodyPart);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        reqSucc = true;
        asyncHandler.onComplete(this.asyncHttpResponse);
        lock.lock();
        try {
            notifyCondition.signalAll();
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public AsyncHttpResponse get() throws InterruptedException, ExecutionException {
        if (!reqSucc) {
            lock.lock();
            try {
                do {
                    if (reqSucc) {
                        break;
                    }
                    notifyCondition.await();
                } while (!reqSucc);
            } finally {
                lock.unlock();
            }
        }
        return asyncHttpResponse;
    }

    @Override
    public AsyncHttpResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (!reqSucc) {
            lock.lock();
            try {
                long remainTime = notifyCondition.awaitNanos(timeout * 1000);
                if (remainTime < 0) {
                    throw new TimeoutException("wait response future timeout.");
                }
            } finally {
                lock.unlock();
            }
        }
        return asyncHttpResponse;
    }

    public AsyncHandler getAsyncHandler() {
        return asyncHandler;
    }
}

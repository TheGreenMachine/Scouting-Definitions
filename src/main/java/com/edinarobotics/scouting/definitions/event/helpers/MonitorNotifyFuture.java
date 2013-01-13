package com.edinarobotics.scouting.definitions.event.helpers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import com.edinarobotics.scouting.definitions.event.Future;
import com.edinarobotics.scouting.definitions.event.Result;

/**
 * This class allows asynchronous access to the result of notifying event monitors
 * using {@link com.edinarobotics.scouting.definitions.event.helpers.EventFiringManager EventFiringManager}.
 * It to any exceptions that were thrown during this process.
 */
public class MonitorNotifyFuture extends Future<Void>{
	private Set<MonitorNotifyTask> notifyTasks;
	
	/**
	 * Constructs a MonitorNotifyFuture for an
	 * {@link com.edinarobotics.scouting.definitions.event.Event Event}
	 * with the given {@code eventId} that is notifying registered
	 * event monitors with a set of {@link MonitorNotifyTask} objects,
	 * {@code notifyTasks}.
	 * @param eventId The String ID of the Event whose monitors
	 * are to be notified.
	 * @param notifyTasks A set of MonitorNotifyTasks that are
	 * notifying the registered event monitors.
	 */
	public MonitorNotifyFuture(String eventId, Set<MonitorNotifyTask> notifyTasks){
		super(eventId);
		this.notifyTasks = notifyTasks;
	}
	
	/**
	 * Waits for the completion of the monitor notification process,
	 * then returns {@code null}. This method can be used as a 
	 * sort of join to the monitor notification process.
	 * This method will block until the monitor notification process
	 * is complete.
	 * @return {@code null}.
	 * @throws InterruptedException If the current thread was
	 * interrupted while waiting for the completion of the
	 * monitor notification process.
	 */
	public Void get() throws InterruptedException{
		for(MonitorNotifyTask notifyTask : notifyTasks){
			notifyTask.join();
		}
		return null;
	}
	
	/**
	 * Waits for the completion of the monitor notification process
	 * or a timeout (whichever comes sooner), then returns {@code null}.
	 * This method will block until it returns as described above, it is
	 * useful as a join to the monitor notification process.
	 * @return {@code null}.
	 * @throws TimeoutException If the specified wait time was exhausted.
	 * @throws CancellationException If the monitor notification process (not the
	 * Event itself) was cancelled.
	 * @throws ExecutionException If an exception occurred during the
	 * event firing process and outside of the event monitoring methods.
	 * @throws InterruptedException If the current thread was interrupted
	 * while waiting for the completion of the monitor notification process.
	 */
	public Void get(long timeout, TimeUnit unit) throws CancellationException, TimeoutException, ExecutionException, InterruptedException{
		return super.get(timeout, unit);
	}

	/**
	 * The monitor notification process cannot be cancelled,
	 * as such this method does nothing and always returns
	 * {@code false}.
	 * @param mayInterruptIfRunning Has no effect on this method.
	 * @return {@code false}, as required by {@link Future#cancel(boolean)}.
	 * @see Future#cancel(boolean)
	 */
	public boolean cancel(boolean mayInterruptIfRunning) {
		return false;
	}

	/**
	 * Indicates whether the monitor notification process
	 * is complete. If this  method returns {@code true},
	 * the {@link #get()} method will return immediately.
	 * @return {@code true} if the monitor notification process
	 * is complete, {@code false} otherwise.
	 */
	public boolean isDone() {
		for(MonitorNotifyTask notifyTask : notifyTasks){
			if(!notifyTask.isDone()){
				return false;
			}
		}
		return true;
	}

	/**
	 * Because the monitor notification process cannot
	 * be cancelled, this method always returns {@code false}.
	 * @return {@code false}, as required by
	 * {@link Future#isCancelled()}.
	 * @see Future#isCancelled()
	 */
	public boolean isCancelled() {
		return false;
	}

	/**
	 * Indicates whether or not an event monitoring method threw
	 * an Exception while being notified of the outcome of 
	 * the Event. If this method returns {@code true}, exceptions
	 * are available from the {@link #getExceptions()} method. This
	 * method does not block, results are available (and may change)
	 * throughout the event firing process.
	 * @return {@code true} if an event monitoring method has thrown
	 * an Exception.
	 */
	public boolean hasError() {
		return getExceptions().size() > 0;
	}
	
	/**
	 * Provides access to any exceptions thrown by
	 * event monitoring methods during the monitor notification
	 * process. This method returns an unmodifiable Set
	 * of the Exception objects thrown by the event monitoring
	 * methods.
	 * @return A Set of the Exceptions thrown by event monitoring
	 * methods during the monitor notification process.
	 * @see MonitorNotifyTask#getExceptions()
	 */
	public Set<Exception> getExceptions(){
		Set<Exception> exceptions = new HashSet<Exception>();
		for(MonitorNotifyTask notifyTask : notifyTasks){
			for(Exception exception: notifyTask.getExceptions()){
				exceptions.add(exception);
			}
		}
		return Collections.unmodifiableSet(exceptions);
	}

	/**
	 * Indicates the result of the monitor notification process.
	 * If the monitor notification process is not yet complete,
	 * this method returns {@code null}.<br/>
	 * If {@link #hasError()} returns {@code true}, this method
	 * returns {@link Result#ERROR}. However, all monitors will
	 * still have been notified.<br/>
	 * Otherwise, this method returns {@link Result#SUCCESS}.<br/>
	 * Because the monitor notification process cannot be cancelled,
	 * this method will never return {@link Result#CANCELLED}.
	 * @return A Result value as described above.
	 */
	public Result getResult() {
		if(!isDone()){
			return null;
		}
		else if(hasError()){
			return Result.ERROR;
		}
		return Result.SUCCESS;
	}
}

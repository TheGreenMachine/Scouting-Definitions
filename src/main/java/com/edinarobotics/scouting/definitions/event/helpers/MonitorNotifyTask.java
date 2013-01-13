package com.edinarobotics.scouting.definitions.event.helpers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import com.edinarobotics.scouting.definitions.event.Event;
import com.edinarobotics.scouting.definitions.event.Result;

/**
 * This Runnable handles the asynchronous notification of event monitors
 * It will submit events to {@link RegisteredEventMonitor} objects.
 */
public class MonitorNotifyTask implements Runnable{
	private Event event;
	private Result result;
	private RegisteredEventMonitor monitor;
	private Set<Exception> exceptions;
	private CountDownLatch latch;
	
	/**
	 * Creates a new MonitorNotifyTask that will notify the
	 * given {@code monitor} that the given {@code event} has
	 * completed with a status indicated by {@code result}.
	 * @param monitor The {@link RegisteredEventMonitor} to 
	 * be notified of the outcome of the Event.
	 * @param event The Event to be passed to the {@code monitor}.
	 * @param result The {@link Result} of the action represented
	 * by the given {@link Event}.
	 */
	public MonitorNotifyTask(RegisteredEventMonitor monitor, Event event, Result result){
		this.event = event;
		this.result = result;
		this.monitor = monitor;
		exceptions = new HashSet<Exception>();
		latch = new CountDownLatch(1);
	}

	/**
	 * This method is called by this monitor's
	 * notifying thread to notify the given
	 * monitor of the outcome of an Event.
	 * This method will call
	 * {@link RegisteredEventMonitor#notifyMonitor(Event, Result)}
	 * with the given Event and Result from
	 * {@link #MonitorNotifyTask(RegisteredEventMonitor, Event, Result)}.
	 * If an exception occurs while calling the event monitoring method,
	 * the exception is stored for retrieval by the
	 * {@link #getExceptions()} method. The exception will not
	 * rise from this method.
	 */
	public void run() {
		try{
			monitor.notifyMonitor(event, result);
		}catch(Exception e){
			exceptions.add(e);
		}finally{
			latch.countDown();
		}
	}
	
	/**
	 * Waits for the completion of the {@link #run()} method.
	 * This method will block until {@link #run()} returns and
	 * can be used as a join to this event monitoring method's
	 * notification process.
	 * @throws InterruptedException If the calling thread
	 * was interrupted while waiting for the {@link #run()}
	 * method to complete.
	 */
	public void join() throws InterruptedException{
		latch.await();
	}
	
	/**
	 * Indicates whether the {@link #run()} method has completed.
	 * If this method returns {@code true} calls to {@link #join()}
	 * will return immediately.
	 * @return {@code true} if the {@link #run()} method has
	 * finished, {@code false} otherwise.
	 */
	public boolean isDone(){
		return latch.getCount() == 0;
	}
	
	/**
	 * Returns an unmodifiable {@link Set} containing any exceptions
	 * that were thrown by the event monitoring method when it was
	 * notified of the outcome of the given {@link Event}.
	 * This method does not block and results are available (and may change)
	 * during the monitor notification process.
	 * @return A Set containing the exceptions thrown by the event monitoring
	 * method.
	 * @see #run()
	 */
	public Set<Exception> getExceptions(){
		return Collections.unmodifiableSet(exceptions);
	}
}

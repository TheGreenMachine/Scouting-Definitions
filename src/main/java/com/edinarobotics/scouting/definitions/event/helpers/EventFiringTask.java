package com.edinarobotics.scouting.definitions.event.helpers;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import com.edinarobotics.scouting.definitions.event.Event;
import com.edinarobotics.scouting.definitions.event.Cancellable;
import com.edinarobotics.scouting.definitions.event.ListenerPriority;

/**
 * This Runnable handles the asynchronous firing of events
 * to related listeners. It will submit events to {@link RegisteredEventListener}
 * objects in the correct order.
 */
public class EventFiringTask implements Runnable{
	private Event event;
	private Map<ListenerPriority, Set<RegisteredEventListener>> listeners;
	private ListenerPriority[] priorityOrder;
	private Set<Exception> exceptions;
	private CountDownLatch latch;
	
	/**
	 * Create a new EventFiringTask that will fire the given {@code event} to
	 * the {@link RegisteredEventListener} objects in {@code listeners}.
	 * @param event The Event to be fired.
	 * @param listeners The RegisteredEventListener objects to which the event
	 * is to be distributed.
	 */
	public EventFiringTask(Event event, Map<ListenerPriority, Set<RegisteredEventListener>> listeners){
		this.event = event;
		this.listeners = listeners;
		//Sort ListenerPriority values by their call order (declared order)
		priorityOrder = ListenerPriority.values();
		Arrays.sort(priorityOrder);
		exceptions = new HashSet<Exception>();
		latch = new CountDownLatch(1);
	}
	
	/**
	 * This method is called by the event firing thread to distribute
	 * the given event to the proper RegisteredEventListener objects.
	 * This method will call the invoke the RegisteredEventListener objects
	 * in the proper order. If an Exception occurs when passing the event
	 * to a RegisteredEventListener, the Exception is stored for retrieval using
	 * {@link #getExceptions()} and the cancellation state of the Event is restored
	 * to its value prior to invoking the failed RegisteredEventListener. The
	 * Exception will not rise from this method.
	 */
	public void run(){
		try{
			for(ListenerPriority priority : priorityOrder){
				//Use each priority in the correct order
				for(RegisteredEventListener listener : listeners.get(priority)){
					//Call each RegisteredEventListener at each priority
					boolean cancelled = false;
					if(event instanceof Cancellable){
						//If event is cancellable, store its cancellation state
						//In case of exception, cancellation will be rolled back
						cancelled = ((Cancellable)event).isCancelled();
					}
					try{
						listener.fireEvent(event);
					}catch(Exception e){
						exceptions.add(e);
						if(event instanceof Cancellable){
							//An exception occurred, roll back the cancellation
							((Cancellable)event).setCancelled(cancelled);
						}
					}
				}
			}
		}finally{
			latch.countDown();
		}
	}
	
	/**
	 * Returns the Event which was distributed to the
	 * RegisteredEventListener objects. Calling this method blocks
	 * until the {@link #run()} method has been called and completed.
	 * @return The Event that was passed to interested
	 * RegisteredEventListener objects.
	 * @throws InterruptedException If the current thread
	 * was interrupted while waiting for the {@link #run()} method to
	 * complete.
	 */
	public Event get() throws InterruptedException{
		latch.await();
		return event;
	}
	
	/**
	 * Indicates whether the {@link #run()} method has finished.
	 * If this method returns {@code true}, calls to {@link #get()}
	 * will return immediately.
	 * @return {@code true} if the {@link #run()} method
	 * has finished, {@code false} otherwise.
	 */
	public boolean isDone(){
		return latch.getCount() == 0;
	}
	
	/**
	 * Returns an unmodifiable Set containing exceptions
	 * thrown during the firing of the given Event.
	 * This method does not block and results are available
	 * during the event-firing sequence. The Set returned
	 * by this method may change throughout the event
	 * handling process.
	 * @return A Set containing the exceptions thrown by
	 * {@link RegisteredEventListener} objects during the
	 * event handling process.
	 * @see #run()
	 */
	public Set<Exception> getExceptions(){
		return Collections.unmodifiableSet(exceptions);
	}
}

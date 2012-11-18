package com.edinarobotics.scouting.definitions.event.helpers;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.edinarobotics.scouting.definitions.event.Event;
import com.edinarobotics.scouting.definitions.event.EventListener;
import com.edinarobotics.scouting.definitions.event.EventRegistrar;
import com.edinarobotics.scouting.definitions.event.Listener;
import com.edinarobotics.scouting.definitions.event.ListenerPriority;

/**
 * This class provides a full implementation of an {@link EventRegistrar}
 * and can be used to fire {@link Event} objects.
 * It can be used to easily add event firing capabilities to a class. It will
 * handle all requirements of the Event system.
 */
public class EventFiringManager implements EventRegistrar{
	private Map<ListenerPriority, Set<RegisteredEventListener>> listeners;
	private ExecutorService execServ;
	
	/**
	 * Creates a new EventFiringManager with no registered events.
	 */
	public EventFiringManager(){
		execServ = Executors.newCachedThreadPool();
		//Internal map used to store registered listeners
		listeners = new HashMap<ListenerPriority, Set<RegisteredEventListener>>();
		for(ListenerPriority priority : ListenerPriority.values()){
			listeners.put(priority, new HashSet<RegisteredEventListener>());
		}
	}
	
	/**
	 * Registers all methods tagged with an {@link EventListener} annotation
	 * in the given object for events.
	 * All methods in {@code listener} will receive any possible {@link Event}
	 * objects that are fired from {@link #fireEvent(Event)}.
	 * @param listener The {@link Listener} to be registered for events.
	 */
	public void registerEvents(Listener listener){
		for(Method method : listener.getClass().getMethods()){
			if(method.isAnnotationPresent(EventListener.class)){
				RegisteredEventListener newListener = new RegisteredEventListener(method, listener);
				listeners.get(newListener.getPriority()).add(newListener);
			}
		}
	}
	
	/**
	 * Unregisters all event handling methods in the given
	 * {@link Listener} object.
	 * If the given Listener object is not registered for any events
	 * with this EventFiringManager, no changes will occur.
	 * @param listener The Listener to unregister from all events.
	 */
	public void unregisterListener(Listener listener){
		for(ListenerPriority priority : listeners.keySet()){
			Set<RegisteredEventListener> toRemove = new HashSet<RegisteredEventListener>();
			for(RegisteredEventListener regListener : listeners.get(priority)){
				if(regListener.getListener() == listener){
					toRemove.add(regListener);
				}
			}
			for(RegisteredEventListener regListener : toRemove){
				listeners.get(priority).remove(regListener);
			}
		}
	}
	
	/**
	 * Fires the given Event and distributes it to all relevant
	 * registered Event handling methods. This method will correctly
	 * route any any type of Event even if no handling methods exist for
	 * an Event. It will distribute the Event to all listening methods
	 * in the correct order and will respect their priorities and
	 * {@code ignoreCancelled} values. The event firing process
	 * is handled in a new thread.
	 * @param event The Event to be distributed to all relevant
	 * event handling methods.
	 * @return An {@link EventFiringFuture} providing access
	 * to the results of this event firing process.
	 * @see EventListener#priority()
	 * @see EventListener#ignoreCancelled()
	 */
	public EventFiringFuture fireEvent(Event event){
		EventFiringTask fireTask = new EventFiringTask(event, listeners);
		execServ.submit(fireTask);
		return new EventFiringFuture(event.getId(), fireTask);
	}
}

package com.edinarobotics.scouting.definitions.event.helpers;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.edinarobotics.scouting.definitions.event.Event;
import com.edinarobotics.scouting.definitions.event.EventListener;
import com.edinarobotics.scouting.definitions.event.EventMonitor;
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
	private Set<RegisteredEventMonitor> monitors;
	private ExecutorService execServ;
	private Lock listenerLock;
	private Lock monitorLock;
	
	/**
	 * Creates a new EventFiringManager with no registered events.
	 */
	public EventFiringManager(){
		execServ = Executors.newCachedThreadPool();
		//Internal map used to store registered listeners
		listeners = new HashMap<ListenerPriority, Set<RegisteredEventListener>>();
		monitors = new HashSet<RegisteredEventMonitor>();
		for(ListenerPriority priority : ListenerPriority.values()){
			listeners.put(priority, new HashSet<RegisteredEventListener>());
		}
		listenerLock = new ReentrantLock();
		monitorLock = new ReentrantLock();
	}
	
	/**
	 * Registers all methods tagged with an {@link EventListener} or an
	 * {@link EventMonitor} annotation in the given object for events.
	 * All methods in {@code listener} will receive any possible {@link Event}
	 * objects that are fired from {@link #fireEvent(Event)}.
	 * @param listener The {@link Listener} to be registered for events.
	 */
	public void registerEvents(Listener listener){
		listenerLock.lock();
		try{
			for(Method method : listener.getClass().getMethods()){
				if(method.isAnnotationPresent(EventListener.class)){
					RegisteredEventListener newListener = new RegisteredEventListener(method, listener);
					listeners.get(newListener.getPriority()).add(newListener);
				}
			}
		}finally{
			listenerLock.unlock();
		}
		monitorLock.lock();
		try{
			for(Method method : listener.getClass().getMethods()){
				if(method.isAnnotationPresent(EventMonitor.class)){
					RegisteredEventMonitor newMonitor = new RegisteredEventMonitor(method, listener);
					monitors.add(newMonitor);
				}
			}
		}finally{
			monitorLock.unlock();
		}
	}
	
	/**
	 * Unregisters all event handling or monitoring methods in the given
	 * {@link Listener} object.
	 * If the given Listener object is not registered for any events
	 * with this EventFiringManager, no changes will occur.
	 * @param listener The Listener to unregister from all events.
	 */
	public void unregisterListener(Listener listener){
		listenerLock.lock();
		try{
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
		}finally{
			listenerLock.unlock();
		}
		monitorLock.lock();
		try{
			Set<RegisteredEventMonitor> toRemove = new HashSet<RegisteredEventMonitor>();
			for(RegisteredEventMonitor monitor : monitors){
				if(monitor.getListener() == listener){
					toRemove.add(monitor);
				}
			}
			for(RegisteredEventMonitor monitor : toRemove){
				monitors.remove(monitor);
			}
		}finally{
			monitorLock.unlock();
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
		EventFiringTask fireTask = new EventFiringTask(event, getListenerMapCopy());
		execServ.submit(fireTask);
		return new EventFiringFuture(event.getId(), fireTask, getMonitorSetCopy(), execServ);
	}
	
	/**
	 * Returns a copy of the internal registered listener map, {@code listeners}.
	 * This map is not connected to {@code listeners} and is suitable to pass
	 * to other objects (such as an {@link EventFiringTask}).
	 * @return A copy of the registered listener map, {@code listeners}.
	 */
	private Map<ListenerPriority, Set<RegisteredEventListener>> getListenerMapCopy(){
		Map<ListenerPriority, Set<RegisteredEventListener>> newMap = new HashMap<ListenerPriority, Set<RegisteredEventListener>>();
		for(ListenerPriority priority : ListenerPriority.values()){
			newMap.put(priority, new HashSet<RegisteredEventListener>());
		}
		listenerLock.lock();
		try{
			for(ListenerPriority priority : ListenerPriority.values()){
				newMap.put(priority, new HashSet<RegisteredEventListener>(listeners.get(priority)));
			}
		}finally{
			listenerLock.unlock();
		}
		return newMap;
	}
	
	/**
	 * Returns a copy of the internal registered monitor set, {@code monitors}.
	 * This set is not connected to {@code monitors} and is suitable to pass
	 * to other objects (such as an {@link MonitorNotifyTask}).
	 * @return A copy of the registered monitor map, {@code monitors}.
	 */
	private Set<RegisteredEventMonitor> getMonitorSetCopy(){
		monitorLock.lock();
		try{
			return new HashSet<RegisteredEventMonitor>(monitors);
		}finally{
			monitorLock.unlock();
		}
	}
}

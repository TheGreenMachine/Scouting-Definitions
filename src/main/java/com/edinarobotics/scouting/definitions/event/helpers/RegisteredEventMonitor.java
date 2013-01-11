package com.edinarobotics.scouting.definitions.event.helpers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.edinarobotics.scouting.definitions.event.Event;
import com.edinarobotics.scouting.definitions.event.EventMonitor;
import com.edinarobotics.scouting.definitions.event.Listener;
import com.edinarobotics.scouting.definitions.event.Result;

/**
 * This class handles the reflection tasks of calling an
 * {@link EventMonitor} tagged event monitoring method.
 * It examines the arguments of handling methods to determine
 * the correct {@link Event} type to send.
 * It provides method to invoke these monitor methods
 * and to provide events to them for processing.
 */
public class RegisteredEventMonitor {
	private Class<? extends Event> eventType;
	private Method monitorMethod;
	private Listener listener;
	
	/**
	 * Constructs a new RegisteredEventMonitor surrounding the
	 * given {@code monitorMethod} of the provided {@code listener} object.
	 * @param monitorMethod The event monitoring method to be wrapped
	 * by this RegisteredEventMonitor.
	 * @param listener The object on which {@code monitorMethod} is to be called.
	 * @throws IllegalArgumentException If {@code monitorMethod} does not have
	 * an EventMonitor annotation or if {@code monitorMethod} does not accept an
	 * {@link Event} subclass and a {@link Result} value as arguments.
	 */
	public RegisteredEventMonitor(Method monitorMethod, Listener listener){
		//Attempt to get the EventMonitor annotation on this method
		EventMonitor monitorAnnotation = monitorMethod.getAnnotation(EventMonitor.class);
		if(monitorAnnotation == null){
			throw new IllegalArgumentException("Monitor method does not have an EventMonitor annotation.");
		}
		//Get the parameter types of the method
		Class<?>[] monitorMethodParameters = monitorMethod.getParameterTypes();
		//Verify that the parameters are valid for an event-monitoring method
		if(monitorMethodParameters.length != 2 || !Event.class.isAssignableFrom(monitorMethodParameters[0]) ||
				!Result.class.isAssignableFrom(monitorMethodParameters[1])){
			//Parameters are invalid, throw exception
			throw new IllegalArgumentException("Monitor method signature is invalid. Cannot accept (Event, Result).");
		}
		//Store the type of the Event-subclass parameter
		eventType = (Class<? extends Event>)monitorMethodParameters[0];
		//Store the monitor method in monitorMethod
		this.monitorMethod = monitorMethod;
		//Make sure that monitor method is accessible
		if(!monitorMethod.isAccessible()){
			monitorMethod.setAccessible(true);
		}
		//Store the listener object in listener
		this.listener = listener;
	}
	
	/**
	 * Returns the {@link Listener} object to which this
	 * RegisteredEventMonitor is attached.
	 * @return The Listener object to which this event monitoring
	 * method is attached.
	 */
	public Listener getListener(){
		return listener;
	}
	
	/**
	 * Returns the class of the type of Event accepted
	 * by this RegisteredEventMonitor.
	 * @return The Class object representing the type
	 * of Event accepted by this RegisteredEventMonitor.
	 */
	public Class<? extends Event> getEventType(){
		return eventType;
	}
	
	/**
	 * Invokes the method wrapped by this RegisteredEventMonitor with the
	 * given Event.
	 * This method performs <em>no</em> checks. It will directly invoke the
	 * method wrapped by this RegisteredEventMonitor with the provided
	 * Event.
	 * @param event The event to be passed to the event monitoring method.
	 * @param result The result of the action represented by {@code event}. This
	 * value will be passed to the monitoring method.
	 * @throws IllegalAccessException If the event handling method is inaccessible.
	 * @throws IllegalArgumentException If the given Event is not a suitable argument
	 * for this method.
	 * @throws InvocationTargetException If the underlying method throws an exception.
	 * @see Method#invoke(Object, Object...)
	 */
	public void invoke(Event event, Result result) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		monitorMethod.invoke(getListener(), event, result);
	}
	
	/**
	 * Handles sending Event objects to the underlying method of this
	 * RegisteredEventMonitor.
	 * This method verifies the parameter types of the underlying method
	 * before submitting the Event object. It is generally safe to submit
	 * any type of Event object to this method. 
	 * @param event The Event to be sent to the underlying method of this
	 * RegisteredEventMonitor.
	 * @param result The result of the action represented by {@code event}. This
	 * value will be passed to the monitoring method.
	 * @throws IllegalAccessException If the event monitoring method is inaccessible.
	 * @throws IllegalArgumentException If the given Event is not a suitable argument
	 * for this method (this issue should be prevented by fireEvent).
	 * @throws InvocationTargetException If the underlying method throws an exception.
	 * @see #invoke(Event, Result)
	 */
	public void notifyMonitor(Event event, Result result) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if(getEventType().isAssignableFrom(event.getClass())){
			invoke(event, result);
		}
	}
}

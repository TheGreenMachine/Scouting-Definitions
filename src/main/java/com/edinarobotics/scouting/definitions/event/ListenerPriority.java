package com.edinarobotics.scouting.definitions.event;

/**
 * The values in this enum represent the possible priorities with which
 * an event listener can be registered.
 * The priority with which a listener is registered affects when the listener
 * will receive the event as well as what control it has over the event's
 * outcome.
 */
public enum ListenerPriority {
	
	/**
	 * The listener is of very low importance.
	 * Listeners with this priority are run first and their actions on the
	 * event may be undone by subsequent listeners.
	 */
	LOWEST(0),
	
	/**
	 * The listener of of low importance.
	 * Listeners with this priority are run second.
	 */
	LOW(1),
	
	/**
	 * The listener is of normal importance.
	 * This is the default value with which event listeners are registered.
	 * Listeners with this priority are run after the {@link #LOWEST} and {@link #LOW}
	 * priorities.
	 */
	NORMAL(2),
	
	/**
	 * The listener is of high importance.
	 * Listeners with this priority are run after {@link #NORMAL} priority listeners.
	 */
	HIGH(3),
	
	/**
	 * The listener is of critical importance and <em>must</em> have final say in what
	 * happens to the event.
	 * Listeners with this priority are run last and have final say in the outcome of
	 * the event.
	 */
	HIGHEST(4),
	
	/**
	 * The listener is notified after the event is completed (on success or failure).
	 * Listeners with this priority have no say in the outcome of the event and
	 * are used to monitor the outcome of an event.<br/>
	 * No modifications should be made to events under this priority!
	 */
	MONITOR(5);
	
	private final int order;
	
	private ListenerPriority(int order){
		this.order = order;
	}
	
	/**
	 * Returns a number indicating the order in which a listener with this priority should
	 * be called. A value of 0 indicates that this priority should be called first, higher
	 * values should be called later.
	 * @return A number indicating the order in which a listener with a priority should be called.
	 */
	public int getCallOrder(){
		return order;
	}
}

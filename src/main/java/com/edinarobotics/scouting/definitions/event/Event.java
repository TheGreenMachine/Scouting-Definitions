package com.edinarobotics.scouting.definitions.event;

import java.util.UUID;

/**
 * This class represents events used throughout the scouting system.
 */
public abstract class Event {
	private final String id;
	
	/**
	 * This constructor handles some default setup tasks for Events.
	 * For example, It assigns a randomly generated, unique ID value to each
	 * Event object.
	 */
	public Event(){
		this.id = UUID.randomUUID().toString();
	}
	
	/**
	 * This constructor handles some default setup tasks for Events.
	 * It allows manually setting the id for {@link Event} objects.
	 * Use it only when several events must have the same ID value.
	 * @param id The {@link String} id value for this {@link Event} object.
	 */
	public Event(String id){
		this.id = id;
	}
	
	/**
	 * Returns the ID value assigned to this event as a {@link String}.
	 * All {@link Event} objects have IDs associated with them. These ID values can
	 * be used to identify the method call that produced this event.
	 * @return A {@link String} object representing the ID for this {@link Event}
	 * object.
	 */
	public final String getId(){
		return id;
	}
}

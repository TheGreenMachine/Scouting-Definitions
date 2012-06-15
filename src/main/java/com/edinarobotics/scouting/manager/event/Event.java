package com.edinarobotics.scouting.manager.event;

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
		id = UUID.randomUUID().toString().toLowerCase();
	}
	
	/**
	 * Returns the random, unique ID value assigned to this event as a {@link String}.
	 * All {@link Event} objects have unique IDs associated to them.
	 * @return A {@link String} object representing the unique ID for this {@link Event}
	 * object.
	 */
	public final String getId(){
		return id;
	}
}

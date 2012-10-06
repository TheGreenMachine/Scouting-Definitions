package com.edinarobotics.scouting.definitions.database.events;

import com.edinarobotics.scouting.definitions.database.changes.EntryEditChange;

/**
 * This event is fired by {@link com.edinarobotics.scouting.definitions.database.Database Database}
 * implementations whenever a table is modified by a {@link EntryEditChange} object in a
 * {@link com.edinarobotics.scouting.definitions.database.changes.Transaction Transaction}.
 */
public class EntryEditChangeEvent extends DatabaseChangeEvent{
	
	/**
	 * Constructs a EntryEditChangeEvent representing the given
	 * {@link EntryEditChange} object.
	 * @param change The EntryEditChange to be stored in this
	 * EntryEditChangeEvent.
	 */
	public EntryEditChangeEvent(EntryEditChange change){
		super(change);
	}
	
	/**
	 * Allows access to the {@link EntryEditChange} object stored in this EntryEditChangeEvent.
	 * @return The EntryEditChange object stored in this EntryEditChangeEvent.
	 */
	public EntryEditChange getChange(){
		return (EntryEditChange)super.getChange();
	}
}
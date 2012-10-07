package com.edinarobotics.scouting.definitions.database.events;

import com.edinarobotics.scouting.definitions.database.changes.TableCreationChange;

/**
 * This event is fired by {@link com.edinarobotics.scouting.definitions.database.Database Database}
 * implementations whenever a table is modified by a {@link TableCreationChange} object in a
 * {@link com.edinarobotics.scouting.definitions.database.changes.Transaction Transaction}.
 */
public class TableCreationChangeEvent extends DatabaseChangeEvent{
	
	/**
	 * Constructs a TableCreationChangeEvent representing the given
	 * {@link TableCreationChange} object.
	 * @param change The TableCreationChange to be stored in this
	 * TableCreationChangeEvent.
	 */
	public TableCreationChangeEvent(TableCreationChange change){
		super(change);
	}
	
	/**
	 * Constructs a TableCreationChangeEvent representing the given {@link TableCreationChange} object and
	 * having the given String ID value.
	 * @param change The TableCreationChange to be stored in this TableCreationChangeEvent.
	 * @param id The String ID value to be assigned to this event.
	 * @see com.edinarobotics.scouting.definitions.event.CancellableEvent#CancellableEvent(String)
	 */
	public TableCreationChangeEvent(TableCreationChange change, String id){
		super(change, id);
	}

	/**
	 * Allows access to the {@link TableCreationChange} object stored in this TableCreationChangeEvent.
	 * @return The TableCreationChange object stored in this TableCreationChangeEvent.
	 */
	public TableCreationChange getChange(){
		return (TableCreationChange)super.getChange();
	}
}

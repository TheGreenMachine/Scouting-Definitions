package com.edinarobotics.scouting.definitions.database.events;

import com.edinarobotics.scouting.definitions.database.changes.TableDeletionChange;

/**
 * This event is fired by {@link com.edinarobotics.scouting.definitions.database.Database Database}
 * implementations whenever a table is modified by a {@link TableDeletionChange} object in a
 * {@link com.edinarobotics.scouting.definitions.database.changes.Transaction Transaction}.
 */
public class TableDeletionChangeEvent extends DatabaseChangeEvent{
	
	/**
	 * Constructs a TableDeletionChangeEvent representing the given
	 * {@link TableDeletionChange} object.
	 * @param change The TableDeletionChange to be stored in this
	 * TableDeletionChangeEvent.
	 */
	public TableDeletionChangeEvent(TableDeletionChange change){
		super(change);
	}
	
	/**
	 * Constructs a TableDeletionChangeEvent representing the given {@link TableDeletionChange} object and
	 * having the given String ID value.
	 * @param change The TableDeletionChange to be stored in this TableDeletionChangeEvent.
	 * @param id The String ID value to be assigned to this event.
	 * @see com.edinarobotics.scouting.definitions.event.CancellableEvent#CancellableEvent(String)
	 */
	public TableDeletionChangeEvent(TableDeletionChange change, String id){
		super(change, id);
	}
	
	/**
	 * Allows access to the {@link TableDeletionChange} object stored in this TableDeletionChangeEvent.
	 * @return The TableDeletionChange object stored in this TableDeletionChangeEvent.
	 */
	public TableDeletionChange getChange(){
		return (TableDeletionChange)super.getChange();
	}
}

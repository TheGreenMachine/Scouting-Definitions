package com.edinarobotics.scouting.definitions.database.events;

import com.edinarobotics.scouting.definitions.database.changes.ColumnInsertionChange;

public class ColumnInsertionChangeEvent extends DatabaseChangeEvent{
	
	/**
	 * Constructs a ColumnInsertionChangeEvent representing the given
	 * {@link ColumnInsertionChange} object.
	 * @param change The ColumnInsertionChange to be stored in this
	 * ColumnInsertionChangeEvent.
	 */
	public ColumnInsertionChangeEvent(ColumnInsertionChange change){
		super(change);
	}
	
	/**
	 * Allows access to the {@link ColumnInsertionChange} object stored in this ColumnInsertionChangeEvent.
	 * @return The ColumnInsertionChange object stored in this ColumnInsertionChangeEvent.
	 */
	public ColumnInsertionChange getChange(){
		return (ColumnInsertionChange)super.getChange();
	}
}

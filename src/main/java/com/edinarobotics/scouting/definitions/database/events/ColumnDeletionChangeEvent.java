package com.edinarobotics.scouting.definitions.database.events;

import com.edinarobotics.scouting.definitions.database.changes.ColumnDeletionChange;

public class ColumnDeletionChangeEvent extends DatabaseChangeEvent{

	/**
	 * Constructs a ColumnDeletionChangeEvent representing the given
	 * {@link ColumnDeletionChange} object.
	 * @param change The ColumnDeletionChange to be stored in this
	 * ColumnDeletionChangeEvent.
	 */
	public ColumnDeletionChangeEvent(ColumnDeletionChange change) {
		super(change);
	}
	
	/**
	 * Allows access to the {@link ColumnDeletionChange} object stored in the DatabaseChangeEvent.
	 * @return The ColumnDeletionChange object stored in this DatabaseChangeEvent.
	 */
	public ColumnDeletionChange getChange(){
		return (ColumnDeletionChange)super.getChange();
	}
}

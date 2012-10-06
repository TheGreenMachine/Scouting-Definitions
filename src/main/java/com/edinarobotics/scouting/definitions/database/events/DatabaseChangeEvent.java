package com.edinarobotics.scouting.definitions.database.events;

import com.edinarobotics.scouting.definitions.database.changes.Change;
import com.edinarobotics.scouting.definitions.database.references.TableReference;
import com.edinarobotics.scouting.definitions.event.CancellableEvent;

/**
 * This event is fired by {@link com.edinarobotics.scouting.definitions.database.Database Database}
 * implementations whenever a table is modified by a {@link Change} object in a
 * {@link com.edinarobotics.scouting.definitions.database.changes.Transaction Transaction}.
 */
public abstract class DatabaseChangeEvent extends CancellableEvent{
	private Change databaseChange;
	
	/**
	 * Constructs a DatabaseChangeEvent representing the given {@link Change} object.
	 * @param change The Change to be stored in this DatabaseChangeEvent.
	 */
	public DatabaseChangeEvent(Change change){
		this.databaseChange = change;
	}
	
	/**
	 * Allows access to the {@link Change} object stored in the DatabaseChangeEvent.
	 * @return The Change object stored in this DatabaseChangeEvent.
	 */
	public Change getChange(){
		return databaseChange;
	}
	
	/**
	 * Gets the {@link TableReference} of the table upon which the
	 * {@link Change} object contained in this DatabaseChangeEvent operates.
	 * @return The TableReference of the table that will be modified should the
	 * represented Change be performed.
	 * @see #getChange()
	 * @see Change#getTargetTableReference()
	 */
	public TableReference getTargetTableReference(){
		return getChange().getTargetTableReference();
	}
	
	/**
	 * Gets the String name of the table upon which the {@link Change} object
	 * contained in this DatabaseChangeEvent operates.
	 * @return The String name of the table that will be modified should the
	 * represented Change be performed.
	 * @see #getChange()
	 * @see Change#getTargetTableName()
	 */
	public String getTargetTableName(){
		return getChange().getTargetTableName();
	}
}

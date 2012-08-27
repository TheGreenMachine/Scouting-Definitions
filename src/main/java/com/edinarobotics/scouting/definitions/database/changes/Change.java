package com.edinarobotics.scouting.definitions.database.changes;

import com.edinarobotics.scouting.definitions.database.references.TableReference;

/**
 * This is a marker interface for all database
 * Change operations. Classes implementing this
 * interface can be used in {@link Transaction}
 * objects to perform operations on
 * {@link com.edinarobotics.scouting.definitions.database.Database Database}
 * implementations.
 * <br/>
 * This interface is intended for internal use only. Implementing it yourself
 * can cause severe problems. <em>Do not</em> implement this interface.
 */
public interface Change {
	/**
	 * Returns the {@link TableReference} of the table targeted by this
	 * Change implementation. The definition of "targeted" depends on the
	 * Change implementation.
	 * @return The TableReference of the table targeted by this Change.
	 */
	public TableReference getTargetTableReference();
	
	/**
	 * Returns the name of the table targeted by this Change implementation.
	 * The definition of "targeted" depends on the Change implementation.
	 * @return The {@code String} name of the table targeted by this Change.
	 */
	public String getTargetTableName();
}

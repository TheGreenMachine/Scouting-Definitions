package com.edinarobotics.scouting.definitions.database.changes;

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

}

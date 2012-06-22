package com.edinarobotics.scouting.definitions.database.types;

/**
 * Interface used as the parent for all data types available for
 * use in scouting data.<br/>
 * <em>This interface is intended mostly as a marker
 * for included data types. Do not implement it!</em>
 */
public interface Data {
	
	/**
	 * Provides a {@link String} representation of a
	 * {@link Data} object's state that can be used to
	 * reconstruct it.
	 * @return A {@link String} representing this object's
	 * current state.
	 */
	public String serializeToString();
}

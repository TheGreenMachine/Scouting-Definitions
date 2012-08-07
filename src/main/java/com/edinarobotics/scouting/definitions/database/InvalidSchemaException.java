package com.edinarobotics.scouting.definitions.database;

import com.edinarobotics.scouting.definitions.database.changes.Change;

/**
 * This exception indicates that some {@link Change} to the database
 * would produce an invalid database schema or would violate the 
 * database schema in some way. For example, attempting
 * to create two columns with the same name or attempting to
 * insert no columns into a table.
 */
@SuppressWarnings("serial")
public class InvalidSchemaException extends Exception{
	
	/**
	 * Constructs a new InvalidSchemaException with
	 * {@code null} as its detail message.
	 */
	public InvalidSchemaException(){
		super();
	}
	
	/**
	 * Constructs a new InvalidSchemaException with
	 * the specified detail message.
	 * @param message The detail message, saved for later
	 * retrieval by the {@link Throwable#getMessage()} method.
	 */
	public InvalidSchemaException(String message){
		super(message);
	}
	
	/**
	 * Constructs a new InvalidSchemaException 
	 * with the specified detail message and cause.
	 * <br/><br/>
	 * Note that the detail message associated with {@code cause}
	 * is <em>not</em> automatically incorporated in this exception's
	 * detail message.
	 * @param message The detail message, saved for later
	 * retrieval by the {@link Throwable#getMessage()} method.
	 * @param cause The cause (which is saved for later retrieval
	 * by the {@link Throwable#getCause()} method). A {@code null}
	 * value is permitted, and indicates that the cause is nonexistant
	 * or unknown.
	 */
	public InvalidSchemaException(String message, Throwable cause){
		super(message, cause);
	}
	
	/**
	 * Constructs a new InvalidSchemaException with the
	 * specified cause and a detail message of
	 * {@code (cause==null ? null : cause.toString())}
	 * (which typically contains the class and detail message of
	 * {@code cause}). This constructor is useful for exceptions
	 * that are little more than wrappers for other throwables.
	 * @param cause The cause (which is saved for later retrieval
	 * by the {@link Throwable#getCause()} method). A {@code null}
	 * value is permitted and indicates that the cause is nonexistant
	 * or unknown.
	 */
	public InvalidSchemaException(Throwable cause){
		super(cause);
	}
}

package com.edinarobotics.scouting.definitions.database;

import com.edinarobotics.scouting.definitions.database.changes.Change;
import com.edinarobotics.scouting.definitions.database.types.Data;

/**
 * This exception indicates that some {@link Change} to
 * a {@link Database} was attempting to insert an incorrect
 * type of {@link Data} object into a {@link Database} entry.
 */
@SuppressWarnings("serial")
public class InvalidTypeException extends Exception{
	
	/**
	 * Constructs a new InvalidTypeException with
	 * {@code null} as its detail message.
	 */
	public InvalidTypeException(){
		super();
	}
	
	/**
	 * Constructs a new InvalidTypeException with
	 * the specified detail message.
	 * @param message The detail message, saved for later
	 * retrieval by the {@link Throwable#getMessage()} method.
	 */
	public InvalidTypeException(String message){
		super(message);
	}
	
	/**
	 * Constructs a new InvalidTypeException 
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
	public InvalidTypeException(String message, Throwable cause){
		super(message, cause);
	}
	
	/**
	 * Constructs a new InvalidTypeException with the
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
	public InvalidTypeException(Throwable cause){
		super(cause);
	}
}

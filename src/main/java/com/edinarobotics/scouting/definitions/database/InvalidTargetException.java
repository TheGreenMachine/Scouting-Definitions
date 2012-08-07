package com.edinarobotics.scouting.definitions.database;

import com.edinarobotics.scouting.definitions.database.changes.Change;

/**
 * This exception indicates that the target of 
 * a {@link Change} was invalid for some reason.
 * The target could be invalid because the target
 * is nonexistent or because some type of target
 * is not allowed.
 */
@SuppressWarnings("serial")
public class InvalidTargetException extends Exception{
	
	/**
	 * Constructs a new InvalidTargetException with
	 * {@code null} as its detail message.
	 */
	public InvalidTargetException(){
		super();
	}
	
	/**
	 * Constructs a new InvalidTargetException with
	 * the specified detail message.
	 * @param message The detail message, saved for later
	 * retrieval by the {@link Throwable#getMessage()} method.
	 */
	public InvalidTargetException(String message){
		super(message);
	}
	
	/**
	 * Constructs a new InvalidTargetException 
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
	public InvalidTargetException(String message, Throwable cause){
		super(message, cause);
	}
	
	/**
	 * Constructs a new InvalidTargetException with the
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
	public InvalidTargetException(Throwable cause){
		super(cause);
	}
}

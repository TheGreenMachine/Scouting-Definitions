package com.edinarobotics.scouting.definitions.database;

/**
 * This exception indicates that a {@link Row} object
 * does not contain some column.
 */
@SuppressWarnings("serial")
public class NoSuchColumnException extends Exception{
	
	/**
	 * Constructs a new NoSuchColumnException with
	 * {@code null} as its detail message.
	 */
	public NoSuchColumnException(){
		super();
	}
	
	/**
	 * Constructs a new NoSuchColumnException with
	 * the specified detail message.
	 * @param message The detail message, saved for later
	 * retrieval by the {@link Throwable#getMessage()} method.
	 */
	public NoSuchColumnException(String message){
		super(message);
	}
	
	/**
	 * Constructs a new NoSuchColumnException 
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
	public NoSuchColumnException(String message, Throwable cause){
		super(message, cause);
	}
	
	/**
	 * Constructs a new NoSuchColumnException with the
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
	public NoSuchColumnException(Throwable cause){
		super(cause);
	}
}

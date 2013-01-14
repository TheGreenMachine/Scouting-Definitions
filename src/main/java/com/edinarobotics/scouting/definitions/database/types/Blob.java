package com.edinarobotics.scouting.definitions.database.types;

import java.sql.SQLException;

/**
 * Defines a blob data type for use in scouting data.
 * This data can store a blob value that can be stored
 * in a Java class {@code Blob} value.
 * Many of its methods are implemented similarly to
 * the methods in {@link java.sql.Blob}.<br/>
 * @see java.lang.Blob
 */
public class Blob implements Data
{
	private java.sql.Blob value;
	
	/**
	 * Constructs a new {@link blob} object representing the
	 * given {@code Blob} value.
	 * @param value The value to store in this object.
	 */
	public Blob(java.sql.Blob value)
	{
		this.value = value;
	}
	
	/**
	 * Constructs a new {@link blob} object representing the
	 * given {@code Blob} value.
	 * Takes an array of bytes to create the {@code blob}.
	 * @param value The value to store in this object.
	 */
	public Blob(byte[] array)
	{
		// Try to set the Blob to be the byte array starting at 0
		try
		{
			value.setBytes(0, array);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the {@code blob} value stored in this {@link Blob} object.
	 * @return The exact {@code blob} value stored in this object.
	 */
	public java.sql.Blob getValue()
	{
		return value;
	}
	
	/**
	 * Compares this object to another object. The result is
	 * {@code true} if and only if the argument {@code obj}
	 * is also a {@link Blob} object that
	 * contains the same {@code blob}
	 * value as this object.
	 * Uses casting to the {@link java.lang.Blob} class to accomplish the
	 * evaluation.
	 * @param obj The object to be compared to this
	 * {@link Blob} object.
	 * @return {@code true} if the objects are equal as
	 * described above, {@code false} otherwise.
	 */
	public boolean equals(Object obj)
	{
		if(obj instanceof Blob)
			return  (java.sql.Blob) obj == value;
		else
			return  false;
	}
	
	/**
	 * Returns a {@link String} object representing this
	 * {@link Blob} object's value. The value is calculated
	 * using {@link Blob#toString()}.
	 * @return A {@link String} object representing the
	 * value of this object.
	 * @see Blob#toString()
	 */
	public String toString()
	{
		return getValue().toString();
	}
	
	/**
	 * Follows the contract of {@link Data#serializeToString()}.
	 * Gives a {@link Blob} representation of this object.
	 * @return A serialized {@link String} representing the value
	 * stored in this {@link Blob} object.
	 * @see Data#serializeToString()
	 */
	public String serializeToString()
	{
		return getValue().toString();
	}
}

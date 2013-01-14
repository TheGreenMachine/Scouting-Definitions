package com.edinarobotics.scouting.definitions.database.types;

/**
 * Defines a boolean data type for use in scouting data.
 * This data can store a boolean value that can be stored
 * in a Java class {@code Boolean} value.
 * Many of its methods are implemented similarly to
 * the methods in {@link java.lang.Boolean}.<br/>
 * @see java.lang.Boolean
 */
public class Boolean implements Data
{
	private boolean value;
	private String serializedTrue = "1";
	private String serializedFalse = "0";
	
	/**
	 * Constructs a new {@link boolean} object representing the
	 * given {@code boolean} value.
	 * @param value The value to store in this object.
	 */
	public Boolean(boolean value)
	{
		this.value = value;
	}
	
	/**
	 * Constructs a new {@link boolean} object representing the
	 * given {@code String} value.
	 * @param value The value to store in this object as generated by the
	 * {@link Boolean#serializeToString()} method.
	 */
	public Boolean(String serialized)
	{
		if(serialized == serializedTrue)
			this.value = true;
		else if(serialized == serializedFalse)
			this.value = false;
		else
			new Exception();
	}
	
	/**
	 * Returns the {@code boolean} value stored in this {@link Boolean} object.
	 * @return The exact {@code boolean} value stored in this object.
	 */
	public boolean getValue()
	{
		return value;
	}
	
	/**
	 * Compares this object to another object. The result is
	 * {@code true} if and only if the argument {@code obj}
	 * is also a {@link Boolean} object that
	 * contains the same {@code boolean}
	 * value as this object.
	 * Uses casting to the {@link java.lang.Boolean} class to accomplish the
	 * evaluation.
	 * @param obj The object to be compared to this
	 * {@link Blob} object.
	 * @return {@code true} if the objects are equal as
	 * described above, {@code false} otherwise.
	 */
	public boolean equals(Object obj)
	{
		if(obj instanceof Boolean)
			return  (java.lang.Boolean) obj == value;
		else
			return  false;
	}
	
	/**
	 * Returns a {@link String} object representing this
	 * {@link Boolean} object's value. The value is calculated
	 * using {@link Boolean#toString()}.
	 * @return A {@link String} object representing the
	 * value of this object.
	 * @see Boolean#toString()
	 */
	public String toString()
	{
		return getValue() ? "true" : "false";
	}
	
	/**
	 * Follows the contract of {@link Data#serializeToString()}.
	 * Gives a {@link Boolean} representation of this object.
	 * @return A serialized {@link String} representing the value
	 * stored in this {@link Boolean} object.
	 * @see Data#serializeToString()
	 */
	public String serializeToString()
	{
		return getValue() ?
				serializedTrue : serializedFalse;
	}
}
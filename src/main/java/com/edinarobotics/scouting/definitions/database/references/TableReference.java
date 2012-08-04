package com.edinarobotics.scouting.definitions.database.references;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This class handles parsing table reference strings.
 * These are strings of the form {@code "<Table Name>"} (such as "Table") 
 * and are case-sensitive.
 * It also supports parsing the names of metatables. 
 */
public class TableReference {
	private String tableRef;
	
	/**
	 * Create a new {@link TableReference} object from the given table reference {@link String}.
	 * @param tableRef The table reference {@link String} to parse.
	 * @throws InvalidReferenceException If {@code tableRef} does not follow the correct format.
	 */
	public TableReference(String tableRef) throws InvalidReferenceException{
		Pattern tableRefPattern = Pattern.compile("^(@?[a-zA-Z0-9]+)$");
		Matcher matcher = tableRefPattern.matcher(tableRef);
		String tabName = matcher.group(1); //Get the table name
		if (!isValid(matcher, tabName)){
			throw new InvalidReferenceException("Bad table reference: "+tableRef);
		}
		this.tableRef = tabName;
	}
	
	/**
	 * This is a private method used to determine whether or not the regular expression
	 * actually matched something.
	 * @param matcher The {@link Matcher} object to check for having matched results.
	 * @param result The {@link String} that was matched by {@code matcher}. This is checked to
	 * be not {@code null} and not empty.
	 * @return {@code true} if proper values were matched, {@code false} otherwise.
	 */
	static boolean isValid(Matcher matcher, String result){
		if (!matcher.matches() || result == null || result.isEmpty()){
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the name of the table as parsed from the {@link String} in {@link #TableReference(String)}. 
	 * @return The name of the table as parsed from the reference {@link String}.
	 */
	public String getTableName(){
		return tableRef;
	}
	
	/**
	 * Returns the table reference {@link String} that can be used to construct this object.
	 * @return a {@link String} of the form {@code "<Table Name>"}.
	 */
	public String toString(){
		return tableRef;
	}
	
	/**
	 * Returns a hash code value for this object.
	 * This value is computed in accordance with {@link Object#hashCode()}.
	 * The hash code is the value of the hash code of the {@link String} returned
	 * by {@link #toString()}.
	 * @return A hash code value for this object.
	 * @see String#hashCode()
	 * @see #toString()
	 */
	public int hashCode(){
		return toString().hashCode();
	}
	
	/**
	 * Indicates whether some object is equal to this one.
	 * Objects are equal to a {@link TableReference} object if they
	 * are also a {@link TableReference} object and if their {@link #toString()} methods
	 * return the same value.
	 * @param obj The object to be compared for equality to this one.
	 * @return {@code true} if the objects are equal, {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof TableReference){
			return obj.toString().equals(toString());
		}
		return false;
	}
}

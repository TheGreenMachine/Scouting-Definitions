package com.edinarobotics.scouting.definitions.database.references;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.edinarobotics.scouting.definitions.database.types.Integer;

/**
 * This class handles parsing row reference strings.
 * These are strings of the form {@code "<Table Name>#<Integer Primary Key Value>"}
 * (such as "Table#2" or "Table#-2") and are case-sensitive.
 * @see TableReference
 */
public class RowReference {
	private TableReference tableRef;
	private Integer rowRef;
	
	/**
	 * Create a new {@link RowReference} object from the given row reference {@link String}.
	 * @param rowRef The row reference {@link String} to parse.
	 * @throws InvalidReferenceException If {@code tableRef} does not follow the correct format.
	 */
	public RowReference(String rowRef) throws InvalidReferenceException{
		Pattern rowRefPattern = Pattern.compile("^(@?[a-zA-Z0-9]+)#(-?[0-9]+)$");
		Matcher matcher = rowRefPattern.matcher(rowRef);
		String tabName = matcher.group(1); //Get the table name
		String rowName = matcher.group(2); //Get the primary key number
		if (!TableReference.isValid(matcher, tabName) || !TableReference.isValid(matcher, rowName)){
			throw new InvalidReferenceException("Bad row reference: "+rowRef);
		}
		this.tableRef = new TableReference(tabName);
		this.rowRef = new Integer(rowName);
	}
	
	/**
	 * Return the {@link TableReference} object for the table containing this column.
	 * @return The {@link TableReference} object for the table containing this column.
	 */
	public TableReference getTableReference(){
		return this.tableRef;
	}
	
	/**
	 * Return the {@link String} name of the table containing this column.
	 * This value is equivalent to {@code getTableReference().getTableName()}.
	 * @return The name of the table containing this column.
	 */
	public String getTableName(){
		return this.tableRef.getTableName();
	}
	
	/**
	 * Return the {@link Integer} value of the primary key that can be used to reference
	 * this row.
	 * @return The {@link Integer} value of the primary key for the referenced row.
	 */
	public Integer getRowPrimaryKey(){
		return rowRef;
	}
	
	/**
	 * Return the row reference {@link String} that can be used to construct this object.
	 * @return A {@link String} of the form {@code "<Table Name>#<Integer Primary Key Value>"}.
	 */
	public String toString(){
		return getTableName()+"#"+getRowPrimaryKey().toString();
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
	 * Objects are equal to a {@link RowReference} object if they
	 * are also a {@link RowReference} object and if their {@link #toString()} methods
	 * return the same value.
	 * @param obj The object to be compared for equality to this one.
	 * @return {@code true} if the objects are equal, {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof RowReference){
			return obj.toString().equals(toString());
		}
		return false;
	}
}


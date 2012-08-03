package com.edinarobotics.scouting.definitions.database.references;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This class handles parsing column reference strings.
 * These are strings of the form {@code "<Table Name>.<Column Name>"}
 * (such as "Table.Column") and are case-sensitive.
 * @see TableReference
 */
public class ColumnReference {
	private TableReference tableRef;
	private String columnRef;
	
	/**
	 * Create a new {@link ColumnReference} object from the given column reference {@link String}.
	 * @param columnRef The column reference {@link String} to parse.
	 */
	public ColumnReference(String columnRef){
		Pattern columnRefPattern = Pattern.compile("^(@?[a-zA-Z0-9]+).([a-zA-Z0-9]+)$");
		Matcher matcher = columnRefPattern.matcher(columnRef);
		String tabName = matcher.group(1); //Get the table name
		String colName = matcher.group(2); //Get the column name
		if (!TableReference.isValid(matcher, tabName) || !TableReference.isValid(matcher, colName)){
			throw new IllegalArgumentException("Bad column reference: "+columnRef);
		}
		this.tableRef = new TableReference(tabName);
		this.columnRef = colName;
	}
	
	/**
	 * Return the {@link TableReference} object for the table containing this column.
	 * @return The {@link TableReference} object for the table containing this column.
	 */
	public TableReference getTableReference(){
		return tableRef;
	}
	
	/**
	 * Return the {@link String} name of the table containing this column.
	 * This value is equivalent to {@code getTableReference().getTableName()}.
	 * @return The name of the table containing this column.
	 */
	public String getTableName(){
		return tableRef.getTableName();
	}
	
	/**
	 * Return the {@link String} name of the column referenced in this object.
	 * @return The name of the column referenced by this {@link ColumnReference} object.
	 */
	public String getColumnName(){
		return columnRef;
	}
	
	/**
	 * Return the column reference {@link String} that can be used to construct this object.
	 * @return a {@link String} of the form {@code "<Table Name>.<Column Name>"}.
	 */
	public String toString(){
		return getTableName()+"."+getColumnName();
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
	 * Objects are equal to a {@link ColumnReference} object if they
	 * are also a {@link ColumnReference} object and if their {@link #toString()} methods
	 * return the same value.
	 * @param obj The object to be compared for equality to this one.
	 * @return {@code true} if the objects are equal, {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof ColumnReference){
			return obj.toString().equals(toString());
		}
		return false;
	}
}

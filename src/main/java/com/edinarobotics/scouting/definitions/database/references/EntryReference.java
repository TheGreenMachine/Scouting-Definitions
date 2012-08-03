package com.edinarobotics.scouting.definitions.database.references;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.edinarobotics.scouting.definitions.database.types.Integer;

/**
 * This class handles parsing full entry reference strings.
 * These strings specify a specific entry (cell) in a database table.
 * They are strings of the form {@code "<Table Name>.<Column Name>#<Integer Primary Key Value>"} 
 * (such as "Table.Column#2" or "Table.Column#-2") and are case-sensitive.
 * @see TableReference
 * @see ColumnReference
 * @see RowReference
 */
public class EntryReference {
	private TableReference tableRef;
	private ColumnReference columnRef;
	private RowReference rowRef;
	
	/**
	 * Create a {@link EntryReference} object from the given entry reference {@link String}.
	 * @param elementRef The entry reference {@link String} to parse.
	 */
	public EntryReference(String elementRef){
		Pattern elementRefPattern = Pattern.compile("^(@?[a-zA-Z0-9]+).([a-zA-Z0-9]+)#(-?[0-9]+)$");
		Matcher matcher = elementRefPattern.matcher(elementRef);
		String tabName = matcher.group(1); //Get the table name
		String columnName = matcher.group(2); //Get the column name
		String rowName = matcher.group(3); //Get the primary key number
		if (!TableReference.isValid(matcher, tabName) || !TableReference.isValid(matcher, columnName) || !TableReference.isValid(matcher, rowName)){
			throw new IllegalArgumentException("Bad element reference: "+elementRef);
		}
		this.tableRef = new TableReference(tabName);
		this.columnRef = new ColumnReference(tabName+"."+columnName);
		this.rowRef = new RowReference(tabName+"#"+rowName); 
	}
	
	/**
	 * Return the {@link TableReference} object for the table containing this entry.
	 * @return The {@link TableReference} object for the table containing this entry.
	 */
	public TableReference getTableReference(){
		return this.tableRef;
	}
	
	/**
	 * Return the {@link ColumnReference} object for the column containing this entry.
	 * @return The {@link ColumnReference} object for the table containing this entry.
	 */
	public ColumnReference getColumnReference(){
		return this.columnRef;
	}
	
	/**
	 * Return the {@link RowReference} object for the row containing this entry.
	 * @return The {@link RowReference} object for the row containing this entry.
	 */
	public RowReference getRowReference(){
		return this.rowRef;
	}
	
	/**
	 * Return the {@link String} name of the table containing this entry.
	 * This value is equivalent to {@code getTableReference().getTableName()}.
	 * @return The name of the table containing this entry.
	 */
	public String getTableName(){
		return this.getTableReference().getTableName();
	}
	
	/**
	 * Return the {@link String} name of the column containing this entry.
	 * This value is equivalent to {@code getColumnReference().getColumnName()}.
	 * @return The name of the column containing this entry.
	 */
	public String getColumnName(){
		return this.getColumnReference().getColumnName();
	}
	
	/**
	 * Return the {@link Integer} value of the primary key that can be used to reference
	 * the row containing this entry.
	 * @return The {@link Integer} value of the primary key for the row containing this entry.
	 */
	public Integer getRowPrimaryKey(){
		return this.rowRef.getRowPrimaryKey();
	}
	
	/**
	 * Return the row reference {@link String} that can be used to construct this object.
	 * @return A {@link String} of the form {@code "<Table Name>.<Column Name>#<Integer Primary Key Value>"}.
	 */
	public String toString(){
		return getTableName()+"."+getColumnName()+"#"+getRowPrimaryKey();
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
	 * Objects are equal to a {@link EntryReference} object if they
	 * are also a {@link EntryReference} object and if their {@link #toString()} methods
	 * return the same value.
	 * @param obj The object to be compared for equality to this one.
	 * @return {@code true} if the objects are equal, {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof EntryReference){
			return obj.toString().equals(toString());
		}
		return false;
	}
}

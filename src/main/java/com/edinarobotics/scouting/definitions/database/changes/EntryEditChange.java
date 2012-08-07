package com.edinarobotics.scouting.definitions.database.changes;

import com.edinarobotics.scouting.definitions.database.Column;
import com.edinarobotics.scouting.definitions.database.Row;
import com.edinarobotics.scouting.definitions.database.references.ColumnReference;
import com.edinarobotics.scouting.definitions.database.references.EntryReference;
import com.edinarobotics.scouting.definitions.database.references.InvalidReferenceException;
import com.edinarobotics.scouting.definitions.database.references.RowReference;
import com.edinarobotics.scouting.definitions.database.references.TableReference;
import com.edinarobotics.scouting.definitions.database.types.Data;
import com.edinarobotics.scouting.definitions.database.types.Integer;

/**
 * This {@link Change} is used to edit the value stored in
 * a {@link com.edinarobotics.scouting.definitions.database.Database Database}
 * entry.
 * </br>
 * Database implementations have the responsibility to ensure that attempting
 * to store an incorrect {@link Data} type in an entry will fail with an
 * exception.
 */
public final class EntryEditChange implements Change{
	private EntryReference targetEntry;
	private Data newValue;
	
	/**
	 * Constructs a new EntryEditChange that will attempt to change
	 * the value stored in {@code targetEntry} to {@code newValue}.
	 * @param targetEntry The {@link EntryReference} indicating
	 * the entry to change.
	 * @param newValue The {@link Data} value that will be substituted
	 * into the target entry.
	 */
	public EntryEditChange(EntryReference targetEntry, Data newValue){
		this.targetEntry = targetEntry;
		this.newValue = newValue;
	}
	
	/**
	 * Constructs a new EntryEditChange that will attempt to change
	 * the value stored in {@code entryReference} to {@code newValue}.
	 * @param entryReference The entry reference {@code String} indicating
	 * the {@link com.edinarobotics.scouting.definitions.database.Database Database}
	 * entry to change.
	 * @param newValue The {@link Data} value that will be substituted into
	 * the target entry.
	 * @throws InvalidReferenceException If {@code entryReference} is not a valid
	 * entry reference {@code String}.
	 * @see EntryReference#EntryReference(String)
	 */
	public EntryEditChange(String entryReference, Data newValue) throws InvalidReferenceException{
		this(new EntryReference(entryReference), newValue);
	}
	
	/**
	 * Returns the {@link EntryReference} that indicates the target
	 * {@link com.edinarobotics.scouting.definitions.database.Database Database}
	 * entry.
	 * @return The EntryReference indicating what Database entry is to be changed.
	 */
	public EntryReference getTargetEntryReference(){
		return targetEntry;
	}
	
	/**
	 * Returns the {@link Data} object that is to be inserted into
	 * the target {@link com.edinarobotics.scouting.definitions.database.Database Database}
	 * entry.
	 * @return The {@link Data} object that will be inserted into the Database.
	 */
	public Data getNewValue(){
		return newValue;
	}
	
	/**
	 * Returns the {@link Class} object representing the type of the {@link Data}
	 * object that is to be inserted into a
	 * {@link com.edinarobotics.scouting.definitions.database.Database Database}
	 * entry.
	 * @return The Class object representing the type of Data that is
	 * to be substituted into the target Database entry.
	 */
	public Class<? extends Data> getNewValueType(){
		return getNewValue().getClass();
	}
	
	/**
	 * Returns the {@link TableReference} of the table containing the target entry.
	 * @return The TableReference for the table containing the target entry.
	 */
	public TableReference getTargetTableReference(){
		return getTargetEntryReference().getTableReference();
	}
	
	/**
	 * Returns the name of the table containing the target entry.
	 * @return The {@code String} name of the table containing the target entry.
	 */
	public String getTargetTableName(){
		return getTargetTableReference().getTableName();
	}
	
	/**
	 * Returns the {@link ColumnReference} of the {@link Column} containing the target entry.
	 * @return The ColumnReference for the Column that contains the target entry.
	 */
	public ColumnReference getTargetColumnReference(){
		return getTargetEntryReference().getColumnReference();
	}
	
	/**
	 * Returns the name of the {@link Column} containing the target entry.
	 * @return THe {@code String} name of the Column containing the target entry.
	 */
	public String getTargetColumnName(){
		return getTargetColumnReference().getColumnName();
	}
	
	/**
	 * Returns the {@link RowReference} of the {@link Row} containing
	 * the target entry.
	 * @return The RowReference for the row containing the target entry.
	 */
	public RowReference getTargetRowReference(){
		return getTargetEntryReference().getRowReference();
	}
	
	/**
	 * Returns the {@link Integer} primary key value of the {@link Row}
	 * containing the target entry.
	 * @return The Integer primary key value of the Row containing the
	 * target entry.
	 */
	public Integer getTargetRowPrimaryKeyValue(){
		return getTargetRowReference().getRowPrimaryKey();
	}
	
	/**
	 * Returns a hash code value for this EntryEditChange as described in
	 * {@link Object#hashCode()}.
	 * @return A hash code value for this object.
	 */
	public int hashCode(){
		return targetEntry.hashCode() + newValue.hashCode();
	}
	
	/**
	 * Determines whether some object is equal to this EntryEditChange.
	 * An {@code Object} is equal to this one if it is also an
	 * EntryEditChange and its {@link #getTargetEntryReference()} and
	 * {@link #getNewValue()} methods return values that are equal to
	 * what is returned by these methods on this object.
	 * @param obj The object to be tested for equality against this one.
	 * @return {@code true} if the objects are equal as defined above,
	 * {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof EntryEditChange){
			EntryEditChange entryObj = (EntryEditChange) obj;
			return entryObj.getTargetEntryReference().equals(getTargetEntryReference()) && entryObj.getNewValue().equals(getNewValue());
		}
		return false;
	}
}

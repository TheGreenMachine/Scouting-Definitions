package com.edinarobotics.scouting.definitions.database.changes;

import com.edinarobotics.scouting.definitions.database.Column;
import com.edinarobotics.scouting.definitions.database.references.ColumnReference;
import com.edinarobotics.scouting.definitions.database.references.InvalidReferenceException;
import com.edinarobotics.scouting.definitions.database.references.TableReference;

/**
 * This {@link Change} is used to delete a column from a
 * {@link com.edinarobotics.scouting.definitions.database.Database Database}.
 * <br/>It is important to note that deleting the primary key column from a
 * table is currently not supported (all tables must have a primary key column).
 * It is the responsibility of the Database implementation to prevent such an
 * operation.<br/>
 * The only way to accomplish changing the name of a primary key column
 * would be to delete the entire database table ({@link TableDeletionChange}) 
 * and then recreate a new table from scratch.
 */
public final class ColumnDeletionChange implements Change{
	private ColumnReference oldColRef;
	
	/**
	 * Constructs a new ColumnDeletionChange that attempts to delete
	 * the column referenced by {@code targetColumnReference}.
	 * @param targetColumnReference A {@link ColumnReference} referencing
	 * the {@link Column} to be deleted.
	 */
	public ColumnDeletionChange(ColumnReference targetColumnReference){
		this.oldColRef = targetColumnReference;
	}
	
	/**
	 * Constructs a new ColumnDeletionChange that attempts to delete
	 * the column referenced by the column reference {@code String}.
	 * @param targetColumnReference The {@code String} column reference
	 * indicating the {@link Column} to be deleted.
	 * @throws InvalidReferenceException If {@code targetColumnReference}
	 * is not a valid column reference {@code String}.
	 * @see ColumnReference#ColumnReference(String)
	 */
	public ColumnDeletionChange(String targetColumnReference) throws InvalidReferenceException{
		this(new ColumnReference(targetColumnReference));
	}
	
	/**
	 * Returns the internal {@link ColumnReference} object used to
	 * indicate the {@link Column} to be deleted.
	 * @return The ColumnReference of the Column to be deleted.
	 */
	public ColumnReference getTargetColumnReference(){
		return oldColRef;
	}
	
	/**
	 * Returns the name of the {@link Column} to be deleted.
	 * @return The {@code String} name of the Column to be deleted.
	 * @see ColumnReference#getColumnName()
	 */
	public String getTargetColumnName(){
		return getTargetColumnReference().getColumnName();
	}
	
	/**
	 * Returns the {@link TableReference} of the table containing
	 * the {@link Column} to be deleted.
	 * @return The TableReference of the table containing the
	 * Column targeted for deletion.
	 */
	public TableReference getTargetTableReference(){
		return getTargetColumnReference().getTableReference();
	}
	
	/**
	 * Returns the name of the table containing the {@link Column}
	 * to be deleted.
	 * @return The {@code String} name of the table containing the
	 * Column to be deleted.
	 */
	public String getTargetTableName(){
		return getTargetTableReference().getTableName();
	}
	
	/**
	 * Returns a hash code value for this object as defined in
	 * {@link Object#hashCode()}.
	 * @return A hash code value for this object.
	 */
	public int hashCode(){
		return oldColRef.hashCode();
	}
	
	/**
	 * Determines whether or not some object is equal to this
	 * ColumnDeletionChange.
	 * An object is equal to this one if it is also a
	 * ColumnDeletionChange and if its internal
	 * {@link ColumnReference} (from {@link #getTargetColumnReference()})
	 * is equal to this ColumnDeletionChange's internal
	 * ColumnReference.
	 * @param obj The object to be tested for equality against this one.
	 * @return {@code true} if the objects are equal as defined above,
	 * {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof ColumnDeletionChange){
			return ((ColumnDeletionChange) obj).getTargetColumnReference().equals(getTargetColumnReference());
		}
		return false;
	}
}

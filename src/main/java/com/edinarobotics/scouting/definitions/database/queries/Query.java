package com.edinarobotics.scouting.definitions.database.queries;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.edinarobotics.scouting.definitions.database.InvalidTargetException;
import com.edinarobotics.scouting.definitions.database.Row;
import com.edinarobotics.scouting.definitions.database.queries.constraints.Constraint;
import com.edinarobotics.scouting.definitions.database.references.InvalidReferenceException;
import com.edinarobotics.scouting.definitions.database.references.TableReference;

/**
 * Queries are used to extract information from a
 * {@link com.edinarobotics.scouting.definitions.database.Database Database}
 * implementation.
 * They can retrieve information from a table using {@link Constraint} objects
 * to dictate characteristics of the desired {@link Row} object results.
 */
public final class Query {
	protected TableReference tableRef;
	protected Set<Constraint> constraints;
	
	/**
	 * Constructs a new Query that operates on the table indicated
	 * by its table reference {@code String}.
	 * This creates a Query that includes no {@link Constraint} objects
	 * and will thus return every {@link Row} in the table.
	 * @param tableReference The {@code String} table reference indicating
	 * the table to be queried.
	 * @throws InvalidReferenceException If {@code tableReference} is
	 * not a valid table reference {@code String}.
	 */
	public Query(String tableReference) throws InvalidReferenceException{
		this(new TableReference(tableReference));
	}
	
	/**
	 * Constructs a new Query that operates on the table indicated
	 * by its {@link TableReference} object.
	 * This creates a Query that includes no {@link Constraint} objects
	 * and will thus return every {@link Row} in the table.
	 * @param tableReference The TableReference indicating
	 * the table to be queried.
	 */
	public Query(TableReference tableReference){
		this.tableRef = tableReference;
		constraints = new HashSet<Constraint>();
	}
	
	/**
	 * This constructor is used internally to create new Query objects
	 * with additional {@link Constraint} objects.
	 * This constructor is used by the {@link #constrain(Constraint)} method.
	 * @param workingTable The {@link TableReference} indicating the
	 * table to be queried.
	 * @param currentConstraints The {@link Set} of Constraint objects
	 * to be stored in the new Query. This Set includes the added
	 * Constraint.
	 */
	protected Query(TableReference workingTable, Set<Constraint> currentConstraints){
		this.tableRef = workingTable;
		this.constraints = currentConstraints;
	}
	
	/**
	 * This method adds a {@link Constraint} to an existing query.
	 * A Query including the Constraint is created and returned by this method.
	 * @param constraint The Constraint to be added to this Query.
	 * @return A new Query object adding Constraint, {@code constraint}.
	 * @throws InvalidTargetException If the table on which {@code constraint}
	 * operates is not the same as the table on which this Query is set to
	 * operate. Queries can only retrieve information from a single table.
	 */
	public Query constrain(Constraint constraint) throws InvalidTargetException{
		if (constraint.getTableName() == null || constraint.getTableName().equals(getTableName())){
			Set<Constraint> newConstraints = new HashSet<Constraint>(getConstraints());
			newConstraints.add(constraint);
			return new Query(getTableReference(), newConstraints);
		}
		throw new InvalidTargetException("Invalid constraint. The table "+constraint.getTableName()+" does not match "+this.tableRef.getTableName()+".");
	}
	
	/**
	 * Returns a {@link Set} of the {@link Constraint} objects contained
	 * by this Query.
	 * @return A Set of the Constraint objects that affect this Query.
	 */
	public Set<Constraint> getConstraints(){
		return Collections.unmodifiableSet(this.constraints);
	}
	
	/**
	 * Determines whether or not a {@link Row} satisfies
	 * the requirements of this Query.
	 * @param row The Row object to check against this
	 * Query.
	 * @return {@code true} if the {@link Row} satisfies
	 * this Query, {@code false} otherwise.
	 */
	public boolean satisfiesQuery(Row row){
		for(Constraint constraint : this.getConstraints()){
			if(!constraint.satisfiesConstraint(row)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns the {@link TableReference} indicating the table
	 * on which this Query operates.
	 * @return The {@link TableReference} indicating the table
	 * on which this Query operates.
	 */
	public TableReference getTableReference(){
		return tableRef;
	}
	
	/**
	 * Returns the name of the table on which this Query
	 * operates.
	 * @return The {@code String} name of the table on
	 * which this Query operates.
	 */
	public String getTableName(){
		return getTableReference().getTableName();
	}
	
	/**
	 * Returns a hash code value for this Query as defined
	 * in {@link Object#hashCode()}.
	 * @return A hash code value for this object.
	 */
	public int hashCode(){
		return tableRef.hashCode() + constraints.hashCode();
	}
	
	/**
	 * Determines whether or not some object is equal to this
	 * Query.
	 * An {@code Object} is equal to this Query if it is also a
	 * Query, if its {@link Set} of {@link Constraint} objects
	 * is equal to this Query's Set of constraints (from {@link #getConstraints()})
	 * and if its {@link TableReference} is equal to this Query's
	 * internal TableReference (from {@link #getTableReference()}).
	 * @param obj The object to be tested for equality against this one.
	 * @return {@code true} if the objects are equal as defined above,
	 * {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof Query){
			Query otherQuery = (Query) obj;
			return otherQuery.getConstraints().equals(getConstraints()) && otherQuery.getTableReference().equals(getTableReference());
		}
		return false;
	}
}

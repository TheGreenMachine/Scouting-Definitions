package com.edinarobotics.scouting.definitions.database;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import com.edinarobotics.scouting.definitions.database.references.RowReference;
import com.edinarobotics.scouting.definitions.database.types.Data;
import com.edinarobotics.scouting.definitions.database.queries.Query;

/**
 * RowSet is stores the {@link Row} objects returned by a {@link Query}.
 * It can be used to retrieve these rows as well as perform other operations
 * on them.
 */
public class RowSet implements Iterable<Row>{
	protected Set<Row> rows;
	
	/**
	 * Constructs a new RowSet using the given {@link Set} of {@link Row} objects.
	 * @param rows The rows to be stored in this RowSet.
	 * @throws InvalidSchemaException If not all Row objects share the same
	 * set of columns (A RowSet must be a subset of a table and all Row objects
	 * must have the same columns).
	 * @see Row#getColumns()
	 */
	public RowSet(Set<Row> rows) throws InvalidSchemaException{
		Set<Column> sampleColumns = ((Row) rows.toArray()[0]).getColumns();
		for(Row row : rows){
			if(!row.getColumns().equals(sampleColumns)){
				throw new InvalidSchemaException("Not all Row objects have the same set of Columns.");
			}
		}
		this.rows = new HashSet<Row>(rows);
	}
	
	/**
	 * Returns an {@link Iterator} over the {@link Row} objects
	 * in this RowSet.
	 * @return An Iterator over the Row objects in this RowSet.
	 */
	public Iterator<Row> iterator(){
		return Collections.unmodifiableSet(rows).iterator();
	}
	
	/**
	 * Returns all the values in a single {@link Column}.
	 * These values are returned as a {@link Map} of 
	 * {@link RowReference} objects to {@link Data} values.
	 * This can be used to determine what {@link Row} the values
	 * came from.
	 * @param column The Column to read.
	 * @return A Map of the values of the requested Column as described above.
	 * @throws NoSuchColumnException If the requested Column is not in this
	 * RowSet.
	 */
	public Map<RowReference, Data> getValuesInColumn(Column column) throws NoSuchColumnException{
		Map<RowReference, Data> returnMap = new HashMap<RowReference, Data>();
		for(Row row : this.rows){
			returnMap.put(row.getRowReference(), row.getColumnValue(column));
		}
		return Collections.unmodifiableMap(returnMap);
	}
	
	/**
	 * Returns a {@link Set} of all available {@link Column} objects in this RowSet.
	 * Any of these columns can be used to extract data from this RowSet.
	 * @return A Set of all the columns in this RowSet.
	 */
	public Set<Column> getColumns(){
		return Collections.unmodifiableSet(((Row)rows.toArray()[0]).getColumns());
	}
	
	/**
	 * Returns a raw {@link Set} object of the {@link Row} objects
	 * in this RowSet.
	 * This Set can be used for any advanced operations that are not
	 * directly supported by RowSet.
	 * @return A Set of the Row objects in this RowSet.
	 */
	public Set<Row> getRows(){
		return Collections.unmodifiableSet(rows);
	}
	
	/**
	 * Returns the number of {@link Row} objects in this RowSet.
	 * If this number is greater than {@link Integer#MAX_VALUE},
	 * returns {@code Integer.MAX_VALUE}.
	 * @return The number of Row objects in this RowSet.
	 * @see Set#size()
	 */
	public int size(){
		return rows.size();
	}
	
	/**
	 * Returns a hash code value for this RowSet as described
	 * in {@link Object#hashCode()}.
	 * @return A hash code value for this RowSet.
	 * @see Set#hashCode()
	 */
	public int hashCode(){
		return rows.hashCode();
	}
	
	/**
	 * Determines whether or not some object is equal to this
	 * RowSet.
	 * An object is equal to this RowSet if it is also a RowSet, and
	 * its {@link #getRows()} method returns a {@link Set} that is
	 * equal to the result of this RowSet's {@code getRows()} method.
	 * @param obj The object to be tested for equality against this RowSet.
	 * @return {@code true} if the objects are equal as defined above,
	 * {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof RowSet){
			return ((RowSet) obj).getRows().equals(getRows());
		}
		return false;
	}
}

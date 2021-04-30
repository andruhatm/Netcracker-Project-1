package repo.db.dao;

public interface DAO<T> {

	boolean save(T entity);
	
	boolean get(int id);
}

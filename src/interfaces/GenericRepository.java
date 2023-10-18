package interfaces;

public interface GenericRepository<T> {
// método abstrato
	void exportar(T obj) throws Exception;
}
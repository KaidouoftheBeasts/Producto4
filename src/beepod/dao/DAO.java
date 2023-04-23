package beepod.dao;

import beepod.modelo.Articulo;

import java.util.List;

/**
 * Interface que sirve de molde para otros interfaces.
 * @param <T>
 * @param <K>
 */
public interface DAO <T, K>{
    void insertar(T a) throws DAOException;
    void modificar(T a) throws DAOException;
    void eliminar(T a) throws DAOException;
    List<T> obtenerTodos()throws DAOException;
    T obtener(K id)throws DAOException;
    boolean existe(T a)throws DAOException;
}

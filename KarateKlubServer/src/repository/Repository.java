/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;


import java.util.List;

/**
 *
 * @author Folio1040
 */
public interface Repository<T> {
    List<T> getAll(T param) throws Exception;
    List<T> getAllWithCondition(T param) throws Exception;
    void add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param)throws Exception;
    T getOne(T param);
}

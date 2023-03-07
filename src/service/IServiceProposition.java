/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;
import java.util.List;
/**
 *
 * @author mohamed gabsi
 * @param <T>
 */
public interface IServiceProposition<T> {
    
    void insert(T t);
    void delete(int id);
    void update(T t,int id);
    List<T> readAll();
    T readById(int id);
}

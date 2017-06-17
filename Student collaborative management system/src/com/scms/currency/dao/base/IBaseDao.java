package com.scms.currency.dao.base;

import java.io.Serializable;
import java.util.List;

import com.scms.util.Page;
import com.scms.util.data.GeneralQueryHelper;
/**
 * Dao 通用操作
 * <br>save(T t)
 * <br>update(T t);
 * <br>delete(int id);
 * <br>getAll();
 * <br>findByCondition(String hql);
 * <br>findById(int id);
 * <br>事物开启 : save update dalete 
 * 
 * 
 * @author 李航_Computer
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	/**
	 * 通过QueryHelper类获取内容
	 * @param generalQueryHelper
	 * @return
	 */
	GeneralQueryHelper<T>  getByQueryHelper(GeneralQueryHelper<T> generalQueryHelper);
	
	/**
	 * 保存一个对象
	 * @param t
	 */
	void save(T t);
	/**
	 * 更新一个对象
	 * @param t
	 */
	void update(T t);
	/**
	 * 通过指定 id删除一个对象
	 * @param hql
	 */
	void delete(int id);
	
	
	void delete (T t);
	/**
	 * 执行指定的hql 语句
	 * @param hql
	 */
	void execute(String hql);
	/**
	 * 获取所有
	 * @return list<T>
	 */
	List<T> getAll();
	/**
	 * 通过指定的HQL条件查内容
	 * @param hql 条件
	 * @return List<T>
	 */
	List<T> findByCondition(String hql);
	
	/**
	 * 通过指定的HQL条件查内容  并分页
	 * @param hql
	 * @param page
	 * @return
	 */
	List<T> findByCondition(String hql,Page page);
	/**
	 * 通过id查对象
	 * @param i
	 * @return T
	 */
	T findById(int id);
	
	/**
	 * 通过id查对象
	 * @param i
	 * @return T
	 */
	T findById(Serializable id);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	int getAllCount();
	/**
	 * 通过指定的条件获取记录数
	 * @param hql
	 * @return
	 */
	int getConditionCount(String hql);
	
	
	/**
	 * 
	 * 获取分页对象 (使用默认的总记录)
	 * @param everyPage 显示多少数据
	 * @param currentPage 当前页
	 * @return
	 */
	Page getDefaultPage(int everyPage,int currentPage);
	
	/**
	 * 获取指定的记录数的分页对象
	 * 
	 * @param everyPage 显示多少数据
	 * @param currentPage 当前页
	 * @param hql 条件
	 * @return
	 */
	Page getConditionPage(int everyPage,int currentPage,String hql);
	
}

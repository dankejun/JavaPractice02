package com.lianxi.java.service;

import com.lianxi.java.been.Customer;

/**
 * @author dankejun
 * @create 2020-02-01 17:12
 * @description 对象的管理模块，内部用数组管理一组Customer对象，并提供相应的增删改查，
 * 供CustomerView调用
 */
public class CustomerList {
    private Customer[] customers;//保存客戶对象
    private int total = 0;//记录已保持客户的数目

    /**
     * 构造器，用来初始化customer数组
     * @param totalCustomer：指定customer数组的最大空间
     */
    public CustomerList(int totalCustomer) {
        customers = new Customer[totalCustomer];
    }

    /**
     * 将指定客户添加到数组中
     * @param customer：指定客户
     * @return 添加成功返回true，否则返回false
     */
    public boolean addCustomer(Customer customer) {
        if (total >= customers.length){
            return false;
        }else {
            customers[total] = customer;
            total++;
            return true;
        }

    }

    /**
     * 修改指定索引位置的客户信息
     * @param index
     * @param customer
     * @return 修改成功返回true，否则返回fales
     */
    public boolean replaceCustomer(int index, Customer customer) {
        if (index < 0 || index >+ total) {
            return false;
        }else {
            customers[index] = customer;
            return true;
        }
    }

    /**
     * 删除指定索引位置的客户信息
     * @param index
     * @return 删除成功返回true,否则返回false
     */
    public boolean deleteCustomer(int index) {
        if (index < 0 || index >= total) {
            return false;
        }else {
            //用索引位置后面的元素依次替换前面一个元素，并且让最后一个元素为null
            int i = index;
             for (; i < total-1; i++) {
                customers[i] = customers[i+1];
            }
            customers[i] = null;
            total--;
            return true;
        }
    }

    /**
     * 获取所有的客户信息
     * @return 客户信息数组
     */
    public Customer[] getAllCustomers() {
        Customer[] cust = new Customer[total];
        for (int i = 0; i < total; i++) {
            cust[i] = customers[i];
        }
        return cust;
    }

    /**
     * 获取指定索引的客户信息
     * @param index
     * @return
     */
    public Customer getCustomer(int index) {
        if (index < 0 || index >= total){
            return null;
        }else {
            return customers[index];
        }
    }

    /**
     * 获取存储的客户数量
     * @return
     */
    public int getTotal() {
        return total;
    }
}

package com.lianxi.java.ui;

import com.lianxi.java.been.Customer;
import com.lianxi.java.service.CustomerList;
import com.lianxi.java.util.CMUtility;

/**
 * @author dankejun
 * @create 2020-02-01 17:15
 * @description 主模块，负责菜单的显示和处理用户操作
 */
public class CustomerView {
    private CustomerList customerList = new CustomerList(10);

    public CustomerView(){
        Customer customer = new Customer("王涛",'男',23,"123456789","123@qq.com");
        customerList.addCustomer(customer);
    }

    public static void main(String[] args) {
        CustomerView customerView = new CustomerView();
        customerView.enterMainMune();
    }

    /**
     * 显示客户信息管理软件界面
     */
    public void enterMainMune() {
        boolean isFlag = true;
        while (isFlag) {
            System.out.println("\n------------客户信息管理系统----------------");
            System.out.println("                 1.添加客户");
            System.out.println("                 2.修改客户");
            System.out.println("                 3.删除客户");
            System.out.println("                 4.客户列表");
            System.out.println("                 5.退    出");
            System.out.print("                请选择（1-5）：");

            char mene = CMUtility.readMenuSelection();
            switch (mene) {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomer();
                    break;
                case '5':
                    System.out.print("确认是否退出（Y/N）：");
                    char isExit = CMUtility.readConfirmSelection();
                    if (isExit=='Y') {
                        isFlag = false;
                    }

            }
        }
    }
    /**
     * 添加客户信息
     */
    private void addNewCustomer() {
        System.out.println("-----------------添加客户--------------------");
        System.out.print("姓名：");
        String name = CMUtility.readString(10);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(13);
        System.out.print("邮箱：");
        String email = CMUtility.readString(30);

        //将上述数据封装到对象中
        Customer customer = new Customer(name,gender,age, phone, email);
        boolean isSuccess = customerList.addCustomer(customer);
        if (isSuccess){
            System.out.println("-----------------添加成功--------------------");
        }else {
            System.out.println("-------------客户已满，添加失败---------------");
        }
    }

    /**
     * 修改客户信息
     */
    private void modifyCustomer(){
        System.out.println("-----------------添加客户--------------------");
        Customer cust;
        int number;
        for (;;){
            System.out.print("请选择待修改用户编号（-1退出）：");
            number = CMUtility.readInt();

            if (number == -1){
                return;
            }
            cust = customerList.getCustomer(number - 1);
            if (cust == null) {
                System.out.println("无法找到指定客户！");
            }else {//找到客户
                break;
            }
        }
        //修改客户信息
        System.out.print("姓名（" + cust.getName() + "）：");
        String name = CMUtility.readString(10, cust.getName());
        System.out.print("性别（" + cust.getGender()+ "）：");
        char gemder = CMUtility.readChar(cust.getGender());
        System.out.print("年龄（" + cust.getAge() + "）：");
        int age = CMUtility.readInt(cust.getAge());
        System.out.print("电话（" + cust.getPhone() + "）：");
        String phine = CMUtility.readString(13, cust.getPhone());
        System.out.print("邮箱（" + cust.getEmail() + "）：");
        String email = CMUtility.readString(10, cust.getEmail());

        Customer customer = new Customer(name,gemder, age, phine, email);
        boolean isRepalaced = customerList.replaceCustomer(number - 1,customer);
        if (isRepalaced) {
            System.out.println("-----------------修改成功--------------------");
        }else {

            System.out.println("-----------------修改失败--------------------");
        }
    }

    /**
     * 删除客户信息
     */
    private void deleteCustomer(){
        System.out.println("-----------------删除客户--------------------");

        int number;

        for (;;) {
            System.out.print("请选择待删除用户编号（-1退出）：");
            number = CMUtility.readInt();
            if (number == -1){
                return;
            }
            Customer cust = customerList.getCustomer(number - 1);
            if (cust == null) {
                System.out.println("无法找到指定客户！");
            }else {
                System.out.println("姓名:" + cust.getName() + "");
                break;
            }
        }
        //删除客户信息
        System.out.print("确认是否删除（Y/N)：");
        char isDelete = CMUtility.readConfirmSelection();
        if (isDelete == 'Y'){
            boolean deleteSuccess = customerList.deleteCustomer(number - 1);
            if (deleteSuccess) {
                System.out.println("删除成功！");
            }else {
                System.out.println("删除失败！");
            }
        }
    }

    /**
     * 获取客户信息列表
     */
    private void listAllCustomer(){
        System.out.println("-----------------客户列表--------------------");
        int total = customerList.getTotal();
        if (total == 0){
            System.out.println("没有客户记录！");
        }else {
            System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
            Customer[] cust = customerList.getAllCustomers();
            for (int i = 0; i < cust.length; i++) {
                Customer customer = cust[i];
                System.out.println((i+1) + "\t\t" + customer.getName() + "\t" +
                                    customer.getGender() + "\t\t" + customer.getAge() +
                                    "\t" + customer.getPhone() + "\t" + customer.getEmail());
            }
        }
        System.out.println("---------------客户列表完成------------------");

    }

}

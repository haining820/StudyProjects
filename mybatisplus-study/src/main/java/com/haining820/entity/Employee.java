package com.haining820.entity;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-08
 * Time: 10:37
 */

/**
 * @ClassName Employee
 * @Description TODO
 */
public class Employee {
    private int id;
    private int staffId;
    private String name;
    private String mobile;
    private String area;
    private int gender;
    private int isValid;

    public Employee() {
    }

    public Employee(int staffId, String name, String mobile, String area, int gender, int isValid) {
        this.staffId = staffId;
        this.name = name;
        this.mobile = mobile;
        this.area = area;
        this.gender = gender;
        this.isValid = isValid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", staffId=" + staffId +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", area='" + area + '\'' +
                ", gender=" + gender +
                ", isValid=" + isValid +
                '}';
    }
}

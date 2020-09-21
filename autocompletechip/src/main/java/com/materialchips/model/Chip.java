package com.materialchips.model;


public class Chip implements ChipInterface {

    private Object UserIndex;
    private String FullName;
    private String UserName;
    private String UserDepartment;

    public Chip(Object userIndex, String fullName, String userName, String userDepartment) {
        UserIndex = userIndex;
        FullName = fullName;
        UserName = userName;
        UserDepartment = userDepartment;
    }

    public Chip(Object userIndex, String fullName, String userName) {
        UserIndex = userIndex;
        FullName = fullName;
        UserName = userName;
    }


    public Chip(Object userIndex, String fullName) {
        UserIndex = userIndex;
        FullName = fullName;
    }

    public Chip(String fullName, String userName, String userDepartment) {
        FullName = fullName;
        UserName = userName;
        UserDepartment = userDepartment;
    }


    @Override
    public Object getUserIndex() {
        return UserIndex;
    }

    @Override
    public String getFullName() {
        return FullName;
    }

    @Override
    public String getUserName() {
        return UserName;
    }

    @Override
    public String getUserDepartment() {
        return UserDepartment;
    }
}

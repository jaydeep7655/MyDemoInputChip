package com.example.mydenoinputchip

import com.materialchips.model.ChipInterface
import java.io.Serializable

class ResultSet(
    private var userIndex: Int,
    private var userName: String,
    private var userDepartment: String,
    private var fullName: String
) : Serializable, ChipInterface {
    override fun getUserDepartment(): String {
        return userDepartment
    }

    fun setUserDepartment(userDepartment: String) {
        this.userDepartment = userDepartment
    }

    override fun getUserIndex(): Any {
        return userIndex
    }

    fun setUserIndex(userIndex: Int) {
        this.userIndex = userIndex
    }

    override fun getUserName(): String {
        return userName
    }

    fun setUserName(userName: String) {
        this.userName = userName
    }

    override fun getFullName(): String {
        return fullName
    }

    fun setFullName(fullName: String) {
        this.fullName = fullName
    }
}
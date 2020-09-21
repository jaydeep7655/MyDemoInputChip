package com.example.mydenoinputchip

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.materialchips.ChipsInput

class MainActivity : AppCompatActivity() {
    private lateinit var chipsUser: ChipsInput
    private var mUserList: ArrayList<ResultSet>? = null
    lateinit  var mValidateButton: Button
    lateinit  var mAddInChipButton: Button
    lateinit  var mChipListText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getList()
    }
      private fun getList() {
          mUserList = java.util.ArrayList()
        chipsUser = findViewById(R.id.chips_input) as ChipsInput
          mValidateButton=findViewById(R.id.validate)
          mAddInChipButton=findViewById(R.id.addInChip)
          mChipListText=findViewById(R.id.chip_list)


        for (i in 0..24) {
            val contactChip = ResultSet(
                i, "userName$i", "UserDepartment$i",
                "UserFullName$i"
            )
            // add contact to the list
            mUserList!!.add(contactChip)
        }
        // pass contact list to chips input
          chipsUser.filterableList = mUserList

          // show selected chips
          mValidateButton.setOnClickListener(object : View.OnClickListener {
              override fun onClick(v: View) {
                  var listString = ""
                  for (chip in chipsUser.getSelectedChipList()) {
                      listString += chip.getUserName()
                          .toString() + " (" + (if (chip.userIndex != null) chip.userName else "") + ")" + ", "
                  }
                  mChipListText.setText(listString)
              }
          })

          // show selected chips

          // show selected chips
          mAddInChipButton.setOnClickListener {
              val contactChip = ResultSet(26, "userName26", "UserDepartment26", "userfullname26")
              if (chipsUser != null) chipsUser.addChip(contactChip)
          }


      }



}
package com.example.mydenoinputchip

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.materialchips.ChipsInput
import com.materialchips.model.ChipInterface

class MainActivity : AppCompatActivity() {
    private lateinit var chipsUser: ChipsInput
    private lateinit var CC_input: ChipsInput


    private var mUserList: ArrayList<ResultSet>? = null


    lateinit  var mValidateButton: Button
    lateinit  var mAddInChipButton: Button

    lateinit  var mChipListText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getList()
        chipValidation()
    }

    private fun chipValidation() {
        // chips listener
        chipsUser.addChipsListener(object : ChipsInput.ChipsListener {

            override fun onChipAdded(chip: ChipInterface?, newSize: Int) {
                if(CC_input.selectedChipList.contains(chip)){
                    Toast.makeText(applicationContext,"User alredy In CC",Toast.LENGTH_SHORT).show()
                }






            }

            override fun onChipRemoved(chip: ChipInterface?, newSize: Int) {
                Toast.makeText(applicationContext,"Chip Removed",Toast.LENGTH_SHORT).show()
            }

            override fun onTextChanged(text: CharSequence) {

            }
        })
        CC_input.addChipsListener(object : ChipsInput.ChipsListener {

            override fun onChipAdded(chip: ChipInterface?, newSize: Int) {
               if(chipsUser.selectedChipList.contains(chip)){
                   Toast.makeText(applicationContext,"User alredy In User",Toast.LENGTH_SHORT).show()
               }

            }

            override fun onChipRemoved(chip: ChipInterface?, newSize: Int) {
                Toast.makeText(applicationContext,"Chip Removed",Toast.LENGTH_SHORT).show()
            }

            override fun onTextChanged(text: CharSequence) {

            }
        })

    }

    private fun getList() {
          mUserList = java.util.ArrayList()
          chipsUser = findViewById(R.id.chips_input) as ChipsInput
          CC_input = findViewById(R.id.cc_input) as ChipsInput
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
          CC_input.filterableList = mUserList



          // show selected chips
          mValidateButton.setOnClickListener(object : View.OnClickListener {
              override fun onClick(v: View) {
                  var listString = ""
                  for (chip in chipsUser.getSelectedChipList()) {
                      listString += chip.getUserName()
                          .toString() + " (" + (if (chip.userIndex != null) chip.fullName else "") + ")" + ", "
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
package com.example.birthdeathreg

import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.birthdeathreg.databinding.FragmentBirthDetailsBinding
import java.util.*


class birth_details : Fragment(),DatePickerDialog.OnDateSetListener {
    private lateinit var binding:FragmentBirthDetailsBinding

    var day=0
    var month=0
    var year=0

    var SavedDay=0
    var SavedMonth=0
    var SavedYear=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentBirthDetailsBinding.inflate(layoutInflater,container,false)

        //gender drop down
        val gender=resources.getStringArray(R.array.gender)
        val arrayAdapterGender=ArrayAdapter(requireContext(),R.layout.drop_down,gender)
        binding.genderEdit.setAdapter(arrayAdapterGender)

        //id proof of father drop down
        val idProofOfFather=resources.getStringArray(R.array.id_proof_father)
        val arrayAdapterIdProofOfFather=ArrayAdapter(requireContext(),R.layout.drop_down,idProofOfFather)
        binding.idProofFatherEdit.setAdapter(arrayAdapterIdProofOfFather)

        //id proof of mother drop down
        val idProofOfMother=resources.getStringArray(R.array.id_proof_father)
        val arrayAdapterIdProofOfMother=ArrayAdapter(requireContext(),R.layout.drop_down,idProofOfMother)
        binding.idProofMotherEdit.setAdapter(arrayAdapterIdProofOfMother)

        val viewPager= activity?.findViewById<ViewPager2>(R.id.view_pager2)

        binding.dateButton.setOnClickListener{
            getTimeCalender()
            DatePickerDialog(requireContext(),this,year,month,day).show()
        }
        //validation while clicking the next button
        binding.nextBirth.setOnClickListener{
            if(checkValidation()){
                viewPager?.currentItem = 1
            }
            else{
                Toast.makeText(context, "Error in form!! Fill up all the required field", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
    //necessary validation where ever needed
    private fun checkValidation():Boolean{
        var holder:Boolean=true
        if(binding.genderEdit.text.toString().isEmpty()){
            binding.genderEdit.setError("Please select Gender")
            holder=false
        }
        else{
            binding.genderEdit.setError(null)
        }
        if(binding.dobLayout.text.toString().isEmpty()) {
            binding.dobLayout.setError("Please Select Date")
            holder = false
        }

        if(binding.childFirstNameEdit.text.toString().isNotEmpty()){
            if(!containsOnlyLetters(binding.childFirstNameEdit.text.toString())){
                holder=false
            }
        }
        if(binding.childMiddleNameEdit.text.toString().isNotEmpty()){
            if(!containsOnlyLetters(binding.childMiddleNameEdit.text.toString())){
                binding.childMiddleNameLayout.helperText="Only letters must be submitted"
                holder=false
            }
        }
        if(binding.childLastNameEdit.text.toString().isNotEmpty()) {
            if (!containsOnlyLetters(binding.childLastNameEdit.text.toString())) {
                binding.childLastNameLayout.helperText = "Only letters must be submitted"
                holder=false
            }
        }
        if(binding.fatherFirstNameEdit.text.toString().isNotEmpty()){
            if(!containsOnlyLetters(binding.fatherFirstNameEdit.text.toString())){
                binding.fatherFirstNameLayout.helperText="Only letters must be submitted"
                holder=false
            }
        }
        if(binding.fatherMiddleNameEdit.text.toString().isNotEmpty()){
            if(!containsOnlyLetters(binding.fatherMiddleNameEdit.text.toString())){
                binding.fatherMiddleNameLayout.helperText="Only letters must be submitted"
                holder=false
            }
        }
        if(binding.fatherLastNameEdit.text.toString().isNotEmpty()){
            if(!containsOnlyLetters(binding.fatherLastNameEdit.text.toString())){
                binding.fatherLastNameLayout.helperText="Only letters must be submitted"
                holder=false
            }
        }
        if(binding.motherFirstNameEdit.text.toString().isEmpty()){
            binding.motherFirstNameEdit.setError("Please enter mother's first name")
            holder=false
        }
        else{
            if(!containsOnlyLetters(binding.motherFirstNameEdit.text.toString())){
                binding.motherFirstNameLayout.helperText="only letters are allowed here"
                holder=false
            }
        }
        if(binding.motherMiddleNameEdit.text.toString().isNotEmpty()){
            if(!containsOnlyLetters(binding.motherMiddleNameEdit.text.toString())){
                binding.motherMiddleNameLayout.helperText="only letters are allowed"
                holder=false
            }
        }
        if(binding.motherLastNameEdit.text.toString().isEmpty()){
            binding.motherLastNameEdit.error="Please Enter mother's last name"
            holder=false
        }
        else{
            if(!containsOnlyLetters(binding.motherLastNameEdit.text.toString())){
                binding.motherLastNameLayout.helperText="only letters are allowed here"
                holder=false
            }
        }
        return holder
    }

    //method to check whether the input has only letters and not nums
    private fun containsOnlyLetters(input:String):Boolean{
        for(item in input){
            if(!item.isLetter() && !item.isWhitespace()){
                return false
            }
        }
        return true
    }

    private fun getTimeCalender(){
        val cal=Calendar.getInstance()
        day=cal.get(Calendar.DAY_OF_MONTH)
        month=cal.get(Calendar.MONTH)
        year=cal.get(Calendar.YEAR)
    }

    //adding a datePicker
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        SavedDay=dayOfMonth
        SavedMonth=month+1
        SavedYear=year
        binding.dobLayout.text="$SavedDay/$SavedMonth/$SavedYear"
    }



    }




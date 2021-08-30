package com.example.birthdeathreg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.birthdeathreg.databinding.FragmentAddressDetailBinding

class address_detail : Fragment() {
    private lateinit var binding:FragmentAddressDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentAddressDetailBinding.inflate(layoutInflater,container,false)

        //country drop down
        val country=resources.getStringArray(R.array.country)
        val countryAdapter=ArrayAdapter(requireContext(),R.layout.drop_down,country)

        //state drop down
        val state=resources.getStringArray(R.array.state)
        val stateAdapter=ArrayAdapter(requireContext(),R.layout.drop_down,state)

        //district drop down
        val district=resources.getStringArray(R.array.district)
        val districtAdapter=ArrayAdapter(requireContext(),R.layout.drop_down,district)

        binding.districtEdit.setAdapter(districtAdapter)
        binding.districtEditAddressParentTob.setAdapter(districtAdapter)
        binding.districtEditImportant.setAdapter(districtAdapter)
        binding.districtEditTownOfMother.setAdapter(districtAdapter)
        binding.districtEditPob.setAdapter(districtAdapter)

        binding.CountryEdit.setAdapter(countryAdapter)
        binding.stateEdit.setAdapter(stateAdapter)

        binding.CountryEditPob.setAdapter(countryAdapter)
        binding.stateEditPob.setAdapter(stateAdapter)

        binding.CountryEditImportant.setAdapter(countryAdapter)
        binding.stateEditImportant.setAdapter(stateAdapter)

        binding.CountryEditAddressParentTob.setAdapter(countryAdapter)
        binding.stateEditAddressParentTob.setAdapter(stateAdapter)

        val townOrVillage=resources.getStringArray(R.array.townOrVillage)
        val townOrVillageAdapter=ArrayAdapter(requireContext(),R.layout.drop_down,townOrVillage)
        binding.townOrVillageEditTownOfMother.setAdapter(townOrVillageAdapter)

        binding.CountryEditTownOfMother.setAdapter(countryAdapter)
        binding.stateEditTownOfMother.setAdapter(stateAdapter)
        val viewPager=activity?.findViewById<ViewPager2>(R.id.view_pager2)

        binding.radioImportant.setOnCheckedChangeListener{_,_ ->
            if(binding.otherRadio.isChecked){
                binding.addressLine1EditImportant.setText("")
                binding.addressLine2EditImportant.setText("")
                binding.addressLine3EditImportant.setText("")
                binding.CountryEditImportant.setText("")
                binding.stateEditImportant.setText("")
                binding.districtEditImportant.setText("")
                binding.pinEditImportant.setText("")


            }
            if(binding.sameAsPermanentAddressRadio.isChecked){
                binding.addressLine1EditImportant.text = binding.addressLine1Edit.text
                binding.addressLine2EditImportant.text=binding.addressLine2Edit.text
                binding.addressLine3EditImportant.text=binding.addressLine3Edit.text
                binding.CountryEditImportant.text=binding.CountryEdit.text
                binding.stateEditImportant.text=binding.stateEdit.text
                binding.districtEditImportant.text=binding.districtEdit.text
                binding.pinEditImportant.text=binding.pinEdit.text

                binding.addressLine1EditImportant.setError(null)
                binding.addressLine2EditImportant.setError(null)
                binding.addressLine3EditImportant.setError(null)
                binding.CountryEditImportant.setError(null)
                binding.stateEditImportant.setError(null)
                binding.districtEditImportant.setError(null)
                binding.pinEditImportant.setError(null)
            }
        }

        binding.radioAddressOfParentAtTob.setOnCheckedChangeListener{ _, _ ->
            if(binding.other2.isChecked){
                binding.addressLine1EditAddressParentDob.setText("")
                binding.addressLine2EditAddressParentTob.setText("")
                binding.addressLine3EditAddressParentTob.setText("")
                binding.CountryEditAddressParentTob.setText("")
                binding.stateEditAddressParentTob.setText("")
                binding.pinEditAddressParentTob.setText("")
                binding.districtEditAddressParentTob.setText("")
            }
            if(binding.sameAsPermanentAddressRadio2.isChecked){
                binding.addressLine1EditAddressParentDob.text = binding.addressLine1Edit.text
                binding.addressLine2EditAddressParentTob.text=binding.addressLine2Edit.text
                binding.addressLine3EditAddressParentTob.text=binding.addressLine3Edit.text
                binding.CountryEditAddressParentTob.text=binding.CountryEdit.text
                binding.stateEditAddressParentTob.text=binding.stateEdit.text
                binding.districtEditAddressParentTob.text=binding.districtEdit.text
                binding.pinEditAddressParentTob.text=binding.pinEdit.text

                binding.addressLine1EditAddressParentDob.setError(null)
                binding.addressLine2EditAddressParentTob.setError(null)
                binding.addressLine3EditAddressParentTob.setError(null)
                binding.CountryEditAddressParentTob.setError(null)
                binding.stateEditAddressParentTob.setError(null)
                binding.districtEditAddressParentTob.setError(null)
                binding.pinEditAddressParentTob.setError(null)
            }
        }


        binding.nextAddress.setOnClickListener{
            if(checkValidation()){
                viewPager?.currentItem = 2
            }
            else{
                Toast.makeText(context,"Error check form", Toast.LENGTH_SHORT).show()
            }
        }
        binding.PrevAddress.setOnClickListener{
            viewPager?.currentItem=0
        }

        return binding.root
    }
    private fun checkValidation():Boolean{
        var holder=true
        if(binding.districtEdit.text.toString().isEmpty()) {
            binding.districtEdit.setError("please select district")
            holder = false
        }
        else{
            binding.districtEdit.setError(null)
        }
        if(binding.districtEditTownOfMother.text.toString().isEmpty()) {
            binding.districtEditTownOfMother.setError("Please enter district")
            holder = false
        }
        else{
            binding.districtEditTownOfMother.setError(null)
        }
        if(binding.districtEditAddressParentTob.text.toString().isEmpty()) {
            binding.districtEditAddressParentTob.setError("Please emter district")
            holder = false
        }
        else{
            binding.districtEditAddressParentTob.setError(null)
        }
        if(binding.districtEditImportant.text.toString().isEmpty()) {
            binding.districtEditImportant.setError("Please enter district")
            holder = false
        }
        else{
            binding.districtEditImportant.setError(null)
        }
        if(binding.districtEditPob.text.toString().isEmpty()) {
            binding.districtEditPob.setError("Please enter district")
            holder = false
        }
        else{
            binding.districtEditPob.setError(null)
        }
        if(binding.addressLine1Edit.text.toString().isEmpty()) {
            binding.addressLine1Edit.setError("Please Enter address")
            holder=false
        }
        if(binding.addressLine2Edit.text.toString().isEmpty()) {
            binding.addressLine2Edit.setError("Please Enter address")
            holder=false
        }
        if(binding.CountryEdit.text.toString().isEmpty()) {
            binding.CountryEdit.setError("Please select country")
            holder = false
        }
        else{
            binding.CountryEdit.setError(null)
        }
        if(binding.stateEdit.text.toString().isEmpty()) {
            binding.stateEdit.setError("Please select state")
            holder = false
        }
        else{
            binding.stateEdit.setError(null)
        }
        if(binding.pinEdit.text.toString().isEmpty()) {
            binding.pinEdit.setError("Please Enter Pin")
            holder=false
        }
        if(binding.mobileEdit.text.toString().isEmpty()) {
            binding.mobileEdit.setError("Please Enter mobile number")
            holder=false
        }
        else if(binding.mobileEdit.text.toString().length<10){
            binding.mobileEdit.setError("Mobile number cannot be less than 10 digits")
            holder=false
        }
        if(binding.addressLine1PobEdit.text.toString().isEmpty()) {
            binding.addressLine1PobEdit.setError("Please Enter address")
            holder=false
        }
        if(binding.addressLine2PobEdit.text.toString().isEmpty()) {
            binding.addressLine2PobEdit.setError("Please Enter address")
            holder=false
        }
        if(binding.CountryEditPob.text.toString().isEmpty()) {
            binding.CountryEditPob.setError("Please Select Country")
            holder = false
        }
        else{
            binding.CountryEditPob.setError(null)
        }
        if(binding.stateEditPob.text.toString().isEmpty()) {
            binding.stateEditPob.setError("Please select State")
            holder = false
        }
        else{
            binding.stateEditPob.setError(null)
        }
        if(binding.pinEditPob.text.toString().isEmpty()) {
            binding.pinEditPob.setError("Pin is Needed")
            holder=false
        }
        if(binding.orderOfBirthEditPob.text.toString().isEmpty()) {
            binding.orderOfBirthEditPob.setError("Order of Birth in needed")
            holder=false
        }
        if(binding.informantNameEditImportant.text.toString().isEmpty()) {
            binding.informantNameEditImportant.setError("Informant name is needed")
            holder=false
        }
        if(binding.addressLine1EditImportant.text.toString().isEmpty()){
            binding.addressLine1EditImportant.setError("Address is needed")
            holder=false
        }
        if(binding.addressLine2EditImportant.text.toString().isEmpty()) {
            binding.addressLine2EditImportant.setError("Address is needed")
            holder=false
        }
        if(binding.CountryEditImportant.text.toString().isEmpty()) {
            binding.CountryEditImportant.setError("Please select Country")
            holder = false
        }
        else{
            binding.CountryEditImportant.setError(null)
        }
        if(binding.stateEditImportant.text.toString().isEmpty()) {
            binding.stateEditImportant.setError("Please select state")
            holder = false
        }
        else{
            binding.stateEditImportant.setError(null)

        }
        if(binding.pinEditImportant.text.toString().isEmpty()) {
            binding.pinEditImportant.setError("Pin is needed")
            holder=false
        }
        if(binding.addressLine1EditAddressParentDob.text.toString().isEmpty()) {
            binding.addressLine1EditAddressParentDob.setError("Address is needed")
            holder=false
        }
        if(binding.addressLine2EditAddressParentTob.text.toString().isEmpty()){
            binding.addressLine2EditAddressParentTob.setError("Address is needed")
            holder=false}
        if(binding.CountryEditAddressParentTob.text.toString().isEmpty()) {
            binding.CountryEditAddressParentTob.setError("Please enter country")
            holder = false
        }
        else{
            binding.CountryEditAddressParentTob.setError(null)
        }
        if(binding.stateEditAddressParentTob.text.toString().isEmpty()) {
            binding.stateEditAddressParentTob.setError("Pleasse enter state")
            holder = false
        }
        else{
            binding.stateEditAddressParentTob.setError(null)
        }
        if(binding.pinEditAddressParentTob.text.toString().isEmpty()) {
            binding.pinEditAddressParentTob.setError("Pin is needed")
            holder=false
        }
        if(binding.townOrVillageEditTownOfMother.text.toString().isEmpty()) {
            binding.townOrVillageEditTownOfMother.setError("Please select Town or village")
            holder = false
        }
        else{
            binding.townOrVillageEditTownOfMother.setError(null)
        }
        if(binding.villageNameEditAddressParentTob.text.toString().isEmpty()) {
            binding.villageNameEditAddressParentTob.setError("Village Name is needed")
            holder=false
        }
        if(binding.CountryEditTownOfMother.text.toString().isEmpty()) {
            binding.CountryEditTownOfMother.setError("Please select country")
            holder = false
        }
        else{
            binding.CountryEditTownOfMother.setError(null)

        }
        if(binding.stateEditTownOfMother.text.toString().isEmpty()) {
            binding.stateEditTownOfMother.setError("Pleaase enter state")
            holder = false
        }
        else{
            binding.stateEditTownOfMother.setError(null)
        }

        return holder
    }


}

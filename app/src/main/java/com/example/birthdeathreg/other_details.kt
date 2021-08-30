package com.example.birthdeathreg

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.birthdeathreg.databinding.FragmentOtherDetailsBinding
import com.example.birthdeathreg.network.DataClassHolder
import com.example.birthdeathreg.network.DataClassInner
import com.example.birthdeathreg.network.PostDataBody
import com.example.birthdeathreg.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class other_details : Fragment() {

    private lateinit var binding: FragmentOtherDetailsBinding
    private var finalList= mutableListOf<String>()

    init {
        val postDataBody = PostDataBody("CO_RELIGION", "CO")
        lifecycleScope.launchWhenCreated{
            val response = try {
                RetrofitInstance.api.postData(postDataBody)
            } catch (e: HttpException) {
                Log.e("other_details", "IOException, you might not have internet connection")
//                finalList.add("connect to internet")
                finalList.toTypedArray()
                return@launchWhenCreated
            }
            catch (e:IOException){
                Log.e("other_details", "IOException, you might not have internet connection")
//                finalList.add("connect to internet")
                finalList.toTypedArray()
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                val result: DataClassHolder = response.body()!!
                val religionList = result.subDomainList!!
                religionList.forEach {
                    finalList.add(it.subDomainName.toString())
                }
                finalList.toTypedArray()
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentOtherDetailsBinding.inflate(layoutInflater,container,false)

        //religion drop down
//        val religion=resources.getStringArray(R.array.religion_list)
        val religionAdapter:ArrayAdapter<String> =ArrayAdapter(requireContext(),R.layout.drop_down,finalList)
        binding.religionFamilyDrop.setAdapter(religionAdapter)

        //father level of education
        val fatherLevelOfEducation=resources.getStringArray(R.array.level_of_education)
        val fatherLevelOfEducationAdapter=ArrayAdapter(requireContext(),R.layout.drop_down,fatherLevelOfEducation)
        binding.fatherEducationDrop.setAdapter(fatherLevelOfEducationAdapter)

        //mother level of education
        val motherLevelOfEducation=resources.getStringArray(R.array.level_of_education)
        val motherLevelOfEducationAdapter=ArrayAdapter(requireContext(),R.layout.drop_down,motherLevelOfEducation)
        binding.motherEducationLevelDrop.setAdapter(motherLevelOfEducationAdapter)

        //father occupation
        val fatherOccupation= resources.getStringArray(R.array.occupation)
        val fatherOccupationAdapter=ArrayAdapter(requireContext(),R.layout.drop_down,fatherOccupation)
        binding.FatherOccupationDrop.setAdapter(fatherOccupationAdapter)

        //mother occupation
        val motherOccupation=resources.getStringArray(R.array.occupation)
        val motherOccupationAdapter=ArrayAdapter(requireContext(),R.layout.drop_down,motherOccupation)
        binding.MotherOccupationDrop.setAdapter(motherOccupationAdapter)

        //type of attention
        val typeOfAttention=resources.getStringArray(R.array.type_of_attention)
        val typeOfAttenionAdapter=ArrayAdapter(requireContext(),R.layout.drop_down,typeOfAttention)
        binding.typeOfAttentionDeliveryEdit.setAdapter(typeOfAttenionAdapter)

        //method Of delivery
        val methodOfDelivery=resources.getStringArray(R.array.method_of_delivery)
        val methodOfDeliveryAdapter=ArrayAdapter(requireContext(),R.layout.drop_down,methodOfDelivery)
        binding.methodOfDeliveryEdit.setAdapter(methodOfDeliveryAdapter)

        //proof of event
        val proofOfEvent=resources.getStringArray(R.array.proof_of_event_)
        val proofOfDeliveryAdapter=ArrayAdapter(requireContext(),R.layout.drop_down,proofOfEvent)
        binding.proofEventEditOther.setAdapter(proofOfDeliveryAdapter)

        //miss case
        val missCase=resources.getStringArray(R.array.miss_case)
        val missCaseAdapter=ArrayAdapter(requireContext(),R.layout.drop_down,missCase)
        binding.missCaseEditOther.setAdapter(missCaseAdapter)

        binding.registrationUnitEdit.isEnabled=false
        binding.registrationDateLayout.isEnabled=false

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateInstance() //or use getDateInstance()
        val formattedDate = formatter.format(date)
        binding.registrationDateEdit.setText(formattedDate)
        val viewPager=activity?.findViewById<ViewPager2>(R.id.view_pager2)

        binding.nextOther.setOnClickListener{
            if(checkValidation()){
                viewPager?.currentItem=3
            }
            else{
                Toast.makeText(context,"error in form check requirements",Toast.LENGTH_SHORT).show()
            }
        }
        binding.PrevOther.setOnClickListener{
            viewPager?.currentItem=1
        }

        return binding.root
    }
    private fun checkValidation():Boolean{
        var holder=true
        if(binding.religionFamilyDrop.text.toString().isEmpty()) {
            binding.religionFamilyDrop.setError("Religion is needed")
            holder=false
        }
        else{
            binding.religionFamilyDrop.setError(null)
        }
        if(binding.fatherEducationDrop.text.toString().isEmpty()){
            binding.fatherEducationDrop.setError("Father education level is needed")
            holder=false
        }
        else{
            binding.fatherEducationDrop.setError(null)
        }
        if(binding.motherEducationLevelDrop.text.toString().isEmpty()){
            binding.motherEducationLevelDrop.setError("Mother education level is needed")
            holder=false
        }
        else{
            binding.motherEducationLevelDrop.setError(null)
        }
        if(binding.FatherOccupationDrop.text.toString().isEmpty()){
            binding.FatherOccupationDrop.setError("Father education level is needed")
            holder=false
        }
        else{
            binding.FatherOccupationDrop.setError(null)
        }
        if(binding.MotherOccupationDrop.text.toString().isEmpty()){
            binding.MotherOccupationDrop.setError("Mother education is needed")
            holder=false
        }
        else{
            binding.MotherOccupationDrop.setError(null)
        }
        if(binding.motherMarriageAgeDrop.text.toString().isEmpty()){
            binding.motherMarriageAgeDrop.setError("Mother's age at time of marriage is needed")
            holder=false
        }

        if(binding.motherBirthingAgeEdit.text.toString().isEmpty()){
            binding.motherBirthingAgeEdit.setError("Age of mother during birth is needed")
            holder=false
        }
        if(binding.motherNumberOfChildEdit.text.toString().isEmpty()){
            binding.motherNumberOfChildEdit.setError("This field is needed")
            holder=false
        }
        if(binding.typeOfAttentionDeliveryEdit.text.toString().isEmpty()){
            binding.typeOfAttentionDeliveryEdit.setError("This field is mandatory")
            holder=false
        }
        else{
            binding.typeOfAttentionDeliveryEdit.setError(null)
        }
        if(binding.methodOfDeliveryEdit.text.toString().isEmpty()){
            binding.methodOfDeliveryEdit.setError("Method of Delivery is required")
            holder=false
        }
        else{
            binding.methodOfDeliveryEdit.setError(null)
        }
        if(binding.proofEventEditOther.text.toString().isEmpty()){
            binding.proofEventEditOther.setError("Proof of event is needed")
            holder=false
        }
        else{
            binding.proofEventEditOther.setError(null)
        }
        if(binding.remarkEditOther.text.toString().isEmpty()){
            binding.remarkEditOther.setError("Remark is needed")
            holder=false
        }
        else{
            binding.remarkEditOther.setError(null)
        }
        if(binding.missCaseEditOther.text.toString().isEmpty()){
            binding.missCaseEditOther.setError("This field is mandatory")
            holder=false
        }
        else{
            binding.missCaseEditOther.setError(null)
        }

        return holder
    }

}
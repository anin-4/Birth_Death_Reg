package com.example.birthdeathreg

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.birthdeathreg.databinding.FragmentAttachDocumentsBinding
import java.util.*


class attach_documents : Fragment() {
   private lateinit var binding: FragmentAttachDocumentsBinding
   var selectedFileAuthorization: Uri?=null
   var selectedImageAuthorization:Bitmap?=null
   var selectedFileInformant:Uri?=null
   var selectedImageInformant:Bitmap?=null
   var selectFileOther:Uri?=null
   var selectedImageOther:Bitmap?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding= FragmentAttachDocumentsBinding.inflate(inflater,container,false)

        binding.fileChooseAuthorization.setOnClickListener{
            val intent = Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT)

            startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
        }
        binding.fileChooseInformant.setOnClickListener {
            val intent=Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,"Select a file"),112)
        }
        binding.fileChooseOther.setOnClickListener {
            val intent=Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,"Select a file"),113)
        }
        binding.cameraButton.setOnClickListener{
            val takePicture=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicture,121)
        }
        binding.cameraButton2.setOnClickListener{
            val takePicture=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicture,122)
        }

        binding.cameraButton3.setOnClickListener {
            val takePicture=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicture,123)
        }

        binding.submitButton.setOnClickListener {
           if(checkValidation()){
               Toast.makeText(context,"Okay valiation done",Toast.LENGTH_SHORT).show()
           }
            else{
                Toast.makeText(context,"Provide necessary documents",Toast.LENGTH_SHORT).show()
           }
        }

        return binding.root
    }

    private fun checkValidation(): Boolean {
        if(selectedFileAuthorization==null && selectedImageAuthorization==null){
            return false
        }
        if(selectedFileInformant==null && selectedImageInformant==null){
            return false
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            selectedFileAuthorization= data?.data //The uri with the location of the file
            Toast.makeText(context,"$selectedFileAuthorization",Toast.LENGTH_SHORT).show()
        }
        else if(requestCode==121 && resultCode== RESULT_OK){
            selectedImageAuthorization=data?.extras?.get("data") as Bitmap
            binding.image1.layoutParams.height=400
            binding.image1.layoutParams.width=400
            binding.image1.requestLayout()
            binding.image1.setImageBitmap(selectedImageAuthorization)
            binding.image1.visibility=View.VISIBLE
            binding.deleteButton1.visibility=View.VISIBLE
            binding.deleteButton1.setOnClickListener {
                selectedImageAuthorization=null
                binding.image1.layoutParams.height=0
                binding.image1.layoutParams.width=0
                binding.image1.requestLayout()
                binding.image1.visibility=View.INVISIBLE
                binding.deleteButton1.visibility=View.INVISIBLE

            }
        }
        if(requestCode==112 && resultCode== RESULT_OK){
            selectedFileInformant=data?.data
            Toast.makeText(context,"$selectedFileInformant",Toast.LENGTH_SHORT).show()
        }
        else if(requestCode==122 && resultCode== RESULT_OK){
            selectedImageInformant=data?.extras?.get("data") as Bitmap
            binding.image2.layoutParams.height=400
            binding.image2.layoutParams.width=400
            binding.image2.requestLayout()
            binding.image2.setImageBitmap(selectedImageInformant)
            binding.image2.visibility=View.VISIBLE
            binding.deleteButton2.visibility=View.VISIBLE
            binding.deleteButton2.setOnClickListener {
                selectedImageInformant=null
                binding.image2.layoutParams.height=0
                binding.image2.layoutParams.width=0
                binding.image2.requestLayout()
                binding.image2.visibility=View.INVISIBLE
                binding.deleteButton2.visibility=View.INVISIBLE
            }

        }
        if(requestCode==113 && resultCode== RESULT_OK){
            selectFileOther=data?.data
            Toast.makeText(context,"$selectFileOther",Toast.LENGTH_SHORT).show()
        }

        else if(requestCode==123 && resultCode== RESULT_OK){
            selectedImageOther=data?.extras?.get("data") as Bitmap
            binding.image3.layoutParams.height=400
            binding.image3.layoutParams.width=400
            binding.image3.requestLayout()
            binding.image3.setImageBitmap(selectedImageOther)
            binding.image3.visibility=View.VISIBLE
            binding.deleteButton3.visibility=View.VISIBLE
            binding.deleteButton3.setOnClickListener {
                selectedImageOther=null
                binding.image3.layoutParams.height=0
                binding.image3.layoutParams.width=0
                binding.image3.requestLayout()
                binding.image3.visibility=View.INVISIBLE
                binding.deleteButton3.visibility=View.INVISIBLE

            }
        }


    }


}
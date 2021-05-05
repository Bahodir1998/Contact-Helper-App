package com.example.contactpermission

import android.os.Bundle
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.contactpermission.databinding.FragmentSmsBinding
import com.example.contactpermission.models.Contact


class SmsFragment : Fragment() {

    lateinit var binding: FragmentSmsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSmsBinding.inflate(layoutInflater)
        val contact: Contact = arguments?.getSerializable("contact") as Contact
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.name.text = contact.name
        binding.number.text = contact.number

        binding.send.setOnClickListener {
            val smsBody = binding.smsBody.text.toString()
            if (smsBody.trim() != "") {
                sendSMS(contact.number, smsBody)
                findNavController().popBackStack()
            } else {
                Toast.makeText(
                    requireActivity().applicationContext, "Message is empty",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return binding.root
    }

    private fun sendSMS(phoneNo: String?, msg: String?) {
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNo, null, msg, null, null)
            Toast.makeText(
                requireActivity().applicationContext, "Message Sent",
                Toast.LENGTH_LONG
            ).show()
        } catch (ex: Exception) {
            Toast.makeText(
                requireActivity().applicationContext, ex.message.toString(),
                Toast.LENGTH_LONG
            ).show()
            ex.printStackTrace()
        }
    }
}
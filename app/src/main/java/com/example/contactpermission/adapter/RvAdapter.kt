package com.example.contactpermission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactpermission.databinding.ItemContactBinding
import com.example.contactpermission.models.Contact
import com.example.contactpermission.utils.OnMyItemTouchHelperAdapter

class RvAdapter(var contactList: List<Contact>, var onMyItemClickListener: OnMyItemClickListener) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemContactBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemContactBinding.root) {
        fun onBind(contact: Contact, position: Int) {
            itemContactBinding.name.text = contact.name
            itemContactBinding.number.text = contact.number
            itemContactBinding.call.setOnClickListener {
                onMyItemClickListener.onCallClick(contact)
            }
            itemContactBinding.sms.setOnClickListener {
                onMyItemClickListener.onSmsClick(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(contactList[position], position)
    }

    override fun getItemCount(): Int = contactList.size

    interface OnMyItemClickListener {
        fun onCallClick(contact: Contact)

        fun onSmsClick(contact: Contact)
    }
}
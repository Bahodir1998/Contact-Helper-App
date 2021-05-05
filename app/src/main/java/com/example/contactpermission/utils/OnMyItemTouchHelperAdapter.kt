package com.example.contactpermission.utils

import com.example.contactpermission.models.Contact

interface OnMyItemTouchHelperAdapter {
    fun onSwipe(contact: Contact,position:Int)
}
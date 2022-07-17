package com.example.starcast.`interface`

import android.view.View

/*
*
* @Author Kushan (koolkvd)
*
* Implement this interface wherever you wish to get an item click callback
* Inside recycler view, pass the reference of the callback listener activity or fragment
* in the viewholder constructor, implement the OnClickListener and call the listener's overriden method
* from the onClick call of the viewholder
*
* This is the best practice for binding a listener to a recyclerview so use this
* Please avoid binding the listener in onBindViewHolder, it will not work properly in a lot of cases
* eg attaching a TextWatcher on an viewholder's edittext in onBind method will not work properly and will show text
* in random viewholders
*
* Also avoid using position inside click listener position passed, use layoutPosition in the viewholder adapter ... it will avoid crashes
*
* */
interface RecyclerItemClickListener {
    fun onRecyclerItemClicked(view: View?,position: Int,extra: Any? = null)
}
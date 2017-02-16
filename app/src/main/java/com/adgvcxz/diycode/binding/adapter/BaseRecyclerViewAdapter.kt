package com.adgvcxz.diycode.binding.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */
class BaseRecyclerViewAdapter<T>: Adapter<RecyclerView.ViewHolder>() {

    private var items: List<T>? = null
    private var inflater: LayoutInflater? = null

    override fun getItemCount(): Int = if (items == null) 0 else items!!.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent!!.context)
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
        return object : RecyclerView.ViewHolder(binding.root){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

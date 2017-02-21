package com.adgvcxz.diycode.binding.adapter

import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import com.adgvcxz.diycode.BR
import com.adgvcxz.diycode.ui.base.view.BaseViewModel
import com.adgvcxz.diycode.ui.base.view.LoadingViewModel
import com.adgvcxz.diycode.util.extensions.ensureChangeOnMainThread
import java.lang.ref.WeakReference

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */
class BaseRecyclerViewAdapter<in T : BaseViewModel> : RecyclerView.Adapter<ViewHolder>() {

    private var items: List<T>? = null
    private var inflater: LayoutInflater? = null
    private val callback = WeakReferenceOnListChangedCallback(this)
    private var recyclerView: RecyclerView? = null
    private val loadingModel: LoadingViewModel by lazy { LoadingViewModel() }

    var loadMore = false
    var loadAll = true
        set(value) {
            if (value && loadMore && items != null && itemCount > items!!.size) {
                notifyItemRemoved(itemCount - 1)
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent!!.context)
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
        return object : ViewHolder(binding.root) {}
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val binding = DataBindingUtil.getBinding<ViewDataBinding>(holder!!.itemView)
        if (binding.root.id != loadingModel.contentId()) {
            binding.setVariable(BR.model, items!![position])
        } else {
            binding.setVariable(BR.model, loadingModel)
        }
        binding.executePendingBindings()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        if (this.recyclerView == null && items != null && items is ObservableList) {
            (items as ObservableList).addOnListChangedCallback(callback)
        }
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        super.onDetachedFromRecyclerView(recyclerView)
        if (this.recyclerView == null && items != null && items is ObservableList) {
            (items as ObservableList).removeOnListChangedCallback(callback)
        }
        this.recyclerView = recyclerView
    }

    override fun getItemCount(): Int {
        if (items == null) {
            return 0
        } else if (loadMore && !loadAll) {
            return items!!.size + 1
        }
        return items!!.size
    }

    override fun getItemViewType(position: Int): Int = items!![position].contentId()

    fun setList(items: List<T>) {
        if (this.items == items) {
            return
        }
        if (recyclerView != null) {
            if (this.items is ObservableList) {
                (this.items as ObservableList).removeOnListChangedCallback(callback)
            }
            if (items is ObservableList) {
                items.addOnListChangedCallback(callback)
            }
        }
        this.items = items
        notifyDataSetChanged()
    }

    class WeakReferenceOnListChangedCallback<T : BaseViewModel>(adapter: BaseRecyclerViewAdapter<T>) : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        val adapterRef: WeakReference<BaseRecyclerViewAdapter<T>> = WeakReference(adapter)

        override fun onItemRangeMoved(p0: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
            val adapter = adapterRef.get()
            if (adapter != null) {
                ensureChangeOnMainThread()
                for (i in 0..itemCount - 1) {
                    adapter.notifyItemMoved(fromPosition + i, toPosition + i)
                }
            }
        }

        override fun onChanged(p0: ObservableList<T>?) {
            val adapter = adapterRef.get()
            if (adapter != null) {
                ensureChangeOnMainThread()
                adapter.notifyDataSetChanged()
            }
        }

        override fun onItemRangeChanged(p0: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            val adapter = adapterRef.get()
            if (adapter != null) {
                ensureChangeOnMainThread()
                adapter.notifyItemRangeChanged(positionStart, itemCount)
            }
        }

        override fun onItemRangeInserted(p0: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            val adapter = adapterRef.get()
            if (adapter != null) {
                ensureChangeOnMainThread()
                adapter.notifyItemRangeChanged(positionStart, itemCount)
            }
        }

        override fun onItemRangeRemoved(p0: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            val adapter = adapterRef.get()
            if (adapter != null) {
                ensureChangeOnMainThread()
                adapter.notifyItemRangeChanged(positionStart, itemCount)
            }
        }

    }

}

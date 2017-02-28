package com.adgvcxz.diycode.binding.recycler

import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import com.adgvcxz.diycode.BR
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.binding.base.BaseViewModel
import com.adgvcxz.diycode.binding.base.EmptyViewModel
import com.adgvcxz.diycode.binding.base.LoadingViewModel
import com.adgvcxz.diycode.util.extensions.ensureChangeOnMainThread
import com.adgvcxz.diycode.util.extensions.rx
import java.lang.ref.WeakReference

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */
class BaseRecyclerViewAdapter<T : BaseViewModel> : RecyclerView.Adapter<ViewHolder>() {

    private var items: List<T>? = null
    private var inflater: LayoutInflater? = null
    private val callback = WeakReferenceOnListChangedCallback(this)
    private var recyclerView: RecyclerView? = null
    private val firstModel: EmptyViewModel by lazy { EmptyViewModel() }
    var onClickItemListener: OnRecyclerViewItemClickListener<T>? = null
    var loadMoreListener: OnLoadMoreListener? = null

    val loadingModel: LoadingViewModel by lazy {
        object : LoadingViewModel() {
            init {
                this.status.rx().filter { it == Loading }
                        .subscribe {
                            loadMoreListener?.loadMore()
                        }
            }
        }
    }

    var loadMore = false
        set(value) {
            field = value
            if (value && scrollListener == null) {
                scrollListener = object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                        if (loadMore && !loadAll && isNotEmpty() && loadingModel.status.get() == LoadingViewModel.Nothing) {
                            val manager = recyclerView?.layoutManager as LinearLayoutManager
                            val lastPosition = manager.findLastVisibleItemPosition()
                            val count = manager.itemCount
                            if (count == lastPosition + 1) {
                                loadingModel.status.set(LoadingViewModel.Loading)
                            }
                        }
                    }
                }
                this.recyclerView?.addOnScrollListener(scrollListener)
            }
        }
    var loadAll = true
        set(value) {
            val count = itemCount
            field = value
            if (value && loadMore && isNotEmpty() && count > items!!.size) {
                notifyItemRemoved(itemCount - 1)
            }
        }

    var firstTopMargin: Int = 0

    private var scrollListener: RecyclerView.OnScrollListener? = null


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent!!.context)
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
        when (viewType) {
            R.layout.item_empty_view -> {
                val lp = binding.root.layoutParams
                lp.height = firstTopMargin
                binding.root.layoutParams = lp
            }
        }
        return object : ViewHolder(binding.root) {}
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val binding = DataBindingUtil.getBinding<ViewDataBinding>(holder!!.itemView)
        var index = position
        if (firstTopMargin > 0) {
            index = position - 1
        }
        if (index in 0..items!!.size - 1) {
            binding.setVariable(BR.model, items!![index])
        } else if (index == items!!.size) {
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
        var count = 0
        if (firstTopMargin > 0) {
            count++
        }
        if (items == null || items!!.isEmpty()) {
            return count
        } else if (loadMore && !loadAll) {
            count++
        }
        return items!!.size + count
    }

    override fun getItemViewType(position: Int): Int {
        var index = position
        if (firstTopMargin > 0) {
            index = position - 1
        }
        if (index in 0..items!!.size - 1) {
            return items!![index].contentId()
        } else if (index == items!!.size) {
            return loadingModel.contentId()
        }
        return firstModel.contentId()
    }

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

    fun isNotEmpty(): Boolean = items != null && items!!.isNotEmpty()

    class WeakReferenceOnListChangedCallback<T : BaseViewModel>(adapter: BaseRecyclerViewAdapter<T>) : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        val adapterRef: WeakReference<BaseRecyclerViewAdapter<T>> = WeakReference(adapter)
        val offset: Int get() {
            val adapter = adapterRef.get() ?: return 0
            if (adapter.firstTopMargin > 0) {
                return 1
            }
            return 0
        }

        override fun onItemRangeMoved(p0: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
            val adapter = adapterRef.get()
            if (adapter != null) {
                ensureChangeOnMainThread()
                for (i in 0..itemCount - 1) {
                    adapter.notifyItemMoved(fromPosition + i + offset, toPosition + i + offset)
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
                adapter.notifyItemRangeChanged(positionStart + offset, itemCount)
            }
        }

        override fun onItemRangeInserted(p0: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            val adapter = adapterRef.get()
            if (adapter != null) {
                ensureChangeOnMainThread()
                adapter.notifyItemRangeInserted(positionStart + offset, itemCount)
            }
        }

        override fun onItemRangeRemoved(p0: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            val adapter = adapterRef.get()
            if (adapter != null) {
                ensureChangeOnMainThread()
                adapter.notifyItemRangeRemoved(positionStart + offset, itemCount)
            }
        }
    }
}

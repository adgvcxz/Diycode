package com.adgvcxz.diycode.binding.observable

import android.databinding.ListChangeRegistry
import android.databinding.ObservableList
import android.databinding.ObservableList.OnListChangedCallback
import java.util.*


/**
 * zhaowei
 * Created by zhaowei on 2017/2/24.
 */

class ResetArrayList<T> : ArrayList<T>(), ObservableList<T> {

    @Transient private var listeners = ListChangeRegistry()

    override fun addOnListChangedCallback(listener: OnListChangedCallback<out ObservableList<T>>?) {
        listeners.add(listener)
    }

    override fun removeOnListChangedCallback(listener: OnListChangedCallback<out ObservableList<T>>?) {
        listeners.remove(listener)
    }

    override fun add(element: T): Boolean {
        super.add(element)
        notifyAdd(size - 1, 1)
        return true
    }

    override fun add(index: Int, element: T) {
        super.add(index, element)
        notifyAdd(index, 1)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        val oldSize = size
        val added = super.addAll(elements)
        if (added) {
            notifyAdd(oldSize, size - oldSize)
        }
        return added
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        val added = super.addAll(index, elements)
        if (added) {
            notifyAdd(index, elements.size)
        }
        return added
    }

    override fun clear() {
        val oldSize = size
        super.clear()
        if (oldSize != 0) {
            notifyRemove(0, oldSize)
        }
    }

    override fun remove(element: T): Boolean {
        val index = indexOf(element)
        if (index >= 0) {
            removeAt(index)
            return true
        }
        return false
    }

    override fun removeAt(index: Int): T {
        val value = super.removeAt(index)
        notifyRemove(index, 1)
        return value
    }

    override fun set(index: Int, element: T): T {
        val value = super.set(index, element)
        listeners.notifyChanged(this, index, 1)
        return value
    }

    fun reset(elements: Collection<T>) {
        super.clear()
        super.addAll(elements)
        notifyChange()
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex)
        notifyRemove(fromIndex, toIndex - fromIndex)
    }

    private fun notifyAdd(start: Int, count: Int) {
        listeners.notifyInserted(this, start, count)
    }

    private fun notifyRemove(start: Int, count: Int) {
        listeners.notifyRemoved(this, start, count)
    }

    private fun notifyChange() {
        listeners.notifyChanged(this)
    }

}
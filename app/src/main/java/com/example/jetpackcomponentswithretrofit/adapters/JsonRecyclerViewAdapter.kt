package com.example.jetpackcomponentswithretrofit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackcomponentswithretrofit.model.PhotoData
import com.example.jetpackcomponentswithretrofit.R
import com.example.jetpackcomponentswithretrofit.databinding.PhotoLayoutItemBinding

class JsonRecyclerViewAdapter(
    val context: Context,
    private val jsonList: List<PhotoData>
) : RecyclerView.Adapter<JsonRecyclerViewAdapter.ViewHolder>() {
    private var onClickItem: ((PhotoData) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=DataBindingUtil.inflate<PhotoLayoutItemBinding>(inflater,
            R.layout.photo_layout_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.photo=jsonList[position]

        holder.itemView.setOnClickListener {
            onClickItem?.invoke(jsonList[position])
        }
    }

    override fun getItemCount(): Int {
        return jsonList.size
    }

    inner class ViewHolder(var view:PhotoLayoutItemBinding) : RecyclerView.ViewHolder(view.root) {
        /*private val textView: TextView = itemView.findViewById(R.id.photo_layout_item_tv)
        private val image: ImageView = itemView.findViewById(R.id.photo_layout_item_img)
        fun bindView(item: PhotoData) {
            textView.text = item.title
            Glide.with(context).load(item.thumbnailUrl).into(image)
        }*/

    }
    fun setOnClickItem(callback: (PhotoData) -> Unit) {
        this.onClickItem = callback
    }
}
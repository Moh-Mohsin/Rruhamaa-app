package net.ruhamaa.mobile.ui.casedetail.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import net.ruhamaa.mobile.R
import net.ruhamaa.mobile.data.model.Image
import net.ruhamaa.mobile.databinding.ImageListItemBinding
import kotlin.math.roundToInt

class ImageListAdapter(private val onImageClicked: (Image) -> Unit) :
    ListAdapter<Image, ImageListAdapter.ViewHolder>(
        ImageDiff()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ImageListItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }

    inner class ViewHolder(private val binding: ImageListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            binding.image.load(Uri.parse(image.url))
//            binding.image.load(Uri.parse("file:///android_asset/case_image_1.jpg"))
//            binding.image.load("https://live.staticflickr.com/7553/15199758144_bc9194b189_b.jpg")
            binding.root.setOnClickListener {
                onImageClicked(image)
            }
        }

    }

    class ImageDiff : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Image, newItem: Image) = oldItem == newItem
    }

}
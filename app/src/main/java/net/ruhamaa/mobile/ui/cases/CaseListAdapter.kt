package net.ruhamaa.mobile.ui.cases

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
import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.databinding.CaseListItemBinding
import kotlin.math.roundToInt

class CaseListAdapter(private val onCaseClicked: (Case) -> Unit) :
    ListAdapter<Case, CaseListAdapter.ViewHolder>(CaseDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(CaseListItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val case = getItem(position)
        holder.bind(case)
    }

    inner class ViewHolder(private val binding: CaseListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(case: Case) {
            val context = binding.root.context
            binding.mainImage.load(case.imgUrl) {
                crossfade(true)
                val drawable = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_icon, null)!!
                DrawableCompat.setTint(drawable, ResourcesCompat.getColor(context.resources, R.color.green, null))
                placeholder(drawable)
//                transformations(RoundedCornersTransformation())
            }
            binding.description.text = case.description
            binding.currentDonations.text =
                context.getString(R.string.money_amount, case.currentDonations.toString())
            binding.targetDonations.text =
                context.getString(R.string.money_amount, case.targetDonations.toString())
            val progressPercent = ((case.currentDonations / case.targetDonations) * 100).roundToInt()
            binding.donationProgress.progress = progressPercent
            binding.root.setOnClickListener {
                onCaseClicked(case)
            }
        }

    }

    class CaseDiff : DiffUtil.ItemCallback<Case>() {
        override fun areItemsTheSame(oldItem: Case, newItem: Case) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Case, newItem: Case) = oldItem == newItem
    }

}
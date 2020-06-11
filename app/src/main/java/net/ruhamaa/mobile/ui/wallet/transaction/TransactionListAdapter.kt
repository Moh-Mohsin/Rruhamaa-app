package net.ruhamaa.mobile.ui.wallet.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_wallet.view.*
import net.ruhamaa.mobile.R
import net.ruhamaa.mobile.data.model.Transaction
import net.ruhamaa.mobile.databinding.TransactionListItemBinding
import net.ruhamaa.mobile.util.getColorCompact

class TransactionListAdapter :
    ListAdapter<Transaction, TransactionListAdapter.ViewHolder>(TransactionDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(TransactionListItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = getItem(position)
        holder.bind(transaction)
    }

    inner class ViewHolder(private val binding: TransactionListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            val context = binding.root.context
            binding.date.text = transaction.createdAt
            if (transaction.operation == 0){
                binding.operation.setText(R.string.withdrawal)
                binding.amount.setTextColor(context.getColorCompact(R.color.red))
                binding.amount.text = "- ${transaction.amount}"
            } else {
                binding.operation.setText(R.string.deposit)
                binding.amount.setTextColor(context.getColorCompact(R.color.green))
                binding.amount.text = "+ ${transaction.amount}"
            }
        }

    }

    class TransactionDiff : DiffUtil.ItemCallback<Transaction>() {
        //TODO: replace with id comparison when available
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction) = oldItem == newItem
    }

}
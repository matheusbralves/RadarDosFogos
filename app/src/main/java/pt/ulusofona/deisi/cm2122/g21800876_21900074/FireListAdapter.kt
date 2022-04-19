package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.ItemFireBinding


class FireListAdapter (private var items: List<String>) : RecyclerView.Adapter<FireListAdapter.FireListViewHolder>() {
    class FireListViewHolder(val  binding: ItemFireBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FireListViewHolder {
        return FireListViewHolder(
            ItemFireBinding.inflate (
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FireListViewHolder, position: Int) {
        holder.binding.fireLocation.text = items[position]
    }

    override fun getItemCount() = items.size
}
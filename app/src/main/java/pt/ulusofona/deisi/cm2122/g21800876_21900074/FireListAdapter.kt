package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.ItemFireBinding

private val TAG = MainActivity::class.java.simpleName

class FireListAdapter (
    private var items: List<FireParcelable> = listOf(),
    private val onClick: (FireParcelable) -> Unit)
    : RecyclerView.Adapter<FireListAdapter.FireListViewHolder>() {
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
        //Log.i(TAG, "${items[position].nome} TESTE")
        holder.binding.fireDistrito.text = items[position].distrito
        holder.binding.itemFire.setOnClickListener{ onClick(items[position]) }
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<FireParcelable>) {
        this.items = items
        notifyDataSetChanged()
    }
}
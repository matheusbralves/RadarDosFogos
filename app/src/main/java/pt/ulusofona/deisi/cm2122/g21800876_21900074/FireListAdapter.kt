package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.ItemFireBinding

/***
 *
 * Conecta a Lista de Fires no ScrollView
 * Pega a Lista deFires e cria os items do fragmento lista
 *
 * Se precisar adicionar onLongClick serguir a ideia do onClick
 *
 * Mudar em FireListFragment e aqui
 *
 * ***/

class FireListAdapter (
    private var items: List<FireParcelable> = listOf(),
    private val onClick: (FireParcelable) -> Unit)
    //private val onLongClick: (FireParcelable) -> Boolean)
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
        //Preenche cada item
        holder.binding.fireLocal.text = items[position].fire_name
        holder.binding.fireStatus.text = items[position].status
        holder.binding.fireData.text = items[position].data
        holder.binding.fireHora.text = items[position].hora

        holder.binding.itemFire.setOnClickListener{ onClick(items[position]) }
        //holder.binding.itemFire.setOnLongClickListener{ onLongClick(items[position]) }
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<FireParcelable>) {
        this.items = items
        notifyDataSetChanged()
    }
}
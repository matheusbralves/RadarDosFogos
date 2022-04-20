package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.util.Log
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentFireListBinding

private val TAG = MainActivity::class.java.simpleName

class FireListFragment : Fragment() {
    private lateinit var binding: FragmentFireListBinding
    private var model: FireModel = FireModel
    private val adapter = FireListAdapter()

    override fun onStart() {
        super.onStart()
        binding.rvList.layoutManager = LinearLayoutManager(activity as Context)
        binding.rvList.adapter = adapter
        adapter.updateItems(model.getAllRegistros())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Adicionar isso em todos os fragmentos pra ficar com o titulo certo na barra laranja
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.lista_de_fogos)

        val view = inflater.inflate(
            R.layout.fragment_fire_list, container, false
        )
        binding = FragmentFireListBinding.bind(view)
        return binding.root
    }
}
package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentDashboardBinding


private const val ARG_RADIUSLIST = "param1"
private lateinit var binding: FragmentDashboardBinding
private val TAG = MainActivity::class.java.simpleName

class DashboardFragment : Fragment() {
    //Lista de fogos dentro de um certo raio
    //Passar a lista dentro da main activity dentro dos ()
    private var radiusList = ArrayList(radiusListMain)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            radiusList = it.getStringArrayList(ARG_RADIUSLIST)!!//Confirmar se ta funcionando isso
        }

        //Adicionar isso em todos os fragmentos pra ficar com o titulo certo na barra laranja
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Início"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(
            R.layout.fragment_dashboard, container, false
        )
        binding = FragmentDashboardBinding.bind(view)
        return binding.root
    }

    override fun onStart(){
        super.onStart()
        Log.i(TAG, "Tela dashboard")

        spinnerSetup()
    }

    private fun spinnerSetup(){
        val spinner: Spinner = binding.spinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.radius_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                radiusSelect()
            }
        }
    }

    private fun radiusSelect(){
        val selected = binding.spinner.selectedItem
        Log.i(TAG, "Item é == $selected")
    }

    companion object {
        @JvmStatic fun newInstance(radiusList: ArrayList<String>) : DashboardFragment =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_RADIUSLIST, radiusList)
                }
            }
    }
}
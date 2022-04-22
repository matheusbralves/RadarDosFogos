package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentDetailsBinding

private const val ARG_INC = "ARG_INC"

class DetailsFragment : Fragment() {
    private var fire : FireParcelable? = null
    private lateinit var binding : FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { fire = it.getParcelable(ARG_INC) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        //Adicionar isso em todos os fragmentos pra ficar com o titulo certo na barra laranja
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.detalhe)

        val view = inflater.inflate(
            R.layout.fragment_details, container, false
        )
        binding = FragmentDetailsBinding.bind(view)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        fire?.let{
            binding.distrito.text = "Distrito: ${it.distrito}"
            binding.conselho.text = "Conselho: ${it.conselho}"
            binding.freguesia.text = "Freguesia: ${it.frequesia}"
            binding.data.text = "Data: ${it.data}"
            binding.hora.text = "Hora: ${it.hora}"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(fireParc : FireParcelable) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_INC, fireParc)
                }
            }
    }
}
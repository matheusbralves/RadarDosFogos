package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.content.ContentResolver
import android.net.Uri
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
            binding.localFire.text = "${it.distrito} / " + "${it.conselho} / " + "${it.frequesia}"
            //binding.fotoFire.setImageURI(it.foto?.let { it1 -> setImage(it1) })
            binding.aereosNumero.text = it.planes
            binding.operacionaisNumero.text = it.operationals
            binding.veiculosNumero.text = it.vehicles
            binding.dataFire.text = it.data
            binding.horaFire.text = it.hora
            when(it.status) {
                "Em curso" -> binding.estado.text = resources.getString(R.string.status_em_curso)
                "Terminado" -> binding.estado.text = resources.getString(R.string.status_terminado)
                "Por confirmar" -> binding.estado.text = resources.getString(R.string.status_por_confirmar)
            }
        }
    }

    private fun setImage(foto : Uri) : Uri? {
        return if(foto.toString() == "2131230896"){
            generateUri(R.drawable.sem_foto)
        }else{
            foto
        }
    }

    private fun generateUri(draw : Int) : Uri? {
        return Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(draw))
            .appendPath(resources.getResourceTypeName(draw))
            .appendPath(resources.getResourceEntryName(draw))
            .build()
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
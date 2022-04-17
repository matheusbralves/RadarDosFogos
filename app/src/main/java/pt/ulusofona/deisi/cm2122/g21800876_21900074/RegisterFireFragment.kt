package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentRegisterFireBinding

private lateinit var binding: FragmentRegisterFireBinding
private val TAG = MainActivity::class.java.simpleName

const val PICK_IMAGE = 1
val distritos = listOf("viana do castelo", "braga", "vila real", "braganca" , "bragança", "porto",
        "aveiro", "viseu", "guarda", "coimbra", "castelo branco", "leiria", "lisboa", "santarém",
        "santarem" , "portalegre", "setubal" , "setúbal", "evora", "évora", "beja", "faro")

class RegisterFireFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Adicionar isso em todos os fragmentos pra ficar com o titulo certo na barra laranja
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Registrar Fogo"

        val view = inflater.inflate(
            R.layout.fragment_register_fire, container, false
        )
        binding = FragmentRegisterFireBinding.bind(view)
        return binding.root
    }

    override fun onStart(){
        super.onStart()
        Log.i(TAG, "Tela registrar fogo")

        binding.sendButton.setOnClickListener{ saveRegistro() }
        //binding.fotoInput.setOnClickListener{ savePhoto() }
    }

    //Funções validações + salvar
    private fun validateAll(name : String, numberCC : String, distric : String, date : String, hour : String) : Boolean {
        if(name.isEmpty() || numberCC.isEmpty() || distric.isEmpty() || date.isEmpty() || hour.isEmpty()){
            Toast.makeText(activity, "Necessário preencher todos os campos", Toast.LENGTH_SHORT).show()
            return false
        }
        if(!validateNumberCC(numberCC)){
            Toast.makeText(activity, "Numero Cartão do Cidadão inválido", Toast.LENGTH_SHORT).show()
            return false
        }
        if(!validateDistric(distric)){
            Toast.makeText(activity, "Distrito Inválido", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validateDistric(distric: String) : Boolean{
        if(distric.lowercase() !in distritos){
            return false
        }
        return true
    }

    private fun validateNumberCC(numberCC : String) : Boolean {
        if(numberCC.length != 8){
            return false
        }
        return true
    }

    //Não testei
    private fun savePhoto(){
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Escolha a foto"), PICK_IMAGE)
    }

    private fun saveRegistro(){
        val name = binding.nomeInput.text.toString()
        val numberCC = binding.numeroccInput.text.toString()
        val distric = binding.distritoInput.text.toString()
        val date = "0"
        val hour = "0"
        val photo = ""

        if(validateAll(name, numberCC, distric, date, hour)){
            Log.i(TAG, "Salvar na lista")
        }
    }
}
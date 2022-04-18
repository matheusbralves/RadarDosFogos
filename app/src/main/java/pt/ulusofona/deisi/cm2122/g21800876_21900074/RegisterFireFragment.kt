package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentRegisterFireBinding
import java.text.SimpleDateFormat
import java.util.*

private lateinit var binding : FragmentRegisterFireBinding
private val TAG = MainActivity::class.java.simpleName

const val PICK_IMAGE = 1
private var imageUri: Uri? = null

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
        binding.fotoInput.setOnClickListener{ savePhoto() }
        districtSpinnerSetup()
    }

    private fun districtSpinnerSetup(){
        val spinner: Spinner = binding.spinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.district_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    //Funções validações + salvar
    private fun validateAll(name : String, numberCC : String) : Boolean {
        if(name.isEmpty() || numberCC.isEmpty()){
            Toast.makeText(activity, "Necessário preencher todos os campos", Toast.LENGTH_SHORT).show()
            return false
        }
        if(!validateNumberCC(numberCC)){
            Toast.makeText(activity, "Numero Cartão do Cidadão inválido", Toast.LENGTH_SHORT).show()
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

    private fun savePhoto(){
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data?.data
            binding.fotoInput.setImageURI(imageUri)
        }
    }

    private fun saveRegistro(){
        val name = binding.nomeInput.text.toString()
        Log.i(TAG, "Name == $name")
        val numberCC = binding.numeroccInput.text.toString()
        Log.i(TAG, "NumberCC == $numberCC")
        val distric = binding.spinner.selectedItem
        Log.i(TAG, "Distrito == $distric")
        val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        Log.i(TAG, "Data == $date")
        val hour = SimpleDateFormat("HH", Locale.getDefault()).format(Date())
        Log.i(TAG, "Hora == $hour")
        //Depende do que vai ter na classe de Incendio
        val photo = binding.fotoInput.drawable.toBitmap()
        Log.i(TAG, "Image == $photo")

        if(validateAll(name, numberCC)){
            Log.i(TAG, "Salvar na lista")
            Toast.makeText(activity, "Fogo registrado com sucesso!", Toast.LENGTH_SHORT).show()
            binding.nomeInput.text.clear()
            binding.numeroccInput.text.clear()
            binding.fotoInput.setImageResource(R.drawable.foto_input)
        }
    }
}
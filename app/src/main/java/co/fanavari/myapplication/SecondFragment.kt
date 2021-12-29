package co.fanavari.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import co.fanavari.myapplication.databinding.FragmentFirstBinding
import co.fanavari.myapplication.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false)
        _binding = FragmentSecondBinding.inflate(layoutInflater, container, false)

        val view = binding.root

        binding.textViewFragment2.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_firstFragment)

//            val action = FirstFragmentDirections.as()
//            Navigation.findNavController(view).navigate(action)
        }
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

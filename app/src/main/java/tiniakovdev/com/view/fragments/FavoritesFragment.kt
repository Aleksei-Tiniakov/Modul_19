package tiniakovdev.com.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import tiniakovdev.com.databinding.FragmentFavoriteBinding
import tiniakovdev.com.domain.Film
import tiniakovdev.com.utils.AnimationHelper
import tiniakovdev.com.view.MainActivity
import tiniakovdev.com.view.rv_adapter.FilmListRecyclerAdapter
import tiniakovdev.com.view.rv_adapter.TopSpacingItemDecoration


class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var filmAdapter: FilmListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteList: List<Film> = emptyList()

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.favoritesFragmentRoot,
            requireActivity(),
            2,
        )

        binding.favoritesRecycler.apply {
            filmAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        filmAdapter.addItems(favoriteList)
    }
}

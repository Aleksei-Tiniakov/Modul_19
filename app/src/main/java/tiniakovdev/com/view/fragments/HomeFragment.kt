package tiniakovdev.com.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tiniakovdev.com.databinding.FragmentHomeBinding
import tiniakovdev.com.domain.Film
import tiniakovdev.com.utils.AnimationHelper
import tiniakovdev.com.view.MainActivity
import tiniakovdev.com.view.rv_adapter.FilmListRecyclerAdapter
import tiniakovdev.com.view.rv_adapter.TopSpacingItemDecoration
import tiniakovdev.com.viewmodul.HomeFragmentViewModel
import java.util.*


class HomeFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }
    private lateinit var filmAdapter: FilmListRecyclerAdapter
    private lateinit var binding: FragmentHomeBinding

    private var filmsDataBase = listOf<Film>()
        set(value) {
            if (field == value) return
            field = value
            filmAdapter.addItems(field)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.homeFragmentRoot,
            requireActivity(),
            1
        )
        initSearchView()
        initRecycler()
        initPullToRefresh()

        viewModel.filmsListLiveData.observe(viewLifecycleOwner, Observer {
            filmsDataBase = it
            filmAdapter.addItems(it)
        })
    }

    private fun initPullToRefresh() {
        binding.pullToRefresh.setOnRefreshListener {
            filmAdapter.items.clear()
            viewModel.getFilms()
            binding.pullToRefresh.isRefreshing = false
        }
    }

    private fun initSearchView() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    filmAdapter.addItems(filmsDataBase)
                    return true
                }
                val result = filmsDataBase.filter {
                    it.title.lowercase(Locale.getDefault())
                        .contains(newText.lowercase(Locale.getDefault()))
                }
                filmAdapter.addItems(result)
                return true
            }
        })
    }

    private fun initRecycler() {
        binding.mainRecycler.apply {
            filmAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decoration = TopSpacingItemDecoration(8)
            addItemDecoration(decoration)
        }
    }

}



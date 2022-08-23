package tiniakovdev.com

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tiniakovdev.com.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var film: Film

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilmDetails()

        binding.detailsFabFavorite.setOnClickListener {
            if (!film.isInFavorites) {
                binding.detailsFabFavorite.setImageResource(R.drawable.ic_favorite)
                film.isInFavorites = true
            } else {
                binding.detailsFabFavorite.setImageResource(R.drawable.ic_favorite_border)
                film.isInFavorites = false
            }
        }

        binding.detailsFabShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film:${film.title}\n\n${film.description}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share to:"))
        }
    }

    private fun setFilmDetails() {
        val film = arguments?.get("film") as Film
        binding.detailsPoster.setImageResource(film.poster)
        binding.detailsToolbar.title = film.title
        binding.detailsDescription.text = film.description

        binding.detailsFabFavorite.setImageResource(
            if (film.isInFavorites) R.drawable.ic_favorite
            else R.drawable.ic_favorite_border
        )
    }
}

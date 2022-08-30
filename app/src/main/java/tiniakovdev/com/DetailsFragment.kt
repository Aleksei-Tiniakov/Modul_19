package tiniakovdev.com

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tiniakovdev.com.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding
    private lateinit var film: Film

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilmDetails()

        detailsBinding.detailsFabFavorite.setOnClickListener {
            if (!film.isInFavorites) {
                detailsBinding.detailsFabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                film.isInFavorites = true
            } else {
                detailsBinding.detailsFabFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                film.isInFavorites = false
            }
        }

        detailsBinding.detailsFabShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${film.title} \n\n ${film.description}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share to:"))
        }
    }

    private fun setFilmDetails() {

        film = arguments?.get("film") as Film

        detailsBinding.detailsPoster.setImageResource(film.poster)
        detailsBinding.detailsToolbar.title = film.title
        detailsBinding.detailsDescription.text = film.description

        detailsBinding.detailsFabFavorite.setImageResource(
            if (film.isInFavorites) R.drawable.ic_baseline_favorite_24
            else R.drawable.ic_baseline_favorite_border_24
        )
    }
}

package tiniakovdev.com


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import tiniakovdev.com.databinding.FragmentHomeBinding
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var filmAdapter: FilmListRecyclerAdapter
    private lateinit var binding: FragmentHomeBinding

    private val filmDataBase = listOf(
        Film(
            "Гладиатор", R.drawable.gladiator, "В великой Римской империи не было" +
                    " военачальника, равного генералу Максимусу. Непобедимые легионы, которыми" +
                    " командовал этот благородный воин, боготворили его и могли последовать за ним " +
                    "даже в ад.Но случилось так, что отважный Максимус, готовый сразиться" +
                    " с любым противником в честном бою, оказался бессилен против вероломных придворных" +
                    " интриг. Генерала предали и приговорили к смерти. Чудом избежав гибели, Максимус " +
                    "становится гладиатором. Быстро снискав себе славу в кровавых поединках," +
                    " он оказывается в знаменитом римском Колизее, на арене которого он встретится в " +
                    "смертельной схватке со своим заклятым врагом..."
        ),

        Film(
            "Гарри Поттер и философский камень",
            R.drawable.harry_potter_and_the_sorcerers_stone,
            "Жизнь десятилетнего Гарри Поттера нельзя назвать сладкой: родители умерли," +
                    " едва ему исполнился год, а от дяди и тёти, взявших сироту на воспитание," +
                    " достаются лишь тычки да подзатыльники. Но в одиннадцатый день рождения Гарри " +
                    "всё меняется. Странный гость, неожиданно появившийся на пороге, приносит письмо, " +
                    "из которого мальчик узнаёт, что на самом деле он - волшебник и зачислен в школу" +
                    " магии под названием Хогвартс. А уже через пару недель Гарри будет мчаться в " +
                    "поезде Хогвартс-экспресс навстречу новой жизни, где его ждут невероятные " +
                    "приключения, верные друзья и самое главное — ключ к разгадке тайны смерти его родителей."
        ),

        Film(
            "Достучаться до небес", R.drawable.knockin_on_heavens_door, "Волею" +
                    " судеб две абсолютные противоположности, тихоня Руди и разгильдяй Мартин, " +
                    "оказываются в одной больничной палате. Узнав неутешительные прогнозы, друзья " +
                    "решают использовать последние дни на полную катушку — угнать машину с деньгами," +
                    " напиться текилы, и, конечно, увидеть море."
        ),

        Film(
            "Шрек", R.drawable.shrek, "Жил да был в сказочном государстве большой " +
                    "зеленый великан по имени Шрэк. Жил он в гордом одиночестве в лесу, на болоте, " +
                    "которое считал своим. Но однажды злобный коротышка — лорд Фаркуад, правитель" +
                    " волшебного королевства, безжалостно согнал на Шрэково болото всех сказочных " +
                    "обитателей.\n" + "\n" + "И беспечной жизни зеленого великана пришел конец. " +
                    "Но лорд Фаркуад пообещал вернуть Шрэку болото, если великан добудет ему прекрасную" +
                    " принцессу Фиону, которая томится в неприступной башне, охраняемой огнедышащим драконом."
        ),

        Film(
            "Унесённые призраками", R.drawable.sen_to_chihiro_no_kamikakushi,
            "Тихиро с мамой и папой переезжает в новый дом. Заблудившись по дороге, они " +
                    "оказываются в странном пустынном городе, где их ждет великолепный пир. " +
                    "Родители с жадностью набрасываются на еду и к ужасу девочки превращаются в" +
                    " свиней, став пленниками злой колдуньи Юбабы. Теперь, оказавшись одна среди " +
                    "волшебных существ и загадочных видений, Тихиро должна придумать, как избавить" +
                    " своих родителей от чар коварной старухи."
        ),
        Film(
            "Темный рыцарь", R.drawable.the_dark_knight, "Бэтмен поднимает ставки" +
                    " в войне с криминалом. С помощью лейтенанта Джима Гордона и прокурора Харви Дента" +
                    " он намерен очистить улицы Готэма от преступности. Сотрудничество оказывается " +
                    "эффективным, но скоро они обнаружат себя посреди хаоса, развязанного восходящим" +
                    " криминальным гением, известным напуганным горожанам под именем Джокер."
        ),
        Film(
            "Клиника", R.drawable.scrubs, "Отучившись четыре года в медицинской школе," +
                    " Джон Дориан приходит работать интерном в клинику. Вместе с ним здесь же будет" +
                    " применять полученные знания и его лучший друг со времен колледжа Крис Терк." +
                    " Не имеющие опыта практической работы, молодые специалисты сразу же погружаются" +
                    " в хаотический мир жизни больницы..."
        ),
        Film(
            "Ходячий замок", R.drawable.hauru_no_ugoku_shiro, "Злая ведьма " +
                    "заточила 18-летнюю Софи в тело старухи. Девушка-бабушка бежит из города куда глаза" +
                    " глядят и встречает удивительный дом на ножках, где знакомится с могущественным" +
                    " волшебником Хаулом и демоном Кальцифером. Кальцифер должен служить Хаулу по" +
                    " договору, условия которого он не может разглашать. Девушка и демон решают помочь" +
                    " друг другу избавиться от злых чар."
        ),
        Film(
            "Бойцовский клюб", R.drawable.fight_club, "Сотрудник страховой компании " +
                    "страдает хронической бессонницей и отчаянно пытается вырваться из мучительно " +
                    "скучной жизни. Однажды в очередной командировке он встречает некоего Тайлера " +
                    "Дёрдена — харизматического торговца мылом с извращенной философией. Тайлер уверен," +
                    " что самосовершенствование — удел слабых, а единственное, ради чего стоит жить," +
                    " — саморазрушение.\n" + "\n" + "Проходит немного времени, и вот уже новые друзья" +
                    " лупят друг друга почем зря на стоянке перед баром, и очищающий мордобой" +
                    " доставляет им высшее блаженство. Приобщая других мужчин к простым радостям" +
                    " физической жестокости, они основывают тайный Бойцовский клуб, который начинает" +
                    " пользоваться невероятной популярностью."
        ),
        Film(
            "Интерстеллар ", R.drawable.interstellar, "Когда засуха, пыльные бури и" +
                    " вымирание растений приводят человечество к продовольственному кризису, коллектив" +
                    " исследователей и учёных отправляется сквозь червоточину (которая предположительно" +
                    " соединяет области пространства-времени через большое расстояние) в путешествие," +
                    " чтобы превзойти прежние ограничения для космических путешествий человека и найти" +
                    " планету с подходящими для человечества условиями."
        )
    )

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
        filmAdapter.addItems(filmDataBase)
    }

    private fun initSearchView() {

        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                if (newText.isEmpty()) {
                    filmAdapter.addItems(filmDataBase)
                    return true
                }
                val result = filmDataBase.filter {
                    it.title.lowercase(Locale.getDefault())
                        .contains(newText.lowercase(Locale.getDefault()))
                }
                filmAdapter.addItems(result)
                return true
            }
        })
        initRecycler()
        filmAdapter.addItems(filmDataBase)

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



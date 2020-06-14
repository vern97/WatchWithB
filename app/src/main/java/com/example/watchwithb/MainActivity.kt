package com.example.watchwithb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var popularTVShows: RecyclerView
    private lateinit var popularTVShowsAdapter: TVShowsAdapter
    private lateinit var popularTVShowsLayoutMgr: LinearLayoutManager

    private var popularTVShowsPage = 1

    private lateinit var topRatedTVShows: RecyclerView
    private lateinit var topRatedTVShowsAdapter: TVShowsAdapter
    private lateinit var topRatedTVShowsLayoutMgr: LinearLayoutManager

    private var topRatedTVShowsPage = 1

    private lateinit var onAirTVShows: RecyclerView
    private lateinit var onAirTVShowsAdapter: TVShowsAdapter
    private lateinit var onAirTVShowsLayoutMgr: LinearLayoutManager

    private var onAirTVShowsPage = 1

    private lateinit var airingTodayTVShows: RecyclerView
    private lateinit var airingTodayTVShowsAdapter: TVShowsAdapter
    private lateinit var airingTodayTVShowsLayoutMgr: LinearLayoutManager

    private var airingTodayTVShowsPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Popular TV Shows
        popularTVShows = findViewById(R.id.popular_tvshows)
        popularTVShowsLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )


        popularTVShows.layoutManager = popularTVShowsLayoutMgr
        popularTVShowsAdapter = TVShowsAdapter(mutableListOf()) { tvshow -> showTVShowDetails(tvshow) }
        popularTVShows.adapter = popularTVShowsAdapter

        TVShowsRepository.getPopularTVShows(
            popularTVShowsPage,
            ::onPopularTVShowsFetched,
            ::onError
        )

        //Top rated TV Shows
        topRatedTVShows = findViewById(R.id.top_rated_tvshows)
        topRatedTVShowsLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        topRatedTVShows.layoutManager = topRatedTVShowsLayoutMgr
        topRatedTVShowsAdapter = TVShowsAdapter(mutableListOf()) { tvshow -> showTVShowDetails(tvshow) }
        topRatedTVShows.adapter = topRatedTVShowsAdapter

        TVShowsRepository.getTopRatedTVShows(
            topRatedTVShowsPage,
            ::onTopRatedTVShowsFetched,
            ::onError
        )

        //On air TV Shows
        onAirTVShows = findViewById(R.id.on_air_tvshows)
        onAirTVShowsLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        onAirTVShows.layoutManager = onAirTVShowsLayoutMgr
        onAirTVShowsAdapter = TVShowsAdapter(mutableListOf()) { tvshow -> showTVShowDetails((tvshow))}
        onAirTVShows.adapter = onAirTVShowsAdapter

        TVShowsRepository.getOnAirTVShows(
            onAirTVShowsPage,
            ::onAirTVShowsFetched,
            ::onError
        )

        //Airing today TV Shows
        airingTodayTVShows = findViewById(R.id.airing_today_tvshows)
        airingTodayTVShowsLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        airingTodayTVShows.layoutManager = airingTodayTVShowsLayoutMgr
        airingTodayTVShowsAdapter = TVShowsAdapter(mutableListOf()) { tvshow -> showTVShowDetails((tvshow))}
        airingTodayTVShows.adapter = airingTodayTVShowsAdapter

        TVShowsRepository.getAiringTodayTVShows(
            airingTodayTVShowsPage,
            ::onAiringTodayTVShowsFetched,
            ::onError
        )

        getPopularTVShows()
        getTopRatedTVShows()
        getOnAirTVShows()
        getAiringTodayTVShows()
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_tvshows), Toast.LENGTH_SHORT).show()
    }

    private fun getPopularTVShows() {
        TVShowsRepository.getPopularTVShows(
            popularTVShowsPage,
            ::onPopularTVShowsFetched,
            ::onError
        )
    }

    private fun onPopularTVShowsFetched(tvshows: List<TVShow>) {
        popularTVShowsAdapter.appendTVShows(tvshows)
        attachPopularTVShowsOnScrollListener()
    }

    private fun attachPopularTVShowsOnScrollListener() {
        popularTVShows.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularTVShowsLayoutMgr.itemCount
                val visibleItemCount = popularTVShowsLayoutMgr.childCount
                val firstVisibleItem = popularTVShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularTVShows.removeOnScrollListener(this)
                    popularTVShowsPage++
                    getPopularTVShows()
                }
            }
        })
    }

    private fun getTopRatedTVShows() {
        TVShowsRepository.getTopRatedTVShows(
            topRatedTVShowsPage,
            ::onTopRatedTVShowsFetched,
            ::onError
        )
    }

    private fun onTopRatedTVShowsFetched(tvshows: List<TVShow>) {
        topRatedTVShowsAdapter.appendTVShows(tvshows)
        attachTopRatedTVShowsOnScrollListener()
    }

    private fun attachTopRatedTVShowsOnScrollListener() {
        topRatedTVShows.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedTVShowsLayoutMgr.itemCount
                val visibleItemCount = topRatedTVShowsLayoutMgr.childCount
                val firstVisibleItem = topRatedTVShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedTVShows.removeOnScrollListener(this)
                    topRatedTVShowsPage++
                    getTopRatedTVShows()
                }
            }
        })
    }

    private fun getOnAirTVShows() {
        TVShowsRepository.getOnAirTVShows(
            onAirTVShowsPage,
            ::onAirTVShowsFetched,
            ::onError
        )
    }

    private fun onAirTVShowsFetched(tvshows: List<TVShow>) {
        onAirTVShowsAdapter.appendTVShows(tvshows)
        attachOnAirTVShowsOnScrollListener()
    }

    private fun attachOnAirTVShowsOnScrollListener() {
        onAirTVShows.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = onAirTVShowsLayoutMgr.itemCount
                val visibleItemCount = onAirTVShowsLayoutMgr.childCount
                val firstVisibleItem = onAirTVShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    onAirTVShows.removeOnScrollListener(this)
                    onAirTVShowsPage++
                    getOnAirTVShows()
                }
            }
        })
    }

    private fun getAiringTodayTVShows() {
        TVShowsRepository.getAiringTodayTVShows(
            airingTodayTVShowsPage,
            ::onAiringTodayTVShowsFetched,
            ::onError
        )
    }

    private fun onAiringTodayTVShowsFetched(tvshows: List<TVShow>) {
        airingTodayTVShowsAdapter.appendTVShows(tvshows)
        attachAiringTodayTVShowsOnScrollListener()
    }

    private fun attachAiringTodayTVShowsOnScrollListener() {
        airingTodayTVShows.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = airingTodayTVShowsLayoutMgr.itemCount
                val visibleItemCount = airingTodayTVShowsLayoutMgr.childCount
                val firstVisibleItem = airingTodayTVShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    airingTodayTVShows.removeOnScrollListener(this)
                    airingTodayTVShowsPage++
                    getAiringTodayTVShows()
                }
            }
        })
    }

    private fun showTVShowDetails(tvshow: TVShow) {
        val intent = Intent(this, TVShowDetailsActivity::class.java)
        intent.putExtra(TVSHOW_BACKDROP, tvshow.backdropPath)
        intent.putExtra(TVSHOW_POSTER, tvshow.posterPath)
        intent.putExtra(TVSHOW_TITLE, tvshow.title)
        intent.putExtra(TVSHOW_RATING, tvshow.rating)
        intent.putExtra(TVSHOW_RELEASE_DATE, tvshow.releaseDate)
        intent.putExtra(TVSHOW_OVERVIEW, tvshow.overview)
        startActivity(intent)
    }

}

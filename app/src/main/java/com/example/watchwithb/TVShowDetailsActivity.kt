package com.example.watchwithb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

const val TVSHOW_BACKDROP = "extra_tvshow_backdrop"
const val TVSHOW_POSTER = "extra_tvshow_poster"
const val TVSHOW_TITLE = "extra_tvshow_title"
const val TVSHOW_RATING = "extra_tvshow_rating"
const val TVSHOW_RELEASE_DATE = "extra_tvshow_release_date"
const val TVSHOW_OVERVIEW = "extra_tvshow_overview"

class TVShowDetailsActivity : AppCompatActivity() {

    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshow_details)

        backdrop = findViewById(R.id.tvshow_backdrop)
        poster = findViewById(R.id.tvshow_poster)
        title = findViewById(R.id.tvshow_title)
        rating = findViewById(R.id.tvshow_rating)
        releaseDate = findViewById(R.id.tvshow_release_date)
        overview = findViewById(R.id.tvshow_overview)

        val extras = intent.extras
        if (extras != null) {
            populateDetails(extras)
        } else {
            finish()
        }
    }

    private fun populateDetails(extras: Bundle) {
        extras.getString(TVSHOW_BACKDROP)?.let { backdropPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280$backdropPath")
                .transform(CenterCrop())
                .into(backdrop)
        }
        extras.getString(TVSHOW_POSTER)?.let { posterPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342$posterPath")
                .transform(CenterCrop())
                .into(poster)
        }
        title.text = extras.getString(TVSHOW_TITLE, "")
        rating.rating = extras.getFloat(TVSHOW_RATING, 0f) / 2
        releaseDate.text = extras.getString(TVSHOW_RELEASE_DATE, "")
        overview.text = extras.getString(TVSHOW_OVERVIEW, "")
    }

}

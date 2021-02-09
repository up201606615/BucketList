package pt.atp.bucketlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    private fun setup() {
        findViewById<RecyclerView>(R.id.rv_stories).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = StoryAdapter(Places.placesToVisit)
        }

        findViewById<RecyclerView>(R.id.rv_feed).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = FeedAdapter(Places.placesToVisit)
        }
    }
}
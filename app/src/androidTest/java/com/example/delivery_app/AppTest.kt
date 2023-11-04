package com.example.delivery_app

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Test


// Взято с https://stackoverflow.com/questions/31394569/how-to-assert-inside-a-recyclerview-in-espresso
fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
    checkNotNull(itemMatcher)
    return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder = view.findViewHolderForAdapterPosition(position)
                ?: // has no item on such position
                return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }
}

class AppTest {
    // Поскольку никакой логики в приложении не предусмотрено, просто проверим
    @Test
    fun runApp() {
        ActivityScenario.launch(MainActivity::class.java)
        // Проверка иконки
        onView(withId(R.id.rvProducts)).check(matches(atPosition(0, hasDescendant(withText("Телефон")))))
    }
}
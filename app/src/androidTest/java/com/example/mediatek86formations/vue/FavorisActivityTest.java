package com.example.mediatek86formations.vue;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.example.mediatek86formations.*;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FavorisActivityTest {


        @Rule
        public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

        @Test
        public void filterFavoris()
        {
            onView(withId(R.id.btnFavoris)).perform(click());
            onView(withId(R.id.txtFiltreFavs)).perform(typeText("an"), closeSoftKeyboard());
            onView(withId(R.id.btnFiltrer)).perform(click());
        }


}


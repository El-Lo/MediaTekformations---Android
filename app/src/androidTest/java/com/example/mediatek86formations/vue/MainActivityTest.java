package com.example.mediatek86formations.vue;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.example.mediatek86formations.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;



@RunWith(JUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void gotoFormations()
    {
        onView(withId(R.id.btnFormations)).perform(click());

    }
    @Test
    public void gotoFavoris()
    {
        onView(withId(R.id.btnFavoris)).perform(click());

    }
}
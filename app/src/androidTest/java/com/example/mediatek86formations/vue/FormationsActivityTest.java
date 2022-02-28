package com.example.mediatek86formations.vue;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.mediatek86formations.*;

import org.junit.Rule;
import org.junit.Test;

public class FormationsActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void filterFormations()
    {
        onView(withId(R.id.btnFormations)).perform(click());
        onView(withId(R.id.txtFiltreFavs)).perform(typeText("an"), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
    }
}


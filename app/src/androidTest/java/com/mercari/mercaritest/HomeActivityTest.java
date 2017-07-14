package com.mercari.mercaritest;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;

import com.mercari.mercaritest.ui.HomeActivity;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;

public class HomeActivityTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    HomeActivity activity;

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(
            HomeActivity.class);

    public HomeActivityTest() {
        super(HomeActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    @SmallTest
    public void testRecyclerViewNotNull() {
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view);
        assertNotNull(recyclerView);
    }

    /*
        test RecyclerView item count
     */
    @SmallTest
    public void testRecyclerViewItemsCount() {
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view);
        assertEquals(150, recyclerView.getAdapter().getItemCount());
    }

    /*
        test RecyclerView item content
     */
    @SmallTest
    public void testRecyclerViewContent() {
        onView(withText("men1")).check(matches(isDisplayed()));
    }
}

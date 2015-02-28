package com.bingzer.android.babycare.views.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Concept by:
 * http://thehayro.blogspot.com/2012/12/enable-infinite-paging-with-android.html
 */
public abstract class EndlessPagerAdapter extends PagerAdapter {

    public static final int PAGE_LEFT = 0;
    public static final int PAGE_MIDDLE = 1;
    public static final int PAGE_RIGHT = 2;

    private int selectedPosition;
    private PageModel[] pageModels = new PageModel[3];
    private ViewPager viewPager;

    ///////////////////////////////////////////////////////////////////////////////////

    public EndlessPagerAdapter(ViewPager viewPager){
        this.viewPager = viewPager;
        this.viewPager.setOnPageChangeListener(onPageChangeListener);
        this.viewPager.setCurrentItem(PAGE_MIDDLE, false);

        for (int i = 0; i < pageModels.length; i++) {
            // initing the pagemodel with indexes of -1, 0 and 1
            pageModels[i] = new PageModel(i - 1);
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        // we only need three pages
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PageModel currentPage = pageModels[position];
        currentPage.view = getView(currentPage.view, position, currentPage.getIndex());
        container.addView(currentPage.view);

        return currentPage.view;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    /////////////////////////////////////////////////////////////////////////////////

    public abstract View getView(View convertView, int position, int virtualPosition);

    /////////////////////////////////////////////////////////////////////////////////

    public int getVirtualPosition(){
        PageModel currentPage = pageModels[selectedPosition];
        return currentPage.getIndex();
    }

    public Context getContext(){
        return viewPager.getContext();
    }

    public ViewPager getViewPager(){
        return viewPager;
    }

    /////////////////////////////////////////////////////////////////////////////////


    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            selectedPosition = position;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) { }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {

                final PageModel leftPage = pageModels[PAGE_LEFT];
                final PageModel middlePage = pageModels[PAGE_MIDDLE];
                final PageModel rightPage = pageModels[PAGE_RIGHT];

                if(leftPage == null || middlePage == null || rightPage == null)
                    return;

                final int oldLeftIndex = leftPage.getIndex();
                final int oldMiddleIndex = middlePage.getIndex();
                final int oldRightIndex = rightPage.getIndex();

                // user swiped to right direction --> left page
                if (selectedPosition == PAGE_LEFT) {
                    // moving each page content one page to the right
                    leftPage.setIndex(oldLeftIndex - 1);
                    middlePage.setIndex(oldLeftIndex);
                    rightPage.setIndex(oldMiddleIndex);

                    // user swiped to left direction --> right page
                }
                else if (selectedPosition == PAGE_RIGHT) {
                    leftPage.setIndex(oldMiddleIndex);
                    middlePage.setIndex(oldRightIndex);
                    rightPage.setIndex(oldRightIndex + 1);
                }

                getViewPager().setCurrentItem(PAGE_MIDDLE, false);

                leftPage.view = getView(leftPage.view, PAGE_LEFT, leftPage.getIndex());
                middlePage.view = getView(middlePage.view, PAGE_MIDDLE, middlePage.getIndex());
                rightPage.view = getView(rightPage.view, PAGE_RIGHT, rightPage.getIndex());
            }
        }
    };

    /////////////////////////////////////////////////////////////////////////////////

    class PageModel {

        private int index;
        public View view;

        public PageModel(int index) {
            setIndex(index);
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }


    }

    /////////////////////////////////////////////////////////////////////////////////

    public static class PageModelAdapter {

    }

}

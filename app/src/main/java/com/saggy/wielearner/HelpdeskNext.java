package com.saggy.wielearner;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;

public class HelpdeskNext extends YouTubeBaseActivity {
    int extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpdesk_next);

        extra = getIntent().getIntExtra("extra",0);

        switch (extra){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            default:
        }
    }
}
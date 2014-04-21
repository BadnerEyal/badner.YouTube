package com.example.badner.youtube;


import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import android.os.Bundle;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class EnterApp<onytplayerStateChange> extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    public static final String API_KEY = "AIzaSyCH4blfA4fWx_smU-wJT9amC4DouwxWItA";
    public static final String VIDEO_ID = "0qDdYlyLRoI";
   // private static YouTubePlayer player;
    public ImageView imagebig;
    public ImageView imagclose;

    YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter);
       
        
        imagebig=( ImageView)findViewById(R.id.imageViewbigpic);
        
        
        //  YouTubePlayerView youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubeplayerview);
      //  youTubePlayerView.initialize(API_KEY, this);
  
       youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubeplayerview);
        youTubePlayerView.initialize(API_KEY,this);
       
        
        imagclose =( ImageView)findViewById(R.id.imageViewclose);
        imagclose.setOnClickListener(new OnClickListener() { 
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent(EnterApp.this,se.class);
					startActivity(intent);
			}
		});
        
        
    }

      
   
/**במקרה של כישלון באתחול */
    @Override
    public void onInitializationFailure(Provider provider,
            YouTubeInitializationResult result) {
        Toast.makeText(getApplicationContext(), 
                "onInitializationFailure()", 
                Toast.LENGTH_LONG).show();
         //במקרה של כישלון באתחול נעבור גם לדף ראשי
        Intent intent = new Intent(EnterApp.this,se.class);
		startActivity(intent);
        
    }

    /**במקרה שאתחול הצליח ננגן את הסרט בסיום נעבור לדף הראשי*/
    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {

    	//MainActivity.player = player; //necessary to access inside Runnable 

    	
    	
    	//הפעלה אוטמטית ישר אחרי כניסה
        player.loadVideo(VIDEO_ID, 0);
      
   //מאזין שנדע באיזה מצב אנחנו בצפית הסרט     
  player.setPlayerStateChangeListener(new PlayerStateChangeListener() {
	
	
	@Override
	public void onVideoStarted() {
		// TODO Auto-generated method stub
		
		
	}
	
	//שהסרט נגמר נעבור דף
	@Override
	public void onVideoEnded() {
		// TODO Auto-generated method stub
		  Toast.makeText(EnterApp.this," You must include a subject" , Toast.LENGTH_SHORT).show();	
		  
		  //מעבר לדף הראשי של הסרטים
		  Intent intent = new Intent(EnterApp.this,se.class);
			startActivity(intent);
		 
		  
	}
	
	@Override
	public void onLoading() {
		// TODO Auto-generated method stub
		//imagebig
				imagebig.setVisibility(View.VISIBLE);//שיהיה גלוי
			    //האנמציה
			    //public TranslateAnimation (float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)

				TranslateAnimation slide = new TranslateAnimation(-100, 0, 400,0 );   
			    slide.setDuration(3500); //זמן תזוזה  
			    slide.setFillAfter(true);   
			   //התחלת אנמציה
			    imagebig.startAnimation(slide);
	}
	
	@Override
	public void onLoaded(String arg0) {
		// TODO Auto-generated method stub
		 
	}
	
	//במקרה שיש תקלה בצפית הסרט גם נעבור לדף הראשי
	@Override
	public void onError(ErrorReason arg0) {
		// TODO Auto-generated method stub
		//מעבר לדף הראשי של הסרטים
		  Intent intent = new Intent(EnterApp.this,se.class);
			startActivity(intent);
	}
	
	@Override
	public void onAdStarted() {
		// TODO Auto-generated method stub
		
	}
});
			
			
     
        
    
 }
      
	
}

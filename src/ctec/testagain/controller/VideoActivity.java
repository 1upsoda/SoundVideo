package ctec.testagain.controller;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class VideoActivity extends Activity
{
	private VideoView myPlayer;
	private Button returnButton;
	private MediaController myVideoController;
	private Uri videoLocation;
	
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		myPlayer = (VideoView) findViewById(R.id.videoView1);
		returnButton = (Button) findViewById(R.id.homeButton);
		
		videoLocation = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.party);
		myVideoController = new MediaController(this);
		setupMedia();
		setupListeners();
		
	}

	private void setupMedia()
	{
		myPlayer.setMediaController(myVideoController);
		myPlayer.setVideoURI(videoLocation);
		
	}
	private void setupListeners()
	{
		returnButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent returnIntent = new Intent();
				setResult(RESULT_OK, returnIntent);
				finish();

			}
		});
	}
}

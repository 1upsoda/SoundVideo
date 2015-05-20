package ctec.testagain.controller;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity implements Runnable
{
	private Button startButton, randomButton;
	private Button pauseButton;
	private Button stopButton;
	private MediaPlayer soundPlayer;
	private Thread soundThread;
	private SeekBar soundSeekBar;
	private Button videoButton;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		randomButton = (Button) findViewById(R.id.randomButton);
		videoButton = (Button) findViewById(R.id.videoButton);
		soundSeekBar = (SeekBar) findViewById(R.id.soundSeekBar);
		soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.ragtime);
		startButton = (Button) findViewById(R.id.playButton);
		pauseButton = (Button) findViewById(R.id.pauseButton);
		stopButton = (Button) findViewById(R.id.stopButton);

		setupListeners();
		soundThread = new Thread(this);
		soundThread.start();

	}

	private void setupListeners()
	{

		videoButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View currentView)
			{
				// TODO Auto-generated method stub
				Intent otherScreenIntent = new Intent(currentView.getContext(), VideoActivity.class);
				startActivityForResult(otherScreenIntent, 0);
			}
		});
		startButton.setOnClickListener(new View.OnClickListener()

		{

			@Override
			public void onClick(View currentView)
			{
				soundPlayer.start();
			}
		});
		randomButton.setOnClickListener(new View.OnClickListener()

		{

			@Override
			public void onClick(View currentView)
			{
				soundPlayer.stop();
				int randomChoice = (int) (Math.random() * 4);
				if (randomChoice == 1)
				{
					soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.ventricide);
				}
				else if (randomChoice == 0)
				{
					soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.ragtime);
				}
				else if (randomChoice == 2)
				{
					soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.duress);
				}
				else if (randomChoice == 3)
				{
					soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.loves_me);
				}
				soundPlayer.start();
			}
		});
		stopButton.setOnClickListener(new View.OnClickListener()

		{

			@Override
			public void onClick(View currentView)
			{
				soundPlayer.stop();
				soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.ragtime);
			}
		});

		pauseButton.setOnClickListener(new View.OnClickListener()

		{

			@Override
			public void onClick(View currentView)
			{
				soundPlayer.pause();
			}
		});

		soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				if (fromUser)
				{
					soundPlayer.seekTo(progress);
				}
			}

		});

	}

	@Override
	public void run()

	{
		int currentPosition = 0;
		int soundTotal = soundPlayer.getDuration();

		soundSeekBar.setMax(soundTotal);

		while (soundPlayer != null && currentPosition < soundTotal)
		{
			try
			{
				Thread.sleep(50);
				currentPosition = soundPlayer.getCurrentPosition();
			}
			catch (InterruptedException soundException)
			{
				return;
			}
			catch (Exception otherException)
			{
				return;
			}
			soundSeekBar.setProgress(currentPosition);
		}

	}
}

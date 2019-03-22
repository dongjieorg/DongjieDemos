package com.dongjie.dongjiedemos.mp3;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;

public class Player implements OnCompletionListener {
	private MediaPlayer mediaPlayer;
	private PlayerHandler mPlayerhandler;
	Context mContext = null;

    public Player(Context context) {
        mContext = context;
        mPlayerhandler = new PlayerHandler();

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(this);
        } catch (Exception e) {
            Log.e("mediaPlayer", "error", e);
        }
    }

	public Player(Context context, PlayerCallback playCallback) {
		mContext = context;
		mPlayerhandler = new PlayerHandler();
		if (playCallback != null) {
			mPlayerhandler.setListener(playCallback);
		}
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(this);
		} catch (Exception e) {
			Log.e("mediaPlayer", "error", e);
		}

	}

	public void play() {
		mediaPlayer.start();
		mPlayerhandler.onGetPalyerState(PlayerState.PLAYING);
	}

	public void setVolume(float vol) {
		mediaPlayer.setVolume(vol, vol);
	}

	// 播放AssetFileDescriptor
	public void playFileDescriptor(String name) {
		try {
			AssetManager assetManager = mContext.getAssets();
			AssetFileDescriptor fileDescriptor = assetManager.openFd(name);
			mediaPlayer.reset();
			mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
					fileDescriptor.getStartOffset(), fileDescriptor.getLength());
			mediaPlayer.setLooping(false);
			mediaPlayer.prepare();
			// mediaPlayer.setVolume(0.3f, 0.3f);
			mediaPlayer.start();
			mPlayerhandler.onGetPalyerState(PlayerState.PLAYING);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 播放路径
	public void playFile(String videoFile) {
		Log.d("dongjie", "playing......");
		try {
			mediaPlayer.reset();
			FileInputStream fis = new FileInputStream(videoFile);
			mediaPlayer.setDataSource(fis.getFD());
			fis.close();
			mediaPlayer.prepare();
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			mPlayerhandler.onGetPalyerState(PlayerState.PLAYING);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pause() {
		mediaPlayer.pause();
		mPlayerhandler.onGetPalyerState(PlayerState.PAUSE);
	}

	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mPlayerhandler.onGetPalyerState(PlayerState.STOP);
		}
	}

	public void release() {
		if (mediaPlayer != null) {
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		Log.d("mediaPlayer", "onCompletion");
		mPlayerhandler.onGetPalyerState(PlayerState.STOP);
        if (mOnPlayComplete != null) {
            mOnPlayComplete.onComplete();
        }
	}

	OnPlayComplete mOnPlayComplete = null;

    public void setOnPlayComplete(OnPlayComplete onPlayComplete) {
        mOnPlayComplete = onPlayComplete;
    }

	public interface OnPlayComplete {
        void onComplete();
    }

	class PlayerHandler {
		static final String TAG = "PalyerCallback";

		protected static final int VOICE_THREAD_STOP = 0;
		protected static final int VOICE_RECORD_STOP = 1;

		private PlayerCallback mCallback;

		private Handler mHandler = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				if (mCallback != null)
					switch (msg.what) {
					case PlayerState.PLAYING:
						mCallback.onGetPlayerStatePlaying();
						break;
					case PlayerState.PAUSE:
						mCallback.onGetPlayerStatePause();
						break;
					case PlayerState.STOP:
						mCallback.onGetPlayerStateStop();
						break;
					}
			}
		};

		public PlayerHandler() {

		}

		public void setListener(PlayerCallback listener) {
			mCallback = listener;
		}

		public void onGetPalyerState(int state) {
			mHandler.sendMessage(mHandler.obtainMessage(state));
		}
	}

	public interface PlayerCallback {
		public void onGetPlayerStatePlaying();

		public void onGetPlayerStatePause();

		public void onGetPlayerStateStop();
	}

}

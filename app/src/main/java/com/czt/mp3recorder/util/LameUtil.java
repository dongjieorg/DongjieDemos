package com.czt.mp3recorder.util;

public class LameUtil {
	static{
		System.loadLibrary("mp3lame");
	}

	public native static void init(int inSamplerate, int inChannel,
			int outSamplerate, int outBitrate, int quality);

	public native static int encode(short[] bufferLeft, short[] bufferRight,
			int samples, byte[] mp3buf);

	public native static int flush(byte[] mp3buf);

	public native static void close();
}

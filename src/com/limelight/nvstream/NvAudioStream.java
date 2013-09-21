package com.limelight.nvstream;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.net.rtp.AudioCodec;
import android.net.rtp.AudioGroup;
import android.net.rtp.AudioStream;

public class NvAudioStream {
	private AudioGroup group;
	private AudioStream stream;
	
	public static final int PORT = 48000;
	
	/*public void startStream(String host) throws SocketException, UnknownHostException
	{
		System.out.println("Starting audio group");
		group = new AudioGroup();
		group.setMode(AudioGroup.MODE_NORMAL);
		
		System.out.println("Starting audio stream");
		stream = new AudioStream(InetAddress.getByAddress(new byte[]{0,0,0,0}));
		stream.setMode(AudioStream.MODE_NORMAL);
		stream.associate(InetAddress.getByName(host), PORT);
		stream.setCodec(AudioCodec.PCMA);
		stream.join(group);
		
		for (AudioCodec c : AudioCodec.getCodecs())
			System.out.println(c.type + " " + c.fmtp + " " + c.rtpmap);
		
		System.out.println("Joined");
	}*/
	
	public void start()
	{		
		new Thread(new Runnable() {

			@Override
			public void run() {
				DatagramSocket ds;
				try {
					ds = new DatagramSocket(PORT);
				} catch (SocketException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return;
				}

				for (;;)
				{
					
					DatagramPacket dp = new DatagramPacket(new byte[1500], 1500);
					
					try {
						ds.receive(dp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
					
					//System.out.println("Got UDP 48000: "+dp.getLength());
				}
			}
			
		}).start();
	}
}
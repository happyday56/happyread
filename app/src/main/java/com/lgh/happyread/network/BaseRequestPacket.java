package com.lgh.happyread.network;

import android.content.Context;

import com.lgh.happyread.model.IToStringMap;

public  class BaseRequestPacket {
	public Context context;
	public int action;
	public IToStringMap object;
	public Object extra;
	
	public BaseRequestPacket(){
		
	}
}

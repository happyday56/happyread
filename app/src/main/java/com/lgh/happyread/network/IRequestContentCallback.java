package com.lgh.happyread.network;

public interface IRequestContentCallback {

	public void  onResult(int requestAction, Boolean isSuccess, String content, Object extra);
}

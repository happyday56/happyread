package com.lgh.happyread.network;



public interface IRequestDataPacketCallback {

	void  onSuccess(int requestAction, ResponseDataPacket dataPacket, Object extra);
	void  onRequestFailure(int requestAction, String content,  Object extra);
	void  onAnylizeFailure(int requestAction, String content,  Object extra);
}

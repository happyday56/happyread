package com.lgh.happyread.model;

import org.json.JSONException;
import org.json.JSONObject;

public interface IParseJson {

	public boolean parseJson(JSONObject jsonObject) throws JSONException;
}

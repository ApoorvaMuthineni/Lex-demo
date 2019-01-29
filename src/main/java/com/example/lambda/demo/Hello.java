package com.example.lambda.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.sample.aws.lex.request.LexRequest;
import org.sample.aws.lex.response.DialogAction;
import org.sample.aws.lex.response.LexResponse;
import org.sample.aws.lex.response.Message;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.util.IOUtils;
import com.google.gson.Gson;

public class Hello {

	public LexResponse handleRequest2(LexRequest request, Context context) {
		Message msg = new Message("PlainText",
				"Hi, " + request.getCurrentIntent().getSlots().get("FlowerType") + " are ordered for you");
		DialogAction diaAct = new DialogAction("Close", "Fulfilled", msg);
		LexResponse resp = new LexResponse(diaAct);
		return resp;
	}

	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

		String requestString = IOUtils.toString(inputStream);
		LexRequest obj = new Gson().fromJson(requestString, LexRequest.class);
		Message msg = new Message("PlainText",
				"Hi, " + obj.getCurrentIntent().getSlots().get("FlowerType") + " are ordered ");
		DialogAction diaAct = new DialogAction("Close", "Fulfilled", msg);

		LexResponse resp = new LexResponse(diaAct);
		outputStream.write(new Gson().toJson(resp).getBytes());

	}

}

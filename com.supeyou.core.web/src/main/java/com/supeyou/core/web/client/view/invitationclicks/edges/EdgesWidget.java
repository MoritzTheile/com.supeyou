package com.supeyou.core.web.client.view.invitationclicks.edges;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;

public class EdgesWidget extends WidgetView {

	private Canvas canvas;

	private List<Edge> edges = new ArrayList<>();

	public EdgesWidget(int width, int height) {

		canvas = Canvas.createIfSupported();
		canvas.setCoordinateSpaceWidth(width);
		canvas.setCoordinateSpaceHeight(height);
		canvasSlot.add(canvas);

	}

	public void addNewEdge(Edge edge) {

		edges.add(edge);

	}

	public void render() {

		Context2d context1 = canvas.getContext2d();
		context1.beginPath();
		context1.moveTo(25, 0);
		context1.lineTo(0, 20);
		context1.lineTo(25, 40);
		context1.lineTo(25, 0);
		context1.fill();
		context1.closePath();

	}

}

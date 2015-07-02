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
		// canvas.setWidth("" + width);
		// canvas.setHeight("" + height);
		canvasSlot.add(canvas);

	}

	public void addNewEdge(Edge edge) {

		edges.add(edge);

	}

	public void render() {

		for (Edge edge : edges) {
			Context2d context1 = canvas.getContext2d();
			context1.moveTo(canvas.getCoordinateSpaceWidth() / 2, 0);
			context1.bezierCurveTo(
					canvas.getCoordinateSpaceWidth() / 2, canvas.getCoordinateSpaceHeight() / 2,
					edge.getDistanceFromMiddle(), canvas.getCoordinateSpaceHeight() / 2,
					edge.getDistanceFromMiddle(), canvas.getCoordinateSpaceHeight());

			context1.setStrokeStyle("red");

			context1.stroke();

		}
	}

}

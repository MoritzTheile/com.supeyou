package com.supeyou.core.web.client.view.heropage.supportertree.edges;

public class Edge {

	private int distanceFromMiddle;

	public Edge(int distanceFromMiddle) {
		this.setDistanceFromMiddle(distanceFromMiddle);
	}

	public int getDistanceFromMiddle() {
		return distanceFromMiddle;
	}

	public void setDistanceFromMiddle(int distanceFromMiddle) {
		this.distanceFromMiddle = distanceFromMiddle;
	}

}

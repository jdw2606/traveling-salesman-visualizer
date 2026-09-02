import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class MyCanvas extends JPanel{
	int x = 800;
	int y = 800;
	
	int width = 15;
	
	int numCities = 10;
	
	City[] cities = new City[numCities];
	
	double shortest;
	
	City[] shortestPath;
	
	MyCanvas(JButton button) {
		this.setPreferredSize(new Dimension(x, y));
		
		for (int i = 0; i < numCities; i ++) {
			cities[i] = new City(x, y);
		}
		
		shortest = getDistance(cities);
		shortestPath = cities.clone();
		
	}
	
	public void start() {
		new Thread(() -> {
			findShortest(cities, 0);
			System.out.println("Shortest path " + shortest);
		}).start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g2D);
		
		for (int i = 0; i < numCities; i ++) {
			g2D.fillOval(cities[i].getX(), cities[i].getY(), width, width);
		}
		
		g2D.setColor(Color.BLUE);
		createLines(g2D, shortestPath);
		
		g2D.setColor(Color.RED);
		g2D.drawString("Shortest distance " + shortest, x / 25, y / 25);	
	}
	
	public void createLines(Graphics g, City[] cities) {
		for (int i = 0; i < numCities - 1; i ++) {
			g.drawLine(cities[i].getX() + width/2, 
					   cities[i].getY() + width/2, 
					   cities[i + 1].getX() + width/2, 
					   cities[i + 1].getY() + width/2);
		}
	}
	
	public City[] getCities() {
		return this.cities;
	}
	
	public double getDistance(City[] cities) {
		double sum = 0;
		for (int i = 0; i < numCities - 1; i ++) {
			double d = Math.hypot(cities[i].getX() - cities[i + 1].getX(), 
								  cities[i].getY() - cities[i + 1].getY());
			sum += d;
		}
		return sum;
	}
	
	public void findShortest(City[] cities, int index) {
		if (index == cities.length) {
			double d = getDistance(cities);
			
			if (d < shortest) {
				shortest = d;
				shortestPath = cities.clone();
				SwingUtilities.invokeLater(() -> repaint());
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			return;
		}
		for (int i = index; i < cities.length; i ++) {
			swap(cities, index, i);
			findShortest(cities, index + 1);
			swap(cities, index, i);
		}
		
	}
	
	public void swap(City[] cities, int i, int j) {
		City temp = cities[i];
		cities[i] = cities[j];
		cities[j] = temp;
	}
	

}
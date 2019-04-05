
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawingMovie {

	private Movie movie;
	private PImage coverArt;
	
	public DrawingMovie(Movie m) {
		this.movie = m;
		coverArt = null;
	}
	
	public void draw(PApplet drawer, float x, float y, float width, float height) {
		if (movie != null) {
			if (coverArt != null) {
				drawer.image(coverArt, x, y,width,height);
				
				String title = movie.getTitle();
				drawer.text(title, x, y);
			}
		}
		drawer.stroke(0);
		drawer.noFill();
		drawer.rect(x, y, width, height);
		
	}
	

	public void downloadArt(PApplet drawer) {
		
		Thread downloader = new Thread(new Runnable() {

			@Override
			public void run() {
				
				
				// Find the cover art using IMDB links
				// Initialize coverArt
				
				String url = "https://www.imdb.com/title/tt0114709/"; // TOY STORY
				
				Scanner scan = null;
				try {
					String output = "";
					
					URL reader = new URL(url);
					scan = new Scanner(reader.openStream());
					
					while (scan.hasNextLine()) {
						String line = (scan.nextLine());
						output += line + "\n";
					}
					
					String imgURL = output.substring(output.indexOf("\"image\": \"h")+10, output.indexOf("V1_.jpg\"")+7);
					System.out.println(imgURL);
					
					// find distinguishing parts of the text and use cover art to find the URL
					coverArt = drawer.loadImage(imgURL);
					
				} catch(IOException e) {
					e.printStackTrace();
				}
				finally {
					if (null != scan) {
						scan.close();
					}
				}

				
			}
			
		});
		
		downloader.start();

	}

	
}

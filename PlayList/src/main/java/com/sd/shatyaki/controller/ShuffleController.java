package com.sd.shatyaki.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Shuffle controller.Controls the songs
 * 
 * Each method can be a seperate pai or endpoint. In a normal flow each will be
 * played one after the other
 * 
 * @author shatayki
 *
 */
@RestController
@RequestMapping("/song/test")
public class ShuffleController {
	// Stores initial songs to be played
	private Map<Integer, Integer> songsToPlay;
	// Stores the songs which have been played
	private Map<Integer, Integer> songsPlayed;
	// Math.Random function
	private Random random;
	// Stores the song which was previously played
	private int previous = 0;
	// Stores current song
	private int current = 0;
	// Keeps count of the number of songs that have been played
	private int playedSongCount = 1;
	// Initial song count
	private final int initialSongCount = 100;

	/**
	 * Loads all the 100 songs
	 */
	private void loadSongs() {
		songsToPlay = new HashMap<Integer, Integer>();
		songsPlayed = new HashMap<Integer, Integer>();
		for (int i = 1; i <= initialSongCount; i++) {
			songsToPlay.put(i, i);
		}

	}

	/**
	 * Starting pooint of the the method If it's the first song , plays the first
	 * one otherwise goes to the next method
	 * 
	 * @return
	 */
	@GetMapping("/start")
	public Object start() {
		loadSongs();
		boolean firstSong = true;

		while (playedSongCount != initialSongCount) {
			if (firstSong) {
				random = new Random();

				current = songsToPlay.get(random.nextInt(songsToPlay.size()) + 1);
				songsPlayed.put(current, current);
				previous = current;
				firstSong = false;
				playedSongCount++;
				System.out.println("Playing First " + songsToPlay.get(current));
			} else {
				next();
			}

		}
		return stop();

	}

	/**
	 * Method to play the next song. Mapping checking is there to determine whether
	 * the song has been played ot not
	 */
	@GetMapping("/next")
	public void next() {
		random = new Random();
		int next = random.nextInt(songsToPlay.size()) + 1;

		if (songsPlayed.get(next) == null) {

			previous = current;
			current = next;
			songsPlayed.put(next, next);
			playedSongCount++;
			System.out.println("Playing " + songsToPlay.get(next));
			// return songsToPlay.get(next);
		} else {
			// return

			next();
		}

	}

	/**
	 * Incase the previous song has to be played
	 * 
	 * @return
	 */
	@GetMapping("/previous")
	public int previous() {

		return songsPlayed.get(previous);
	}

	/**
	 * Stop method which is invoked after 100 songa are played. Resettign all the
	 * variables
	 * 
	 * @return
	 */
	public String stop() {

		System.out.println(songsPlayed);
		songsToPlay = null;
		songsPlayed = null;

		previous = 0;
		current = 0;
		playedSongCount = 1;
		return "Done playing 100 songs";
	}

}

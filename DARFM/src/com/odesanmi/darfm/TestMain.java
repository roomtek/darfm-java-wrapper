package com.odesanmi.darfm;

import java.util.Collection;

public class TestMain {

	public static void main(String[] args) throws Exception {

		// Stataions API
		Collection<Station> stations = Station.getRecommendedByCallsign("WLIF");
		// Collection<Station> stations = Station.searchByZip("10039");
		for (Station e : stations) {
			System.out.println(e.getCallsign() + " " + e.getBand());
		}

		System.out.println();
		// Song API
		Collection<Song> song = Song.getTopSongs("Country", 5);

		for (Song e : song) {
			System.out.println(e.getSongartist() + " - " + e.getSongtitle());
		}

		System.out.println();

		// SongArt API
		Collection<SongArt> songart = SongArt.querySongArt("Rihanna");

		for (SongArt e : songart) {
			System.out.println(e.getTitle() + " - " + e.getArturl());
		}

		System.out.println();

		// OnNow API
		OnNow onnow = OnNow.getOnNow("WLIF");
		//
		System.out.println(onnow.getShowname());
		System.out.println(onnow.getShowhost());
		System.out.println(onnow.getTimestamp());
		//
		System.out.println(onnow.getEvent().getTitle());
		System.out.println(onnow.getEvent().getArtist());
		System.out.println(onnow.getEvent().getAlbum());

		// Artist API
		Collection<Artist> artist = Artist.autocompleteArtist("Riha");
		for (Artist e : artist) {
			System.out.println(e.getName());
		}

		System.out.println();

		// StationPlayer API
		StationPlayer player = StationPlayer.getStationPlayer("WLIF");
		System.out.println(player.getWebsiteurl());
		System.out.println(player.getUrl());
		System.out.println(player.getEncoding());

	}
}

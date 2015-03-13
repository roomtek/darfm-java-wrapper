This is a java wrapper in development for the dar.fm API.

Usage:

Stations API
```
// To search for a station with a callsign or Zipcode

Collection<Station> stations = Station.getRecommendedByCallsign("WLIF"); 

for (Station e : stations) {
   System.out.println(e.getCallsign() + " " + e.getBand());
}

```

Song API
```
// To get the top songs for a particular genre

Collection<Song> song = Song.getTopSongs("Country", 5);

for (Song e : song) {
   System.out.println(e.getSongartist() + " - " + e.getSongtitle());
}

```

SongArt API
```
// To search for album art related to a particular artist / track/ album

Collection<SongArt> songart = SongArt.querySongArt("Rihanna");

for (SongArt e : songart) {
   System.out.println(e.getTitle() + " - " + e.getArturl());
}

```

OnNow API
```
// To get the current playing show / track for a station using the callsign.

OnNow onnow = OnNow.getOnNow("WLIF");
//
System.out.println(onnow.getShowname());
System.out.println(onnow.getShowhost());
System.out.println(onnow.getTimestamp());
//
System.out.println(onnow.getEvent().getTitle());
System.out.println(onnow.getEvent().getArtist());
System.out.println(onnow.getEvent().getAlbum());

```

Artist API
```
// Autocompletes the users key entries

Collection<Artist> artist = Artist.autocompleteArtist("Riha"); // returns Rihanna, etc

for (Artist e : artist) {
   System.out.println(e.getName());
}

```

StationPlayer API
```
// Gets a streamable url and other station details. 
//Can be directly used with a MediaPlayer class

StationPlayer player = StationPlayer.getStationPlayer("WLIF");
System.out.println(player.getWebsiteurl());
System.out.println(player.getUrl());
System.out.println(player.getEncoding());
```
/*
 * Copyright (c) 2014, Temitayo Odesanmi
 * All rights reserved.
 *
 * Redistribution and use of this software in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above
 *   copyright notice, this list of conditions and the
 *   following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above
 *   copyright notice, this list of conditions and the
 *   following disclaimer in the documentation and/or other
 *   materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.odesanmi.darfm;

import java.util.Collection;
import java.util.Collections;

/**
 * Bean that contains Station information.<br/>
 * This class contains static methods that executes API methods relating to
 * radio stations.<br/>
 * <br/>
 * Method names are equivalent to the dar.fm API method names.
 * 
 * @see www.dar.fm
 * @author Temitayo Odesanmi
 */
public class Station {

	private String station;
	private int station_id;
	private String callsign;
	private String dial;
	private String band;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private String slogan;
	private String phone;
	private String email;
	private String ubergenre;
	private String genre;
	private String language;
	private String websiteurl;
	private String imageurl;
	private String description;
	private String encoding;
	private String status;

	static final ArtifactFactory<Station> FACTORY = new StationFactory();

	public static Collection<Station> getRecommendedByCallsign(String callsign) {
		DarCaller dar = new DarCaller();
		DomElement element = dar.relatedStationsByCallsign(callsign);
		if (element == null)
			return Collections.emptyList();
		return CollectionBuilder.buildCollection(element, Station.class);
	}

	public static Collection<Station> getRecommendedByArtist(String artist) {
		DarCaller dar = new DarCaller();
		DomElement element = dar.relatedStationsByartist(artist);
		if (element == null)
			return Collections.emptyList();
		return CollectionBuilder.buildCollection(element, Station.class);
	}

	public static Collection<Station> searchByZip(String zipcode) {
		DarCaller dar = new DarCaller();
		DomElement element = dar.stationsByZipCode(zipcode);
		if (element == null)
			return Collections.emptyList();
		return CollectionBuilder.buildCollection(element, Station.class);
	}

	public static Collection<Station> searchByCity(String city) {
		DarCaller dar = new DarCaller();
		DomElement element = dar.stationsByCity(city);
		if (element == null)
			return Collections.emptyList();
		return CollectionBuilder.buildCollection(element, Station.class);
	}

	public static Collection<Station> searchByCityState(String city,
			String state) {
		DarCaller dar = new DarCaller();
		DomElement element = dar.stationsByCityState(city, state);
		if (element == null)
			return Collections.emptyList();
		return CollectionBuilder.buildCollection(element, Station.class);
	}

	public static Collection<Station> searchByCityStateCountry(String city,
			String state, String country) {
		DarCaller dar = new DarCaller();
		DomElement element = dar.stationsByCityStateCountry(city, state,
				country);
		if (element == null)
			return Collections.emptyList();
		return CollectionBuilder.buildCollection(element, Station.class);
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public int getStation_id() {
		return station_id;
	}

	public void setStation_id(int station_id) {
		this.station_id = station_id;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getDial() {
		return dial;
	}

	public void setDial(String dial) {
		this.dial = dial;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUbergenre() {
		return ubergenre;
	}

	public void setUbergenre(String ubergenre) {
		this.ubergenre = ubergenre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getWebsiteurl() {
		return websiteurl;
	}

	public void setWebsiteurl(String websiteurl) {
		this.websiteurl = websiteurl;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private static class StationFactory implements ArtifactFactory<Station> {
		@Override
		public Station parseElement(DomElement element) {
			Station station = new Station();

			// System.out.println("element >> " + element.getTagName());

			station.station_id = Integer.valueOf(element
					.getChildText("station_id"));

			DomElement callsign = element.getChild("callsign");
			if (callsign != null) {
				station.callsign = callsign.getText();
			}

			DomElement dial = element.getChild("dial");
			if (dial != null) {
				station.dial = dial.getText();
			}

			DomElement band = element.getChild("band");
			if (band != null) {
				station.band = band.getText();
			}

			// address has not been completed
			// private String address1;
			// private String address2;
			// private String city;
			// private String state;
			DomElement address1 = element.getChild("address1");
			if (address1 != null) {
				station.address1 = address1.getText();
			}

			DomElement country = element.getChild("country");
			if (country != null) {
				station.country = country.getText();
			}

			DomElement zipcode = element.getChild("zipcode");
			if (zipcode != null) {
				station.zipcode = zipcode.getText();
			}

			DomElement phone = element.getChild("phone");
			if (phone != null) {
				station.phone = phone.getText();
			}

			DomElement email = element.getChild("email");
			if (email != null) {
				station.email = email.getText();
			}

			DomElement slogan = element.getChild("slogan");
			if (slogan != null) {
				station.slogan = slogan.getText();
			}

			DomElement ubergenre = element.getChild("ubergenre");
			if (ubergenre != null) {
				station.ubergenre = ubergenre.getText();
			}

			DomElement genre = element.getChild("genre");
			if (genre != null) {
				station.genre = genre.getText();
			}

			DomElement language = element.getChild("language");
			if (language != null) {
				station.language = language.getText();
			}

			DomElement websiteurl = element.getChild("websiteurl");
			if (websiteurl != null) {
				station.websiteurl = websiteurl.getText();
			}

			DomElement imageurl = element.getChild("imageurl");
			if (imageurl != null) {
				station.imageurl = imageurl.getText();
			}

			DomElement description = element.getChild("description");
			if (description != null) {
				station.description = description.getText();
			}

			DomElement encoding = element.getChild("encoding");
			if (encoding != null) {
				station.encoding = encoding.getText();
			}

			DomElement status = element.getChild("status");
			if (status != null) {
				station.status = status.getText();
			}

			return station;
		}
	}

}

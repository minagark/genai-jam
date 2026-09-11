package com.conygre.spring.boot.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompactDiscTest {

	private CompactDisc cd;
	private Track track1;
	private Track track2;

	@BeforeEach
	public void setUp() {
		cd = new CompactDisc();
		track1 = new Track("Track 1");
		track2 = new Track("Track 2");
	}

	// Constructor Tests
	// Test that the default constructor creates an object with an initialized empty track list
	@Test
	public void testDefaultConstructor() {
		CompactDisc newCd = new CompactDisc();
		assertNotNull(newCd);
		assertNotNull(newCd.getTrackTitles()); // Should initialize with empty list
	}

	// Test that the parameterized constructor correctly initializes all properties
	@Test
	public void testParameterizedConstructor() {
		CompactDisc newCd = new CompactDisc("Is This It", 13.99, "The Strokes", 11);
		assertEquals("Is This It", newCd.getTitle());
		assertEquals(13.99, newCd.getPrice());
		assertEquals("The Strokes", newCd.getArtist());
		assertEquals(11, newCd.getTracks());
	}

	// ID Tests
	// Test setting and getting a positive ID value
	@Test
	public void testSetAndGetId() {
		cd.setId(1);
		assertEquals(1, cd.getId());
	}

	// Test that ID can be set to zero
	@Test
	public void testIdZero() {
		cd.setId(0);
		assertEquals(0, cd.getId());
	}

	// Test that ID can be set to negative values (edge case)
	@Test
	public void testIdNegative() {
		cd.setId(-1);
		assertEquals(-1, cd.getId());
	}

	// Title Tests
	// Test setting and getting a valid title
	@Test
	public void testSetAndGetTitle() {
		cd.setTitle("Is This It");
		assertEquals("Is This It", cd.getTitle());
	}

	// Test that title can be set to null
	@Test
	public void testSetTitleNull() {
		cd.setTitle(null);
		assertNull(cd.getTitle());
	}

	// Test that title can be set to empty string
	@Test
	public void testSetTitleEmpty() {
		cd.setTitle("");
		assertEquals("", cd.getTitle());
	}

	// Artist Tests
	// Test setting and getting a valid artist name
	@Test
	public void testSetAndGetArtist() {
		cd.setArtist("The Strokes");
		assertEquals("The Strokes", cd.getArtist());
	}

	// Test that artist can be set to null
	@Test
	public void testSetArtistNull() {
		cd.setArtist(null);
		assertNull(cd.getArtist());
	}

	// Test that artist can be set to empty string
	@Test
	public void testSetArtistEmpty() {
		cd.setArtist("");
		assertEquals("", cd.getArtist());
	}

	// Price Tests
	// Test setting and getting a valid price value
	@Test
	public void testSetAndGetPrice() {
		cd.setPrice(13.99);
		assertEquals(13.99, cd.getPrice());
	}

	// Test that price can be set to zero
	@Test
	public void testSetPriceZero() {
		cd.setPrice(0.0);
		assertEquals(0.0, cd.getPrice());
	}

	// Test that price can be set to negative values (edge case)
	@Test
	public void testSetPriceNegative() {
		cd.setPrice(-5.99);
		assertEquals(-5.99, cd.getPrice());
	}

	// Test that price can be set to null
	@Test
	public void testSetPriceNull() {
		cd.setPrice(null);
		assertNull(cd.getPrice());
	}

	// Test that price can handle large values
	@Test
	public void testSetPriceLargeValue() {
		cd.setPrice(999.99);
		assertEquals(999.99, cd.getPrice());
	}

	// Tracks Count Tests
	// Test setting and getting a valid track count
	@Test
	public void testSetAndGetTracks() {
		cd.setTracks(11);
		assertEquals(11, cd.getTracks());
	}

	// Test that tracks count can be set to zero
	@Test
	public void testSetTracksZero() {
		cd.setTracks(0);
		assertEquals(0, cd.getTracks());
	}

	// Test that tracks count can be set to negative values (edge case)
	@Test
	public void testSetTracksNegative() {
		cd.setTracks(-1);
		assertEquals(-1, cd.getTracks());
	}

	// Test that tracks count can be set to null
	@Test
	public void testSetTracksNull() {
		cd.setTracks(null);
		assertNull(cd.getTracks());
	}

	// Track Titles (Relationships) Tests
	// Test that track titles list is not null after initialization
	@Test
	public void testGetTrackTitlesNotNull() {
		assertNotNull(cd.getTrackTitles());
	}

	// Test that track titles list is empty when CD is first created
	@Test
	public void testGetTrackTitlesInitiallyEmpty() {
		assertTrue(cd.getTrackTitles().isEmpty());
	}

	// Test adding a single track to the track titles list
	@Test
	public void testAddTrackToTrackTitles() {
		cd.getTrackTitles().add(track1);
		assertEquals(1, cd.getTrackTitles().size());
		assertEquals("Track 1", cd.getTrackTitles().get(0).getTitle());
	}

	// Test adding multiple tracks to the track titles list
	@Test
	public void testAddMultipleTracksToTrackTitles() {
		cd.getTrackTitles().add(track1);
		cd.getTrackTitles().add(track2);
		assertEquals(2, cd.getTrackTitles().size());
	}

	// Test removing a track from the track titles list
	@Test
	public void testRemoveTrackFromTrackTitles() {
		cd.getTrackTitles().add(track1);
		cd.getTrackTitles().add(track2);
		cd.getTrackTitles().remove(track1);
		assertEquals(1, cd.getTrackTitles().size());
		assertEquals("Track 2", cd.getTrackTitles().get(0).getTitle());
	}

	// Test setting the entire track titles list with new tracks
	@Test
	public void testSetTrackTitles() {
		List<Track> newTracks = new ArrayList<>();
		newTracks.add(track1);
		newTracks.add(track2);
		cd.setTrackTitles(newTracks);
		assertEquals(2, cd.getTrackTitles().size());
	}

	// Test setting track titles to an empty list
	@Test
	public void testSetTrackTitlesEmpty() {
		cd.setTrackTitles(new ArrayList<>());
		assertTrue(cd.getTrackTitles().isEmpty());
	}

	// Test setting track titles to null
	@Test
	public void testSetTrackTitlesNull() {
		cd.setTrackTitles(null);
		assertNull(cd.getTrackTitles());
	}

	// Integration Tests
	// Test complete initialization of a CD with all properties and tracks
	@Test
	public void testCompleteCDInitialization() {
		CompactDisc newCd = new CompactDisc("White Ladder", 9.99, "David Gray", 10);
		newCd.setId(12);
		newCd.getTrackTitles().add(track1);

		assertEquals(12, newCd.getId());
		assertEquals("White Ladder", newCd.getTitle());
		assertEquals("David Gray", newCd.getArtist());
		assertEquals(9.99, newCd.getPrice());
		assertEquals(10, newCd.getTracks());
		assertEquals(1, newCd.getTrackTitles().size());
	}

	// Test that CompactDisc implements Serializable interface
	@Test
	public void testSerializability() {
		// CompactDisc should implement Serializable
		assertTrue(java.io.Serializable.class.isAssignableFrom(CompactDisc.class));
	}

	// Test updating multiple properties of a CD
	@Test
	public void testUpdateMultipleProperties() {
		cd.setTitle("Original Title");
		cd.setArtist("Original Artist");
		cd.setPrice(5.0);
		cd.setTracks(5);

		// Update properties
		cd.setTitle("Updated Title");
		cd.setArtist("Updated Artist");
		cd.setPrice(10.0);
		cd.setTracks(10);

		assertEquals("Updated Title", cd.getTitle());
		assertEquals("Updated Artist", cd.getArtist());
		assertEquals(10.0, cd.getPrice());
		assertEquals(10, cd.getTracks());
	}
}

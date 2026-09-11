package com.conygre.spring.boot.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrackTest {

	private Track track;

	@BeforeEach
	public void setUp() {
		track = new Track();
	}

	// Constructor Tests
	// Test that the default constructor creates an object
	@Test
	public void testDefaultConstructor() {
		Track newTrack = new Track();
		assertNotNull(newTrack);
	}

	// Test that constructor with title only initializes title correctly
	@Test
	public void testConstructorWithTitleOnly() {
		Track newTrack = new Track("Wannabe");
		assertEquals("Wannabe", newTrack.getTitle());
	}

	// Test that constructor with all parameters initializes all fields
	@Test
	public void testConstructorWithAllParameters() {
		Track newTrack = new Track(1, "Mama", 16);
		assertEquals(1, newTrack.getId());
		assertEquals("Mama", newTrack.getTitle());
		assertEquals(16, newTrack.getCdId());
	}

	// ID Tests
	// Test setting and getting a positive ID value
	@Test
	public void testSetAndGetId() {
		track.setId(1);
		assertEquals(1, track.getId());
	}

	// Test that ID can be set to zero
	@Test
	public void testSetIdZero() {
		track.setId(0);
		assertEquals(0, track.getId());
	}

	// Test that ID can be set to negative values (edge case)
	@Test
	public void testSetIdNegative() {
		track.setId(-1);
		assertEquals(-1, track.getId());
	}

	// Test that ID can be set to null
	@Test
	public void testSetIdNull() {
		track.setId(null);
		assertNull(track.getId());
	}

	// Title Tests
	// Test setting and getting a valid title
	@Test
	public void testSetAndGetTitle() {
		track.setTitle("Wannabe");
		assertEquals("Wannabe", track.getTitle());
	}

	// Test that title can be set to null
	@Test
	public void testSetTitleNull() {
		track.setTitle(null);
		assertNull(track.getTitle());
	}

	// Test that title can be set to empty string
	@Test
	public void testSetTitleEmpty() {
		track.setTitle("");
		assertEquals("", track.getTitle());
	}

	// Test that title can handle special characters
	@Test
	public void testSetTitleWithSpecialCharacters() {
		track.setTitle("Spice up your life!");
		assertEquals("Spice up your life!", track.getTitle());
	}

	// CD ID Tests
	// Test setting and getting a valid CD ID
	@Test
	public void testSetAndGetCdId() {
		track.setCdId(16);
		assertEquals(16, track.getCdId());
	}

	// Test that CD ID can be set to zero
	@Test
	public void testSetCdIdZero() {
		track.setCdId(0);
		assertEquals(0, track.getCdId());
	}

	// Test that CD ID can be set to negative values (edge case)
	@Test
	public void testSetCdIdNegative() {
		track.setCdId(-1);
		assertEquals(-1, track.getCdId());
	}

	// Test setting CD ID to a valid reference
	@Test
	public void testSetCdIdToValidReference() {
		track.setCdId(16);
		assertEquals(16, track.getCdId());
	}

	// Integration Tests
	// Test complete track initialization with title only constructor
	@Test
	public void testCompleteTrackInitializationWithTitleOnly() {
		Track newTrack = new Track("Mama");
		newTrack.setId(1);
		newTrack.setCdId(16);

		assertEquals(1, newTrack.getId());
		assertEquals("Mama", newTrack.getTitle());
		assertEquals(16, newTrack.getCdId());
	}

	// Test complete track initialization using parameterized constructor
	@Test
	public void testCompleteTrackInitializationWithConstructor() {
		Track newTrack = new Track(2, "Wannabe", 16);

		assertEquals(2, newTrack.getId());
		assertEquals("Wannabe", newTrack.getTitle());
		assertEquals(16, newTrack.getCdId());
	}

	// Test updating multiple track properties
	@Test
	public void testUpdateMultipleProperties() {
		track.setId(1);
		track.setTitle("Original Title");
		track.setCdId(16);

		// Update properties
		track.setTitle("Updated Title");
		track.setCdId(20);

		assertEquals(1, track.getId());
		assertEquals("Updated Title", track.getTitle());
		assertEquals(20, track.getCdId());
	}

	// Test creating multiple tracks with different CD IDs
	@Test
	public void testMultipleTracksWithDifferentCdIds() {
		Track track1 = new Track(1, "Mama", 16);
		Track track2 = new Track(2, "Wannabe", 16);
		Track track3 = new Track(3, "Spice up your life", 16);

		assertEquals(16, track1.getCdId());
		assertEquals(16, track2.getCdId());
		assertEquals(16, track3.getCdId());

		assertEquals("Mama", track1.getTitle());
		assertEquals("Wannabe", track2.getTitle());
		assertEquals("Spice up your life", track3.getTitle());
	}

	// Test that tracks with same title can belong to different CDs
	@Test
	public void testTrackFromDifferentCd() {
		Track track1 = new Track(1, "Mama", 16);
		Track track2 = new Track(1, "Mama", 15); // Same track title but different CD

		assertEquals(16, track1.getCdId());
		assertEquals(15, track2.getCdId());
		assertNotEquals(track1.getCdId(), track2.getCdId());
	}

	// Test that Track implements Serializable interface
	@Test
	public void testSerializability() {
		// Track should implement Serializable
		assertTrue(java.io.Serializable.class.isAssignableFrom(Track.class));
	}

	// Test that track title is case sensitive
	@Test
	public void testTrackTitleCaseSensitivity() {
		track.setTitle("Wannabe");
		assertEquals("Wannabe", track.getTitle());
		assertNotEquals("wannabe", track.getTitle());
	}

	// Test default track properties
	@Test
	public void testDefaultTrackPropertiesAreNull() {
		Track newTrack = new Track();
		assertNull(newTrack.getTitle());
		assertNull(newTrack.getId());
		// Note: cdId is primitive int, so it defaults to 0
		assertEquals(0, newTrack.getCdId());
	}
}

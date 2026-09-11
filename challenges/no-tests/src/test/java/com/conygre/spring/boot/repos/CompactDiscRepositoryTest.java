package com.conygre.spring.boot.repos;

import com.conygre.spring.boot.entities.CompactDisc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompactDiscRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompactDiscRepository repository;

    private CompactDisc testDisc;

    @Before
    public void setUp() {
        testDisc = new CompactDisc();
        testDisc.setTitle("Test Album");
        testDisc.setArtist("Test Artist");
    }

    // ===== save() Tests =====
    @Test
    public void testSave_WithValidDisc_ReturnsSavedDisc() {
        CompactDisc savedDisc = repository.save(testDisc);

        assertNotNull(savedDisc);
        assertNotNull(savedDisc.getId());
        assertEquals("Test Album", savedDisc.getTitle());
        assertEquals("Test Artist", savedDisc.getArtist());
    }

    @Test
    public void testSave_PersistsDiscToDatabase() {
        CompactDisc savedDisc = repository.save(testDisc);
        entityManager.flush();
        entityManager.clear();

        Optional<CompactDisc> retrievedDisc = repository.findById(savedDisc.getId());

        assertTrue(retrievedDisc.isPresent());
        assertEquals("Test Album", retrievedDisc.get().getTitle());
    }

    // ===== findAll() Tests =====
    @Test
    public void testFindAll_WithMultipleDiscs_ReturnsAllDiscs() {
        CompactDisc disc1 = new CompactDisc();
        disc1.setTitle("Album 1");
        disc1.setArtist("Artist 1");
        repository.save(disc1);

        CompactDisc disc2 = new CompactDisc();
        disc2.setTitle("Album 2");
        disc2.setArtist("Artist 2");
        repository.save(disc2);

        Iterable<CompactDisc> allDiscs = repository.findAll();

        assertNotNull(allDiscs);
        int count = 0;
        for (CompactDisc disc : allDiscs) {
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    public void testFindAll_WithEmptyDatabase_ReturnsEmptyList() {
        Iterable<CompactDisc> allDiscs = repository.findAll();

        assertNotNull(allDiscs);
        int count = 0;
        for (CompactDisc disc : allDiscs) {
            count++;
        }
        assertEquals(0, count);
    }

    // ===== findById() Tests =====
    @Test
    public void testFindById_WithValidId_ReturnsDisc() {
        CompactDisc savedDisc = repository.save(testDisc);
        entityManager.flush();

        Optional<CompactDisc> retrievedDisc = repository.findById(savedDisc.getId());

        assertTrue(retrievedDisc.isPresent());
        assertEquals("Test Album", retrievedDisc.get().getTitle());
        assertEquals("Test Artist", retrievedDisc.get().getArtist());
    }

    @Test
    public void testFindById_WithInvalidId_ReturnsEmpty() {
        Optional<CompactDisc> retrievedDisc = repository.findById(999);

        assertFalse(retrievedDisc.isPresent());
    }

    // ===== delete() Tests =====
    @Test
    public void testDelete_WithValidDisc_RemovesFromDatabase() {
        CompactDisc savedDisc = repository.save(testDisc);
        entityManager.flush();

        repository.delete(savedDisc);
        entityManager.flush();

        Optional<CompactDisc> retrievedDisc = repository.findById(savedDisc.getId());

        assertFalse(retrievedDisc.isPresent());
    }

    @Test
    public void testDeleteById_WithValidId_RemovesFromDatabase() {
        CompactDisc savedDisc = repository.save(testDisc);
        entityManager.flush();

        repository.deleteById(savedDisc.getId());
        entityManager.flush();

        Optional<CompactDisc> retrievedDisc = repository.findById(savedDisc.getId());

        assertFalse(retrievedDisc.isPresent());
    }

    // ===== findByArtist() Tests =====
    @Test
    public void testFindByArtist_WithValidArtist_ReturnsDiscs() {
        CompactDisc disc1 = new CompactDisc();
        disc1.setTitle("Album 1");
        disc1.setArtist("The Beatles");
        repository.save(disc1);

        CompactDisc disc2 = new CompactDisc();
        disc2.setTitle("Album 2");
        disc2.setArtist("The Beatles");
        repository.save(disc2);

        CompactDisc disc3 = new CompactDisc();
        disc3.setTitle("Album 3");
        disc3.setArtist("Pink Floyd");
        repository.save(disc3);

        Iterable<CompactDisc> beatlesDiscs = repository.findByArtist("The Beatles");

        assertNotNull(beatlesDiscs);
        int count = 0;
        for (CompactDisc disc : beatlesDiscs) {
            count++;
            assertEquals("The Beatles", disc.getArtist());
        }
        assertEquals(2, count);
    }

    @Test
    public void testFindByArtist_WithInvalidArtist_ReturnsEmpty() {
        CompactDisc savedDisc = repository.save(testDisc);

        Iterable<CompactDisc> result = repository.findByArtist("Non-existent Artist");

        assertNotNull(result);
        int count = 0;
        for (CompactDisc disc : result) {
            count++;
        }
        assertEquals(0, count);
    }

    @Test
    public void testFindByArtist_WithNullArtist_ReturnsEmpty() {
        CompactDisc savedDisc = repository.save(testDisc);

        Iterable<CompactDisc> result = repository.findByArtist(null);

        assertNotNull(result);
    }

    // ===== update() Tests =====
    @Test
    public void testUpdate_ModifiesExistingDisc() {
        CompactDisc savedDisc = repository.save(testDisc);
        entityManager.flush();

        savedDisc.setTitle("Updated Title");
        repository.save(savedDisc);
        entityManager.flush();

        Optional<CompactDisc> retrievedDisc = repository.findById(savedDisc.getId());

        assertTrue(retrievedDisc.isPresent());
        assertEquals("Updated Title", retrievedDisc.get().getTitle());
    }

    // ===== count() Tests =====
    @Test
    public void testCount_WithMultipleDiscs_ReturnsCorrectCount() {
        repository.save(testDisc);
        CompactDisc disc2 = new CompactDisc();
        disc2.setTitle("Album 2");
        disc2.setArtist("Artist 2");
        repository.save(disc2);

        long count = repository.count();

        assertEquals(2, count);
    }

    @Test
    public void testCount_WithEmptyDatabase_ReturnsZero() {
        long count = repository.count();

        assertEquals(0, count);
    }
}
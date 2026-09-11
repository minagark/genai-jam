package com.conygre.spring.boot.services;

import com.conygre.spring.boot.entities.CompactDisc;
import com.conygre.spring.boot.repos.CompactDiscRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CompactDiscServiceImplTest {

    @Mock
    private CompactDiscRepository dao;

    @InjectMocks
    private CompactDiscServiceImpl service;

    private CompactDisc testDisc;

    @Before
    public void setUp() {
        testDisc = new CompactDisc();
        testDisc.setId(1);
        testDisc.setTitle("Test Album");
        testDisc.setArtist("Test Artist");
    }

    // ===== getCatalog() Tests =====
    @Test
    public void testGetCatalog_ReturnsAllDiscs() {
        List<CompactDisc> expectedCatalog = Arrays.asList(testDisc);
        when(dao.findAll()).thenReturn(expectedCatalog);

        Iterable<CompactDisc> result = service.getCatalog();

        assertNotNull(result);
        verify(dao, times(1)).findAll();
    }

    @Test
    public void testGetCatalog_ReturnsEmptyCatalog() {
        when(dao.findAll()).thenReturn(Arrays.asList());

        Iterable<CompactDisc> result = service.getCatalog();

        assertNotNull(result);
        verify(dao, times(1)).findAll();
    }

    // ===== getCompactDiscById() Tests =====
    @Test
    public void testGetCompactDiscById_WithValidId_ReturnsDisc() {
        when(dao.findById(1)).thenReturn(Optional.of(testDisc));

        CompactDisc result = service.getCompactDiscById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test Album", result.getTitle());
        verify(dao, times(1)).findById(1);
    }

    @Test
    public void testGetCompactDiscById_WithInvalidId_ReturnsNull() {
        when(dao.findById(999)).thenReturn(Optional.empty());

        CompactDisc result = service.getCompactDiscById(999);

        assertNull(result);
        verify(dao, times(1)).findById(999);
    }

    // ===== addNewCompactDisc() Tests =====
    @Test
    public void testAddNewCompactDisc_SetsIdToZero() {
        testDisc.setId(5); // Set to non-zero
        CompactDisc savedDisc = new CompactDisc();
        savedDisc.setId(1);
        savedDisc.setTitle("Test Album");
        
        when(dao.save(any(CompactDisc.class))).thenReturn(savedDisc);

        CompactDisc result = service.addNewCompactDisc(testDisc);

        assertEquals(0, testDisc.getId()); // Should be reset to 0
        assertNotNull(result);
        verify(dao, times(1)).save(testDisc);
    }

    @Test
    public void testAddNewCompactDisc_CallsSaveOnRepository() {
        when(dao.save(testDisc)).thenReturn(testDisc);

        service.addNewCompactDisc(testDisc);

        verify(dao, times(1)).save(testDisc);
    }

    // ===== updateCompactDisc() Tests =====
    @Test
    public void testUpdateCompactDisc_CallsSaveOnRepository() {
        when(dao.save(testDisc)).thenReturn(testDisc);

        CompactDisc result = service.updateCompactDisc(testDisc);

        assertNotNull(result);
        verify(dao, times(1)).save(testDisc);
    }

    @Test
    public void testUpdateCompactDisc_ReturnsUpdatedDisc() {
        CompactDisc updatedDisc = new CompactDisc();
        updatedDisc.setId(1);
        updatedDisc.setTitle("Updated Title");
        
        when(dao.save(testDisc)).thenReturn(updatedDisc);

        CompactDisc result = service.updateCompactDisc(testDisc);

        assertEquals("Updated Title", result.getTitle());
        verify(dao, times(1)).save(testDisc);
    }

    // ===== deleteCompactDisc(int) Tests =====
    @Test
    public void testDeleteCompactDiscById_WithValidId() {
        when(dao.findById(1)).thenReturn(Optional.of(testDisc));
        doNothing().when(dao).delete(testDisc);

        service.deleteCompactDisc(1);

        verify(dao, times(1)).findById(1);
        verify(dao, times(1)).delete(testDisc);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testDeleteCompactDiscById_WithInvalidId_ThrowsException() {
        when(dao.findById(999)).thenReturn(Optional.empty());

        service.deleteCompactDisc(999);
    }

    // ===== deleteCompactDisc(CompactDisc) Tests =====
    @Test
    public void testDeleteCompactDiscByObject_CallsDeleteOnRepository() {
        doNothing().when(dao).delete(testDisc);

        service.deleteCompactDisc(testDisc);

        verify(dao, times(1)).delete(testDisc);
    }

    @Test
    public void testDeleteCompactDiscByObject_WithNullDisc() {
        doNothing().when(dao).delete(null);

        service.deleteCompactDisc((CompactDisc) null);

        verify(dao, times(1)).delete(null);
    }
}
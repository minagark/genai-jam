package com.conygre.spring.boot.rest;

import com.conygre.spring.boot.entities.CompactDisc;
import com.conygre.spring.boot.services.CompactDiscService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CompactDiscControllerTest {

    @Mock
    private CompactDiscService service;

    @InjectMocks
    private CompactDiscController controller;

    private CompactDisc testDisc;

    @Before
    public void setUp() {
        testDisc = new CompactDisc();
        testDisc.setId(1);
        testDisc.setTitle("Test Album");
        testDisc.setArtist("Test Artist");
    }

    // ===== findAll() Tests =====
    @Test
    public void testFindAll_ReturnsAllDiscs() {
        List<CompactDisc> expectedDiscs = Arrays.asList(testDisc);
        when(service.getCatalog()).thenReturn(expectedDiscs);

        Iterable<CompactDisc> result = controller.findAll();

        assertNotNull(result);
        verify(service, times(1)).getCatalog();
    }

    @Test
    public void testFindAll_ReturnsEmptyList() {
        when(service.getCatalog()).thenReturn(Arrays.asList());

        Iterable<CompactDisc> result = controller.findAll();

        assertNotNull(result);
        verify(service, times(1)).getCatalog();
    }

    // ===== getCdById() Tests =====
    @Test
    public void testGetCdById_WithValidId_ReturnsDisc() {
        when(service.getCompactDiscById(1)).thenReturn(testDisc);

        CompactDisc result = controller.getCdById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test Album", result.getTitle());
        verify(service, times(1)).getCompactDiscById(1);
    }

    @Test
    public void testGetCdById_WithInvalidId_ReturnsNull() {
        when(service.getCompactDiscById(999)).thenReturn(null);

        CompactDisc result = controller.getCdById(999);

        assertNull(result);
        verify(service, times(1)).getCompactDiscById(999);
    }

    // ===== getByIdWith404() Tests =====
    @Test
    public void testGetByIdWith404_WithValidId_ReturnsOkStatus() {
        when(service.getCompactDiscById(1)).thenReturn(testDisc);

        ResponseEntity<CompactDisc> result = controller.getByIdWith404(1);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(testDisc, result.getBody());
        verify(service, times(1)).getCompactDiscById(1);
    }

    @Test
    public void testGetByIdWith404_WithInvalidId_ReturnsNotFoundStatus() {
        when(service.getCompactDiscById(999)).thenReturn(null);

        ResponseEntity<CompactDisc> result = controller.getByIdWith404(999);

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(service, times(1)).getCompactDiscById(999);
    }

    // ===== deleteCd(int) Tests =====
    @Test
    public void testDeleteCd_WithValidId() {
        doNothing().when(service).deleteCompactDisc(1);

        controller.deleteCd(1);

        verify(service, times(1)).deleteCompactDisc(1);
    }

    @Test
    public void testDeleteCd_WithInvalidId() {
        doNothing().when(service).deleteCompactDisc(999);

        controller.deleteCd(999);

        verify(service, times(1)).deleteCompactDisc(999);
    }

    // ===== deleteCd(CompactDisc) Tests =====
    @Test
    public void testDeleteCdByObject_WithValidDisc() {
        doNothing().when(service).deleteCompactDisc(testDisc);

        controller.deleteCd(testDisc);

        verify(service, times(1)).deleteCompactDisc(testDisc);
    }

    @Test
    public void testDeleteCdByObject_WithNullDisc() {
        doNothing().when(service).deleteCompactDisc(null);

        controller.deleteCd((CompactDisc) null);

        verify(service, times(1)).deleteCompactDisc(null);
    }

    // ===== addCd() Tests =====
    @Test
    public void testAddCd_WithValidDisc() {
        when(service.addNewCompactDisc(testDisc)).thenReturn(testDisc);

        controller.addCd(testDisc);

        verify(service, times(1)).addNewCompactDisc(testDisc);
    }

    @Test
    public void testAddCd_WithNewDisc() {
        CompactDisc newDisc = new CompactDisc();
        newDisc.setTitle("New Album");
        newDisc.setArtist("New Artist");

        when(service.addNewCompactDisc(newDisc)).thenReturn(newDisc);

        controller.addCd(newDisc);

        verify(service, times(1)).addNewCompactDisc(newDisc);
    }
}
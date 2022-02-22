package com.example.mediatek86formations.outils;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

import java.util.Date;

@RunWith(JUnit4.class)
public class MesOutilsTest {

    /**
     * controller la conversion d'une date à une chaine
     */
    @Test
    public void VerifierConvertDateToString() {
        Date dateTest = new Date(120,11,28, 22,00,29);
        String DateString =  MesOutils.convertDateToString(dateTest);
        assertEquals("28/12/2020", DateString);

    }

    /**
     * controller la conversion d'une chaine à une date
     */
    @Test
    public void VerifierConvertStringToDate() {
        Date dateTest = MesOutils.convertStringToDate("2020-12-28 22:00:29",
                "yyyy-MM-dd hh:mm:ss");
        assertEquals(new Date(120,11,28, 22,00,29), dateTest);

    }

}

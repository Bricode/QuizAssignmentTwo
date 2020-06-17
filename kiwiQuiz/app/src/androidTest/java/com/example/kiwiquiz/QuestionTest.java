package com.example.kiwiquiz;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

//Testing main class
@RunWith(AndroidJUnit4.class)
public class QuestionTest {
    private Question testQuestion = new Question("Queenstown","Dunedin",
            "Christchurch", "New Plymouth", "The remarkables", "Queenstown");

    @Test
    public void testGetQuestionA() {
        assertEquals(testQuestion.getQuestionA(), "Queenstown");
    }

    @Test
    public void testSetQuestionA() {
        testQuestion.setQuestionA("Dunedi");
        assertEquals(testQuestion.getQuestionA(), "Dunedi");
    }

    @Test
    public void testGetQuestionB() {
        assertEquals(testQuestion.getQuestionB(), "Dunedin");
    }

    @Test
    public void testSetQuestionB() {
        testQuestion.setQuestionB("Dunedi");
        assertEquals(testQuestion.getQuestionB(), "Dunedi");
    }

    @Test
    public void testGetQuestionC() {
        assertEquals(testQuestion.getQuestionC(), "Christchurch");
    }

    @Test
    public void testSetQuestionC() {
        testQuestion.setQuestionC("Duned");
        assertEquals(testQuestion.getQuestionC(), "Duned");
    }

    @Test
    public void testGetQuestionD() {
        assertEquals(testQuestion.getQuestionD(), "New Plymouth");
    }

    @Test
    public void testSetQuestionD() {
        testQuestion.setQuestionD("Dune");
        assertEquals(testQuestion.getQuestionD(), "Dune");
    }

    @Test
    public void testGetAnswer() {
        assertEquals(testQuestion.getAnswer(), "Queenstown");
    }

    @Test
    public void testSetAnswer() {
        testQuestion.setAnswer("Dune");
        assertEquals(testQuestion.getQuestionD(), "Dune");
    }

    @Test
    public void testGetNameOfResort() {
        assertEquals(testQuestion.getNameOfResort(), "The remarkables");
    }

    @Test
    public void testSetNameOfResort() {
        testQuestion.setNameOfResort("The Remarkables");
        assertEquals(testQuestion.getNameOfResort(), "The Remarkables");
    }
}

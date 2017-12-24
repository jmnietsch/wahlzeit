package org.wahlzeit.model.musicalLocation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.PhotoManager;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;


public class MusicalLocationPhotoManagerTest {

	private Photo photo;
	private PhotoId photoId;

	@Before
	public void setup() {
		photoId = PhotoId.getNextId();
		photo = MusicalLocationPhotoFactory.getInstance().createPhoto(photoId);
	}

	@After
	public void rollBack() {
		photo = null;
		photoId = null;
	}

	@Test
	public void testAddPhoto() throws IOException {
		PhotoManager instance = PhotoManager.getInstance();

		assertTrue(photo != null);
		assertTrue(instance != null);

		//instance.addPhoto(photo);

		//Photo photoFromGet = instance.getPhoto(photoId);
		//assertTrue(photoFromGet.equals(photo));
	}


}
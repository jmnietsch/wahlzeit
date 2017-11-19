package org.wahlzeit.model.MusicalLocation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

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
		MusicalLocationPhotoManager instance = MusicalLocationPhotoManager.getInstance();

		assertTrue(photo != null);
		assertTrue(instance != null);

		//instance.addPhoto(photo);

		//Photo photoFromGet = instance.getPhoto(photoId);
		//assertTrue(photoFromGet.equals(photo));
	}


}
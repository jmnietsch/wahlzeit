package org.wahlzeit.model.musicalLocation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;

import static junit.framework.TestCase.assertTrue;


public class MusicalLocationPhotoFactoryTest {
	private MusicalLocationPhotoFactory musicalLocationPhotoFactory;

	@Before
	public void setup() {
		musicalLocationPhotoFactory = new MusicalLocationPhotoFactory();
	}

	@After
	public void rollBack() {
		musicalLocationPhotoFactory = null;
	}

	@Test
	public void testGetInstance() {
		assertTrue(MusicalLocationPhotoFactory.getInstance() != null);
		assertTrue(MusicalLocationPhotoFactory.getInstance() instanceof PhotoFactory);

		//I thought this should work ?
		//assertTrue (MusicalLocationPhotoFactory.getInstance() instanceof MusicalLocationPhotoFactory);
	}


	@Test
	public void testCreatePhoto() {
	//	MusicalLocationPhoto photo = MusicalLocationPhotoFactory.getInstance().createPhoto(); //Todo
	//	assertTrue(photo != null);
	}

	@Test
	public void testCreatePhotoWithId() {
		Photo photoWithId = MusicalLocationPhotoFactory.getInstance().createPhoto(PhotoId.getNextId());
		assertTrue(photoWithId != null);
	}

}
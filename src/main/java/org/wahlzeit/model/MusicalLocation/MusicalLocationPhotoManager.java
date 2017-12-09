package org.wahlzeit.model.MusicalLocation;

import org.wahlzeit.model.PhotoManager;

import java.util.logging.Logger;

public class MusicalLocationPhotoManager extends PhotoManager {

	protected static final MusicalLocationPhotoManager instance = new MusicalLocationPhotoManager();


	private static final Logger log = Logger.getLogger(MusicalLocationPhotoManager.class.getName());


	private MusicalLocationPhotoManager() {

	}

	public static MusicalLocationPhotoManager getInstance() {
		return instance;
	}

}

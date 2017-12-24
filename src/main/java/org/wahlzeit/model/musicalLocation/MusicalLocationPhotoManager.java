package org.wahlzeit.model.musicalLocation;

import org.wahlzeit.model.PhotoManager;

public class MusicalLocationPhotoManager extends PhotoManager {

	//protected static final MusicalLocationPhotoManager instance = new MusicalLocationPhotoManager();
	//private static final Logger log = Logger.getLogger(MusicalLocationPhotoManager.class.getName());

	public MusicalLocationPhotoManager() {

	}

	public static MusicalLocationPhotoManager getInstance() {
		return instance;
	}

}

package org.wahlzeit.model;

import java.util.logging.Logger;

public class MusicalLocationPhotoManager extends PhotoManager {

	protected static final PhotoManager instance = new MusicalLocationPhotoManager();
	private static final Logger log = Logger.getLogger(MusicalLocationPhotoManager.class.getName());

	public MusicalLocationPhotoManager() {
		super();
	}
}

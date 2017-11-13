package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class MusicalLocationPhotoFactory extends PhotoFactory {
	private static final Logger log = Logger.getLogger(MusicalLocationPhotoFactory.class.getName());

	@Override
	public Photo createPhoto() {
		return new MusicalLocationPhoto();
	}

	@Override
	public Photo createPhoto(PhotoId id) {
		return new MusicalLocationPhoto(id);
	}

	/**
	 * Public singleton access method.
	 */
	public static synchronized PhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic MusicalLocationPhotoFactory").toString());
			setInstance(new MusicalLocationPhotoFactory());
		}

		return instance;
	}


}

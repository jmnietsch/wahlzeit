package org.wahlzeit.model.MusicalLocation;

import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class MusicalLocationPhotoFactory extends PhotoFactory {
	private static final Logger log = Logger.getLogger(MusicalLocationPhotoFactory.class.getName());
	/**
	 * @methodtype factory
	 * @return A new Image Instance
	 */
	@Override
	public MusicalLocationPhoto createPhoto() {
		return new MusicalLocationPhoto();
	}

	/**
	 * @methodtype factory
	 * @param id The Id the created Image will have
	 * @return A new Image Instance
	 */
	@Override
	public MusicalLocationPhoto createPhoto(PhotoId id) {
		return new MusicalLocationPhoto(id);
	}

	/**
	 * Public singleton access method.
	 * @methodtype get
	 */
	public static synchronized PhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic MusicalLocationPhotoFactory").toString());
			setInstance(new MusicalLocationPhotoFactory());
		}

		return instance;
	}

	/**
	 * @methodtype helper
	 */
	public static void initialize(){
		getInstance();
	}


}

package org.wahlzeit.model;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.coordinates.CartesianCoordinateTest;
import org.wahlzeit.model.coordinates.CoordinateTest;
import org.wahlzeit.model.musicalLocation.MusicalLocationTestSuite;
import org.wahlzeit.model.persistence.PersistenceTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		PersistenceTestSuite.class,
		AccessRightsTest.class,
		CoordinateTest.class,
		FlagReasonTest.class,
		GenderTest.class,
		GuestTest.class,
		PhotoFilterTest.class,
		TagsTest.class,
		UserStatusTest.class,
		ValueTest.class,
		CoordinateTest.class,
		CartesianCoordinateTest.class,

		MusicalLocationTestSuite.class
})


public class ModelTestSuite {

}

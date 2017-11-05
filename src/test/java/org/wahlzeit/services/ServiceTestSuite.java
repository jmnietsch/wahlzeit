package org.wahlzeit.services;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.services.mailing.MailingTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		MailingTestSuite.class,
		EmailAddressTest.class,
		LogBuilderTest.class
})


public class ServiceTestSuite {

}

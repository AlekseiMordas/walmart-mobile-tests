package com.walmart.runner;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.uncommons.reportng.FailuresHTMLReporter;
import org.uncommons.reportng.HTMLReporter;


import com.clioption.CliParser;
import com.runner.Runner;
import com.walmart.runner.clioption.DeviceHostOption;
import com.walmart.runner.clioption.DeviceNameOption;
import com.walmart.runner.clioption.DevicePortOtion;
import com.walmart.runner.clioption.OsOption;

public class TestRunner extends Runner {

	private static final Logger LOGGER = Logger.getLogger(TestRunner.class);

	public TestRunner(final String[] args) {
		super(args);
	}

	public static void main(final String[] args) {
		try {
			final Runner tr = new TestRunner(args);
			@SuppressWarnings("rawtypes")
			final
			List<Class> listeners = new ArrayList<Class>();
			listeners.add(HTMLReporter.class);
			listeners.add(FailuresHTMLReporter.class);
			listeners.add(SuiteListener.class);
			tr.setListeners(listeners);
			tr.run();
		} catch (final Exception e) {
			LOGGER.fatal(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		finally {
			final int exitCode = BuildResult.getExitResult();
			LOGGER.info("Exit with code : " + exitCode);
			System.exit(exitCode); 
		}

	}

	@Override
	public void addCommandLineOptions() {
		super.addCommandLineOptions();
		CliParser.getCmdLineOptions().add(new DeviceNameOption());
		CliParser.getCmdLineOptions().add(new DevicePortOtion());
		CliParser.getCmdLineOptions().add(new DeviceHostOption());
		CliParser.getCmdLineOptions().add(new OsOption());
	}

}
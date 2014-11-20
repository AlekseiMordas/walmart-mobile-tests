package org.uncommons.reportng;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.velocity.VelocityContext;
import org.testng.IClass;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

public class FailuresHTMLReporter extends AbstractReporter {
	private static final Comparator<ITestResult> RESULT_COMPARATOR = new TestResultComparator();
	private static final Comparator<IClass> CLASS_COMPARATOR = new TestClassComparator();

	// static File app = new File();

	public FailuresHTMLReporter() {
		super("org/uncommons/reportng/templates/html/");
	}

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectoryName) {
		removeEmptyDirectories(new File(outputDirectoryName));

		boolean useFrames = System.getProperty("org.uncommons.reportng.frames",
				"true").equals("true");

		File outputDirectory = new File(outputDirectoryName, "html");
		if (!outputDirectory.exists()) {
			outputDirectory.mkdirs();
		}
		try {
			createOverview(suites, outputDirectory, !(useFrames));
			createResults(suites, outputDirectory);
			copyResources(outputDirectory);
		} catch (Exception ex) {
			throw new ReportNGException("Failed generating HTML report.", ex);
		}
	}

	private void createOverview(List<ISuite> suites, File outputDirectory,
			boolean isIndex) throws Exception {
		VelocityContext context = createContext();
		context.put("suites", suites);
		generateFile(new File(outputDirectory, (isIndex) ? "index.html"
				: "failures.html"), "overviewfailed.html.vm", context);
	}

	private void createResults(List<ISuite> suites, File outputDirectory)
			throws Exception {
		int index = 1;
		for (ISuite suite : suites) {
			int index2 = 1;
			for (ISuiteResult result : suite.getResults().values()) {
				VelocityContext context = createContext();
				context.put("result", result);
				context.put("failedConfigurations", sortByTestClass(result
						.getTestContext().getFailedConfigurations()));
				context.put("skippedConfigurations", sortByTestClass(result
						.getTestContext().getSkippedConfigurations()));
				context.put("failedTests", sortByTestClass(result
						.getTestContext().getFailedTests()));
				context.put("skippedTests", sortByTestClass(result
						.getTestContext().getSkippedTests()));
				context.put("passedTests", sortByTestClass(result
						.getTestContext().getPassedTests()));
				String fileName = String.format(
						"suite%d_test%d_%s",
						new Object[] { Integer.valueOf(index),
								Integer.valueOf(index2), "results.html" });
				generateFile(new File(outputDirectory, fileName),
						"results.html.vm", context);

				++index2;
			}
			++index;
		}
	}

	private SortedMap<IClass, List<ITestResult>> sortByTestClass(
			IResultMap results) {
		SortedMap<IClass, List<ITestResult>> sortedResults = new TreeMap<IClass, List<ITestResult>>(
				CLASS_COMPARATOR);
		for (ITestResult result : results.getAllResults()) {
			List<ITestResult> resultsForClass = sortedResults.get(result
					.getTestClass());
			if (resultsForClass == null) {
				resultsForClass = new ArrayList<ITestResult>();
				sortedResults.put(result.getTestClass(), resultsForClass);
			}
			int index = Collections.binarySearch(resultsForClass, result,
					RESULT_COMPARATOR);
			if (index < 0) {
				index = Math.abs(index + 1);
			}
			resultsForClass.add(index, result);
		}
		return sortedResults;
	}

	private void copyResources(File outputDirectory) throws IOException {
		copyClasspathResource(outputDirectory, "reportng.css", "reportng.css");
		copyClasspathResource(outputDirectory, "reportng.js", "reportng.js");
		copyClasspathResource(outputDirectory, "sorttable.js", "sorttable.js");

		File customStylesheet = META.getStylesheetPath();
		if (customStylesheet == null)
			return;
		copyFile(outputDirectory, customStylesheet, "custom.css");
	}
}
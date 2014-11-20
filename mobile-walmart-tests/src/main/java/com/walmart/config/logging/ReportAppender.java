package com.walmart.config.logging;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class ReportAppender extends AppenderSkeleton {

	@Override
	protected void append(LoggingEvent event) {

		String log = getLayout().format(event);
		log = StringUtils.replace(log, "\n", "</br>");
		Reporter.log(log, false);

	}

	@Override
	public void close() {

		if (this.closed)
			return;
		this.closed = true;

	}

	@Override
	public boolean requiresLayout() {

		return true;
	}

}
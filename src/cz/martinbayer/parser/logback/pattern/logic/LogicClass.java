package cz.martinbayer.parser.logback.pattern.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogicClass {
	Logger l = LoggerFactory.getLogger(getClass());

	public void doSomething() {
		l.debug("doing something");

		try {
			throw new NullPointerException("neco je null");
		} catch (NullPointerException e) {
			l.error("neco se stalo:",e);
		}
	}
}

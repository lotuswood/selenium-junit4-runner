/*
 * Copyright 2011 Brian Matthews
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.btmatthews.selenium.junit4.runner;

import org.apache.commons.lang.reflect.ConstructorUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.base.Supplier;
import com.thoughtworks.selenium.Selenium;

/**
 * Factory that is responsible for creating the {@link Selenium} instance that
 * wraps a {@link WebDriver} and acting as a an wrapper for the start and stop
 * methods.
 * 
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public class WrappedDriverFactory implements SeleniumFactory<Selenium> {

	/**
	 * The configuration annotation.
	 */
	private WrappedDriverConfiguration configuration;

	/**
	 * The {@link WebDriver} class.
	 */
	private Class<? extends WebDriver> webDriverClass;

	public WrappedDriverFactory(final WrappedDriverConfiguration config,
			final Class<? extends WebDriver> driverClass) {
		configuration = config;
		webDriverClass = driverClass;
	}

	public Selenium create() throws Exception {
		return new WebDriverBackedSelenium(new Supplier<WebDriver>() {
			public WebDriver get() {
				final DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setJavascriptEnabled(true);
				try {
					return (WebDriver) ConstructorUtils.invokeConstructor(
							webDriverClass, new Object[] { capabilities },
							new Class[] { Capabilities.class });
				} catch (Exception e) {
					return null;
				}
			}
		}, configuration.browserURL());
	}

	public void start(final Selenium server) {
		server.start();
	}

	public void stop(final Selenium server) {
		server.stop();
	}

}

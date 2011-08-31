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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code SeleniumRCConfiguration} defines class-level meta-data which can be
 * used to instruct client code with regard to use the <a
 * href="http://seleniumhq.org">Selenium RC Server</a>.
 * 
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SeleniumRCConfiguration {

	/**
	 * The host on which the Selenium Server resides. The default value is
	 * {@literal localhost}.
	 */
	String serverHost() default "localhost";

	/**
	 * The port on which the Selenium Server is listening. The default value is
	 * {@literal 4444}.
	 */
	int serverPort() default 4444;

	/**
	 * The browser URL.
	 */
	String browserURL();

	/**
	 * The browser start commands used to launch the tests. By default we just
	 * run with Firefox.
	 */
	String[] browserStartCommands() default { "*firefox" };
}

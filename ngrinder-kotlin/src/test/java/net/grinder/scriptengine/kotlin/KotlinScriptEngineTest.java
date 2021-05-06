/*
 * Copyright (c) 2012-present NAVER Corp.
 *
 * This file is part of The nGrinder software distribution. Refer to
 * the file LICENSE which is part of The nGrinder distribution for
 * licensing details. The nGrinder distribution is available on the
 * Internet at https://naver.github.io/ngrinder
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.grinder.scriptengine.kotlin;

import net.grinder.engine.common.EngineException;
import net.grinder.engine.common.ScriptLocation;
import net.grinder.engine.process.JUnitThreadContextInitializer;
import org.junit.Test;

import java.io.File;

public class KotlinScriptEngineTest {
	@Test
	public void testRunKotlinScript() throws EngineException {
		JUnitThreadContextInitializer init = new JUnitThreadContextInitializer();
		init.initialize();

		String filePath = "src/test/resources/TestRunner.kts";
		File file = new File(filePath);

		KotlinScriptEngine engine = new KotlinScriptEngine(new ScriptLocation(file.getAbsoluteFile()));

		init.attachWorkerThreadContext();

		KotlinScriptEngine.KotlinWorkerRunnable worker = (KotlinScriptEngine.KotlinWorkerRunnable) engine.createWorkerRunnable();
		worker.run();
		worker.shutdown();

		init.detachWorkerThreadContext();
	}
}

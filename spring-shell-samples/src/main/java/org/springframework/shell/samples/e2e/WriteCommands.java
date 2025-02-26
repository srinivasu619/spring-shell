/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.shell.samples.e2e;

import java.util.function.Supplier;

import org.jline.terminal.Terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class WriteCommands extends BaseE2ECommands {

	@Autowired
	Terminal terminal;

	@Bean
	public CommandRegistration writeTerminalWriterRegistration(Supplier<CommandRegistration.Builder> builder) {
		return builder.get()
			.command(REG, "write-terminalwriter")
			.group(GROUP)
			.withTarget()
				.consumer(ctx -> {
					ctx.getTerminal().writer().println("hi");
					ctx.getTerminal().writer().flush();
				})
				.and()
			.build();
	}

	@ShellMethod(key = LEGACY_ANNO + "write-terminalwriter", group = GROUP)
	public void writeTerminalWriterAnnotation() {
		terminal.writer().println("hi");
		terminal.writer().flush();
	}

	@Bean
	public CommandRegistration writeSystemOutRegistration(Supplier<CommandRegistration.Builder> builder) {
		return builder.get()
			.command(REG, "write-terminalwriter")
			.group(GROUP)
			.withTarget()
				.consumer(ctx -> {
					System.out.println("hi");
				})
				.and()
			.build();
	}

	@ShellMethod(key = LEGACY_ANNO + "write-systemout", group = GROUP)
	public void writeSystemOutAnnotation() {
		System.out.println("hi");
	}
}

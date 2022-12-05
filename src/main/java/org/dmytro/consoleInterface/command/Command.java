package org.dmytro.consoleInterface.command;

import java.util.Scanner;

public interface Command {
    void execute(Scanner scanner) throws Exception;
}

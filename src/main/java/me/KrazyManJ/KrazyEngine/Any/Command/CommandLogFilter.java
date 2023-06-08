package me.KrazyManJ.KrazyEngine.Any.Command;

import me.KrazyManJ.KrazyEngine.Spigot.Command.CommandMapRegistry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds command and registers filter while logging command.
 * This filter is blacklist - commands in this class are not logged.
 */
@SuppressWarnings("unused")
public final class CommandLogFilter {

    public static boolean validRegistered = false;
    private static final Logger logger = (Logger) LogManager.getRootLogger();

    /**
     * Registers pre-made filter that filters out commands that are invalid (non-existent)
     */
    public static void registerValidFilter() {
        if (validRegistered) return;
        logger.addFilter(new vFilter());
    }


    private final List<String> commands = new ArrayList<>();

    /**
     * Adds command to memory of this class before registering filter
     * @param command name of command, even with or without "/" char
     * @return this object
     */
    public CommandLogFilter addCommand(String command) {
        if (!command.startsWith("/")) command = "/" + command;
        commands.add(command);
        return this;
    }

    /**
     * Registers this filter
     */
    public void register() {
        if (commands.size() == 0) return;
        logger.addFilter(new aFilter());
    }


    private final class aFilter extends AbstractFilter {
        @Override
        public Result filter(LogEvent event) {
            return event == null ? Result.NEUTRAL : isLoggable(event.getMessage().getFormattedMessage());
        }

        @Override
        public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
            return isLoggable(msg.getFormattedMessage());
        }

        @Override
        public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
            return isLoggable(msg);
        }

        @Override
        public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
            return msg == null ? Result.NEUTRAL : isLoggable(msg.toString());
        }

        private Result isLoggable(String msg) {
            if (msg == null) return Result.NEUTRAL;
            if (!msg.contains("issued server command:")) return Result.NEUTRAL;

            for (String command : commands)
                if (msg.contains(command)) {
                    return Result.DENY;
                }
            return Result.NEUTRAL;
        }
    }

    private static final class vFilter extends AbstractFilter {
        @Override
        public Result filter(LogEvent event) {
            return event == null ? Result.NEUTRAL : isLoggable(event.getMessage().getFormattedMessage());
        }

        @Override
        public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
            return isLoggable(msg.getFormattedMessage());
        }

        @Override
        public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
            return isLoggable(msg);
        }

        @Override
        public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
            return msg == null ? Result.NEUTRAL : isLoggable(msg.toString());
        }

        private Result isLoggable(String msg) {
            if (msg == null) return Result.NEUTRAL;
            if (!msg.contains("issued server command:")) return Result.NEUTRAL;

            String command = msg.split("issued server command: ")[1].split(" ")[0];
            if (!CommandMapRegistry.isValidCommand(command.substring(1))) return Result.DENY;
            return Result.NEUTRAL;
        }
    }
}

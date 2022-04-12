package me.KrazyManJ.KrazyEngine.Any.Command;

import me.KrazyManJ.KrazyEngine.Spigot.CommandMapRegistry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

import java.util.ArrayList;
import java.util.List;

public final class CommandLogFilter{

    public static boolean validRegistered = false;

    public static void registerValidFilter(){
        if (validRegistered) return;
        ((Logger) LogManager.getRootLogger()).addFilter(new vFilter());
    }


    private final List<String> commands = new ArrayList<>();

    public CommandLogFilter addCommand(String command){
        if (!command.startsWith("/")) command = "/"+command;
        commands.add(command);
        return this;
    }
    public void register(){
        ((Logger) LogManager.getRootLogger()).addFilter(new aFilter());
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

            for (String command : commands) if (msg.contains(command)) return Result.DENY;
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

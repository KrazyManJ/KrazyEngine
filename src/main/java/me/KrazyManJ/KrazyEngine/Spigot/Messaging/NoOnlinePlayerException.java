package me.KrazyManJ.KrazyEngine.Spigot.Messaging;

final class NoOnlinePlayerException extends Exception {
    public NoOnlinePlayerException() {
        super("There is no player online to execute this action!");
    }
}

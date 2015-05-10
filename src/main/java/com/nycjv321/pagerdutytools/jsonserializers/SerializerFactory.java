package com.nycjv321.pagerdutytools.jsonserializers;

/**
 * Created by jvelasquez on 4/18/15.
 */
public class SerializerFactory {
    private static final ServiceSerializer serviceSerializer;
    private static final NoteSerializer noteSerializer;
    private static final UserSerializer userSerializer;
    private static final LogEntrySerializer logEntrySerializer;
    private static final ChannelSerializer channelSerializer;

    static {
        serviceSerializer = new ServiceSerializer();
        noteSerializer = new NoteSerializer();
        userSerializer = new UserSerializer();
        logEntrySerializer = new LogEntrySerializer();
        channelSerializer = new ChannelSerializer();
    }

    public static ServiceSerializer getServiceSerializer() {
        return serviceSerializer;
    }

    public static NoteSerializer getNoteSerializer() {
        return noteSerializer;
    }

    public static UserSerializer getUserSerializer() {
        return userSerializer;
    }

    public static LogEntrySerializer getLogEntrySerializer() {
        return logEntrySerializer;
    }

    public static ChannelSerializer getChannelSerializer() {
        return channelSerializer;
    }
}

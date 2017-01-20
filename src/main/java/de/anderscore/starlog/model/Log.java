package de.anderscore.starlog.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by MBecker on 20.01.2017.
 */
@Entity
@Table(name = "LOG")
public class Log {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    UUID uuid;

    @OneToMany
    List<LogEntry> logEntries = new ArrayList<>();

    public List<LogEntry> getLogEntries() {
        return logEntries;
    }

    public void setLogEntries(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (uuid != null ? !uuid.equals(log.uuid) : log.uuid != null) return false;
        return logEntries != null ? logEntries.equals(log.logEntries) : log.logEntries == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (logEntries != null ? logEntries.hashCode() : 0);
        return result;
    }
}

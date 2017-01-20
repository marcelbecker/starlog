package de.anderscore.starlog.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by MBecker on 20.01.2017.
 */
@Entity
@Table(name = "LOG_ENTRY")
public class LogEntry {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    UUID uuid;

    @Column(name = "POSTED_ON", columnDefinition = "varchar(32)")
    String postedOn;

    @Column(name = "POSTED_BY", columnDefinition = "varchar(64)")
    String postedBy;

    @Column(name = "CONTENT")
    @Lob
    String content;

    public String getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(String postedOn) {
        this.postedOn = postedOn;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogEntry logEntry = (LogEntry) o;

        if (uuid != null ? !uuid.equals(logEntry.uuid) : logEntry.uuid != null) return false;
        if (postedOn != null ? !postedOn.equals(logEntry.postedOn) : logEntry.postedOn != null) return false;
        if (postedBy != null ? !postedBy.equals(logEntry.postedBy) : logEntry.postedBy != null) return false;
        return content != null ? content.equals(logEntry.content) : logEntry.content == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (postedOn != null ? postedOn.hashCode() : 0);
        result = 31 * result + (postedBy != null ? postedBy.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}

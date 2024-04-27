package org.wsd.core.events;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@Document(collection = "eventStore")
public class EventModel {
    @Id
    private UUID id;
    private Date timestamp;
    private UUID aggregateIdentifier;
    private String aggregateType;
    private Integer version;
    private String eventType;
    private BaseEvent event;
}

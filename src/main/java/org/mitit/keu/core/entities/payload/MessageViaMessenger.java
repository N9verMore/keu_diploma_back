package org.mitit.keu.core.entities.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageViaMessenger {
    public long clientId = 2;
    public String receiverPhone;
    public String message;
}

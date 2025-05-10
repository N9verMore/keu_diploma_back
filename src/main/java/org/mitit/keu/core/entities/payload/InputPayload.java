package org.mitit.keu.core.entities.payload;

import lombok.Getter;
import lombok.Setter;
import org.mitit.keu.core.entities.MilitaryMan;
import org.mitit.keu.core.entities.Registry;

@Getter
@Setter
public class InputPayload {
    public MilitaryMan militaryMan;
    public Registry registry;
}

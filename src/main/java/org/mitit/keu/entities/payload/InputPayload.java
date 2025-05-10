package org.mitit.keu.entities.payload;

import lombok.Getter;
import lombok.Setter;
import org.mitit.keu.entities.MilitaryMan;
import org.mitit.keu.entities.Registry;

@Getter
@Setter
public class InputPayload {
    public MilitaryMan militaryMan;
    public Registry registry;
}

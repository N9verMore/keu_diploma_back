package org.mimit.keu.entities.payload;

import org.mimit.keu.entities.MilitaryMan;
import org.mimit.keu.entities.Registry;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputPayload {
    public MilitaryMan militaryMan;
    public Registry registry;
}

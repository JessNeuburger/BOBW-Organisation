package at.htl.erikmayrhofer.organigrampane;


import at.htl.florianschwarcz.organisationalstructurelib.Hierarchy;
import at.htl.florianschwarcz.organisationalstructurelib.Position;

import java.util.Observer;

public interface OrganigramController{
    void setOrganigramPane(OrganigramPane organigramPane);

    Hierarchy getHierarchy();
    void setHierarchy(Hierarchy hierarchy);

    void setRootPosition(Position pos);
    //TODO maybe property for root pos

    void invalidate();
}

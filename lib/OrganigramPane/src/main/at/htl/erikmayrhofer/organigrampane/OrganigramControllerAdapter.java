package at.htl.erikmayrhofer.organigrampane;

import at.htl.florianschwarcz.organisationalstructurelib.Hierarchy;
import at.htl.florianschwarcz.organisationalstructurelib.Position;

public abstract class OrganigramControllerAdapter implements OrganigramController {
    private Hierarchy hierarchy;
    protected OrganigramPane pane;

    @Override
    public void setOrganigramPane(OrganigramPane organigramPane) {
        this.pane = organigramPane;
    }

    @Override
    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    @Override
    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }
}

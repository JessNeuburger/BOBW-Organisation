package at.htl.erikmayrhofer.organigrampane;

import at.htl.florianschwarcz.organisationalstructurelib.Position;

public abstract class OrganigramRootBasedController extends OrganigramControllerAdapter {
    public OrganigramRootBasedController(OrganigramNodeFactory factory) {
        super(factory);
    }

    @Override
    public void setRootPosition(Position pos) {
        populateFromNode(pos, null);
    }

    protected abstract void populateFromNode(Position p, PosPane parentPane);
}

package at.htl.erikmayrhofer.organigrampane;

import at.htl.florianschwarcz.organisationalstructurelib.Position;

public abstract class OrganigramRootBasedController extends OrganigramControllerAdapter {
    @Override
    public void setRootPosition(Position pos) {
        populateFromNode(pos, null);
    }

    protected abstract void populateFromNode(Position p, PosPane parentPane);
}

package at.htl.erikmayrhofer.organigrampane;

import at.htl.florianschwarcz.organisationalstructurelib.Position;

public abstract class OrganigramRootBasedController extends OrganigramControllerAdapter {

    private Position rootPos;

    public OrganigramRootBasedController(OrganigramNodeFactory factory) {
        super(factory);
    }

    @Override
    public void setRootPosition(Position pos) {
        this.rootPos = pos;
        populateFromNode(pos, null);
    }

    protected abstract void populateFromNode(Position p, PosPane parentPane);

    @Override
    public void invalidate() {
        System.out.println("OrganigramRootBasedController invalidate");
        pane.reset();
        populateFromNode(rootPos, null);
    }
}

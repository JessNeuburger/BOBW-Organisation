package at.htl.erikmayrhofer.organigrampane;

import at.htl.florianschwarcz.organisationalstructurelib.Hierarchy;
import at.htl.florianschwarcz.organisationalstructurelib.Position;
import javafx.scene.Node;

public abstract class OrganigramControllerAdapter implements OrganigramController {
    private Hierarchy hierarchy;
    protected OrganigramPane pane;
    private OrganigramNodeFactory factory;

    public OrganigramControllerAdapter(OrganigramNodeFactory factory){
        this.factory = factory;
    }

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

    public OrganigramNodeFactory getFactory() {
        return factory;
    }

    public void setFactory(OrganigramNodeFactory factory) {
        this.factory = factory;
    }

    protected Node createOrganigramNode(Position position){
        return factory.createOrganigramNode(position);
    }
}

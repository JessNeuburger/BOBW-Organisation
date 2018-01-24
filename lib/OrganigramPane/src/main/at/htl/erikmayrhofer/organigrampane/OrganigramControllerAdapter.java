package at.htl.erikmayrhofer.organigrampane;

import at.htl.florianschwarcz.organisationalstructurelib.Hierarchy;
import at.htl.florianschwarcz.organisationalstructurelib.Position;
import javafx.scene.Node;

import java.util.Observable;
import java.util.Observer;

public abstract class OrganigramControllerAdapter implements OrganigramController, Observer{
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
        if(this.hierarchy != null)
            this.hierarchy.deleteObserver(this);
        this.hierarchy = hierarchy;
        if(this.hierarchy != null)
            this.hierarchy.addObserver(this);
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

    @Override
    public void update(Observable o, Object arg) {
        invalidate();
    }
}

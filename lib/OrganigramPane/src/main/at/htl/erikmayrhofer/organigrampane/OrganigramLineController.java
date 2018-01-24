package at.htl.erikmayrhofer.organigrampane;

import at.htl.florianschwarcz.organisationalstructurelib.Position;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class OrganigramLineController extends OrganigramRootBasedController {
    public OrganigramLineController(OrganigramNodeFactory factory) {
        super(factory);
    }

    @Override
    protected void populateFromNode(Position p, PosPane parentPane){

        PosPane lineParent = createPosPaneFromPosition(parentPane, p.getSuperordinate());
        pane.reset();
        if(lineParent != null)
            pane.setRoot(lineParent);

        PosPane thisPane = createPosPaneFromPosition(lineParent, p);
        if(lineParent != null)
            lineParent.addPos(thisPane);
        else
            pane.setRoot(thisPane);

        if(p.getSubordinates() != null) {
            for (Position sub : p.getSubordinates()) {
                PosPane currPane = createPosPaneFromPosition(thisPane, sub);
                thisPane.addPos(currPane);
            }
        }
        pane.relayout();
    }

    private PosPane createPosPaneFromPosition(PosPane parent, Position p){
        if(p == null) return null;
        return new PosPane(pane,createOrganigramNode(p), UUID.randomUUID().toString(), p);
    }
}

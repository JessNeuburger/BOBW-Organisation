package at.htl.erikmayrhofer.organigrampane;

import at.htl.florianschwarcz.organisationalstructurelib.Position;
import javafx.scene.Node;

@FunctionalInterface
public interface OrganigramNodeFactory {
    public Node createOrganigramNode(Position position);
}

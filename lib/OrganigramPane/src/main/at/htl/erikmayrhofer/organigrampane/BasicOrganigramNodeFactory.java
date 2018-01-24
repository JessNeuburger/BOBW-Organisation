package at.htl.erikmayrhofer.organigrampane;

import at.htl.florianschwarcz.organisationalstructurelib.Position;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BasicOrganigramNodeFactory implements OrganigramNodeFactory {
    @Override
    public Node createOrganigramNode(Position p) {
        //TODO CreateOrganigramNode more beautiful
        VBox content = new VBox();

        content.getChildren().addAll(
                new Label(p.getJob().getName()),
                new Label(p.getPerson().getFirstName() + " " + p.getPerson().getLastName()));
        return content;
    }
}

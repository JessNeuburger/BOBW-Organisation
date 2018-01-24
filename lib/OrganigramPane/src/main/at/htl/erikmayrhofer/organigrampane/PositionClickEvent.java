package at.htl.erikmayrhofer.organigrampane;

import at.htl.florianschwarcz.organisationalstructurelib.Position;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;

public class PositionClickEvent extends MouseEvent {
    private Position position;

    public PositionClickEvent(MouseEvent parent, Position person){
        super(
                parent.getSource(),
                parent.getTarget(),
                parent.getEventType(),
                parent.getX(),
                parent.getY(),
                parent.getScreenX(),
                parent.getScreenY(),
                parent.getButton(),
                parent.getClickCount(),
                parent.isShiftDown(),
                parent.isControlDown(),
                parent.isAltDown(),
                parent.isMetaDown(),
                parent.isPrimaryButtonDown(),
                parent.isMiddleButtonDown(),
                parent.isSecondaryButtonDown(),
                parent.isSynthesized(),
                parent.isPopupTrigger(),
                parent.isStillSincePress(),
                parent.getPickResult()
        );
        this.position = person;
    }

    public PositionClickEvent(EventType<? extends MouseEvent> eventType, double x, double y, double screenX, double screenY, MouseButton button, int clickCount, boolean shiftDown, boolean controlDown, boolean altDown, boolean metaDown, boolean primaryButtonDown, boolean middleButtonDown, boolean secondaryButtonDown, boolean synthesized, boolean popupTrigger, boolean stillSincePress, PickResult pickResult, Position person) {
        super(eventType, x, y, screenX, screenY, button, clickCount, shiftDown, controlDown, altDown, metaDown, primaryButtonDown,
                middleButtonDown, secondaryButtonDown, synthesized, popupTrigger, stillSincePress, pickResult);
        this.position = person;
    }

    public PositionClickEvent(Object source, EventTarget target, EventType<? extends MouseEvent> eventType, double x, double y, double screenX, double screenY, MouseButton button, int clickCount, boolean shiftDown, boolean controlDown, boolean altDown, boolean metaDown, boolean primaryButtonDown, boolean middleButtonDown, boolean secondaryButtonDown, boolean synthesized, boolean popupTrigger, boolean stillSincePress, PickResult pickResult, Position person) {
        super(source, target, eventType, x, y, screenX, screenY, button, clickCount, shiftDown, controlDown, altDown, metaDown, primaryButtonDown,
                middleButtonDown, secondaryButtonDown, synthesized, popupTrigger, stillSincePress, pickResult);
        this.position = person;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

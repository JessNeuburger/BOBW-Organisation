package bobworga.utilclass;

import at.htl.florianschwarcz.organisationalstructurelib.Person;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;

public class PersonClickEvent extends MouseEvent{

    private Person person;

    public PersonClickEvent(MouseEvent parent, Person person){
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
        this.person = person;
    }

    public PersonClickEvent(EventType<? extends MouseEvent> eventType, double x, double y, double screenX, double screenY, MouseButton button, int clickCount, boolean shiftDown, boolean controlDown, boolean altDown, boolean metaDown, boolean primaryButtonDown, boolean middleButtonDown, boolean secondaryButtonDown, boolean synthesized, boolean popupTrigger, boolean stillSincePress, PickResult pickResult, Person person) {
        super(eventType, x, y, screenX, screenY, button, clickCount, shiftDown, controlDown, altDown, metaDown, primaryButtonDown,
                middleButtonDown, secondaryButtonDown, synthesized, popupTrigger, stillSincePress, pickResult);
        this.person = person;
    }

    public PersonClickEvent(Object source, EventTarget target, EventType<? extends MouseEvent> eventType, double x, double y, double screenX, double screenY, MouseButton button, int clickCount, boolean shiftDown, boolean controlDown, boolean altDown, boolean metaDown, boolean primaryButtonDown, boolean middleButtonDown, boolean secondaryButtonDown, boolean synthesized, boolean popupTrigger, boolean stillSincePress, PickResult pickResult, Person person) {
        super(source, target, eventType, x, y, screenX, screenY, button, clickCount, shiftDown, controlDown, altDown, metaDown, primaryButtonDown,
                middleButtonDown, secondaryButtonDown, synthesized, popupTrigger, stillSincePress, pickResult);
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

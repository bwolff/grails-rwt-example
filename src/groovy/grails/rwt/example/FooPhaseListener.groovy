package grails.rwt.example

import groovy.util.logging.Commons;

import org.eclipse.rap.rwt.lifecycle.PhaseEvent
import org.eclipse.rap.rwt.lifecycle.PhaseId;
import org.eclipse.rap.rwt.lifecycle.PhaseListener;

@Commons
class FooPhaseListener implements PhaseListener {

    public FooPhaseListener() {
        log.debug "FooPhaseListener created"
    }

    @Override
    public void afterPhase(PhaseEvent arg0) {
        log.debug "FooPhaseListener afterPhase"
    }

    @Override
    public void beforePhase(PhaseEvent arg0) {
        log.debug "FooPhaseListener beforePhase"
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.PREPARE_UI_ROOT
    }
}

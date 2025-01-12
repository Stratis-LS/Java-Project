package com.project.game.service.controls.impl;

import com.badlogic.gdx.Input.Keys;
import com.project.game.service.controls.AbstractButtonControl;
import com.project.game.service.controls.ControlType;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;

/** Allows to control an entity with keyboard events. */
public class KeyboardControl extends AbstractButtonControl {
    public KeyboardControl() {
        // Initial settings:
        up = Keys.Z;
        down = Keys.S;
        left = Keys.Q;
        right = Keys.D;
        jump = Keys.SPACE;
    }

    @Override
    public void attachInputListener(final InputMultiplexer inputMultiplexer) {
        inputMultiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(final int keycode) {
                if (keycode == up || keycode == down || keycode == left || keycode == right) {
                    pressedButtons.add(keycode);
                    updateMovement();
                    return true;
                } else if (keycode == jump) {
                    getListener().jump();
                    return true;
                }
                return false;
            }

            @Override
            public boolean keyUp(final int keycode) {
                if (keycode == up || keycode == down || keycode == left || keycode == right) {
                    pressedButtons.remove(keycode);
                    updateMovement();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public ControlType getType() {
        return ControlType.KEYBOARD;
    }
}

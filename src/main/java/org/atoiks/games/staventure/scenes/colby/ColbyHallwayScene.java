/**
 *  Staventure
 *  Copyright (C) 2017-2019  Atoiks-Games <atoiks-games@outlook.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.atoiks.games.staventure.scenes.colby;

import java.awt.Color;

import org.atoiks.games.framework2d.Scene;
import org.atoiks.games.framework2d.IGraphics;
import org.atoiks.games.framework2d.SceneManager;
import org.atoiks.games.framework2d.ResourceManager;

import org.atoiks.games.framework2d.resource.Texture;

import org.atoiks.games.staventure.prefabs.Player;
import org.atoiks.games.staventure.prefabs.Direction;

public final class ColbyHallwayScene implements Scene {

    private static final int DOOR_LIB_X1 = 10;
    private static final int DOOR_LIB_Y1 = 325;
    private static final int DOOR_LIB_X2 = 72;
    private static final int DOOR_LIB_Y2 = 329;

    private static final int DOOR_BO_X1 = 50;
    private static final int DOOR_BO_Y1 = 121;
    private static final int DOOR_BO_X2 = 102;
    private static final int DOOR_BO_Y2 = 125;

    private static final int DOOR_SA_X1 = 80;
    private static final int DOOR_SA_Y1 = 325;
    private static final int DOOR_SA_X2 = 132;
    private static final int DOOR_SA_Y2 = 329;

    private final Texture bgImg;

    private final Player player;

    public ColbyHallwayScene() {
        this.bgImg = ResourceManager.get("/colby/colby_hallway/floor.png");

        this.player = new Player();
        this.player.state = Player.IDLE_FRAME;
        this.player.speed = 80;
    }

    @Override
    public void enter(Scene from) {
        if (from instanceof LibraryScene) {
            player.direction = Direction.UP;
            player.move(25, 272);
        } else if (from instanceof BusinessOfficeScene) {
            player.direction = Direction.DOWN;
            player.move(68, 130);
        } else {
            player.direction = Direction.RIGHT;
            player.move(25, 218);
        }
    }

    @Override
    public void render(IGraphics g) {
        g.setClearColor(Color.black);
        g.clearGraphics();

        // Draw floor
        g.rotate((float) -Math.PI / 2, 0, 325);
        g.drawTexture(bgImg, 0, 325);
        g.rotate((float) +Math.PI / 2, 0, 325);

        player.render(g);

        g.setColor(Color.red);
        g.fillRect(DOOR_LIB_X1, DOOR_LIB_Y1, DOOR_LIB_X2, DOOR_LIB_Y2);
        g.setColor(Color.black);
        g.fillRect(DOOR_LIB_X1, DOOR_LIB_Y2, DOOR_LIB_X2, DOOR_LIB_Y2 + 40);

        g.setColor(Color.red);
        g.fillRect(DOOR_BO_X1, DOOR_BO_Y1, DOOR_BO_X2, DOOR_BO_Y2);
        g.setColor(Color.black);
        g.fillRect(DOOR_BO_X1, DOOR_BO_Y1 - 40, DOOR_BO_X2, DOOR_BO_Y1);

        g.setColor(Color.red);
        g.fillRect(DOOR_SA_X1, DOOR_SA_Y1, DOOR_SA_X2, DOOR_SA_Y2);
        g.setColor(Color.black);
        g.fillRect(DOOR_SA_X1, DOOR_SA_Y2, DOOR_SA_X2, DOOR_SA_Y2 + 40);
    }

    @Override
    public boolean update(float dt) {
        final float oldX = player.x;
        final float oldY = player.y;
        player.update(dt);

        if (player.x < -6) {
            // TODO: This is the Pierce Hall side of the hallway
            // scene.switchToScene()
            player.x = -6;
        }
        if (player.x > 700 - 26) {
            // TODO: This is the LaBaron side of the hallway
            // scene.switchToScene()
            player.x = 700 - 26;
        }
        if (player.y < 125) {
            if (DOOR_BO_X1 - 1 < player.x && player.x < DOOR_BO_X2 - 26) {
                if (player.y < 125 - 32) {
                    SceneManager.swapScene(new BusinessOfficeScene());
                    return true;
                }
            } else {
                player.y = 125;
            }
        }
        if (player.y > 325 - 32) {
            if (DOOR_SA_X1 - 1 < player.x && player.x < DOOR_SA_X2 - 26) {
                if (player.y > 325) {
                    // Placeholder for SA office
                    player.y = 325 - 32;
                    return true;
                }
            } else if (DOOR_LIB_X1 - 6 < player.x && player.x < DOOR_LIB_X2 - 26) {
                if (player.y > 325) {
                    SceneManager.swapScene(new LibraryScene());
                    return true;
                }
            } else {
                player.y = 325 - 32;
            }
        }
        return true;
    }

    @Override
    public void resize(int w, int h) {
        // Ignore
    }
}

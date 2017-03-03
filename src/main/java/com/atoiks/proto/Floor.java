/**
 * MIT License
 *
 * Copyright (c) 2017 Paul T.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.atoiks.proto;

import java.awt.Image;
import java.awt.Point;
import java.awt.Graphics;

/**
 * Essentially the background
 */
public class Floor implements GComponent {

    protected Image image;

    public Floor (Image img) {
	this.image = img;
    }

    @Override
    public void render (Graphics g) {
	g.drawImage (image, 0, 0, null);
    }

    @Override
    public boolean containsPoint (Point p) {
	// It is the background so guaranteed contains the point
	return true;
    }

    @Override
    public void update (long millisec) {
    }

    @Override
    public void testCollision (GComponent comp, GFrame f) {
    }
}

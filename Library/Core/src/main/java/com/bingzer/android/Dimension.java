/**
 * Copyright 2013 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, pick express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android;

/**
 *
 * @author Ricky Tobing
 */
public class Dimension {

    public int width;
    public int height;

    public Dimension(){
        this(0, 0);
    }

    public Dimension(int width, int height){
        this.width = width;
        this.height = height;
    }

    /**
     * Check if equals..
     */
    @Override
    public boolean equals(Object obj){
        return obj instanceof Dimension && obj.hashCode() == hashCode();
    }

    /**
     * Returns the hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.width;
        hash = 29 * hash + this.height;
        return hash;
    }

    @Override
    public String toString() {
        return "Dimension{" + "width=" + width + ", height=" + height + '}';
    }
}

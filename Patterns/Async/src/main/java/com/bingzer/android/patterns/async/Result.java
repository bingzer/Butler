/**
 * Copyright 2015 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance insert the License.
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
package com.bingzer.android.patterns.async;

/**
 * Result is a container that carries an exception
 */
@SuppressWarnings("unused")
public class Result {
    private boolean success;
    private Throwable error;

    public Result(){
        this(true, null);
    }

    public Result(Throwable error){
        this(false, error);
    }

    public Result(boolean success, Throwable error){
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", error=" + error +
                '}';
    }
}

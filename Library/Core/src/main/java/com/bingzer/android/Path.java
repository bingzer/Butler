/**
 * Copyright 2014 Ricky Tobing
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
package com.bingzer.android;

import com.bingzer.android.async.Delegate;
import com.bingzer.android.async.Result;
import com.bingzer.android.async.Task;
import com.bingzer.android.async.handlers.ResultHandler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.Locale;

import static com.bingzer.android.Stringify.emptyIfNull;
import static com.bingzer.android.async.Async.doAsync;

public final class Path {

    public static final String ROOT = "/";
    public static final String SEPARATOR = "/";

    public static String combine(String path1, String path2){
        String p1 = emptyIfNull(path1);
        String p2 = emptyIfNull(path2);

        if(p1.endsWith(SEPARATOR) || p2.startsWith(SEPARATOR))
            return p1 + p2;
        else
            return p1 + SEPARATOR + p2;
    }

    /**
     * Returns the directory of this file
     */
    public static String getDirectory(String path){
        if(path == null) throw new NullPointerException("path can't be null");

        path = path.trim();
        if(path.equals(ROOT)) return null;

        if(path.endsWith(SEPARATOR))
            path = path.substring(0, path.length() - 1);

        int lastIndex = path.lastIndexOf(SEPARATOR);
        if(lastIndex > 0){
            return path.substring(0, lastIndex);
        }

        return SEPARATOR;
    }

    public static String getFilename(String path){
        if(path == null) return null;

        path = path.trim();
        if(path.equals(ROOT)) return ROOT;

        if(path.endsWith(SEPARATOR))
            path = path.substring(0, path.length() - 1);

        int lastIndex = path.lastIndexOf(SEPARATOR);
        if(lastIndex > -1){
            return path.substring(lastIndex + 1);
        }

        return path;
    }

    /**
     * Delete dir and its children
     */
    public static boolean deleteTree(File dir, boolean deleteSelf) {
        if(dir == null || !dir.isDirectory() || !dir.exists()) return false;
        // delete tree
        for(File f : dir.listFiles()) {
            if(f.isDirectory()) deleteTree(f, true);
            else f.delete();
        }
        return !deleteSelf || dir.delete();
    }

    public static void deleteTreeAsync(final File dir, final boolean deleteSelf, Task<Boolean> task){
        doAsync(task, new Delegate<Boolean>() {
            @Override
            public Boolean invoke() {
                return deleteTree(dir, deleteSelf);
            }
        });
    }

    public static boolean safeCreateDir(File dir){
        return dir.exists() || dir.mkdir();
    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if(sourceFile.equals(destFile)) return;
        if(!destFile.exists())
            if(!destFile.createNewFile())
                throw new IOException("Failed to create file: " + destFile);

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            safeClose(source);
            safeClose(destination);
        }
    }// end copyFile()

    public static void copyFileAsync(final File sourceFile, final File destFile, Task<Result> task){
        doAsync(new ResultHandler(task) {
            @Override public Result invokeOrThrow() throws Throwable {
                copyFile(sourceFile, destFile);
                return new Result();
            }
        });
    }

    /**
     * Copy source stream and write to destFile
     * @throws java.io.IOException
     */
    public static void copyFile(InputStream input, File destFile) throws IOException{
        if(!(input instanceof BufferedInputStream)) input = new BufferedInputStream(input);

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
        byte[] buffer = new byte[1024];
        int read;
        while((read = input.read(buffer)) != -1){
            bos.write(buffer, 0, read);
        }
        bos.flush();

        safeClose(bos);
        safeClose(input);
    }

    public static void copyFileAsync(final InputStream input, final File destFile, Task<Result> task){
        doAsync(new ResultHandler(task) {
            @Override public Result invokeOrThrow() throws Throwable{
                copyFile(input, destFile);
                return new Result();
            }
        });
    }

    public static void copy(InputStream source, StringBuilder builder) throws IOException{
        BufferedInputStream bis = new BufferedInputStream(source);
        byte[] buffer = new byte[1024];
        while(bis.read(buffer) != -1){
            builder.append(new String(buffer));
        }
        safeClose(bis);
    }

    public static void copy(InputStream input, OutputStream dest) throws IOException {
        copy(input, dest, true);
    }

    public static void copy(InputStream input, OutputStream dest, boolean close) throws IOException {
        if(!(input instanceof BufferedInputStream)) input = new BufferedInputStream(input);

        BufferedOutputStream bos = new BufferedOutputStream(dest);
        byte[] buffer = new byte[1024];
        int read;
        while((read = input.read(buffer)) != -1){
            bos.write(buffer, 0, read);
        }
        bos.flush();

        if (close){
            input.close();
            bos.close();
        }
    }

    public static void copyAsync(final InputStream source, final StringBuilder builder, Task<Result> task){
        doAsync(new ResultHandler(task) {
            @Override public Result invokeOrThrow() throws Throwable {
                copy(source, builder);
                return new Result();
            }
        });
    }

    /**
     * Removes extensions
     */
    public static String stripExtension(File file){
        if(file == null) return "";
        return stripExtension(file.getName());
    }

    /**
     * Removes extensions
     */
    public static String stripExtension(String filename){
        if(filename == null) return "";

        int idx = filename.indexOf(".");
        if(idx > 0)
            return filename.substring(0, idx);
        return filename;
    }

    /**
     * Returns the extension. if no extension, it will return null
     */
    public static String getExtension(File file){
        if(file == null) return null;
        return getExtension(file.getName());
    }

    /**
     * Returns the extension. if no extension, it will return null
     */
    public static String getExtension(String filename){
        if(filename == null) return null;

        int start = filename.indexOf(".");
        if(start > 0)
            return filename.substring(start);

        return null;
    }

    public static File[] getFiles(String dir, final String... extensions) {
        return getFiles(new File(dir), extensions);
    }

    public static File[] getFiles(File dir, final String... extensions) {
        if (Collector.isEmpty(extensions))
            return dir.listFiles();

        return dir.listFiles(new FileFilter(){
            @Override public boolean accept(File file) {
                boolean accept = false;
                for(String ext : extensions){
                    if(accept = (file.getName().toLowerCase(Locale.getDefault()).endsWith(ext)))
                        break;
                }
                return accept;
            }
        });
    }

    public static long getDirectorySize(File dir) {
        long size = 0;
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                size += file.length();
            }
            else
                size += getDirectorySize(file);
        }
        return size;
    }

    public static void getDirectorySizeAsync(final File dir, Task<Long> task){
        doAsync(task, new Delegate<Long>() {
            @Override public Long invoke() {
                return getDirectorySize(dir);
            }
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    public static void safeClose(OutputStream output){
        if(output != null) {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void safeClose(Writer writer){
        if(writer != null){
            try{
                writer.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void safeClose(Reader reader){
        if(reader != null){
            try{
                reader.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void safeClose(InputStream input){
        if(input != null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void safeClose(FileChannel fileChannel){
        if(fileChannel != null){
            try{
                fileChannel.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void safeClose(Closeable closeable){
        if(closeable != null){
            try{
                closeable.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    public static String clean(String path){
        // make sure that path has "/"
        if(path != null && !path.startsWith(SEPARATOR)) return SEPARATOR + path;
        return path;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    private Path(){
        // nothing
    }
}
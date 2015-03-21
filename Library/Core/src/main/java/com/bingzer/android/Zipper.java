package com.bingzer.android;

import com.bingzer.android.async.Result;
import com.bingzer.android.async.Task;
import com.bingzer.android.async.handlers.ResultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static com.bingzer.android.async.Async.doAsync;

/**
 * ZipUtils.
 */
@SuppressWarnings("unused")
public final class Zipper {

    /**
     * Async call for zip()
     * @param directory the source directory
     * @param zipFile the target zip file
     * @param task the task (supports Task.WithProgressReporting)
     */
    public static void zipAsync(final File directory, final File zipFile, final Task<Result> task){
        doAsync(new ResultHandler(task) {
            @Override public Result invokeOrThrow() throws Throwable {
                return zip(directory, zipFile, new ZippingCallback() {
                    @Override public void onProgress(float progress, float total) {
                        if (task instanceof WithProgressReporting)
                            ((WithProgressReporting) task).onProgress(progress, total);
                    }

                    @Override public void onError(Throwable throwable) {
                        if (task instanceof WithErrorReporting)
                            ((WithErrorReporting) task).onError(throwable);
                    }

                    @Override public void onCompleted(Result result) {
                        if (task != null)
                            task.onCompleted(result);
                    }
                });
            }
        });
    }

    /**
     * Zip entire directory to zip file
     * @param directory the source directory
     * @param zipFile the target zip file
     * @throws IOException
     */
    public static void zip(File directory, File zipFile) throws IOException {
        Result result = zip(directory, zipFile, null);
        if(!result.isSuccess())
            throw new IOException(result.getError());
    }

    /**
     * Async call for unzip()
     * @param zipFile the source zip file
     * @param directory the target directory
     * @param task the task (supports Task.WithProgressReporting)
     */
    public static void unzipAsync(final File zipFile, final File directory, final Task<Result> task){
        doAsync(new ResultHandler(task) {
            @Override public Result invokeOrThrow() throws Throwable{
                return unzip(zipFile, directory, new ZippingCallback() {
                    @Override public void onProgress(float progress, float total) {
                        if (task instanceof WithProgressReporting)
                            ((WithProgressReporting) task).onProgress(progress, total);
                    }

                    @Override public void onError(Throwable throwable) {
                        if (task instanceof WithErrorReporting)
                            ((WithErrorReporting) task).onError(throwable);
                    }

                    @Override public void onCompleted(Result result) {
                        if (task != null)
                            task.onCompleted(result);
                    }
                });
            }
        });
    }

    /**
     * Extract a zip file to a directory, keeping the same structure
     * @param zipFile the source zip file
     * @param directory the target directory
     */
    public static void unzip(File zipFile, File directory) throws IOException{
        Result result = unzip(zipFile, directory, null);
        if (!result.isSuccess())
            throw new IOException(result.getError());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    public static Result unzip(File zipFile, File directory, ZippingCallback callback){
        Result result = new Result();
        try {
            ZipFile zfile = new ZipFile(zipFile);
            final int total = zfile.size();
            int progress = 0;

            Enumeration<? extends ZipEntry> entries = zfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                File file = new File(directory, entry.getName());
                if (entry.isDirectory()) {
                    Path.safeCreateDir(file);
                } else {
                    Path.safeCreateDir(file.getParentFile());
                    InputStream in = zfile.getInputStream(entry);
                    Path.copyFile(in, file);
                }

                if(callback != null)
                    callback.onProgress(++progress, total);
            }
            result.setSuccess(true);
        } catch (IOException e) {
            result.setSuccess(false);
            result.setError(e);
        }
        return result;
    }

    public static Result zip(File directory, File zipFile, ZippingCallback callback) {
        Result result = new Result();
        try {
            final File[] files = directory.listFiles();
            final int total = files.length;
            int progress = 0;

            ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(zipFile));
            for (File file : files){
                if (file.isDirectory()) continue;

                ZipEntry entry = new ZipEntry(relativizePath(directory, file));
                zipOutput.putNextEntry(entry);

                InputStream in = new FileInputStream(file);
                Path.copy(in, zipOutput, false);
                in.close();

                zipOutput.closeEntry();

                if(callback != null)
                    callback.onProgress(++progress, total);
            }
            zipOutput.close();

            result.setSuccess(true);
        } catch (IOException e){
            result.setError(e);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * Zips collections of {@code files} to {@code zipFile}
     * Each file is checked must be direct/indirect child of {@code directory}.
     */
    public static Result zip(File directory, Collection<File> files, File zipFile, Zipper.ZippingCallback callback){
        Result result = new Result();
        try {
            final int total = files.size();
            int progress = 0;

            ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(zipFile));
            for (File file : files){
                if (file.isDirectory()) continue;

                ZipEntry entry = new ZipEntry(relativizePath(directory, file));
                zipOutput.putNextEntry(entry);

                InputStream in = new FileInputStream(file);
                Path.copy(in, zipOutput, false);
                in.close();

                zipOutput.closeEntry();

                if(callback != null)
                    callback.onProgress(++progress, total);
            }
            zipOutput.close();

            result.setSuccess(true);
        } catch (IOException e){
            result.setError(e);
            result.setSuccess(false);
        }
        return result;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    private static String relativizePath(File directory, File file){
        String name = file.getAbsolutePath().substring(directory.getAbsolutePath().length());
        if (name.startsWith("/"))
            name = name.substring(1);
        return name;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    private Zipper(){
        // nothing
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    public static interface ZippingCallback extends Task.WithProgressReporting<Result>{
    }
}

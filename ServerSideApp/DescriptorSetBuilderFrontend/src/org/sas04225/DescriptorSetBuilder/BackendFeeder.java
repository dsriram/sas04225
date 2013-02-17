/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.DescriptorSetBuilder;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sas04225.DescriptorSetBuilder.RepoProvider.ImageGroup;
import org.sas04225.DescriptorSetBuilder.RepoProvider.RepoProvider;
import org.sas04225.proto.DescriptorSetBuilderResultProto;
import org.sas04225.proto.ImageGroupProto;

/**
 *
 * @author sriram
 */
public class BackendFeeder {

    private OutputStream pipe_out;
    private InputStream pipe_in;
    private RepoProvider repo_provider;
    private BuildResultHandler handler;
    private final FileOutputStream cache_metadata;

    public BackendFeeder(OutputStream out, InputStream in, RepoProvider repo_provider) throws FileNotFoundException {
        this.pipe_out = out;
        this.pipe_in = in;
        this.repo_provider = repo_provider;
        cache_metadata = new FileOutputStream(System.getProperty("user.home")+"/"+Main.cache_dir+"/metadata");
        final DataOutputStream out_file = new DataOutputStream(cache_metadata);
        handler = new BuildResultHandler() {
            @Override
            public void onResult(DescriptorSetBuilderResult result) {
                try {
                    out_file.writeUTF(result.group_name);
                    out_file.writeInt(result.descriptor_count);
                    out_file.writeLong(result.startIndex);
                    out_file.writeLong(result.endIndex);
                    out_file.flush();
                } catch (IOException ex) {
                    Logger.getLogger(BackendFeeder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

    public void setBuildResultHandler(BuildResultHandler handler) {
        this.handler = handler;
    }

    public void pushImageGroups() {
        try {
            ArrayList<ImageGroupProto.ImageGroup> groups = new ArrayList<>();
            List<ImageGroup> group = repo_provider.getImageGroups();
            for (int i=0;i<group.size();i++) {
                File[] files = fetchFilesFromURI(group.get(i).getImages());
                ImageGroupProto.ImageGroup.Builder msg = ImageGroupProto.ImageGroup.newBuilder();
                msg.setGroupName(group.get(i).getGroupName());
                ArrayList<String> filenames = new ArrayList<>();
                System.out.println(group.get(i).getGroupName());
                for (File img : files) {
                    filenames.add(img.getAbsolutePath());
                    System.out.println(img.getAbsolutePath());
                }
                msg.addAllImages(filenames);
                groups.add(msg.buildPartial());
                msg.clearGroupName();
                msg.clearImages();
                msg.clear();
            }
                //pipe_out.flush();
                DescriptorSetBuilderResult result = null;
                pipe_out.write(groups.size());
               for (int i=0; i< groups.size(); i++) {
                   ImageGroupProto.ImageGroup msg = groups.get(i);
                try {
    //                byte[] data = msg.toByteArray();
    //                pipe_out.write(new byte[]{(byte)(count>>24),(byte)(count>>16),(byte)(count>>8),(byte)count});
    //                pipe_out.write(data);
    //                msg.writeTo(pipe_out);
                    //msg.writeDelimitedTo(pipe_out);
                    byte[] data = msg.toByteArray();
                    int count =data.length;
                    Logger.getLogger(BackendFeeder.class.getName()).log(Level.INFO, "Pushing image group: {0} count: {1}", new Object[]{msg.getGroupName(), count});
                    pipe_out.write(count);
                    pipe_out.write(data);
                    pipe_out.flush();
//                    Thread.sleep(2000);
                    result = waitForResponse();
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(BackendFeeder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(BackendFeeder.class.getName()).log(Level.SEVERE, "Error when writing to pipe: Group Name: " + msg.getGroupName(), ex);
                } finally {
                    if (result != null) {
                        handler.onResult(result);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(BackendFeeder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private File fileURIHandler(URI uri) {
        return new File(uri);
    }

    private File[] fetchFilesFromURI(List<URI> images) {
        ArrayList<File> files = new ArrayList<>();
        for (URI uri : images) {
            String scheme = uri.getScheme();
            switch (scheme) {
                case "file":
                    File img = fileURIHandler(uri);
                    files.add(img);
                    break;
                default:
                    break;
            }
        }
        return files.toArray(new File[0]);
    }

    private DescriptorSetBuilderResult waitForResponse() throws IOException {
        int len = pipe_in.read();
        Logger.getLogger(BackendFeeder.class.getName()).log(Level.INFO, "Response len: "+len);
        byte[] data = new byte[len];
        pipe_in.read(data);
        DescriptorSetBuilderResultProto.DescriptorSetBuilderResult msg = DescriptorSetBuilderResultProto.DescriptorSetBuilderResult.parseFrom(data);
        DescriptorSetBuilderResult result = new DescriptorSetBuilderResult(msg.getGroupName(), msg.getDescriptorCount(), msg.getStartIndex(), msg.getEndIndex());
        return result;
    }

    public static interface BuildResultHandler {

        public void onResult(DescriptorSetBuilderResult result);
    }
}

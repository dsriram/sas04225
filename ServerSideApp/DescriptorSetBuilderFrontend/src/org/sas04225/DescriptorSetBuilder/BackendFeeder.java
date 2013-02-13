/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.DescriptorSetBuilder;

import java.io.File;
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

    public BackendFeeder(OutputStream out, InputStream in, RepoProvider repo_provider) {
        this.pipe_out = out;
        this.pipe_in = in;
        this.repo_provider = repo_provider;
        handler = new BuildResultHandler() {
            @Override
            public void onResult(DescriptorSetBuilderResult result) {
            }
        };
    }

    public void setBuildResultHandler(BuildResultHandler handler) {
        this.handler = handler;
    }

    public void pushImageGroups() {
        for (ImageGroup group : repo_provider.getImageGroups()) {
            Logger.getLogger(BackendFeeder.class.getName()).log(Level.INFO, "Pushing image group: "+group.getGroupName());
            File[] files = fetchFilesFromURI(group.getImages());
            ImageGroupProto.ImageGroup.Builder msg = ImageGroupProto.ImageGroup.newBuilder();
            msg.setGroupName(group.getGroupName());
            for (File img : files) {
                msg.addImages(img.getAbsolutePath());
            }
            DescriptorSetBuilderResult result = null;
            try {
                msg.build().writeTo(pipe_out);
                pipe_out.flush();
                //result = waitForResponse();
            } catch (IOException ex) {
                Logger.getLogger(BackendFeeder.class.getName()).log(Level.SEVERE, "Error when writing to pipe: Group Name: " + group.getGroupName(), ex);
            }
            if (result != null) {
                handler.onResult(result);
            }
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
        DescriptorSetBuilderResultProto.DescriptorSetBuilderResult msg = DescriptorSetBuilderResultProto.DescriptorSetBuilderResult.parseFrom(pipe_in);
        DescriptorSetBuilderResult result = new DescriptorSetBuilderResult(msg.getGroupName(), msg.getDescriptorCount(), msg.getStartIndex(), msg.getEndIndex());
        return result;
    }

    public static interface BuildResultHandler {

        public void onResult(DescriptorSetBuilderResult result);
    }
}

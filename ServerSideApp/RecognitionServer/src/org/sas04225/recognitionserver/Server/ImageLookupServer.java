/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.recognitionserver.Server;

import com.google.protobuf.ByteString;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sas04225.proto.LookupQueryProto;
import org.sas04225.proto.LookupResultProto;
import org.sas04225.proto.LookupResultProto.DMatch;
import org.sas04225.recognitionserver.GroupNameLookup;
import org.sas04225.recognitionserver.IndexRangeLookup;

/**
 *
 * @author sriram
 */
public class ImageLookupServer {
    
    private IndexRangeLookup indxLukUp;
    private GroupNameLookup grpLukUp;
    private Backend bcknd;
    
    public ImageLookupServer(Backend backend, IndexRangeLookup iLU, GroupNameLookup gLU) {
        indxLukUp = iLU;
        grpLukUp = gLU;
        bcknd = backend;
    }
    
    public void testLookup()
    {
        try {
            LookupQueryProto.LookupQuery.Builder lukqb = LookupQueryProto.LookupQuery.newBuilder();
            lukqb.setRequestId(0x123456);
            lukqb.setPath("/home/sriram/Development/Databases/camera_repo/1.jpg");
            LookupQueryProto.Descriptors des = LookupQueryProto.Descriptors.newBuilder().setDescriptorSize(0).addDescriptors(ByteString.EMPTY).build();
            lukqb.setDescriptors(des);
            Backend.writeMessage(bcknd.pipe_out, lukqb.build());
            byte[] res_data = Backend.readMessageData(bcknd.pipe_in);
            LookupResultProto.LookupResult result = LookupResultProto.LookupResult.parseFrom(res_data);
            processResult(result);
        } catch (IOException ex) {
            Logger.getLogger(ImageLookupServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void processResult(LookupResultProto.LookupResult result)
    {
        int i = 0;
        System.out.println("Total matches: "+ result.getMatchesCount());
        for(DMatch match : result.getMatchesList())
        {
            int grpIdx = indxLukUp.lookup(match.getTrainIdx());
            String grpName = grpLukUp.lookup(grpIdx);
            if(grpName.equals("stc-notebook"))
                i++;
            System.out.println(grpName);
        }
        System.out.println("True matches: "+ i);
    }
}

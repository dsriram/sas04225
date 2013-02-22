package org.sas04225.RadioMapStorageReader;

import com.google.protobuf.InvalidProtocolBufferException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.sas04225.proto.WifiScanResultProto.WifiScanResult;

public class WifiRecordReader {

    private File filePath;
    InputStream in;

    public WifiRecordReader(String filename) throws FileNotFoundException {
        filePath = new File(filename);
        in = new FileInputStream(filePath);
    }

    public WifiScanResult nextResult() {
        WifiScanResult result = null;
        try {
            if (in.available() == 0) {
                return null;
            }
            byte[] data = readMessageData(in);
            try {
                result = WifiScanResult.parseFrom(data);
            } catch (InvalidProtocolBufferException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
        return result;
    }

    public static byte readMessageData   (InputStream in)  [] throws IOException {
        int len = 0;

        len = in.read();
        len |= (in.read()) << 8;
        len |= (in.read()) << 16;
        len |= (in.read()) << 24;

        byte[] data = new byte[len];
        in.read(data, 0, len);

        return data;
    }
}
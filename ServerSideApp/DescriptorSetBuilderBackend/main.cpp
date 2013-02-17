/* 
 * File:   main.cpp
 * Author: sriram
 *
 * Created on February 9, 2013, 11:08 AM
 */

#include <cstdlib>
#include <iostream>
#include "ImageGroup.pb.h"
#include "DescriptorSetBuilderResult.pb.h"
#include <unistd.h>
#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/io/zero_copy_stream.h>
#include <google/protobuf/io/zero_copy_stream_impl.h>
#include <google/protobuf/io/tokenizer.h>
#include <fcntl.h>
#include <google/protobuf/stubs/common.h>
#include <fstream>

#define LOGFILE "/home/sriram/Desktop/log.txt"

using namespace std;
using namespace google::protobuf::io;

/*
 * 
 */

int writeToBuf(org::sas04225::proto::DescriptorSetBuilderResult msg, char* buf) {
    int len = msg.ByteSize();
    msg.SerializeToArray((void*) buf, len);
    return len;
}

int main(int argc, char** argv) {
    GOOGLE_PROTOBUF_VERIFY_VERSION;
    
    org::sas04225::proto::ImageGroup imgrp;
    org::sas04225::proto::DescriptorSetBuilderResult result;
    char buf[2048];
    uint8_t len = 0;
    
    fstream fout(LOGFILE, fstream::out);

    int fd = 0;
    int fd2 = 1;

    int count = -1;
    read(fd,&count,1);
    fcntl(fd2, F_SETFL, O_DSYNC | O_WRONLY);
    fcntl(fd, F_SETFL, O_RSYNC | O_RDONLY);
    fout << "Count: " << count << endl;
    fout.flush();
    for (int i = 0; i < count; i++) {
        imgrp.Clear();
        read(fd, &len, 1);
        read(fd, (void*) buf, len);
        fout << "Length: " << (int) len << endl;
        imgrp.ParseFromArray(buf, len);
        fout << "Group name " << imgrp.group_name() << endl;
        fout.flush();
        result.Clear();
        string* name = result.mutable_group_name();
        *name = imgrp.group_name();
        result.set_descriptor_count(10);
        result.set_startindex(0);
        result.set_endindex(9);
        len = writeToBuf(result, buf);
        fout << "Response: " << (int) len << endl << endl;
        write(fd2, &len, 1);
        write(fd2, (void*) buf, len);
    }
    fout << "Backend: completed" << endl;
    return 0;
}

class ProtobufLogCollector : public google::protobuf::io::ErrorCollector {
public:

    ProtobufLogCollector() {
    }

    ~ProtobufLogCollector() {
    }

    void AddError(int line, int column, const string& message) {
        std::cout << message << std::endl;
    }

    void AddWarning(int line, int column, const string& message) {
        std::cout << message << std::endl;
    }
};


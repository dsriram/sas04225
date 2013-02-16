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

using namespace std;
using namespace google::protobuf::io;

/*
 * 
 */

int writeToBuf(org::sas04225::proto::DescriptorSetBuilderResult msg,char* buf)
{
    int len = msg.ByteSize();
    msg.SerializeToArray((void*)buf,len);
    return len;
}

int main(int argc, char** argv) {
    GOOGLE_PROTOBUF_VERIFY_VERSION;
    string s;
    char c = 'a';
    org::sas04225::proto::ImageGroup imgrp;
    org::sas04225::proto::DescriptorSetBuilderResult result;
    char buf[2048];
    uint8_t len = 0;
    int fd = open("/tmp/common_pipe_out",O_RDONLY|O_DSYNC);
    int fd2 = open("/tmp/common_pipe_in", O_WRONLY|O_DSYNC);
//    coded_input->ReadVarint32(&len);
//    ZeroCopyOutputStream* raw_output = new FileOutputStream(fd2);
//    CodedOutputStream* coded_output = new CodedOutputStream(raw_output);
    int count = -1;
    count = 3;
    cout << "Count: " << count <<endl;
    cout.flush();
    for (int i = 0; i < count; i++) {
        imgrp.Clear();
        read(fd,&len,1);
        read(fd,(void*)buf,len);
        cout<<"Length: "<<(int)len<<endl;
        //imgrp.ParseFromFileDescriptor(fd);
        imgrp.ParseFromArray(buf,len);
        //close(fd);
        cout<<"Group name "<<imgrp.group_name()<<endl;
        imgrp.PrintDebugString();
        cout.flush();
        result.Clear();
        string* name = result.mutable_group_name();
        *name = imgrp.group_name();
        result.set_descriptor_count(10);
        result.set_startindex(0);
        result.set_endindex(9);
        len = writeToBuf(result,buf);
        cout<<"Response: "<<(int)len<<endl<<endl;
        write(fd2,&len,1);
        write(fd2,(void*)buf,len);
        //close(fd2);
//        result.SerializeToOstream(&output);
//        result.SerializeToCodedStream(coded_output);
    }
    cout << "Backend: completed" << endl;
    //    int n;
    //    cin>>n;
    return 0;
}

class ProtobufLogCollector : public google::protobuf::io::ErrorCollector {
    public:
        ProtobufLogCollector() {}
        ~ProtobufLogCollector() {}

        void AddError(int line, int column, const string& message) { std::cout << message << std::endl; }
        void AddWarning(int line, int column, const string& message) { std::cout << message << std::endl; }
};

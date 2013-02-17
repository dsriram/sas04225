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

#include "Trainer.hpp"

#include <glog/logging.h>

#define LOGFILE "/home/sriram/Development/DescriptorSetCache/log.txt"

#define CACHE_FILE "/home/sriram/Development/DescriptorSetCache/cache"
#define CACHE_METADATA "/home/sriram/Development/DescriptorSetCache/metadata"

using namespace std;
using namespace google::protobuf::io;

/*
 * 
 */

org::sas04225::Trainer trainer;

int writeToBuf(org::sas04225::proto::DescriptorSetBuilderResult msg, char* buf) {
    int len = msg.ByteSize();
    msg.SerializeToArray((void*) buf, len);
    return len;
}

org::sas04225::proto::DescriptorSetBuilderResult addGroup(org::sas04225::proto::ImageGroup imgrp) {
    org::sas04225::proto::DescriptorSetBuilderResult result;
    result.Clear();
    string* name = result.mutable_group_name();
    *name = imgrp.group_name();
    uint32_t start_index = trainer.descriptor_count;
    uint32_t descriptor_count = 0;
//    for(int i= 0; i<imgrp.images_size(); i++) {
//        descriptor_count += trainer.addImage(imgrp.images(i));
//    }
    vector<string> images;
    for(int i= 0; i<imgrp.images_size(); i++) {
        images.push_back(imgrp.images(i));
    }
    descriptor_count = trainer.addImages(images);
    uint32_t end_index = trainer.descriptor_count -1;
    result.set_descriptor_count(descriptor_count);
    result.set_startindex(start_index);
    result.set_endindex(end_index);
    return result;
}

int main(int argc, char** argv) {
    GOOGLE_PROTOBUF_VERIFY_VERSION;

    char buf[2048];
    uint8_t len = 0;

    fstream fout(LOGFILE, fstream::out);

    int fd = 0;
    int fd2 = 1;

    int count = 0;
    read(fd, &count, 1);
    fcntl(fd2, F_SETFL, O_DSYNC | O_WRONLY);
    fcntl(fd, F_SETFL, O_RSYNC | O_RDONLY);
    fout << "Count: " << count << endl;
    fout.flush();
    for (int i = 0; i < count; i++) {
        org::sas04225::proto::ImageGroup imgrp;
        imgrp.Clear();
        read(fd, &len, 1);
        read(fd, (void*) buf, len);
        fout << "Length: " << (int) len << endl;
        imgrp.ParseFromArray(buf, len);
        fout << "Group name " << imgrp.group_name() << endl;
        fout.flush();
        org::sas04225::proto::DescriptorSetBuilderResult result = addGroup(imgrp);
        len = writeToBuf(result, buf);
        fout << "Response: " << (int) len << endl << "Count: "<< result.descriptor_count() <<endl<<"End index: "<<result.endindex()<<endl<<endl;
        write(fd2, &len, 1);
        write(fd2, (void*) buf, len);
    }
    trainer.train();
    trainer.save(CACHE_FILE);
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


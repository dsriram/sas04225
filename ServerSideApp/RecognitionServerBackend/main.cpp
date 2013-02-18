/* 
 * File:   main.cpp
 * Author: sriram
 *
 * Created on February 18, 2013, 11:53 AM
 */

#include <cstdlib>
#include <iostream>
#include <string>
#include <vector>

#include <ext/stdio_filebuf.h>

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/nonfree/features2d.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/legacy/legacy.hpp>
#include <opencv/cv.h>
#include <opencv2/flann/flann.hpp>

//#include "MatchResultAnalytics.hpp"
#include "RecognitionServerBackendInit.pb.h"
#include "msgIO.hpp"


using namespace std;
using namespace cv;
using namespace org::sas04225::proto;
using namespace std;

#define LOGFILE "/home/sriram/Development/log_RecognitionServerBackend.txt"
#define ENDL " __@endl__ "
/*
 * 
 */




Mat indexMat;
cv::flann::Index* flann_index;


int main(int argc, char** argv) {
    GOOGLE_PROTOBUF_VERIFY_VERSION;

    char buf[2048];
    uint8_t len = 0;
    
    int fd = 0;
    int fd2 = 1;
    int fd3 = 2;
    fcntl(fd2, F_SETFL, O_DSYNC | O_WRONLY);
    fcntl(fd3, F_SETFL, O_DSYNC | O_WRONLY);
    fcntl(fd, F_SETFL, O_RSYNC | O_RDONLY);
    
    __gnu_cxx::stdio_filebuf<char> filebuf(fd3, std::ios::out);
    ostream log(&filebuf);
    
//    fstream log(LOGFILE,fstream::out);
    
    RecognitionServerBackendInit msg;
    msg.Clear();
    readFromFD<RecognitionServerBackendInit>(msg,0);
    log<<"Count: "<<msg.descriptor_count()<<" Desc cache file: "<<msg.cache_path()<<endl;
    log.flush();
    
    double t = 0;
    log<<"Loading descriptors from cache.."<<endl;
    t = (double) getTickCount();
    indexMat = Mat::zeros(msg.descriptor_count(), 64, CV_8UC1);
    flann_index = new cv::flann::Index(indexMat, cv::flann::SavedIndexParams(msg.cache_path()), cvflann::FLANN_DIST_EUCLIDEAN);
    FREAK extractor(true, true, 22.0f, 4);
    cv::BRISK detector_agast(30, 5);
    t = ((double) getTickCount() - t) / getTickFrequency();
    log<<"Descriptors loaded. Took "<<t<<" s"<<endl;
    
    return 0;
    
    
}

